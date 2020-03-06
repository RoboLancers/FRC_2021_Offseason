package frc.robot.subsystems.misc;

import edu.wpi.first.wpilibj.DigitalInput;
import frc.robot.RobotMap;

public class IRSensor {
    private DigitalInput IRTwo, IRFour, IRThree, IROne;;

    public IRSensor(){
        IROne = new DigitalInput(RobotMap.Sensors.IR.FIRST_IR_PORT);
        IRTwo = new DigitalInput(RobotMap.Sensors.IR.SECOND_IR_PORT);
        IRThree = new DigitalInput(RobotMap.Sensors.IR.THIRD_IR_PORT);
        IRFour = new DigitalInput(RobotMap.Sensors.IR.FOURTH_IR_PORT);

    }
        public boolean getIRTwo(){
        return IRTwo.get();
        }

        public boolean getIRFour(){
            return IRFour.get();
        }

        public boolean getIRThree(){
            return IRThree.get();
        }

        public boolean getIROne(){
            return IROne.get();
        }

        public boolean isStorageFull(){
            return IRTwo.get() && IRThree.get() && IRFour.get() && IROne.get();

        }
        public boolean isThreeBallsIn(){
            return IRTwo.get() && IRThree.get();

        }
}
