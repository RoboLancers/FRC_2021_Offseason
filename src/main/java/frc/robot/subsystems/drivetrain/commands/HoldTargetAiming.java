package frc.robot.subsystems.drivetrain.commands;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.Constants;
import frc.robot.subsystems.drivetrain.Drivetrain;
import frc.robot.subsystems.drivetrain.enums.AimingTarget;
import frc.robot.subsystems.misc.Limelight;

public class HoldTargetAiming extends InstantCommand {
    Drivetrain drivetrain;
    Limelight limelight;
    double leftPower, rightPower, turningOffset, distanceOffset;
    double turningkP = 0.006;
    double distancekP = 0.02;
    double allowedDistanceError;

    public HoldTargetAiming(Drivetrain drivetrain, Limelight limelight, AimingTarget aimingTarget) {
        this.drivetrain = drivetrain;
        this.limelight = limelight;
        this.allowedDistanceError = aimingTarget.getDistance();
        addRequirements(drivetrain);
    }

    @Override
    public void execute() {
        if (limelight.hasTarget()) {
            turningOffset = limelight.getXOffset();
            distanceOffset = -(allowedDistanceError - limelight.getYOffset());
            leftPower = (distanceOffset * distancekP) + (turningOffset * turningkP) - Constants.Trajectory.kSTATIC;
            rightPower = (distanceOffset * distancekP) - (turningOffset * turningkP) - Constants.Trajectory.kSTATIC;
            drivetrain.getLeft().getMaster().set(leftPower);
            drivetrain.getRight().getMaster().set(rightPower);
        }
    }
}
