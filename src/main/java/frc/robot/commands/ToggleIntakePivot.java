package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.enums.IntakePivotState;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.manipulators.intake.IntakePivot;

import static frc.robot.RobotContainer.intakePivot;

public class ToggleIntakePivot extends InstantCommand {

    public ToggleIntakePivot(){
        addRequirements(intakePivot);
    }

    @Override
    public void initialize(){
        intakePivot.setIntakePivot(intakePivot.getState() == IntakePivotState.INTAKEUP ? IntakePivotState.INTAKEDOWN : IntakePivotState.INTAKEUP);
    }

}
