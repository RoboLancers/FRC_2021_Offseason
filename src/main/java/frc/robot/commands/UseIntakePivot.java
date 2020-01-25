package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.enums.IntakePivotState;
import frc.robot.subsystems.IntakePivot;

import static frc.robot.RobotContainer.intakePivot;

public class UseIntakePivot extends InstantCommand {
    private IntakePivotState intakePivotState;

    public UseIntakePivot(IntakePivotState intakePivotState){
        addRequirements(intakePivot);
        this.intakePivotState = intakePivotState;
    }
}
