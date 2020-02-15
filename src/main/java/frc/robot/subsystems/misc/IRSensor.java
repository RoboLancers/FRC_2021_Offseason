package frc.robot.subsystems.misc;

import edu.wpi.first.wpilibj.DigitalInput;
import frc.robot.RobotMap;

public class IRSensor {

    private DigitalInput IR2, IR4, IR3, IR1;

    public IRSensor(){

        IR2 = new DigitalInput(RobotMap.Sensors.IR.SECOND_IR_PORT);
        IR4 = new DigitalInput(RobotMap.Sensors.IR.FOURTH_IR_PORT);
        IR3 = new DigitalInput(RobotMap.Sensors.IR.THIRD_IR_PORT);
        IR1 = new DigitalInput(RobotMap.Sensors.IR.FIRST_IR_PORT);
    }

    public boolean getIR2(){
        return IR2.get();
    }

    public boolean getIR4(){
        return IR4.get();
    }

    public boolean getIR3(){
        return IR3.get();
    }

    public boolean getIR1(){
        return IR1.get();
    }

    public boolean isStorageFull(){
        return IR2.get() && IR3.get() && IR4.get() && IR1.get();

    }
    public boolean isThreeBallsIn(){
        return IR2.get() && IR3.get();
    }
}
