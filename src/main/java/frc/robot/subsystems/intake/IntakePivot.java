package frc.robot.subsystems.intake;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.RobotMap;
import frc.robot.subsystems.intake.enums.IntakePivotState;

public class IntakePivot extends SubsystemBase {

    private DoubleSolenoid intakePivot;

    public IntakePivotState state;

    public IntakePivot(){
        intakePivot = new DoubleSolenoid(RobotMap.Manipulator.Intake.INTAKE_PIVOT_PORT_UP, RobotMap.Manipulator.Intake.INTAKE_PIVOT_PORT_DOWN);
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
