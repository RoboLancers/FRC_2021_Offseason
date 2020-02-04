package frc.robot.subsystems.manipulators.spinner.commands;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.Subsystem;
import frc.robot.subsystems.manipulators.spinner.enums.SpinnerPivotState;

import java.util.Set;

import static frc.robot.RobotContainer.spinnerPivot;

public class ToggleSpinnerPivot extends InstantCommand {
    Set<Subsystem> subsystems;

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
