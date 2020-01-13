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
    private static ColorSensor instance;

    public ColorSensorV3 colorSensor;
    public ColorMatch colorMatcher;

    I2C.Port i2cport = I2C.Port.kOnboard;

    ColorSensor() {
        colorSensor = new ColorSensorV3(i2cport);
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
        return instance.getRawColor();
    }

    public String getColorString(){

        Color detectedColor = ColorSensor.getInstance().getColor();
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


   public static synchronized ColorSensor getInstance() {
       if(instance == null) {
           instance = new ColorSensor();
       }
       return instance;

   }



}
