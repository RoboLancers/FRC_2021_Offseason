package frc.robot.subsystems.climber.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.climber.PullUp;

//using neo
public class UsePullUp extends CommandBase {
    private PullUp pullUp;
    private double pullPower;

    public UsePullUp(){
        pullUp = RobotContainer.pullUp;
        addRequirements(pullUp);
    }

    @Override
    public void execute() {
        pullUp.getPullUpMotor().set(0.3);
    }

    @Override
    public void end(boolean interrupted) {
        pullUp.getPullUpMotor().set(0);
    }
}
