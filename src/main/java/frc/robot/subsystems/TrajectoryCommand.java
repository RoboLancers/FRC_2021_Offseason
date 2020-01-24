package frc.robot.subsystems;

import edu.wpi.first.wpilibj.controller.SimpleMotorFeedforward;
import edu.wpi.first.wpilibj.trajectory.constraint.DifferentialDriveVoltageConstraint;
import frc.robot.Constants;
import frc.robot.RobotContainer;

public class TrajectoryCommand {
    DifferentialDriveVoltageConstraint voltageConstraint = new DifferentialDriveVoltageConstraint(
            new SimpleMotorFeedforward(Constants.Odometry.kSTATIC,
                    Constants.Odometry.kVELOCITY,
                    Constants.Odometry.kACCELERATION),
            RobotContainer.odometry.kinematics,
            Constants.Odometry.MAX_VOLTAGE);
}
