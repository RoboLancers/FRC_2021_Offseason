package frc.robot.subsystems.climber.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.climber.Climber;
import frc.robot.utilities.XboxController;

//using neo
public class PullUp extends CommandBase {
    private Climber climber;
    private double pullPower;

    public PullUp(){
        addRequirements(RobotContainer.climber);
    }

    @Override
    public void execute() {
        pullPower = RobotContainer.manipulatorXboxController.getAxisValue(XboxController.Axis.RIGHT_Y);

        climber.getPullUpMotor().set(pullPower);
    }
}
