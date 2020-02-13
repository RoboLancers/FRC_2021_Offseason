package frc.robot.autonomous;

import edu.wpi.first.wpilibj.controller.SimpleMotorFeedforward;
import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.trajectory.Trajectory;
import edu.wpi.first.wpilibj.trajectory.TrajectoryConfig;
import edu.wpi.first.wpilibj.trajectory.TrajectoryGenerator;
import edu.wpi.first.wpilibj.trajectory.constraint.DifferentialDriveVoltageConstraint;
import edu.wpi.first.wpilibj.util.Units;
import frc.robot.Constants;
import java.util.Arrays;

public class Trajectories {
    final TrajectoryConfig config;

    public Trajectories(Odometry odometry) {
        DifferentialDriveVoltageConstraint voltageConstraint = new DifferentialDriveVoltageConstraint(
                new SimpleMotorFeedforward(Constants.Trajectory.kSTATIC,
                        Constants.Trajectory.kVELOCITY,
                        Constants.Trajectory.kACCELERATION),
                odometry.getKinematics(),
                Constants.Trajectory.MAX_VOLTAGE);

        this.config = new TrajectoryConfig(Constants.Trajectory.MAX_VELOCITY_CONSTRAINT,
                Constants.Trajectory.MAX_ACCELERATION_CONSTRAINT)
                .setKinematics(odometry.getKinematics())
                .addConstraint(voltageConstraint)
                .setStartVelocity(0)
                .setEndVelocity(0);
    }

    public Trajectory straightForward() {
        return TrajectoryGenerator.generateTrajectory(Arrays.asList(
                new Pose2d(0, 0, new Rotation2d(0)),
                new Pose2d(3, 0, new Rotation2d(0))),
                this.config
        );
    }

    public Trajectory turnRight() {
        return TrajectoryGenerator.generateTrajectory(Arrays.asList(
                new Pose2d(0, 0, new Rotation2d(0)),
                new Pose2d(6, -3, new Rotation2d(Units.degreesToRadians(-90)))),
                this.config
        );
    }

    public Trajectory backward() {
        return TrajectoryGenerator.generateTrajectory(Arrays.asList(
                new Pose2d(0, 0, new Rotation2d(0)),
                new Pose2d(-6, 3, new Rotation2d(Units.degreesToRadians(-45)))),
                this.config.setReversed(true)
        );
    }
}
