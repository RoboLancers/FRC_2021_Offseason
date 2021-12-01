package frc.robot.autonomous;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.drivetrain.Drivetrain;
import frc.robot.subsystems.misc.Limelight;
import java.lang.Math;

public class AimHeadingTarget extends CommandBase {
    /*
        All regular comments are notes
        All comments with an exclamation point require adjustment / measurement
        All comments with an star are not necessary in the solely heading based adjustments, and are used to adjust the distance
    */
    private Drivetrain drivetrain;
    private Limelight limelight;

    // Used to weight the horizontal error
    public static double horizontalAdjustmentCoefficient = 0.02; // ! Change this
    // If the absolute value of the horizontal error is less than this threshold, add the minimum absolute adjustment value multiplied by the sign of the horizontal error
    public static double requiresAbsoluteAdjustmentThreshold = 0.04; // ! Change this
    public static double minimumAbsoluteAdjustment = 0.02; // ! Change this

    // Used to weight the distance error
    // * public static double distanceAdjustmentCoefficient = 0.02; // ! Change this

    // Height of the limelight off the ground
    // * public static double limeLightHeight = 12.0; // ! Measure this
    // Height of the target off the ground
    // * public static double targetHeight = 25.0; // ! Measure this
    // Angle the limeLight is mounted to the robot at
    // * public static double limeLightMountingAngle = 50.0; // ! Measure this
    // Ideal distance between the robot and target when it shoots
    // * public static double idealTargetDistance = 100.0; // !! Measure this - This one is very important and also chosen entirely arbitrarily

    // How much the robot should turn every update cycle if it has not found a target yet
    public static double seekingAdjustment = 0.2; // ! Change this

    public AimHeadingTarget(Drivetrain drivetrain, Limelight limelight) {
        this.drivetrain = drivetrain;
        this.limelight = limelight;
        addRequirements(drivetrain);
    }

    // Called on update cycles where the robot has a target
    private void adjustWithTarget() {
        double targetX = limelight.getXOffset();
        // * double targetY = limelight.getYOffset();

        double horizontalAdjustment = -targetX * AimHeadingTarget.horizontalAdjustmentCoefficient;
        if(Math.abs(horizontalAdjustment) < AimHeadingTarget.requiresAbsoluteAdjustmentThreshold){
            horizontalAdjustment += Math.signum(horizontalAdjustment) * AimHeadingTarget.minimumAbsoluteAdjustment;
        }

        /*
            tan = opposite / adjacent
            tan(mountAngle + targetY) = verticalDisplacement / targetDistance
            targetDistance = verticalDisplacement / tan(mountAngle + targetY)
        */

        // * double verticalDisplacement = AutoTargetAiming.targetHeight - AutoTargetAiming.limeLightHeight;
        // * double targetDistance = verticalDisplacement / Math.tan(AutoTargetAiming.limeLightMountingAngle + targetY);

        // * double distanceFromIdeal = AutoTargetAiming.idealTargetDistance - targetDistance;
        // * double distanceAdjustment = -distanceFromIdeal * AutoTargetAiming.distanceAdjustmentCoefficient;

        double leftPower = -horizontalAdjustment; // * + distanceAdjustment;
        double rightPower = horizontalAdjustment; // * + distanceAdjustment;

        drivetrain.getLeft().getMain().set(leftPower);
        drivetrain.getRight().getMain().set(rightPower);
    }

    // Called on update cycles where the robot does not have a target
    private void adjustWithoutTarget() {
        double leftPower = AimHeadingTarget.seekingAdjustment;
        double rightPower = -AimHeadingTarget.seekingAdjustment;

        drivetrain.getLeft().getMain().set(leftPower);
        drivetrain.getRight().getMain().set(rightPower);
    }

    @Override
    public void execute() {
        if(limelight.hasTarget()){
            this.adjustWithTarget();
        } else {
            this.adjustWithoutTarget();
        }
    }

    @Override
    public void end(boolean interrupted) {
        drivetrain.setVoltage(0, 0);
    }
}
