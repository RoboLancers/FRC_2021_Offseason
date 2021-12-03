package frc.robot.subsystems.climber;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.RobotMap;
import edu.wpi.first.wpilibj.SlewRateLimiter;

public class Hooker extends SubsystemBase {
    private CANSparkMax hookUp;
    public SlewRateLimiter hookfilter;


    public Hooker(){
        hookUp = new CANSparkMax(RobotMap.Manipulator.Climber.TALONSRX_PORT, CANSparkMaxLowLevel.MotorType.kBrushless);
        hookUp.setIdleMode(CANSparkMax.IdleMode.kBrake);
        hookUp.setInverted(true);
        hookfilter = new SlewRateLimiter(0.8);

    }

    public void set(double power) {
        hookUp.set(hookfilter.calculate(power));
    }
}
