package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import edu.wpi.first.wpilibj2.command.Subsystem;
import frc.robot.Constants;
import frc.robot.commands.UseDrivetrain;

public class Drivetrain implements Subsystem {
    private Transmission left, right;
    private static Drivetrain drivetrain;

    public Drivetrain() {
        left = new Transmission(TransmissionSide.LEFT, Constants.Drivetrain.Left.MASTER, Constants.Drivetrain.Left.SLAVE_ONE, Constants.Drivetrain.Left.SLAVE_TWO);
        right = new Transmission(TransmissionSide.RIGHT, Constants.Drivetrain.Left.MASTER, Constants.Drivetrain.Right.SLAVE_ONE, Constants.Drivetrain.Right.SLAVE_TWO);
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
