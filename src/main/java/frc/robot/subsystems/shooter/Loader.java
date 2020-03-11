package frc.robot.subsystems.shooter;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotMap;

public class Loader extends SubsystemBase {
    private TalonSRX loader;
    private Timer timer;

    public Loader(){
        loader = new TalonSRX(RobotMap.Manipulator.Shooter.SHOOTER_LOADER_PORT);
        loader.setInverted(true);
        timer = new Timer();
    }

    public TalonSRX getLoaderMotor() {
        return loader;
    }

    public Timer getTimer() {
        return timer;
    }
}
