package frc.robot.subsystems.drivetrain;

import com.revrobotics.CANSparkMax;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotMap;
import frc.robot.subsystems.drivetrain.enums.GearBoxSides;

public class Drivetrain extends SubsystemBase{
    private GearBox left, right;

    public Drivetrain() {
        left = new GearBox(GearBoxSides.LEFT, RobotMap.Drivetrain.Left.MASTER, RobotMap.Drivetrain.Left.SLAVE_ONE, RobotMap.Drivetrain.Left.SLAVE_TWO);
        right = new GearBox(GearBoxSides.RIGHT, RobotMap.Drivetrain.Right.MASTER, RobotMap.Drivetrain.Right.SLAVE_ONE, RobotMap.Drivetrain.Right.SLAVE_TWO);
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

    public CANSparkMax getRightMasterMotor() {
        return right.getMaster();
    }

    public double getLeftEncoder(){
        return left.getEncoderRevolutions();
    }

    public double getRightEncoder(){
        return right.getEncoderRevolutions();
    }

    public void setVoltage(double leftVoltage, double rightVoltage) {
        left.setVoltage(leftVoltage);
        right.setVoltage(rightVoltage);
    }

}
