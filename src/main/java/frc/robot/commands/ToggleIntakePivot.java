package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.enums.IntakePivotState;

import frc.robot.subsystems.manipulators.intake.IntakePivot;

import static frc.robot.RobotContainer.intakePivot;

public class ToggleIntakePivot extends InstantCommand {

    private final IntakePivot intakePivot;

    public ToggleIntakePivot(final IntakePivot intakePivot ){
        this.intakePivot = intakePivot;
        addRequirements(intakePivot);
    }

    @Override
    public void initialize(){
        this.intakePivot.set(intakePivot.getState() == IntakePivotState.INTAKEUP ? IntakePivotState.INTAKEDOWN : IntakePivotState.INTAKEUP);
    }

}
