package frc.robot.subsystems.drivetrain.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.drivetrain.Drivetrain;
import frc.robot.utilities.Utilities;
import frc.robot.utilities.XboxController;
import edu.wpi.first.wpilibj.SlewRateLimiter;


public class UseDrivetrain extends CommandBase {
    private final Drivetrain drivetrain;
    private final XboxController xboxController;
    public SlewRateLimiter filter1;
    public SlewRateLimiter filter2;

    double throttle, turn, leftPower, rightPower, maxPower;

    public UseDrivetrain(Drivetrain subsystem, XboxController xboxController) {
        this.drivetrain = subsystem;
        this.xboxController = xboxController;
        addRequirements(drivetrain);
        filter1 = new SlewRateLimiter(0.9);
        filter2 = new SlewRateLimiter(0.5);
    }

    @Override
    public void execute() {
        //magnitude of the maximum power
        maxPower = 0.75;

        // forwards & backwards
        throttle = xboxController.getAxisValue(XboxController.Axis.LEFT_Y);
        // ensures throttle value has magnitude no more than maxPower
        throttle = (throttle < 0 ? Math.max(-maxPower, throttle) : Math.min(maxPower, throttle));
        
        // left & right
        turn = (xboxController.getAxisValue(XboxController.Axis.RIGHT_X))*0.4;

        leftPower = -throttle + turn;
        rightPower = -throttle - turn;
        
        // values are NEGATIVE b/c of inversion of the y axis
        /*
        leftPower = -xboxController.getAxisValue(XboxController.Axis.LEFT_Y);
        rightPower = -xboxController.getAxisValue(XboxController.Axis.RIGHT_Y);
        */

        drivetrain.getLeft().getMain().set(leftPower);
        drivetrain.getRight().getMain().set(rightPower);
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
