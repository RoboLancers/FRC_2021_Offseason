package frc.robot.subsystems.shooter.commands;

import com.revrobotics.ControlType;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.shooter.Shooter;


public class RevUpShooter extends CommandBase {
    private final Shooter shooter;
    private double targetRPM;

    public RevUpShooter(Shooter shooter, double targetRPM) {
        this.shooter = shooter;
        this.targetRPM = targetRPM;

        addRequirements(shooter);
    }

    @Override
    public void initialize() {
    }

    @Override
    public void execute() {
        shooter.getPidController().setReference(targetRPM, ControlType.kVelocity);
    }

    @Override
    public void end(boolean interrupted) {
        shooter.getMaster().set(0);
    }
}
