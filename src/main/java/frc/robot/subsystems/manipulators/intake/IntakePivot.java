package frc.robot.subsystems.manipulators.intake;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;
import frc.robot.enums.IntakePivotState;

public class IntakePivot extends SubsystemBase {

    private DoubleSolenoid intakePivot;

    public IntakePivotState state;

    public IntakePivot(){
        intakePivot = new DoubleSolenoid(Constants.Manipulator.Intake.INTAKE_PIVOT_PORT_UP, Constants.Manipulator.Intake.INTAKE_PIVOT_PORT_DOWN);
        state = IntakePivotState.INTAKENEUTRAL;
    }

    public void set(IntakePivotState intakePivotState){
        intakePivot.set(intakePivotState.getValue());
        state = intakePivotState;
    }

    public IntakePivotState getState() {
        return state;
    }
}
