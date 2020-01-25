package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Subsystem;
import frc.robot.RobotContainer;

import java.util.HashSet;
import java.util.Set;

public class UseClimber implements Command {
    Set<Subsystem> subsystems;

    public UseClimber(){
        subsystems = new HashSet<Subsystem>();
        subsystems.add(RobotContainer.climber);
    }

    @Override
    public void execute() {
        //RobotContainer.climber.getMaster().set();
    }

    @Override
    public Set<Subsystem> getRequirements() {
        return subsystems;
    }
}
