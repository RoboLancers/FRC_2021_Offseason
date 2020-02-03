package frc.robot.autonomous;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.controller.RamseteController;
import edu.wpi.first.wpilibj.controller.SimpleMotorFeedforward;
import edu.wpi.first.wpilibj.trajectory.Trajectory;
import edu.wpi.first.wpilibj2.command.RamseteCommand;
import frc.robot.Constants;
import frc.robot.RobotContainer;

public class Autonomous extends RamseteCommand{
    private RamseteCommand autonomousCommand;

    public Autonomous(Trajectory trajectory) {
        super(trajectory,
                RobotContainer.odometry::getPose2dFeet,
                new RamseteController(0,0),
                new SimpleMotorFeedforward(Constants.Odometry.kSTATIC,
                        Constants.Odometry.kVELOCITY,
                        Constants.Odometry.kACCELERATION),
                RobotContainer.odometry.getKinematics(),
                RobotContainer.odometry::getWheelSpeeds,
                new PIDController(0, 0, 0),
                new PIDController(0, 0, 0),
                RobotContainer.drivetrain::setVoltage,
                RobotContainer.drivetrain);
    }

    public Autonomous() {
        this(Trajectories.straightForward);
    }
}
