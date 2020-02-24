package frc.robot.subsystems.spinner.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.spinner.Spinner;

/**UNFNISHED CODE*/
public class UseSpinner extends CommandBase {
    private Spinner spinner;
    private double spinnerPower;

    public UseSpinner(Spinner spinner, double spinnerPower) {
        addRequirements(spinner);
        this.spinnerPower = spinnerPower;
        this.spinner = spinner;
    }

    @Override
    public void execute() {
        spinner.set(spinnerPower);
    }
}
