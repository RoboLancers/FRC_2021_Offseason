package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;

public class GearBox {
    private CANSparkMax master, follow1, follow2;


    GearBox(GearBoxSides side, int masterPort, int follow1Port, int follow2Port) {
        master = new CANSparkMax(masterPort, CANSparkMaxLowLevel.MotorType.kBrushless);
        follow1 = new CANSparkMax(follow1Port, CANSparkMaxLowLevel.MotorType.kBrushless);
//        follow2 = new CANSparkMax(follow2Port, CANSparkMaxLowLevel.MotorType.kBrushless);

        follow1.follow(master);
//        follow2.follow(master);

        if(side == GearBoxSides.LEFT){
            master.setInverted(true);
        }else{

        }
    }

    public CANSparkMax getMaster(){
        return master;
    }
}
