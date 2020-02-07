package frc.robot.subsystems.drivetrain.commands;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.Subsystem;
import frc.robot.RobotContainer;
import frc.robot.subsystems.drivetrain.Drivetrain;
import frc.robot.utilities.XboxController;

import java.util.HashSet;
import java.util.Set;

public class UseDrivetrain extends CommandBase {
    Set<Subsystem> subsystems;
    private final Drivetrain drivetrain;
    double throttle, turn, leftPower, rightPower;

    public UseDrivetrain() {
        subsystems = new HashSet<>();
        drivetrain = RobotContainer.drivetrain;
        subsystems.add(drivetrain);
        addRequirements(drivetrain);
    }

    @Override
    public void execute() {

        if(Math.abs(RobotContainer.xboxController.getAxisValue(XboxController.Axis.LEFT_Y)) > 0.2) {
            throttle = RobotContainer.xboxController.getAxisValue(XboxController.Axis.LEFT_Y);
        } else {
            throttle = 0;
        }

        if(Math.abs(RobotContainer.xboxController.getAxisValue(XboxController.Axis.RIGHT_X)) > 0.2) {
            turn = RobotContainer.xboxController.getAxisValue(XboxController.Axis.RIGHT_X);
        } else {
            turn = 0;
        }

        leftPower = throttle - turn;
        rightPower = throttle + turn;

        drivetrain.getLeft().getMaster().set(leftPower);
        drivetrain.getRight().getMaster().set(rightPower);
    }
    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public Set<Subsystem> getRequirements() {
        return subsystems;
    }
}
