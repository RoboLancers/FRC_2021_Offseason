package frc.robot.subsystems.manipulators.intake.enums;

import edu.wpi.first.wpilibj.DoubleSolenoid;

public enum IntakePivotState {
    INTAKEUP(DoubleSolenoid.Value.kForward), INTAKEDOWN(DoubleSolenoid.Value.kReverse), INTAKENEUTRAL(DoubleSolenoid.Value.kOff);

    private DoubleSolenoid.Value value;

    IntakePivotState(DoubleSolenoid.Value value){this.value = value;}

    public DoubleSolenoid.Value getValue(){return value;}
}