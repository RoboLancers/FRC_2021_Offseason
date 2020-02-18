package frc.robot.subsystems.shooter.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.shooter.Shooter;

/**SHOULD NOT REQUIRE A TARGET RPM*/
public class RevUpShooter extends CommandBase {
    private final Shooter shooter;
    private double targetRPM, targetVelocity;

    public RevUpShooter(Shooter shooter, double targetRPM){
        this.shooter = shooter;
        this.targetRPM = targetRPM;
        addRequirements(shooter);
    }

    @Override
    public void initialize(){
        shooter.resetPID();
        shooter.setTargetRPM(targetRPM);
    }

    @Override
    public void execute(){
        shooter.setMotorToTarget();
    }


    @Override
    public boolean isFinished(){
//        return shooter.fastEnough();
        return false;
    }

    @Override
    public void end(boolean interrupted) {
        shooter.setTargetInchesPerSec(0);
    }
}
