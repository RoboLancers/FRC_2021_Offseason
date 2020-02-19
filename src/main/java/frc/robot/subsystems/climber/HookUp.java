package frc.robot.subsystems.climber;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotMap;

public class HookUp extends SubsystemBase {
    private TalonSRX hookUp;

    public HookUp(){
        hookUp = new TalonSRX(RobotMap.Manipulator.Climber.TALONSRX_PORT);
        hookUp.setInverted(true);
    }

    public TalonSRX getHookUpMotor(){
        return hookUp;
    }

    public void set(int power){
        hookUp.set(ControlMode.PercentOutput, power);
    }

}
