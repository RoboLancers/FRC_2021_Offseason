package frc.robot.subsystems.shooter;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.RobotMap;

public class Shooter extends SubsystemBase {
    private TalonSRX master, slave;
    private PIDController pidController;

    public Shooter() {
        master = new TalonSRX(RobotMap.Manipulator.Shooter.SHOOTER_MASTER_PORT);
        slave = new TalonSRX(RobotMap.Manipulator.Shooter.SHOOTER_SLAVE_PORT);

        master.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder);

        slave.follow(master);
        slave.setInverted(true);

        pidController = new PIDController(Constants.Shooter.kP, Constants.Shooter.kI, Constants.Shooter.kD);
        pidController.setTolerance(Constants.Shooter.SHOOTER_RPM_TOLERANCE);
    }

    public TalonSRX getMaster(){
        return master;
    }

    public PIDController getPidController() {
        return pidController;
    }

    public void resetEncoder(){
        master.setSelectedSensorPosition(0);
    }
}