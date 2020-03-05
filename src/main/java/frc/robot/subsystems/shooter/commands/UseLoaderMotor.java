package frc.robot.subsystems.shooter.commands;

import com.ctre.phoenix.motorcontrol.ControlMode;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.shooter.Loader;

public class UseLoaderMotor extends CommandBase {
    private Loader loader;
    private double loaderMotorPower;

    public UseLoaderMotor(Loader loader, double loaderMotorPower) {
        this.loader = loader;
        this.loaderMotorPower = loaderMotorPower;
        addRequirements(loader);
    }

    @Override
    public void execute() {
        loader.getLoaderMotor().set(ControlMode.PercentOutput, loaderMotorPower);
    }
    @Override
    public void end(boolean interrupted) {
        loader.getLoaderMotor().set(ControlMode.PercentOutput, 0);
    }
}
