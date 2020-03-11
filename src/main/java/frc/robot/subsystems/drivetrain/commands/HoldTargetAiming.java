package frc.robot.subsystems.drivetrain.commands;

import com.revrobotics.ControlType;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.Constants;
import frc.robot.subsystems.drivetrain.Drivetrain;
import frc.robot.subsystems.drivetrain.enums.AimingTarget;
import frc.robot.subsystems.misc.Limelight;

public class HoldTargetAiming extends InstantCommand {
    Drivetrain drivetrain;
    Limelight limelight;
    double leftPower, rightPower, turningOffset, distanceOffset;
    double turningkP = 0.025;
    double distancekP = 0.1;
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
            if(limelight.getXOffset() < 10) {
//                turningOffset += 2

            } else {
            }

            distanceOffset = -(allowedDistanceError - limelight.getYOffset());

            leftPower = (distanceOffset * distancekP) + (turningOffset * turningkP);
            rightPower = (distanceOffset * distancekP) - (turningOffset * turningkP);
            drivetrain.getLeft().getMaster().getPIDController().setReference(leftPower, ControlType.kDutyCycle, 0, Constants.Trajectory.kSTATIC);
            drivetrain.getRight().getMaster().getPIDController().setReference(rightPower, ControlType.kDutyCycle, 0, Constants.Trajectory.kSTATIC);
        }
    }
}
