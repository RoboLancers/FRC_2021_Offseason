package frc.robot.autonomous.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.drivetrain.Drivetrain;
import frc.robot.subsystems.misc.Limelight;

public class SeekTargetDistance extends CommandBase {
    private Limelight limelight;
    private Drivetrain drivetrain;

    private static double seekAdjustment = 0.4;
    
    public SeekTargetDistance(Limelight limelight, Drivetrain drivetrain){
        this.limelight = limelight;
        this.drivetrain = drivetrain;
        addRequirements(limelight, drivetrain);
    }

    @Override
    public void execute(){
        this.drivetrain.getLeftMainMotor().set(SeekTargetDistance.seekAdjustment);
        this.drivetrain.getRightMainMotor().set(SeekTargetDistance.seekAdjustment);
    }

    @Override
    public void end(boolean interrupted){
        this.drivetrain.getLeftMainMotor().set(0);
        this.drivetrain.getRightMainMotor().set(0);
    }

    @Override
    public boolean isFinished(){
        double deltaY = RevUsingTarget.targetHeight - RevUsingTarget.limeLightHeight;
        double deltaX = deltaY / Math.tan(RevUsingTarget.limeLightMountingAngle + this.limelight.getYOffset() * Math.PI / 180);
        return !Double.isNaN(Math.sqrt((Math.tan(RevUsingTarget.shooterReleaseAngle * deltaX - deltaY)) / 4.9));
    }
}
