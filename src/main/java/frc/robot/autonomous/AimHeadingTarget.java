package frc.robot.autonomous;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.drivetrain.Drivetrain;
import frc.robot.subsystems.misc.Limelight;
import java.lang.Math;

public class AimHeadingTarget extends CommandBase {
    private Limelight limelight;
    private Drivetrain drivetrain;

    // Used to weight the horizontal error
    public static double adjustmentCoefficient = 0.02;
    // If the absolute value of the horizontal error is less than this threshold, the heading is accurate enough and the command has finished
    public static double minimumHeadingError = 0.02;
    // If the absolute value of the horizontal error is less than this threshold, add the minimum absolute adjustment value multiplied by the sign of the horizontal error
    public static double requiresAbsoluteAdjustmentThreshold = 0.04;
    public static double minimumAbsoluteAdjustment = 0.01;
    // How much the robot should turn each update cycle if it has not found a target yet
    public static double seekingAdjustment = 0.4;

    public AimHeadingTarget(Limelight limelight, Drivetrain drivetrain) {
        this.limelight = limelight;
        this.drivetrain = drivetrain;
        addRequirements(drivetrain);
    }

    // Called on update cycles where the limelight has a target
    private void adjustWithTarget() {
        double targetX = this.limelight.getXOffset();

        double horizontalAdjustment = -targetX * AimHeadingTarget.adjustmentCoefficient;
        if(Math.abs(horizontalAdjustment) > AimHeadingTarget.requiresAbsoluteAdjustmentThreshold){
            horizontalAdjustment += Math.signum(horizontalAdjustment) * AimHeadingTarget.minimumAbsoluteAdjustment;
        }

        double leftPower = -horizontalAdjustment;
        double rightPower = horizontalAdjustment;

        this.drivetrain.getLeft().getMain().set(leftPower);
        this.drivetrain.getRight().getMain().set(rightPower);
    }

    // Called on update cycles where the limelight does not have a target
    private void adjustWithoutTarget() {
        double leftPower = AimHeadingTarget.seekingAdjustment;
        double rightPower = -AimHeadingTarget.seekingAdjustment;

        this.drivetrain.getLeft().getMain().set(leftPower);
        this.drivetrain.getRight().getMain().set(rightPower);
    }

    @Override
    public void execute() {
        if(this.limelight.hasTarget()){
            this.adjustWithTarget();
        } else {
            this.adjustWithoutTarget();
        }
    }

    @Override
    public void end(boolean interrupted) {
        this.drivetrain.setVoltage(0, 0);
    }

    @Override
    public boolean isFinished(){
        return Math.abs(this.limelight.getXOffset()) < AimHeadingTarget.minimumHeadingError;
    }
}
