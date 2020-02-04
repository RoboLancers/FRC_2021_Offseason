package frc.robot.subsystems.manipulators.spinner;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.subsystems.manipulators.spinner.enums.SpinnerPivotState;

public class SpinnerPivot extends SubsystemBase {

    private DoubleSolenoid spinnerPivot;
    private SpinnerPivotState spinnerPivotState;

    public SpinnerPivot(){
        spinnerPivot = new DoubleSolenoid(Constants.Manipulator.Spinner.SPINNER_PIVOT_PORT_UP, Constants.Manipulator.Spinner.SPINNER_PIVOT_PORT_DOWN);
        spinnerPivotState = SpinnerPivotState.NEUTRAL;
    }

    public void setSpinnerPivot(SpinnerPivotState spinnerPivotState){
        spinnerPivot.set(spinnerPivotState.getValue());
    }

    public SpinnerPivotState getSpinnerPivot(){
        return spinnerPivotState;

    }
}
