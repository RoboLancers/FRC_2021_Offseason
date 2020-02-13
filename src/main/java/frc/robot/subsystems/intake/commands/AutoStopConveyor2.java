package frc.robot.subsystems.intake.commands;

import com.ctre.phoenix.motorcontrol.ControlMode;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.intake.Intake;
import frc.robot.subsystems.misc.IRSensor;

public class AutoStopConveyor2 extends CommandBase {
    private Intake intake;
    private IRSensor irSensor;

    public AutoStopConveyor2(Intake intake) {
        this.intake = intake;
        addRequirements(intake);
    }

    @Override
    public void execute() {
        if (irSensor.isThreeBallsIn()) {
            this.intake.getTransferMotor().set(ControlMode.PercentOutput, 0);
        }
    }

    @Override
    public boolean isFinished(){ return false; }
}