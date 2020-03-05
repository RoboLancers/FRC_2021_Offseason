package frc.robot.subsystems.misc;

import edu.wpi.first.wpilibj.DigitalInput;
import frc.robot.RobotMap;

public class IRSensor {
    private DigitalInput IRSensor;

    public IRSensor(){
        IRSensor = new DigitalInput(RobotMap.Sensors.IR_PORT);

    }

    public boolean get(){
        return IRSensor.get();
    }
}
