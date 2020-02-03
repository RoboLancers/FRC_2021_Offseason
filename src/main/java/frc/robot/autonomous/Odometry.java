package frc.robot.autonomous;

import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.kinematics.ChassisSpeeds;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveKinematics;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveWheelSpeeds;
import frc.robot.Constants;
import frc.robot.RobotContainer;

public class Odometry {
    DifferentialDriveOdometry odometry;
    DifferentialDriveKinematics kinematics;
    DifferentialDriveWheelSpeeds wheelSpeeds;
    ChassisSpeeds chassisSpeeds;

    public Odometry(){
        odometry = new DifferentialDriveOdometry(Rotation2d.fromDegrees((RobotContainer.gyro.getYaw())));
        chassisSpeeds = new ChassisSpeeds(Constants.Odometry.MAX_VELOCITY, Constants.Odometry.MAX_VELOCITY_SIDE, Constants.Odometry.ANGULAR_VELOCITY);
        wheelSpeeds = new DifferentialDriveWheelSpeeds(RobotContainer.drivetrain.getLeft().getVelocity(), RobotContainer.drivetrain.getRight().getVelocity());
        kinematics = new DifferentialDriveKinematics(Constants.Odometry.ROBOT_WIDTH);
    }

    public void updateOdometry() {
        odometry.update(Rotation2d.fromDegrees(RobotContainer.gyro.getYaw()), RobotContainer.drivetrain.getLeft().getDistance(), RobotContainer.drivetrain.getRight().getDistance());
    }

    public Pose2d getPose2dFeet(){
        return odometry.getPoseMeters();
    }

    public void resetOdometry(Pose2d pose){
        odometry.resetPosition(pose, Rotation2d.fromDegrees(RobotContainer.gyro.getYaw()));
    }

    public DifferentialDriveWheelSpeeds getWheelSpeeds() {
        return new DifferentialDriveWheelSpeeds(RobotContainer.drivetrain.getLeft().getVelocity(), RobotContainer.drivetrain.getRight().getVelocity());
    }

    public DifferentialDriveKinematics getKinematics() {
        return kinematics;
    }
}
