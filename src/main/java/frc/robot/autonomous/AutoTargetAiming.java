package frc.robot.autonomous;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.drivetrain.Drivetrain;
import frc.robot.subsystems.misc.Limelight;

public class AutoTargetAiming extends CommandBase {
    Drivetrain drivetrain;
    Limelight limelight;
    double leftPower, rightPower, turningOffset, distanceOffset;
    double turningkP = 0.006;
    double distancekP = 0.02;
    private static final double TURNING_TARGET = 1;
    private static final double DISTANCE_TARGET = 20;
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
            distanceOffset = DISTANCE_TARGET - limelight.getYOffset();
            leftPower = (distanceOffset * distancekP) + (turningOffset * turningkP);
            rightPower = (distanceOffset * distancekP) - (turningOffset * turningkP);
            drivetrain.getLeft().getMaster().set(leftPower);
            drivetrain.getRight().getMaster().set(rightPower);
        }
    }

    @Override
    public boolean isFinished() {
        if(limelight.hasTarget()) {
            return Math.abs(turningOffset) < TURNING_TARGET && Math.abs(distanceOffset) < ALLOWED_DISTANCE_ERROR;
        };
        return false;
    }
}
