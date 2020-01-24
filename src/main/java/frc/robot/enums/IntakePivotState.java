package frc.robot.enums;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import frc.robot.subsystems.Intake;

public enum IntakePivotState {
    UP(DoubleSolenoid.Value.kForward), DOWN(DoubleSolenoid.Value.kReverse), NEUTRAL(DoubleSolenoid.Value.kOff);

    private DoubleSolenoid.Value value;

    IntakePivotState(DoubleSolenoid.Value value){this.value = value;}

    public DoubleSolenoid.Value getValue(){return value;}
}