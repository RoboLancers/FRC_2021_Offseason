package frc.robot.autonomous;

import com.revrobotics.ControlType;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.drivetrain.Drivetrain;
import frc.robot.subsystems.misc.Limelight;
import frc.robot.subsystems.shooter.Shooter;

public class RevUsingTarget extends CommandBase {
    private Limelight limelight;
    private Drivetrain drivetrain;
    private Shooter shooter;

    // All distances in measurements in meters
    // All angles in radians

    // ! All of these should be calculated properly at some point

    // Height of the limelight off the ground
    public static double limeLightHeight = 0.3048;
    // Height of the target off the ground
    public static double targetHeight = 2.286;
    // Angle the limeLight is mounted to the robot at
    public static double limeLightMountingAngle = 50 * Math.PI / 180;
    // Angle the shooter releases the ball at
    public static double shooterReleaseAngle = 50.0 * Math.PI / 180;
    // Radius of the flywheels used to shoot
    public static double shooterFlywheelRadius = 0.0635;
    // Hom much should the robot move backward each update cycle if the robot is too close to be able to hit the target
    public static double seekAdjustment = -0.05;

    public RevUsingTarget(Limelight limelight, Drivetrain drivetrain, Shooter shooter){
        this.limelight = limelight;
        this.drivetrain = drivetrain;
        this.shooter = shooter;
        addRequirements(shooter);
    }

    // Called on update cycles where the limelight has a target
    void revWithTarget(){
        double targetXAxisAngle = limelight.getYOffset() * Math.PI / 180;
        double deltaY = RevUsingTarget.targetHeight - RevUsingTarget.limeLightHeight;
        double deltaX = deltaY / Math.tan(RevUsingTarget.limeLightMountingAngle + targetXAxisAngle);
        double cos = Math.cos(RevUsingTarget.shooterReleaseAngle);
        double sin = Math.sin(RevUsingTarget.shooterReleaseAngle);
        double timeSquared = (sin * deltaX - cos * deltaY) / (cos * 9.81 / 2);
        if(timeSquared >= 0){
            double time = Math.sqrt(timeSquared);
            double releaseVelocity = deltaX / (cos * time);
            double rpm = 60 * releaseVelocity / RevUsingTarget.shooterFlywheelRadius;
            this.shooter.getPidController().setReference(rpm, ControlType.kVelocity);
            SmartDashboard.putBoolean("real target velocity", true);
            SmartDashboard.putNumber("target release velocity", releaseVelocity);
            SmartDashboard.putNumber("target shooter rpm", rpm);
        } else {
            this.drivetrain.getLeft().getMain().set(RevUsingTarget.seekAdjustment);
            this.drivetrain.getRight().getMain().set(RevUsingTarget.seekAdjustment);
            SmartDashboard.putBoolean("real target velocity", true);
            SmartDashboard.putNumber("target release velocity", 0);
            SmartDashboard.putNumber("target shooter rpm", 0);
        }
    }

    @Override
    public void execute(){
        if(limelight.hasTarget()){
            this.revWithTarget();
        }
    }

    @Override
    public boolean isFinished(){
        // how to read the current rpm
        return true;
    }
}