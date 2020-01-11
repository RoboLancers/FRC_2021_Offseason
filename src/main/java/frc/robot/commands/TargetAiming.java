package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Subsystem;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Limelight;

import java.util.Set;

public class TargetAiming implements Command {
    double leftPower, rightPower, turningOffset, distanceOffset;
    double turningkP = 0.006;
    double distancekP = 0.02;
    double allowedDistanceError = 20;

    public TargetAiming() {
        hasRequirement(Drivetrain.getInstance());
    }

    public void execute() {
        if (Limelight.getInstance().hasTarget()) {
            turningOffset = Limelight.getInstance().getXOffset();
            distanceOffset = allowedDistanceError - Limelight.getInstance().getYOffset();
            leftPower = (distanceOffset * distancekP) + (turningOffset * turningkP);
            rightPower = (distanceOffset * distancekP) - (turningOffset * turningkP);

            Drivetrain.getInstance().getLeftTransmission().getMaster().set(leftPower);
            Drivetrain.getInstance().getRightTransmission().getMaster().set(rightPower);
        }
    }

    @Override
    public Set<Subsystem> getRequirements() {
        return null;
    }
}
