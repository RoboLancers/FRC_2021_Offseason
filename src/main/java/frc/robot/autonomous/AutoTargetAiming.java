package frc.robot.autonomous;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.drivetrain.Drivetrain;
import frc.robot.subsystems.misc.Limelight;
import java.lang.Math;

public class AutoTargetAiming extends CommandBase {
    private Drivetrain drivetrain;
    private Limelight limelight;

    // Used to weight the horizontal error
    public static double horizontalAdjustmentCoefficient = 0.1; // ! Change this
    // If the absolute value of the horizontal error is less than this threshold, add the minimum absolute adjustment value multiplied by the sign of the horizontal error
    public static double requiresAbsoluteAdjustmentThreshold = 0.1; // ! Change this
    public static double minimumAbsoluteAdjustment = 0.1; // ! Change this

    // Used to weight the distance error
    public static double distanceAdjustmentCoefficient = 0.1; // ! Change this

    // Height of the limelight off the ground
    public static double limeLightHeight = 0.0; // ! Measure this
    // Height of the target off the ground
    public static double targetHeight = 106.0; // ! Measure this
    // Angle the limeLight is mounted to the robot at
    public static double limeLightMountingAngle = 35.0; // ! Measure this
    // Ideal distance between the robot and target when it shoots
    public static double idealTargetDistance = 50.0; // !! Measure this - This one is very important and also chosen entirely arbitrarily

    // How much the robot should turn every update cycle if it has not found a target yet
    public static double seekingAdjustment = 0.3; // ! Change this

    public AutoTargetAiming(Drivetrain drivetrain, Limelight limelight) {
        this.drivetrain = drivetrain;
        this.limelight = limelight;
        addRequirements(drivetrain);
    }

    // Called on update cycles where the robot has a target
    private void adjustWithTarget() {
        double targetX = limelight.getXOffset();
        double targetY = limelight.getYOffset();

        double horizontalAdjustment = -targetX * AutoTargetAiming.horizontalAdjustmentCoefficient;
        if(Math.abs(horizontalAdjustment) < AutoTargetAiming.requiresAbsoluteAdjustmentThreshold){
            horizontalAdjustment += Math.signum(horizontalAdjustment) * AutoTargetAiming.minimumAbsoluteAdjustment;
        };

        // tan = opposite / adjacent
        // tan(mountAngle + targetY) = verticalDisplacement / targetDistance
        // targetDistance = verticalDisplacement / tan(mountAngle + targetY)
        double verticalDisplacement = AutoTargetAiming.targetHeight - AutoTargetAiming.limeLightHeight;
        double targetDistance = verticalDisplacement / Math.tan(AutoTargetAiming.limeLightMountingAngle + targetY);

        double distanceFromIdeal = AutoTargetAiming.idealTargetDistance - targetDistance;
        double distanceAdjustment = distanceFromIdeal * AutoTargetAiming.distanceAdjustmentCoefficient;

        double leftPower = distanceAdjustment + horizontalAdjustment;
        double rightPower = distanceAdjustment - horizontalAdjustment;

        drivetrain.getLeft().getMain().set(leftPower);
        drivetrain.getRight().getMain().set(rightPower);
    }

    // Called on update cycles where the robot does not have a target
    private void adjustWithoutTarget() {
        double leftPower = AutoTargetAiming.seekingAdjustment;
        double rightPower = -AutoTargetAiming.seekingAdjustment;

        drivetrain.getLeft().getMain().set(leftPower);
        drivetrain.getRight().getMain().set(rightPower);
    }

    @Override
    public void execute() {
        if(limelight.hasTarget()){
            this.adjustWithTarget();
        } else {
            this.adjustWithoutTarget();
        };
    }

    @Override
    public boolean isFinished() {
        // what to do for this?
        return false;
    }

    @Override
    public void end(boolean interrupted) {
        drivetrain.setVoltage(0, 0);
    }
}
