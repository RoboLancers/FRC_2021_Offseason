package frc.robot.subsystems.manipulators.shooter.commands;

import com.ctre.phoenix.motorcontrol.ControlMode;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.manipulators.Shooter;

public class UseShooter extends CommandBase {
    private final Shooter shooter = RobotContainer.shooter;

    double targetSpeed = 0.7;

    double masterSpeed = shooter.getMaster().getSelectedSensorVelocity();
    public UseShooter(){
        addRequirements(RobotContainer.shooter);
    }

    @Override
    public void execute(){


        shooter.getMaster().set(ControlMode.PercentOutput, targetSpeed);



        if(masterSpeed == targetSpeed) {
            shooter.getLoader().set(ControlMode.PercentOutput, 1);
        }
    }

    @Override
    public boolean isFinished(){
        return false;
    }





}
