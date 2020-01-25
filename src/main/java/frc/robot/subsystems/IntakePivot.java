package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.enums.IntakePivotState;

public class IntakePivot extends SubsystemBase {

    private DoubleSolenoid intakePivot;

    private IntakePivotState state;

    public IntakePivot(){
        intakePivot = new DoubleSolenoid(Constants.Manipulator.Intake.INTAKE_PIVOT_PORT_UP, Constants.Manipulator.Intake.INTAKE_PIVOT_PORT_DOWN);
        state = IntakePivotState.INTAKENEUTRAL;
    }

    public void setIntakePivot(IntakePivotState intakePivotState){
        intakePivot.set(intakePivotState.getValue());
        state = intakePivotState;
    }

    public IntakePivotState getState() {
        return state;
    }
}
