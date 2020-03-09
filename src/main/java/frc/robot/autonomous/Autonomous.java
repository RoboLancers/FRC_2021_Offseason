package frc.robot.autonomous;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.RobotContainer;
import frc.robot.autonomous.enums.Objective;
import frc.robot.autonomous.enums.StartingPosition;
import frc.robot.autonomous.routine.ShootSixPowerCells;
import frc.robot.autonomous.routine.ShootThreePowerCells;

@SuppressWarnings("FieldCanBeLocal")
public class Autonomous {
    private Command autonomousCommand;

    private StartingPosition previouslySelectedStartingPosition, selectedStartingPosition;
    private Objective previouslySelectedObjective, selectedObjective;

    private RobotContainer robotContainer;

    public Autonomous(RobotContainer robotContainer) {
        this.robotContainer = robotContainer;
        autonomousCommand = new ShootThreePowerCells(robotContainer.drivetrain, robotContainer.gyro, robotContainer.loader, robotContainer.shooter, robotContainer.irsensor, robotContainer.intake, robotContainer.odometry, robotContainer.limelight, StartingPosition.SHOOTING, robotContainer.trajectories);
    }

    public void update() {
        selectedStartingPosition = robotContainer.networkInterface.getStartingPositionChooser().getSelected();
        selectedObjective = robotContainer.networkInterface.getObjectiveChooser().getSelected();

        if (selectedObjective != previouslySelectedObjective || selectedStartingPosition != previouslySelectedStartingPosition) {
            if (selectedObjective == Objective.THREE_BALL) {
                autonomousCommand = new ShootThreePowerCells(robotContainer.drivetrain, robotContainer.gyro, robotContainer.loader, robotContainer.shooter, robotContainer.irsensor, robotContainer.intake, robotContainer.odometry, robotContainer.limelight, selectedStartingPosition, robotContainer.trajectories);
            } else if(selectedObjective == Objective.SIX_BALL) {
                autonomousCommand = new ShootSixPowerCells(robotContainer.drivetrain, robotContainer.gyro, robotContainer.loader, robotContainer.shooter, robotContainer.irsensor, robotContainer.intake, robotContainer.odometry, robotContainer.limelight, selectedStartingPosition, robotContainer.trajectories);
            }
            if (autonomousCommand != null) {
                robotContainer.networkInterface.getCurrentlySelectedAutonomousEntry().setString(autonomousCommand.getName());
            }
        }
        previouslySelectedObjective = selectedObjective;
        previouslySelectedStartingPosition = selectedStartingPosition;
    }

    public Command getAutonomousCommand() {
        return autonomousCommand;
    }
}
