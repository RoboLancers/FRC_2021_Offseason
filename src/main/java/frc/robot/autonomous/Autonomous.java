package frc.robot.autonomous;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.RobotContainer;
import frc.robot.autonomous.enums.Objective;
import frc.robot.autonomous.enums.StartingPosition;
import frc.robot.autonomous.routine.ShootThreePowerCells;
import frc.robot.subsystems.drivetrain.Drivetrain;
import frc.robot.subsystems.intake.Intake;
import frc.robot.subsystems.misc.Gyro;
import frc.robot.subsystems.misc.Limelight;
import frc.robot.subsystems.shooter.Shooter;

public class Autonomous {
    private Command autonomousCommand;

    private StartingPosition previouslySelectedStartingPosition, selectedStartingPosition;
    private Objective previouslySelectedObjective, selectedObjective;

    private Drivetrain drivetrain;
    private Gyro gyro;
    private Shooter shooter;
    private Intake intake;
    private Odometry odometry;
    private Limelight limelight;

    public Autonomous(Drivetrain drivetrain, Gyro gyro, Shooter shooter, Intake intake, Odometry odometry, Limelight limelight) {
        this.drivetrain = drivetrain;
        this.gyro = gyro;
        this.shooter = shooter;
        this.intake = intake;
        this.odometry = odometry;
        this.limelight = limelight;
    }

    public void update() {
        selectedStartingPosition = RobotContainer.networkInterface.getStartingPositionChooser().getSelected();
        selectedObjective = RobotContainer.networkInterface.getObjectiveChooser().getSelected();

        if (selectedObjective != previouslySelectedObjective || selectedStartingPosition != previouslySelectedStartingPosition) {
            if (selectedObjective == Objective.THREE_BALL) {
                autonomousCommand = new ShootThreePowerCells(drivetrain, gyro, shooter, intake, odometry, limelight, selectedStartingPosition);
            }
            if (autonomousCommand != null) {
                RobotContainer.networkInterface.getCurrentlySelectedAutonomousEntry().setString(autonomousCommand.getName());
            }
        }
        previouslySelectedObjective = selectedObjective;
        previouslySelectedStartingPosition = selectedStartingPosition;
    }

    public Command getAutonomousCommand() {
        return autonomousCommand;
    }
}
