package frc.robot.subsystems.drivetrain;

import com.revrobotics.CANSparkMax;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotMap;
import frc.robot.subsystems.drivetrain.enums.GearBoxSides;

public class Drivetrain extends SubsystemBase {
    // Any reason to privatize left and right?
    private GearBox left, right;

    public Drivetrain() {
        left = new GearBox(GearBoxSides.LEFT, RobotMap.Drivetrain.Left.FRONT, RobotMap.Drivetrain.Left.BACK_ONE, RobotMap.Drivetrain.Left.BACK_TWO);
        right = new GearBox(GearBoxSides.RIGHT, RobotMap.Drivetrain.Right.FRONT, RobotMap.Drivetrain.Right.BACK_ONE, RobotMap.Drivetrain.Right.BACK_TWO);
    }

    public GearBox getLeft() {
        return left;
    }

    public GearBox getRight() {
        return right;
    }

    public void setVoltage(double leftVoltage, double rightVoltage) {
        left.setVoltage(leftVoltage);
        right.setVoltage(rightVoltage);
    }

    public CANSparkMax getLeftMainMotor() {
        return left.getMain();
    }

    public CANSparkMax getRightMainMotor() {
        return right.getMain();
    }

    public double getLeftEncoder(){
        return left.getEncoderRevolutions();
    }

    public double getRightEncoder(){
        return right.getEncoderRevolutions();
    }

    public void stop(){
        setVoltage(0, 0);
    }
}
