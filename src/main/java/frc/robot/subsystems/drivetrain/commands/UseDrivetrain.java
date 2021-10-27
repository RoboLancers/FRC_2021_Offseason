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
        turn = xboxController.getAxisValue(XboxController.Axis.RIGHT_X);
        drivetrain.Drive(throttle, turn);
        // leftPower = (throttle + turn)/1.75;
        // rightPower = (throttle - turn)/1.75;
        
        // values are NEGATIVE b/c of inversion of the y axis
        /*
        leftPower = -xboxController.getAxisValue(XboxController.Axis.LEFT_Y);
        rightPower = -xboxController.getAxisValue(XboxController.Axis.RIGHT_Y);
        */

        // drivetrain.getLeft().getMain().set(Math.pow(leftPower,3));
        // drivetrain.getRight().getMain().set(Math.pow(rightPower,3));
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
