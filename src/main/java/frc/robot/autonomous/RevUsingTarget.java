package frc.robot.autonomous;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.misc.Limelight;
import frc.robot.subsystems.shooter.Shooter;

public class RevUsingTarget extends CommandBase {
    private Shooter shooter;
    private Limelight limelight;

    // Height of the limelight off the ground
    public static double limeLightHeight = 12.0; // ! Measure this
    // Height of the target off the ground
    public static double targetHeight = 25.0; // ! Measure this
    // Angle the limeLight is mounted to the robot at
    public static double limeLightMountingAngle = 50.0; // ! Measure this
    // Angle the shooter releases the ball at
    public static double shooterReleaseAngle = 50.0;

    public RevUsingTarget(Shooter shooter, Limelight limelight){
        this.shooter = shooter;
        this.limelight = limelight;
        addRequirements(shooter);
    }

    void revWithTarget(){
        double targetY = limelight.getYOffset();
        double verticalDisplacement = RevUsingTarget.targetHeight - RevUsingTarget.limeLightHeight;
        double targetDistance = verticalDisplacement / Math.tan(RevUsingTarget.limeLightMountingAngle + targetY);
        SmartDashboard.putNumber("Approximate Target Distance", targetDistance);
        double sin = Math.sin(Math.PI / 180 * RevUsingTarget.shooterReleaseAngle);
        double cos = Math.cos(Math.PI / 180 * RevUsingTarget.shooterReleaseAngle);
        if(targetDistance * sin - verticalDisplacement * cos < 0){
            // oops cant hit
        } else {
            double time = Math.sqrt((targetDistance * sin - verticalDisplacement * cos) / 4.9);
            double targetReleaseVelocity = targetDistance / (cos * time);
            SmartDashboard.putNumber("Approximate Release Velocity", targetReleaseVelocity);
        };
    }

    @Override
    public void execute(){
        if(limelight.hasTarget()){
            this.revWithTarget();
        }
    }
}