package frc.robot.subsystems.drivetrain;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;
import frc.robot.Constants;
import frc.robot.subsystems.drivetrain.enums.GearBoxSides;
import frc.robot.utilities.Utilities;

public class GearBox {
    private CANSparkMax master, slave1, slave2;

    public GearBox(GearBoxSides side, int masterPort, int slave1Port, int follow2Port) {
        master = new CANSparkMax(masterPort, CANSparkMaxLowLevel.MotorType.kBrushless);
        slave1 = new CANSparkMax(slave1Port, CANSparkMaxLowLevel.MotorType.kBrushless);
        slave2 = new CANSparkMax(follow2Port, CANSparkMaxLowLevel.MotorType.kBrushless);

        master.restoreFactoryDefaults();
        slave1.restoreFactoryDefaults();
        slave2.restoreFactoryDefaults();

        slave1.follow(master);
        slave2.follow(master);

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

    public double getEncoderRevolutions(){
        return master.getEncoder().getPosition();
    }

    public double getDistance() {
        return (getEncoderRevolutions() / Constants.Robot.GEAR_RATIO) * Constants.Robot.WHEEL_CIRCUMFERENCE;
    }

    public double getVelocity() {
        return Utilities.RPMtoRPS(master.getEncoder().getVelocity() * Constants.Robot.WHEEL_CIRCUMFERENCE);
    }

    public void setVoltage(double voltage) {
        master.setVoltage(voltage);
    }
}
