package frc.robot.subsystems.shooter;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.RobotMap;

public class Shooter extends SubsystemBase {
    private CANSparkMax master, slave;
    private PIDController pidController;

    public Shooter() {
        master = new CANSparkMax(RobotMap.Manipulator.Shooter.SHOOTER_MASTER_PORT, CANSparkMaxLowLevel.MotorType.kBrushless);
        slave = new CANSparkMax(RobotMap.Manipulator.Shooter.SHOOTER_SLAVE_PORT, CANSparkMaxLowLevel.MotorType.kBrushless);

        master.restoreFactoryDefaults();
        slave.restoreFactoryDefaults();

        master.getEncoder();

        slave.follow(master);
        slave.setInverted(true);

        pidController = new PIDController(Constants.Shooter.kP, Constants.Shooter.kI, Constants.Shooter.kD);
        pidController.setTolerance(Constants.Shooter.SHOOTER_RPM_TOLERANCE);
    }

    public CANSparkMax getMaster(){
        return master;
    }

    public PIDController getPidController() {
        return pidController;
    }

    public void resetEncoder(){
        master.getEncoder().setPosition(0);
    }
}