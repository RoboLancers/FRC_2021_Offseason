package frc.robot.subsystems.drivetrain.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.drivetrain.Drivetrain;
import frc.robot.utilities.Utilities;
import frc.robot.utilities.XboxController;

public class UseDrivetrain extends CommandBase {
    private final Drivetrain drivetrain;
    private final XboxController xboxController;
    double throttle, turn, leftPower, rightPower;

    public UseDrivetrain(Drivetrain subsystem, XboxController xboxController) {
        this.drivetrain = subsystem;
        this.xboxController = xboxController;
        addRequirements(drivetrain);
    }

    @Override
    public void execute() {
        throttle = xboxController.getAxisValue(XboxController.Axis.LEFT_Y);
        turn = xboxController.getAxisValue(XboxController.Axis.RIGHT_X) / 1.5;


        leftPower = throttle + turn;
        rightPower = throttle - turn;

        drivetrain.getLeft().getMain().set(Utilities.squareKeepSign(leftPower));
        drivetrain.getRight().getMain().set(Utilities.squareKeepSign(rightPower));
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
