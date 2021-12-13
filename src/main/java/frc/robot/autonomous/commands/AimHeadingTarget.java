package frc.robot.autonomous.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.drivetrain.Drivetrain;
import frc.robot.subsystems.misc.Limelight;
import java.lang.Math;

public class AimHeadingTarget extends CommandBase {
    private Limelight limelight;
    private Drivetrain drivetrain;
    private boolean hadTargetLastFrame = false;
    private double lastTargetX = 0;

    // Used to weight the horizontal error
    public static double adjustmentCoefficient = 0.02;
    // If the absolute value of the horizontal error is less than this threshold, add the minimum absolute adjustment value multiplied by the sign of the horizontal error
    public static double requiresAbsoluteAdjustmentThreshold = 0.04;
    public static double minimumAbsoluteAdjustment = 0.01;
    // How much the robot should turn each update cycle if it has not found a target yet
    public static double seekAdjustment = 0.3;
    // If the absolute value of the horizontal error is less than this threshold, the heading is accurate enough and the command has finished
    public static double minimumHeadingError = 0.02;
    // If the absolute value of the difference between the current target x and last target x is less than this threshold, the robot has properly aligned and come to a stop
    // Without checking this, the robot can move very fast to match the target, consider it found, and the momentum of the robot will make it keep turing past the target
    public static double minimumDeltaTargetX = 0.05;

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
            horizontalAdjustment -= Math.signum(horizontalAdjustment) * AimHeadingTarget.minimumAbsoluteAdjustment;
        }

        if(Math.abs(horizontalAdjustment) < AimHeadingTarget.requiresAbsoluteAdjustmentThreshold){
            horizontalAdjustment += Math.signum(horizontalAdjustment) * AimHeadingTarget.minimumAbsoluteAdjustment;
        };

        this.drivetrain.getLeft().getMain().set(-horizontalAdjustment);
        this.drivetrain.getRight().getMain().set(horizontalAdjustment);
    }

    // Called on update cycles where the limelight does not have a target
    private void adjustWithoutTarget() {
        // TODO: Save relative location of the target, and use this to sign the adjustment so it wont ever have to spin more than 180 degrees
        this.drivetrain.getLeft().getMain().set(AimHeadingTarget.seekAdjustment);
        this.drivetrain.getRight().getMain().set(-AimHeadingTarget.seekAdjustment);
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
        boolean finished = this.limelight.hasTarget() && Math.abs(this.limelight.getXOffset()) < AimHeadingTarget.minimumHeadingError && this.hadTargetLastFrame && Math.abs(this.lastTargetX - this.limelight.getXOffset()) < AimHeadingTarget.minimumDeltaTargetX;
        this.hadTargetLastFrame = this.limelight.hasTarget();
        this.lastTargetX = this.limelight.getXOffset();
        return finished;
    }
}
