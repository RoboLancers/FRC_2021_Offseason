package frc.robot.autonomous.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.drivetrain.Drivetrain;
import frc.robot.subsystems.misc.Limelight;

public class AimHeading extends CommandBase {
    private Limelight limelight;
    private Drivetrain drivetrain;

    public static double adjustmentCoefficient = 0.15;

    public static double seekAdjustment = 0.3;

    public static double minimumError = 0.2;

    public AimHeading(Limelight limelight, Drivetrain drivetrain){
        this.limelight = limelight;
        this.drivetrain = drivetrain;
        addRequirements(limelight, drivetrain);
    }

    private double weightAdjustment(double adjustment){
        if(adjustment == 0){
            return 0;
        } else {
            return adjustment < 0 ? adjustment / (-adjustment + 1) : adjustment / (adjustment + 1);
        }
    }

    private void adjustWithTarget(){
        double targetX = this.limelight.getXOffset();
        double adjustment = this.weightAdjustment(targetX * AimHeading.adjustmentCoefficient);
        this.drivetrain.getLeft().getMain().set(-adjustment);
        this.drivetrain.getRight().getMain().set(adjustment);
    }

    private void seek(){
        this.drivetrain.getLeft().getMain().set(AimHeading.seekAdjustment);
        this.drivetrain.getRight().getMain().set(-AimHeading.seekAdjustment);
    }

    @Override
    public void execute(){
        if(this.limelight.hasTarget()){
            this.adjustWithTarget();
        } else {
            this.seek();
        }
    }

    @Override
    public boolean isFinished(){
        return this.limelight.hasTarget() && Math.abs(this.limelight.getXOffset()) < AimHeading.minimumError;
    }

    @Override
    public void end(boolean interrupted){
        this.drivetrain.getLeft().getMain().set(0);
        this.drivetrain.getRight().getMain().set(0);
    }
}
