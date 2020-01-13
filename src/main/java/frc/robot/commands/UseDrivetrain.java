package frc.robot.commands;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Subsystem;
import frc.robot.RobotContainer;
import frc.robot.subsystems.Drivetrain;

import java.util.HashSet;
import java.util.Set;

public class UseDrivetrain implements Command {
    Set<Subsystem> subsystems;
    private final Drivetrain drivetrain;
    double throttle, turn, leftPower, rightPower;

    public UseDrivetrain() {
        subsystems = new HashSet<Subsystem>();
        drivetrain = RobotContainer.drivetrain;
        subsystems.add(RobotContainer.drivetrain);
    }

    @Override
    public void execute() {
        throttle = RobotContainer.xboxController.getY(GenericHID.Hand.kLeft);
        turn = RobotContainer.xboxController.getX(GenericHID.Hand.kRight);

        leftPower = throttle - turn;
        rightPower = throttle + turn;

        drivetrain.getLeftTransmission().getMaster().set(leftPower);
        drivetrain.getRightTransmission().getMaster().set(rightPower);
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
