package frc.robot.subsystems;

import com.revrobotics.ColorMatch;
import com.revrobotics.ColorMatchResult;
import com.revrobotics.ColorSensorV3;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.Subsystem;
import frc.robot.Constants;

import static com.revrobotics.ColorSensorV3.*;

public class ColorSensor implements Subsystem {

    public ColorSensorV3 colorSensor;
    public ColorMatch colorMatcher;

    public ColorSensor() {
        colorSensor = new ColorSensorV3(I2C.Port.kOnboard);
        colorMatcher = new ColorMatch();

        colorMatcher.addColorMatch(Constants.ColorSensor.kBlueTarget);
        colorMatcher.addColorMatch(Constants.ColorSensor.kGreenTarget);
        colorMatcher.addColorMatch(Constants.ColorSensor.kRedTarget);
        colorMatcher.addColorMatch(Constants.ColorSensor.kYellowTarget);

    }
    public Color getColor() {
        return colorSensor.getColor();
    }

    public double getIR(){
        return colorSensor.getIR();
    }

    public RawColor getRawColor(){
        return colorSensor.getRawColor();
    }

    public String getColorString(){

        Color detectedColor = colorSensor.getColor();
        ColorMatchResult match = colorMatcher.matchClosestColor(detectedColor);
        String colorString;

        if (match.color == Constants.ColorSensor.kBlueTarget) {
            colorString = "Blue";
        } else if (match.color == Constants.ColorSensor.kRedTarget) {
            colorString = "Red";
        } else if (match.color == Constants.ColorSensor.kGreenTarget) {
            colorString = "Green";
        } else if (match.color == Constants.ColorSensor.kYellowTarget) {
            colorString = "Yellow";
        } else {
            colorString = "Unknown";
        }

        return colorString;
    }
}
