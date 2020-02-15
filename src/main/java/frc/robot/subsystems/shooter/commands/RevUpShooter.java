package frc.robot.subsystems.shooter.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.shooter.Shooter;

/**SHOULD NOT REQUIRE A TARGET RPM*/
public class RevUpShooter extends CommandBase {
    private final Shooter shooter;
    private double targetRPM, targetVelocity;

    public RevUpShooter(Shooter shooter, double targetVelocity){
        this.shooter = shooter;
        this.targetVelocity = targetVelocity;
        addRequirements(shooter);
    }

    @Override
    public void initialize(){
        shooter.resetPID();
        //shooter.setTargetRPM(targetRPM);
        shooter.setTargetInchesPerSec(targetVelocity);

    }

    @Override
    public void execute(){
        shooter.setMotorToVelocity();
    }

    @Override
    public boolean isFinished(){
        return shooter.fastEnough();
    }
}
