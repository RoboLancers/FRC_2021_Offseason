package frc.robot.autonomous;

import edu.wpi.first.wpilibj.controller.SimpleMotorFeedforward;
import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.geometry.Translation2d;
import edu.wpi.first.wpilibj.trajectory.Trajectory;
import edu.wpi.first.wpilibj.trajectory.TrajectoryConfig;
import edu.wpi.first.wpilibj.trajectory.TrajectoryGenerator;
import edu.wpi.first.wpilibj.trajectory.constraint.DifferentialDriveVoltageConstraint;
import frc.robot.Constants;
import frc.robot.RobotContainer;

import java.util.Arrays;
import java.util.List;

public class Trajectories {
    static DifferentialDriveVoltageConstraint voltageConstraint = new DifferentialDriveVoltageConstraint(
            new SimpleMotorFeedforward(Constants.Odometry.kSTATIC,
                    Constants.Odometry.kVELOCITY,
                    Constants.Odometry.kACCELERATION),
            RobotContainer.odometry.kinematics,
            Constants.Odometry.MAX_VOLTAGE);

    static TrajectoryConfig config = new TrajectoryConfig(Constants.Trajectory.MAX_VELOCITY_CONSTRAINT,
            Constants.Trajectory.MAX_ACCELERATION_CONSTRAINT)
            .setKinematics(RobotContainer.odometry.getKinematics())
            .addConstraint(voltageConstraint);

    public static Trajectory straightForward = TrajectoryGenerator.generateTrajectory(Arrays.asList(
            new Pose2d(0, 0, new Rotation2d(0)),
            new Pose2d(3, 0, new Rotation2d(0))
            ),
            config
    );

}
