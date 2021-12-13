package frc.robot.autonomous.commands;

import com.revrobotics.ControlType;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.drivetrain.Drivetrain;
import frc.robot.subsystems.misc.Limelight;
import frc.robot.subsystems.shooter.Shooter;

public class RevShooter extends CommandBase {
    private Limelight limelight;
    private Drivetrain drivetrain;
    private Shooter shooter;
    private boolean reachedTargetRPM = false;

    public static double limelightHeight = 0.3048;

    public static double targetHeight = 2.286;

    public static double limelightMountAngle = 50 * Math.PI / 180;

    public static double shooterReleaseAngle = 50 * Math.PI / 180;

    public static double shooterFlywheelRadius = 0.0635;

    public static double seekAdjustment = 0.75;

    public static double minimumError = 100;

    public RevShooter(Limelight limelight, Drivetrain drivetrain, Shooter shooter){
        this.limelight = limelight;
        this.drivetrain = drivetrain;
        this.shooter = shooter;
        addRequirements(limelight, drivetrain, shooter);
    }

    @Override
    public void execute(){
        if(this.limelight.hasTarget()){
            /*
                given: ∆x, ∆y, θ
                solve for: v

                ∆x = v * cos θ * t
                ∆y = v * sin θ * t - 4.9 * t ^ 2
                t = ∆x / (v * cos θ)
                ∆y = tan θ * ∆x - 4.9 * (∆x / (v * cos θ)) ^ 2
                4.9 * (∆x / (v * cos θ)) ^ 2 = tan θ * ∆x - ∆y
                (∆x / (v * cos θ)) ^ 2 = (tan θ * ∆x - ∆y) / 4.9
                ∆x / (v * cos θ) = sqrt((tan θ * ∆x - ∆y) / 4.9)
                ∆x = (v * cos θ) * sqrt((tan θ * ∆x - ∆y) / 4.9)
                v = ∆x / (cos θ * sqrt((tan θ * ∆x - ∆y) / 4.9))
            */

            double deltaY = RevShooter.targetHeight - RevShooter.limelightHeight;
            double deltaX = deltaY / Math.tan(RevShooter.limelightMountAngle + this.limelight.getYOffset() * Math.PI / 180);

            double targetReleaseVelocity = deltaX / (Math.cos(RevUsingTarget.shooterReleaseAngle) * Math.sqrt((Math.tan(RevUsingTarget.shooterReleaseAngle * deltaX - deltaY)) / 4.9));

            if(Double.isNaN(targetReleaseVelocity)){
                this.shooter.getPidController().setReference(0, ControlType.kVelocity);
                this.drivetrain.getLeftMainMotor().set(RevShooter.seekAdjustment);
                this.drivetrain.getRightMainMotor().set(RevShooter.seekAdjustment);
            } else {
                drivetrain.setVoltage(0, 0);

                double targetRPM = 60 * targetReleaseVelocity / (2 * Math.PI);

                if(Math.abs(this.shooter.getMaster().getEncoder().getVelocity() - targetRPM) < RevShooter.minimumError){
                    this.reachedTargetRPM = true;
                } else {
                    this.reachedTargetRPM = false;
                }
                this.shooter.getPidController().setReference(targetRPM, ControlType.kVelocity);
            }
        }
    }

    @Override
    public boolean isFinished(){
        return this.reachedTargetRPM;
    }

    @Override
    public void end(boolean interrupted){
        this.drivetrain.setVoltage(0, 0);
    }
}
