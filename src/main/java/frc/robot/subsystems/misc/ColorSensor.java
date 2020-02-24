package frc.robot.subsystems.misc;

import com.revrobotics.ColorMatch;
import com.revrobotics.ColorMatchResult;
import com.revrobotics.ColorSensorV3;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ColorSensor extends SubsystemBase {
    private ColorSensorV3 colorSensor;
    private ColorMatch colorMatcher;

    public ColorSensor() {
        colorSensor = new ColorSensorV3(I2C.Port.kOnboard);
        colorMatcher = new ColorMatch();
        colorMatcher.setConfidenceThreshold(0.75);

        colorMatcher.addColorMatch(Constants.Sensors.kBlueTarget);
        colorMatcher.addColorMatch(Constants.Sensors.kGreenTarget);
        colorMatcher.addColorMatch(Constants.Sensors.kRedTarget);
        colorMatcher.addColorMatch(Constants.Sensors.kYellowTarget);

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

    public Color getColor() {
        return colorSensor.getColor();
    }

    public ColorMatch getColorMatcher(){
        return colorMatcher;
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

        if (match.color == Constants.Sensors.kBlueTarget) {
            return "Blue " + match.confidence;
        } else if (match.color == Constants.Sensors.kRedTarget) {
            return "Red " + match.confidence;
        } else if (match.color == Constants.Sensors.kGreenTarget) {
            return "Green " + match.confidence;
        } else if (match.color == Constants.Sensors.kYellowTarget) {
            return "Yellow " + match.confidence;
        } else {
            return "Unknown";
        }
    }

    @Override
    public void periodic(){
        updateDash();
    }
}
