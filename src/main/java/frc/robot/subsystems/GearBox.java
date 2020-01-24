package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;
import frc.robot.Constants;

public class GearBox {
    private CANSparkMax master, slave1, follow2;


    GearBox(GearBoxSides side, int masterPort, int slave1Port, int follow2Port) {
        master = new CANSparkMax(masterPort, CANSparkMaxLowLevel.MotorType.kBrushless);
        slave1 = new CANSparkMax(slave1Port, CANSparkMaxLowLevel.MotorType.kBrushless);
//        slave2 = new CANSparkMax(follow2Port, CANSparkMaxLowLevel.MotorType.kBrushless);

        master.restoreFactoryDefaults();
        slave1.restoreFactoryDefaults();
//        slave2.restorFactoryDefaults();

        slave1.follow(master);
//        slave2.follow(master);

        master.setOpenLoopRampRate(Constants.Robot.RAMP_RATE);
        slave1.setOpenLoopRampRate(Constants.Robot.RAMP_RATE);

        if(side == GearBoxSides.LEFT){
           master.setInverted(true);
        }
    }

    public CANSparkMax getMaster(){
        return master;
    }

    public void resetEncoder() {
        master.getEncoder().setPosition(0);
    }

    public double getEncoderCount(){
        return master.getEncoder().getPosition();
    }

    public double getDistance() {
        return getEncoderCount() / Constants.Odometry.ENCODER_COUNT;
    }

}
