package frc.robot.subsystems.shooter.commands;

import com.ctre.phoenix.motorcontrol.ControlMode;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.subsystems.shooter.Shooter;

public class ShooterSpeed extends InstantCommand {
    private Shooter shooter;
    double power;
    public ShooterSpeed(Shooter shooter, double power) {
        addRequirements(shooter);
        this.shooter = shooter;
        this.power = power;
    }

    @Override
    public void execute() {
        shooter.getMaster().set(ControlMode.PercentOutput, power);
    }

}
