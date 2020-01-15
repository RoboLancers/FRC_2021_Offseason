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

    public UseColorSensor() {
        subsystems = new HashSet<Subsystem>();
    }


    @Override
    public void execute() {
        ColorMatch matcher = RobotContainer.colorSensor.colorMatcher;

        Color detectedColor = RobotContainer.colorSensor.getColor();
        ColorMatchResult match = matcher.matchClosestColor(detectedColor);

        double IR = RobotContainer.colorSensor.getIR();

    }

    @Override
    public Set<Subsystem> getRequirements() {
        return subsystems;
    }
}
