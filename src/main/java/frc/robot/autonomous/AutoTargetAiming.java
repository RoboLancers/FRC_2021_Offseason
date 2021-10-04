package frc.robot.autonomous;

import com.revrobotics.ControlType;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.drivetrain.Drivetrain;
import frc.robot.subsystems.misc.Limelight;

public class AutoTargetAiming extends CommandBase {
    Drivetrain drivetrain;
    Limelight limelight;
    double leftPower, rightPower, turningOffset, distanceOffset;
    double turningkP = 0.025;
    double distancekP = 0.08;
    private static final double TURNING_TARGET = 1;
    private static final double DISTANCE_TARGET = 0;
    private static final double ALLOWED_DISTANCE_ERROR = 1;

    public AutoTargetAiming(Drivetrain drivetrain, Limelight limelight) {
        this.drivetrain = drivetrain;
        this.limelight = limelight;
        addRequirements(drivetrain);
    }

    @Override
    public void execute() {
        if (limelight.hasTarget()) {
            turningOffset = limelight.getXOffset();
            distanceOffset = -(DISTANCE_TARGET - limelight.getYOffset());
            leftPower = (distanceOffset * distancekP) + (turningOffset * turningkP);
            rightPower = (distanceOffset * distancekP) - (turningOffset * turningkP);
            drivetrain.getLeft().getMain().getPIDController().setReference(leftPower, ControlType.kDutyCycle, 0, Constants.Trajectory.kSTATIC);
            drivetrain.getRight().getMain().getPIDController().setReference(rightPower, ControlType.kDutyCycle, 0, Constants.Trajectory.kSTATIC);
        }
    }

    @Override
    public boolean isFinished() {
        return limelight.hasTarget() && (Math.abs(turningOffset) < TURNING_TARGET) && (Math.abs(distanceOffset) < ALLOWED_DISTANCE_ERROR);
    }

    @Override
    public void end(boolean interrupted) {
        drivetrain.setVoltage(0, 0);
    }
}
