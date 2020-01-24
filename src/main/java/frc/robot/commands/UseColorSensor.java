package frc.robot.commands;

import com.revrobotics.ColorMatch;
import com.revrobotics.ColorMatchResult;
import com.revrobotics.ColorSensorV3;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj.util.ColorShim;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Subsystem;
import frc.robot.Constants;
import frc.robot.RobotContainer;
import frc.robot.subsystems.ColorSensor;

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
