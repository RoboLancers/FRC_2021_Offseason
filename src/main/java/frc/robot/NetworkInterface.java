package frc.robot;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.autonomous.enums.Objective;
import frc.robot.autonomous.enums.StartingPosition;
import frc.robot.autonomous.routine.MoveForward;

public class NetworkInterface {
    private ShuffleboardTab competitionTab;

    private SendableChooser<StartingPosition> startingPositionChooser;
    private SendableChooser<Objective> objectiveChooser;

    private NetworkTableEntry currentlySelectedAutonomousEntry;

    public NetworkInterface() {
        competitionTab = Shuffleboard.getTab("Robot");

        startingPositionChooser = new SendableChooser<>();
        objectiveChooser = new SendableChooser<>();

        for (StartingPosition startingPosition : StartingPosition.values()) {
            startingPositionChooser.addOption(startingPosition.name(), startingPosition);
        }

        for (Objective objective : Objective.values()) {
            objectiveChooser.addOption(objective.name(), objective);
        }

        competitionTab.add("Starting Position Chooser", startingPositionChooser).withPosition(0, 0).withSize(2, 1);
        competitionTab.add("Objective Chooser", objectiveChooser).withPosition(2, 0).withSize(2, 1);

        currentlySelectedAutonomousEntry = competitionTab.add("Currently Selected Autonomous", "").withPosition(0, 2).withSize(4, 1).getEntry();
    }

    public SendableChooser<StartingPosition> getStartingPositionChooser() {
        return startingPositionChooser;
    }

    public SendableChooser<Objective> getObjectiveChooser() {
        return objectiveChooser;
    }

    public NetworkTableEntry getCurrentlySelectedAutonomousEntry() {
        return currentlySelectedAutonomousEntry;
    }
}
