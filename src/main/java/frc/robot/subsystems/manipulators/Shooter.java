package frc.robot.subsystems.manipulators;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;
import edu.wpi.first.wpilibj.CAN;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Shooter extends SubsystemBase {
    private TalonSRX master, slave;

    public Shooter() {
        master = new TalonSRX(Constants.Manipulator.Shooter.SHOOTER_MASTER_PORT);
        slave = new TalonSRX(Constants.Manipulator.Shooter.SHOOTER_SLAVE_PORT);

        slave.follow(master);

    }

    public TalonSRX getMaster() {
        return master;
    }
}
