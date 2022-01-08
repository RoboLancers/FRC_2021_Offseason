package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.utilities.XboxController;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;



public class Robot extends TimedRobot {

    // None of the following code is ever used?

    //private Command m_autonomousCommand;

    //private RobotContainer m_robotContainer;

    // Creates four spark motor objects
    private CANSparkMax leftMotor1;
    private CANSparkMax leftMotor2;
    private CANSparkMax leftMotor3;
    private CANSparkMax rightMotor1;
    private CANSparkMax rightMotor2;
    private CANSparkMax rightMotor3;
    private Joystick driver;
    DifferentialDrive drive;


    private Command m_autonomousCommand;
    private RobotContainer m_robotContainer; 

    //This function is run when the robot is first started up and should be used for any initialization code.


    @Override
    public void robotInit() {
        rightMotor1 = new CANSparkMax(0, MotorType.kBrushless);
        rightMotor2 = new CANSparkMax(1, MotorType.kBrushless);
        rightMotor3 = new CANSparkMax(2, MotorType.kBrushless);
        leftMotor1 = new CANSparkMax(3, MotorType.kBrushless);
        leftMotor2 = new CANSparkMax(4, MotorType.kBrushless);
        leftMotor3 = new CANSparkMax(5, MotorType.kBrushless);
        // configureMotor(rightMotor1);
        // configureMotor(rightMotor2);
        // configureMotor(rightMotor3);
        // configureMotor(leftMotor1);
        // configureMotor(leftMotor2);
        // configureMotor(lefttMotor3);

        driver = new Joystick(0);
        //m_robotContainer = new RobotContainer();




    }

    public class Right {
        private double right1;
        private double right2;
        public Right(double right1, double right2) {
            this.right1 = right1;
            this.right2 = right2;
        }
    }

    public class Left {
        private double left1;
        private double left2;
        public Left(double left1, double left2) {
            this.left1 = left1;                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                  
            this.left2 = left2;
        }
    }

    @Override
    public void robotPeriodic() {
        
    }

    @Override
    public void disabledInit() {
    }

    @Override
    public void disabledPeriodic() {
    }

    @Override
    public void autonomousInit() {
        
    }

    @Override
    public void autonomousPeriodic() {

    }

    @Override
    public void teleopInit() {
        if (m_autonomousCommand != null) {
            m_autonomousCommand.cancel();
        }


    }

    @Override
    public void teleopPeriodic() {
        double throttle = -driver.getRawAxis(1);
        double turn = -driver.getRawAxis(4);
        SmartDashboard.putNumber("throttle", throttle);
        SmartDashboard.putNumber("turn", turn);
       
        double left = throttle + turn;
        double right = throttle - turn;
       
        leftMotor1.set(left);
        leftMotor2.set(left);
        leftMotor3.set(left);
        rightMotor1.set(-right);
        rightMotor2.set(-right);
        rightMotor3.set(-right);

    }

    @Override
    public void testInit() {
        CommandScheduler.getInstance().cancelAll();
    }

    @Override
    public void testPeriodic() {
    }
}
