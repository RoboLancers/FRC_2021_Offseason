package frc.robot.commands;

import frc.robot.enums.IntakePivotState;
import frc.robot.subsystems.manipulators.intake.IntakePivot;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ToggleIntakePivotTest {
    @Test
    public void testToggleMultipleTimes() {
        final IntakePivot intakePivot = new IntakePivot();
        final ToggleIntakePivot toggleIntakePivot = new ToggleIntakePivot(intakePivot);
        assertEquals(IntakePivotState.INTAKEDOWN, intakePivot.getState());
        toggleIntakePivot.initialize();
        assertEquals(IntakePivotState.INTAKEUP, intakePivot.getState());

    }
}
