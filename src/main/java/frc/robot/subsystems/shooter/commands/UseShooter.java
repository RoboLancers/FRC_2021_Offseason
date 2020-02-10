package frc.robot.subsystems.shooter.commands;

import com.ctre.phoenix.motorcontrol.ControlMode;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.shooter.Shooter;

public class UseShooter extends CommandBase {
    private final Shooter shooter;

    public UseShooter(Shooter shooter){
        this.shooter = shooter;
        addRequirements(shooter);

    }

    //make it so PID makes targetSpeed stay on target
    @Override
    public void initialize(){
        this.shooter.doRunShooter(true);
    }

    @Override
    public void execute(){
        shooter.setTargetRPM(9000);
        if(!shooter.fastEnough()){
            shooter.setMotorToTarget();
        } else {
            shooter.getLoaderMotor(ControlMode.PercentOutput, 1);
        }
    }
//ADD: IF FAST ENOUGH AND AIMED, THEN SHOOT
    @Override
    public void end(boolean interrupted){
        this.shooter.doRunShooter(false);
    }

    @Override
    public boolean isFinished(){
        return false;
    }





}
