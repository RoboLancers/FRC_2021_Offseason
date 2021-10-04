package frc.robot.subsystems.drivetrain;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;
import frc.robot.Constants;
import frc.robot.subsystems.drivetrain.enums.GearBoxSides;
import frc.robot.utilities.Utilities;

public class GearBox {
    private CANSparkMax main, secondary1, secondary2;

    public GearBox(GearBoxSides side, int masterPort, int slave1Port, int follow2Port) {
        main = new CANSparkMax(masterPort, CANSparkMaxLowLevel.MotorType.kBrushless);
        secondary1 = new CANSparkMax(slave1Port, CANSparkMaxLowLevel.MotorType.kBrushless);
        secondary2 = new CANSparkMax(follow2Port, CANSparkMaxLowLevel.MotorType.kBrushless);

        main.restoreFactoryDefaults();
        secondary1.restoreFactoryDefaults();
        secondary2.restoreFactoryDefaults();

        main.getEncoder();
        secondary1.getEncoder();
        secondary2.getEncoder();

        secondary1.follow(main);
        secondary2.follow(main);

        main.setOpenLoopRampRate(Constants.Robot.RAMP_RATE);
        secondary1.setOpenLoopRampRate(Constants.Robot.RAMP_RATE);

        if(side == GearBoxSides.RIGHT){
           main.setInverted(true);
        }
    }

    public CANSparkMax getMain(){
        return main;
    }

    public void resetEncoder() {
        main.getEncoder().setPosition(0);
    }

    public double getEncoderRevolutions(){
        return main.getEncoder().getPosition();
    }

    public double getDistance() {
        return (getEncoderRevolutions() / Constants.Robot.GEAR_RATIO) * Constants.Robot.WHEEL_CIRCUMFERENCE;
    }

    public double getVelocity() {
        return Utilities.RPMtoRPS(main.getEncoder().getVelocity() * Constants.Robot.WHEEL_CIRCUMFERENCE);
    }

    public void setVoltage(double voltage) {
        main.setVoltage(voltage);
    }
}
