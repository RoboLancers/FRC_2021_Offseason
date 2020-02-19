package frc.robot.subsystems.climber.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.climber.Puller;
import frc.robot.utilities.XboxController;

public class PullUp extends CommandBase {
    private Puller puller;

    public PullUp(Puller puller){
        addRequirements(puller);
        this.puller = puller;
    }

    @Override
    public void execute() {
        puller.set(RobotContainer.manipulatorXboxController.getAxisValue(XboxController.Axis.RIGHT_Y));
    }
}
