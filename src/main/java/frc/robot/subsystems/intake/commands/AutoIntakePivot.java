package frc.robot.subsystems.intake.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.intake.IntakePivot;
import frc.robot.subsystems.intake.enums.IntakePivotState;
import frc.robot.subsystems.misc.IRSensor;

public class AutoIntakePivot extends CommandBase {
    private IntakePivot intakePivot;
    private IRSensor irSensor;
    public AutoIntakePivot(IntakePivot intakePivot){
    this.intakePivot = intakePivot;
    addRequirements(intakePivot);
    }

    @Override
    public void execute() {
        if(irSensor.isStorageFull()){
            intakePivot.set(IntakePivotState.INTAKEUP);
        } else {
            intakePivot.set(IntakePivotState.INTAKEDOWN);
        }
    }

    @Override
    public boolean isFinished() { return false; }
}
