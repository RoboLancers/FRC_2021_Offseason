package frc.robot.commands;

import frc.robot.enums.GearShifterState;
import frc.robot.subsystems.GearShifter;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ToggleGearShifterTest {
    @Test
    public void testToggleMultipleTimes() {
        final GearShifter gearShifter = new GearShifter();
        final ToggleGearShifter toggleGearShifter = new ToggleGearShifter(gearShifter);
        assertEquals(GearShifterState.HIGHGEAR, gearShifter.getState());
        toggleGearShifter.initialize();
        assertEquals(GearShifterState.LOWGEAR, gearShifter.getState());

    }
}
