package frc.robot.subsystems.spinner.commands;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.subsystems.spinner.enums.SpinnerPivotState;

import static frc.robot.RobotContainer.spinnerPivot;

/**UNFINISHED CODE*/
public class ToggleSpinnerPivot extends InstantCommand {
    private SpinnerPivotState spinnerPivotState;

    public ToggleSpinnerPivot(){
        addRequirements(spinnerPivot);
    }

    @Override
    public void initialize(){

        if(spinnerPivot.getSpinnerPivot() == spinnerPivotState.DOWN){
            spinnerPivot.setSpinnerPivot(SpinnerPivotState.UP);
        }else{

        }
    }
}
