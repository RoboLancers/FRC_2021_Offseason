package frc.robot.commands;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.Subsystem;
import frc.robot.RobotContainer;
import frc.robot.subsystems.Drivetrain;

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

        if(Math.abs(RobotContainer.xboxController.getY(GenericHID.Hand.kLeft)) > 0.2) {
            throttle = RobotContainer.xboxController.getY(GenericHID.Hand.kLeft);
        } else {
            throttle = 0;
        }

        if(Math.abs(RobotContainer.xboxController.getX(GenericHID.Hand.kRight)) > 0.2) {
            turn = RobotContainer.xboxController.getX(GenericHID.Hand.kRight);
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
