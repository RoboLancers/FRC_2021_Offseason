package frc.robot.subsystems.drivetrain;

import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj.SlewRateLimiter;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotMap;
import frc.robot.subsystems.drivetrain.enums.GearBoxSides;

public class Drivetrain extends SubsystemBase {
    // Any reason to privatize left and right?
    private SpeedControllerGroup left, right;
    public DifferentialDrive m_drive;
    private SlewRateLimiter filter;
    public Drivetrain() {
     //   left = new GearBox(GearBoxSides.LEFT, RobotMap.Drivetrain.Left.FRONT, RobotMap.Drivetrain.Left.BACK_ONE, RobotMap.Drivetrain.Left.BACK_TWO);
     //   right = new GearBox(GearBoxSides.RIGHT, RobotMap.Drivetrain.Right.FRONT, RobotMap.Drivetrain.Right.BACK_ONE, RobotMap.Drivetrain.Right.BACK_TWO);
        left = new SpeedControllerGroup(new Spark(RobotMap.Drivetrain.Left.FRONT), new Spark(RobotMap.Drivetrain.Left.BACK_ONE), new Spark(RobotMap.Drivetrain.Left.BACK_TWO));
        right = new SpeedControllerGroup(new Spark(RobotMap.Drivetrain.Right.FRONT), new Spark(RobotMap.Drivetrain.Right.BACK_ONE), new Spark(RobotMap.Drivetrain.Right.BACK_TWO));
        right.setInverted(true);
        m_drive = new DifferentialDrive(left, right);
        filter = new SlewRateLimiter(0.1);

    
    }
    public void Drive(double throttle, double turn){
        
        m_drive.arcadeDrive(filter.calculate(throttle), filter.calculate(turn));


    }
}
 
    /* public GearBox getLeft() {
        return left;
    }

    public GearBox getRight() {
        return right;
    }

    public void setVoltage(double leftVoltage, double rightVoltage) {
        left.setVoltage(leftVoltage);
        right.setVoltage(rightVoltage);
    }

    // The following methods are never used?

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
*/