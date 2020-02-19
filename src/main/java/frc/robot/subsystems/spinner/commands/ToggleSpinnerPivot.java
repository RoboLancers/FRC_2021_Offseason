package frc.robot.subsystems.spinner.commands;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.subsystems.spinner.SpinnerPivot;
import frc.robot.subsystems.spinner.enums.SpinnerPivotState;

/**UNFINISHED CODE*/
public class ToggleSpinnerPivot extends InstantCommand {
    private SpinnerPivot spinnerPivot;

    public ToggleSpinnerPivot(SpinnerPivot spinnerPivot){
        this.spinnerPivot = spinnerPivot;
        addRequirements(spinnerPivot);
    }

    @Override
    public void initialize(){
        spinnerPivot.setSpinnerPivot(spinnerPivot.getSpinnerPivot() == SpinnerPivotState.UP ? SpinnerPivotState.DOWN : SpinnerPivotState.UP);
    }
}
