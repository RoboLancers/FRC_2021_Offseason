package frc.robot;

import com.revrobotics.CANSparkMax;
import edu.wpi.first.wpilibj2.command.Subsystem;
import frc.robot.commands.UseDrivetrain;
import frc.robot.subsystems.TransmissionSide;

public class Drivetrain implements Subsystem {
    private Transmission left, right;
    private static Drivetrain drivetrain;

    private Drivetrain() {
        left = new Transmission(TransmissionSide.left, Constants.LEFT_MASTER_PORT, Constants.LEFT_SLAVE_ONE, Constants.LEFT_SLAVE_TWO);
        right = new Transmission(TransmissionSide.right, Constants.RIGHT_MASTER_PORT, Constants.RIGHT_SLAVE_ONE, Constants.RIGHT_SLAVE_TWO);
        drivetrain.setDefaultCommand(new UseDrivetrain());
    }
    public static synchronized Drivetrain getInstance() {
        if (drivetrain == null) {
            drivetrain = new Drivetrain();
        }
        return drivetrain;
    }

    public Transmission getLeftTransmission() {
        return left;
    }

    public Transmission getRightTransmission() {
        return right;
    }

    public CANSparkMax getLeftMasterMotor() {
        return left.getMaster();
    }

    public  CANSparkMax getRightMasterMotor() {
        return right.getMaster();
    }
}
