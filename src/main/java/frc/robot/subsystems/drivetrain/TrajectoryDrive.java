package frc.robot.subsystems.drivetrain;

import com.ctre.phoenix.sensors.PigeonIMU;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.controller.SimpleMotorFeedforward;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.interfaces.Gyro;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveKinematics;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveWheelSpeeds;
import frc.robot.RobotMap;
import frc.robot.subsystems.drivetrain.enums.GearBoxSides;
import frc.robot.subsystems.drivetrain.GearBox;
import frc.robot.subsystems.drivetrain.Drivetrain;

public class TrajectoryDrive extends SubsystemBase {

    CANSparkMax leftMaster = new CANSparkMax(1, CANSparkMaxLowLevel.MotorType.kBrushless);
    CANSparkMax leftSlave1 = new CANSparkMax(2, CANSparkMaxLowLevel.MotorType.kBrushless);
    CANSparkMax leftSlave2 = new CANSparkMax(3, CANSparkMaxLowLevel.MotorType.kBrushless);

    CANSparkMax rightMaster = new CANSparkMax(4, CANSparkMaxLowLevel.MotorType.kBrushless);
    CANSparkMax rightSlave1 = new CANSparkMax(5, CANSparkMaxLowLevel.MotorType.kBrushless);
    CANSparkMax rightSlave2 = new CANSparkMax(6, CANSparkMaxLowLevel.MotorType.kBrushless);

    PigeonIMU bird = new PigeonIMU(1); //INSERT PORT

    DifferentialDriveKinematics kinematics = new DifferentialDriveKinematics(0.702);
    DifferentialDriveOdometry odometry = new DifferentialDriveOdometry(kinematics, getHeading()); // FIX

    SimpleMotorFeedforward feedforward = new SimpleMotorFeedforward(0.131, 4.03, 0.521);

    PIDController leftPIDController = new PIDController(1.89, 0, 0);
    PIDController rightPIDController = new PIDController(1.89, 0, 0);


    Pose2d pose;

    public TrajectoryDrive() {
        leftSlave1.follow(leftMaster);
        leftSlave1.follow(leftMaster);

        rightSlave1.follow(rightMaster);
        rightSlave2.follow(rightMaster);

        leftMaster.setInverted(false);
        rightMaster.setInverted(true);
    }

    public Rotation2d getHeading() {
        return Rotation2d.fromDegrees(-bird.getFusedHeading());
    }

    public DifferentialDriveWheelSpeeds getSpeeds() {
        return new DifferentialDriveWheelSpeeds(
            leftMaster.getEncoder().getVelocity() / 15.625 * 2 * Math.PI * 0.0762 / 60, 
            rightMaster.getEncoder().getVelocity() / 15.625 * 2 * Math.PI * 0.0762 / 60
            );
    }

    public SimpleMotorFeedforward getFeedforward() {
        return feedforward;
    }

    public DifferentialDriveKinematics getKinematics() {
        return kinematics;
    }

    public PIDController getLeftPidController() {
        return leftPIDController;
    }

    public PIDController getRightPidController() {
        return rightPIDController;
    }

    @Override
    public void periodic() {
        pose = odometry.update(getHeading(), getSpeeds() ); //May or may not want to make two methods for get speeds later
    }

    }

