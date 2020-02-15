package frc.robot.subsystems.shooter.commands;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.subsystems.shooter.Shooter;

public class ChangeShooterSpeed extends InstantCommand {
    private final Shooter shooter;
    private double speed;

    public ChangeShooterSpeed(Shooter shooter, double speed){
        this.shooter = shooter;
        addRequirements(shooter);

        this.speed = speed;
    }

    @Override
    public void initialize(){
        this.shooter.changeShooterSpeed(this.speed);
    }

}
