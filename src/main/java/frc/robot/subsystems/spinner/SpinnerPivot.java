package frc.robot.subsystems.spinner;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.RobotMap;
import frc.robot.subsystems.spinner.enums.SpinnerPivotState;

public class SpinnerPivot extends SubsystemBase {

    private DoubleSolenoid spinnerPivot;
    private SpinnerPivotState spinnerPivotState;

    public SpinnerPivot(){
        spinnerPivot = new DoubleSolenoid(RobotMap.Manipulator.Spinner.SPINNER_PIVOT_PORT_UP, RobotMap.Manipulator.Spinner.SPINNER_PIVOT_PORT_DOWN);
        spinnerPivotState = SpinnerPivotState.NEUTRAL;
    }

    public void setSpinnerPivot(SpinnerPivotState spinnerPivotState){
        spinnerPivot.set(spinnerPivotState.getValue());
    }

    public SpinnerPivotState getSpinnerPivot(){
        return spinnerPivotState;

    }
}
