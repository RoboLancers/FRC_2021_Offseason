package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class GearShifter extends SubsystemBase {

    public static DoubleSolenoid gearShifter;

    public GearShifter(){
        gearShifter = new DoubleSolenoid(Constants.Manipulator.GearShifter.GEAR_SHIFTER_FAST, Constants.Manipulator.GearShifter.GEAR_SHIFTER_SLOW);
        gearShifter.set(DoubleSolenoid.Value.kForward);
    }

    public void setHighGear(){
        gearShifter.set(DoubleSolenoid.Value.kForward);
    }

    public void setLowGear(){
        gearShifter.set(DoubleSolenoid.Value.kReverse);
    }
}
