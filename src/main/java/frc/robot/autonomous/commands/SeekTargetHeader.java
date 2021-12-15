package frc.robot.autonomous.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.drivetrain.Drivetrain;
import frc.robot.subsystems.misc.Limelight;

public class SeekTargetHeader extends CommandBase {
    private Limelight limelight;
    private Drivetrain drivetrain;

    private static double seekAdjustment = 0.3;

    public SeekTargetHeader(Limelight limelight, Drivetrain drivetrain){
        this.limelight = limelight;
        this.drivetrain = drivetrain;
        addRequirements(limelight, drivetrain);
    }

    @Override
    public void execute(){
        this.drivetrain.getLeftMainMotor().set(seekAdjustment);
        this.drivetrain.getRightMainMotor().set(-seekAdjustment);
    }

    @Override
    public void end(boolean interrupted){
        this.drivetrain.getLeftMainMotor().set(0);
        this.drivetrain.getRightMainMotor().set(0);
    }

    @Override
    public boolean isFinished(){
        return this.limelight.hasTarget();
    }
}
