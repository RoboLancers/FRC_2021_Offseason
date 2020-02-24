package frc.robot.subsystems.spinner.commands;

import com.ctre.phoenix.motorcontrol.ControlMode;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.spinner.Spinner;

public class UseSpinner extends CommandBase {
    private Spinner spinner;
    private double spinnerPower;

    public UseSpinner(Spinner spinner, double spinnerPower) {
        addRequirements(spinner);
        this.spinnerPower = spinnerPower;
        this.spinnerPower = spinnerPower;
        addRequirements(spinner);
        this.spinner = spinner;
    }

    @Override
    public void execute() {
        spinner.set(spinnerPower);
        spinner.getSpinner().set(ControlMode.PercentOutput, 1);
    }

    @Override
    public void end(boolean interrupted) {
        spinner.getSpinner().set(ControlMode.PercentOutput, 0);
    }
}
