package frc.robot.subsystems.intake.commands;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.subsystems.intake.enums.IntakePivotState;

import frc.robot.subsystems.intake.IntakePivot;
import frc.robot.subsystems.misc.IRSensor;

/**STORAGE FULL SHOULD BE ITS OWN SEPARATE COMMAND*/
public class ToggleIntakePivot extends InstantCommand {

    private final IntakePivot intakePivot;
    private IRSensor irSensor;

    public ToggleIntakePivot(final IntakePivot intakePivot ){
        this.intakePivot = intakePivot;
        addRequirements(intakePivot);

    }

    @Override
    public void initialize(){
        this.intakePivot.set(intakePivot.getState() == IntakePivotState.INTAKEUP ? IntakePivotState.INTAKEDOWN : IntakePivotState.INTAKEUP);

        //if all 4 ir sensors detect, pivot goes IntakePivotState,INTAKEUP

        if(irSensor.isStorageFull()){
            intakePivot.set(IntakePivotState.INTAKEUP);
        } else{
            intakePivot.set(IntakePivotState.INTAKEDOWN);
        }
    }

}
