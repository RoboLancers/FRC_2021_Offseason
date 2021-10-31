package frc.robot.subsystems.climber;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.RobotMap;

public class Puller extends SubsystemBase {
    private CANSparkMax pullUp;

    public Puller(){
        pullUp = new CANSparkMax(RobotMap.Manipulator.Climber.NEO_PORT, CANSparkMaxLowLevel.MotorType.kBrushless);
        pullUp.restoreFactoryDefaults();
        pullUp.setIdleMode(CANSparkMax.IdleMode.kBrake);
        pullUp.setOpenLoopRampRate(Constants.Climber.PULLER_RAMP_RATE);
    }

    public void set(double power) {
        pullUp.set(power);
        // Prevent over pulling:
        //      set up equivalent of listener on current puller y, and if its max, set power to 0
    }

    public double getPower() {
        return pullUp.get();
    }
}
