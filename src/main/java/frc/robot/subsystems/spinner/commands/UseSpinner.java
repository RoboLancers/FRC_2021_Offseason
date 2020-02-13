package frc.robot.subsystems.spinner.commands;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Subsystem;
import frc.robot.RobotContainer;
import java.util.HashSet;
import java.util.Set;

/**UNFNISHED CODE*/
public class UseSpinner implements Command {
    Set<Subsystem> subsystems;

    public UseSpinner() {
        subsystems = new HashSet<Subsystem>();
        subsystems.add(RobotContainer.spinner);
    }

    @Override
    public void execute() {

    }

    @Override
    public Set<Subsystem> getRequirements() {
        return subsystems;
    }
}
