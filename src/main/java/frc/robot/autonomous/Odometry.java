package frc.robot.autonomous;

import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.kinematics.ChassisSpeeds;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveKinematics;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveWheelSpeeds;
import frc.robot.Constants;
import frc.robot.RobotContainer;
import frc.robot.subsystems.drivetrain.Drivetrain;
import frc.robot.subsystems.misc.Gyro;

public class Odometry {
    DifferentialDriveOdometry odometry;
    DifferentialDriveKinematics kinematics;
    DifferentialDriveWheelSpeeds wheelSpeeds;
    ChassisSpeeds chassisSpeeds;
    Drivetrain drivetrain;
    private Gyro gyro;

    public Odometry(Drivetrain drivetrain, Gyro gyro){
        this.drivetrain = drivetrain;
        this.gyro = gyro;
        odometry = new DifferentialDriveOdometry(Rotation2d.fromDegrees((gyro.getFusedHeading())));
        chassisSpeeds = new ChassisSpeeds(Constants.Odometry.MAX_VELOCITY, Constants.Odometry.MAX_VELOCITY_SIDE, Constants.Odometry.ANGULAR_VELOCITY);
        wheelSpeeds = new DifferentialDriveWheelSpeeds(this.drivetrain.getLeft().getVelocity(), this.drivetrain.getRight().getVelocity());
        kinematics = new DifferentialDriveKinematics(Constants.Odometry.ROBOT_WIDTH);
    }

    public void updateOdometry() {
        odometry.update(Rotation2d.fromDegrees(gyro.getFusedHeading()), this.drivetrain.getLeft().getDistance(), this.drivetrain.getRight().getDistance());
    }

    public Pose2d getPose2d(){
        return odometry.getPoseMeters();
    }

    public void resetOdometry(Pose2d pose){
        odometry.resetPosition(pose, Rotation2d.fromDegrees(gyro.getFusedHeading()));
    }

    public DifferentialDriveWheelSpeeds getWheelSpeeds() {
        return new DifferentialDriveWheelSpeeds(this.drivetrain.getLeft().getVelocity(), this.drivetrain.getRight().getVelocity());
    }

    public DifferentialDriveKinematics getKinematics() {
        return kinematics;
    }
}
