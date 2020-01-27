package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.enums.GearShifterState;
import frc.robot.enums.IntakePivotState;

public class GearShifter extends SubsystemBase {

    private DoubleSolenoid gearShifter;

    public GearShifterState state;

    public GearShifter(){
        gearShifter = new DoubleSolenoid(Constants.Manipulator.GearShifter.GEAR_SHIFTER_HIGH, Constants.Manipulator.GearShifter.GEAR_SHIFTER_LOW);
        state = GearShifterState.HIGHGEAR;
    }

    public void setGearShifter(GearShifterState gearShifterState){
        gearShifter.set(gearShifterState.getValue());
        state = gearShifterState;
    }

    public GearShifterState getState() {
        return state;
    }
}
