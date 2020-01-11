package frc.robot.subsystems;

import com.revrobotics.ColorMatch;
import com.revrobotics.ColorSensorV3;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.Subsystem;

import java.util.ArrayList;

import static com.revrobotics.ColorSensorV3.*;

public class ColorSensor implements Subsystem {
    private static ColorSensor instance;
    ColorSensorV3 colorSensor;
    I2C.Port i2cport = I2C.Port.kOnboard;
    private ArrayList<Color> m_colorsToMatch = new ArrayList<Color>();

    private final Color kBlueTarget = ColorMatch.makeColor(0.143, 0.427, 0.429);
    private final Color kGreenTarget = ColorMatch.makeColor(0.197, 0.561, 0.240);
    private final Color kRedTarget = ColorMatch.makeColor(0.561, 0.232, 0.114);
    private final Color kYellowTarget = ColorMatch.makeColor(0.361, 0.524, 0.113);

    ColorSensor() {
        colorSensor = new ColorSensorV3(i2cport);
        ColorMatch colorMatcher = new ColorMatch();

        colorMatcher.addColorMatch(kBlueTarget);
        colorMatcher.addColorMatch(kGreenTarget);
        colorMatcher.addColorMatch(kRedTarget);
        colorMatcher.addColorMatch(kYellowTarget);

    }

//    public Color getColor() {
//        return colorSensor.getColor();
//    }
//
//    public double getIR(){
//        return colorSensor.getIR();
//    }

//    public ColorMatch matchClosestColor(Color color){
//        double magnitude = color.red + color.blue + color.green;
//
//    }
    public RawColor getRawColor(){
        return instance.getRawColor();
    }

    //Get raw value numbers for colors on color Sensor which is on robot that jenny yanked from my hand


   public static synchronized ColorSensor getInstance() {
       if(instance == null) {
           instance = new ColorSensor();
       }
       return instance;

   }



}
