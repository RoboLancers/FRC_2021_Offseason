package frc.robot.subsystems;

import com.revrobotics.ColorMatch;
import com.revrobotics.ColorMatchResult;
import com.revrobotics.ColorSensorV3;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.Subsystem;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;

import static com.revrobotics.ColorSensorV3.*;

public class ColorSensor extends SubsystemBase {

    public ColorSensorV3 colorSensor;
    public ColorMatch colorMatcher;

    public ColorSensor() {
        colorSensor = new ColorSensorV3(I2C.Port.kOnboard);
        colorMatcher = new ColorMatch();
        colorMatcher.setConfidenceThreshold(0.75);

        colorMatcher.addColorMatch(Constants.ColorSensor.kBlueTarget);
        colorMatcher.addColorMatch(Constants.ColorSensor.kGreenTarget);
        colorMatcher.addColorMatch(Constants.ColorSensor.kRedTarget);
        colorMatcher.addColorMatch(Constants.ColorSensor.kYellowTarget);

    }

    public double getRed(){
        return colorSensor.getColor().red;
    }
    public double getGreen(){
        return colorSensor.getColor().green;
    }
    public double getBlue(){
        return colorSensor.getColor().blue;
    }

    public double getIR(){
        return colorSensor.getIR();
    }

    public void updateDash(){
        SmartDashboard.putNumber("Red", getRed());
        SmartDashboard.putNumber("Green", getGreen());
        SmartDashboard.putNumber("Blue", getBlue());
        SmartDashboard.putNumber("IR", getIR());
        SmartDashboard.putString("Matched Color", getColorString());
    }

    public String getColorString(){

        ColorMatchResult match = colorMatcher.matchColor(colorSensor.getColor());

        if (match.color == Constants.ColorSensor.kBlueTarget) {
            return "Blue " + match.confidence;
        } else if (match.color == Constants.ColorSensor.kRedTarget) {
            return "Red " + match.confidence;
        } else if (match.color == Constants.ColorSensor.kGreenTarget) {
            return "Green " + match.confidence;
        } else if (match.color == Constants.ColorSensor.kYellowTarget) {
            return "Yellow " + match.confidence;
        } else {
            return "Unknown";
        }
    }
}
