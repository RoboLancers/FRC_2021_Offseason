package frc.robot.autonomous;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.misc.Limelight;
import frc.robot.subsystems.shooter.Shooter;
import frc.robot.subsystems.shooter.commands.RevUpShooter;

public class RevUsingTarget extends CommandBase {
    private Shooter shooter;
    private Limelight limelight;

    // Height of the limelight off the ground
    public static double limeLightHeight = 12.0; // ! Measure this
    // Height of the target off the ground
    public static double targetHeight = 90; // ! Measure this
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
        double targetY = Math.PI / 180 * limelight.getYOffset();
        double verticalDisplacement = (RevUsingTarget.targetHeight - RevUsingTarget.limeLightHeight);
        double targetDistance = verticalDisplacement / Math.tan(Math.PI / 180 * RevUsingTarget.limeLightMountingAngle + targetY);
        double sin = Math.sin(Math.PI / 180 * RevUsingTarget.shooterReleaseAngle);
        double cos = Math.cos(Math.PI / 180 * RevUsingTarget.shooterReleaseAngle);
        if((targetDistance * Math.tan(Math.PI / 180 * RevUsingTarget.shooterReleaseAngle) - verticalDisplacement) > 0){
            double time = Math.sqrt((targetDistance * Math.tan(Math.PI / 180 * RevUsingTarget.shooterReleaseAngle) - verticalDisplacement) / 4.9);
            double targetReleaseVelocity = 0.0254 * (targetDistance) / (time * cos);
            if(targetReleaseVelocity > 0){
                double rpm = targetReleaseVelocity * 60 / (2 * Math.PI * 0.05);
                SmartDashboard.putNumber("target vel", targetReleaseVelocity);
                SmartDashboard.putNumber("target rpm", rpm);
            }
        }
    }

    @Override
    public void execute(){
        if(limelight.hasTarget()){
            this.revWithTarget();
        }
    }
}