package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.RobotContainer;
import frc.robot.enums.GearShifterState;
import frc.robot.subsystems.GearShifter;
import static frc.robot.RobotContainer.gearShifter;
public class ToggleGearShifter extends InstantCommand {
    private final GearShifter gearShifter;
    public ToggleGearShifter(final GearShifter gearShifter) {
        this.gearShifter = gearShifter;
        addRequirements(gearShifter);

    }


    @Override
    public void initialize() {
        this.gearShifter.setGearShifter(gearShifter.getState() == GearShifterState.HIGHGEAR ? GearShifterState.LOWGEAR : GearShifterState.HIGHGEAR);

    }
}
