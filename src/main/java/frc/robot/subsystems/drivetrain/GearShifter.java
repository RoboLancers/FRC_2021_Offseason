package frc.robot.subsystems.drivetrain;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.RobotMap;
import frc.robot.subsystems.drivetrain.enums.GearShifterState;

public class GearShifter extends SubsystemBase {

    private DoubleSolenoid gearShifter;

    public GearShifterState state;

    public GearShifter(){
        gearShifter = new DoubleSolenoid(RobotMap.Manipulator.GearShifter.GEAR_SHIFTER_HIGH, RobotMap.Manipulator.GearShifter.GEAR_SHIFTER_LOW);
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
