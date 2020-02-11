package frc.robot.subsystems.misc;

import edu.wpi.first.wpilibj.DigitalInput;
import frc.robot.RobotMap;

public class IRSensor {

    private edu.wpi.first.wpilibj.DigitalInput rightIR, leftIR, topIR, bottomIR;

    public IRSensor(){

        rightIR = new DigitalInput(RobotMap.Sensors.IR.RIGHT_IR_PORT);
        leftIR = new DigitalInput(RobotMap.Sensors.IR.LEFT_IR_PORT);
        topIR = new DigitalInput(RobotMap.Sensors.IR.TOP_IR_PORT);
        bottomIR = new DigitalInput(RobotMap.Sensors.IR.BOTTOM_IR_PORT);
    }

    public boolean getRightIR(){
        return rightIR.get();
    }

    public boolean getLeftIR(){
        return leftIR.get();
    }

    public boolean getTopIR(){
        return topIR.get();
    }

    public boolean getBottomIR(){
        return bottomIR.get();
    }

    public boolean isStorageFull(){
        if(rightIR.get() && topIR.get() && leftIR.get() && bottomIR.get()){
            return true;
        }
            return false;

    }
}
