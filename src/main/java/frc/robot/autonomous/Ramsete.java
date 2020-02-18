package frc.robot.autonomous;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.controller.RamseteController;
import edu.wpi.first.wpilibj.controller.SimpleMotorFeedforward;
import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.trajectory.Trajectory;
import edu.wpi.first.wpilibj2.command.RamseteCommand;
import frc.robot.Constants;
import frc.robot.RobotContainer;
import frc.robot.subsystems.drivetrain.Drivetrain;

public class Ramsete extends RamseteCommand{
    public Ramsete(Odometry odometry, Drivetrain drivetrain, Trajectory trajectory) {
        super(trajectory,
                odometry::getPose2d,
                new RamseteController(Constants.Trajectory.kBETA, Constants.Trajectory.kZETA),
                new SimpleMotorFeedforward(Constants.Trajectory.kSTATIC,
                        Constants.Trajectory.kVELOCITY,
                        Constants.Trajectory.kACCELERATION),
                odometry.getKinematics(),
                odometry::getWheelSpeeds,
                new PIDController(0.0, 0, 0),
                new PIDController(0.0, 0, 0),
                drivetrain::setVoltage,
                drivetrain);
    }
}
