package frc.robot.subsystems.climber;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotMap;

public class PullUp extends SubsystemBase {
    //The Neo Motor is used to pull the hook down
    //The talonSRX Motor is used to lift the hook up
    private CANSparkMax pullUp;

    public PullUp(){
        pullUp = new CANSparkMax(RobotMap.Manipulator.Climber.NEO_PORT, CANSparkMaxLowLevel.MotorType.kBrushless);
        pullUp.restoreFactoryDefaults();
        pullUp.setInverted(true);
        pullUp.setOpenLoopRampRate(RobotMap.Manipulator.Climber.CLIMBER_RAMP_RATE);
    }

    public double getPostion() {
        return pullUp.getEncoder().getPosition();
    }

    public void resetEncoders() {
        pullUp.getEncoder().setPosition(0);
    }

    public CANSparkMax getPullUpMotor() {
        return pullUp;
    }

    public void set(int power) {
        pullUp.set(power);
    }
}
