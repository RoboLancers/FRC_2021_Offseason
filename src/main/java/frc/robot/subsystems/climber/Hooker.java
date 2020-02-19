package frc.robot.subsystems.climber;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.RobotMap;

public class Hooker extends SubsystemBase {
    private TalonSRX hookUp;

    public Hooker(){
        hookUp = new TalonSRX(RobotMap.Manipulator.Climber.TALONSRX_PORT);
        hookUp.configOpenloopRamp(Constants.Climber.HOOKER_RAMP_RATE);
    }

    public void set(double power) {
        hookUp.set(ControlMode.PercentOutput, power);
    }
}
