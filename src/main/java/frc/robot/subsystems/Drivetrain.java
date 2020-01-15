package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import edu.wpi.first.wpilibj2.command.Subsystem;
import frc.robot.Constants;
import frc.robot.commands.UseDrivetrain;

public class Drivetrain implements Subsystem {
    private GearBox left, right;
    private static Drivetrain drivetrain;

    public Drivetrain() {
        left = new GearBox(GearBoxSides.LEFT, Constants.Drivetrain.Left.MASTER, Constants.Drivetrain.Left.SLAVE_ONE, Constants.Drivetrain.Left.SLAVE_TWO);
        right = new GearBox(GearBoxSides.RIGHT, Constants.Drivetrain.Right.MASTER, Constants.Drivetrain.Right.SLAVE_ONE, Constants.Drivetrain.Right.SLAVE_TWO);

    }

    public GearBox getLeft() {
        return left;
    }

    public GearBox getRight() {
        return right;
    }

    public CANSparkMax getLeftMasterMotor() {
        return left.getMaster();
    }

    public  CANSparkMax getRightMasterMotor() {
        return right.getMaster();
    }
}
