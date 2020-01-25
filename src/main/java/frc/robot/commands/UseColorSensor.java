package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Subsystem;
import frc.robot.RobotContainer;

import java.util.HashSet;
import java.util.Set;

public class UseColorSensor implements Command {
    Set<Subsystem> subsystems;
    boolean wrongColor = false;

    public UseColorSensor() {

        subsystems = new HashSet<Subsystem>();
        subsystems.add(RobotContainer.colorSensor);
    }



    @Override
    public void execute() {
        RobotContainer.colorSensor.updateDash();
    }

    @Override
    public Set<Subsystem> getRequirements() {
        return subsystems;
    }
}
