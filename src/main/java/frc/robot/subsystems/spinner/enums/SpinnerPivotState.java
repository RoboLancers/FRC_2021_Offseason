package frc.robot.subsystems.spinner.enums;

import edu.wpi.first.wpilibj.DoubleSolenoid;

public enum SpinnerPivotState {
    UP(DoubleSolenoid.Value.kForward), DOWN(DoubleSolenoid.Value.kReverse), NEUTRAL(DoubleSolenoid.Value.kOff);

    private DoubleSolenoid.Value value;

    SpinnerPivotState(DoubleSolenoid.Value value){this.value = value;}

    public DoubleSolenoid.Value getValue(){return value;}
}

