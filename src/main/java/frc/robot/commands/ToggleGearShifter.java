package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.enums.GearShifterState;
import frc.robot.subsystems.GearShifter;
import static frc.robot.RobotContainer.gearShifter;
public class ToggleGearShifter extends InstantCommand {

    public ToggleGearShifter() {
        addRequirements(gearShifter);
    }

    @Override
    public void initialize() {
        gearShifter.setGearShifter(gearShifter.getState() == GearShifterState.HIGHGEAR ? GearShifterState.LOWGEAR : GearShifterState.HIGHGEAR);
    }
}
