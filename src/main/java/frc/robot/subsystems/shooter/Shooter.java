package frc.robot.subsystems.shooter;

import com.revrobotics.CANPIDController;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.RobotMap;

public class Shooter extends SubsystemBase {
    private CANSparkMax master, slave;
    private CANPIDController pidController;

    public Shooter() {
        master = new CANSparkMax(RobotMap.Manipulator.Shooter.SHOOTER_MASTER_PORT, CANSparkMaxLowLevel.MotorType.kBrushless);
        slave = new CANSparkMax(RobotMap.Manipulator.Shooter.SHOOTER_SLAVE_PORT, CANSparkMaxLowLevel.MotorType.kBrushless);

        master.restoreFactoryDefaults();
        slave.restoreFactoryDefaults();

        master.getEncoder();

        master.setIdleMode(CANSparkMax.IdleMode.kBrake);
        slave.setIdleMode(CANSparkMax.IdleMode.kBrake);

        slave.follow(master, true);

        pidController = master.getPIDController();
        pidController.setP(Constants.Shooter.kP);
        pidController.setI(Constants.Shooter.kI);
        pidController.setD(Constants.Shooter.kD);
        pidController.setFF(Constants.Shooter.kFF);
    }

    public CANSparkMax getMaster(){
        return master;
    }

    public CANPIDController getPidController() {
        return pidController;
    }

    public void resetEncoder(){
        master.getEncoder().setPosition(0);
    }
}