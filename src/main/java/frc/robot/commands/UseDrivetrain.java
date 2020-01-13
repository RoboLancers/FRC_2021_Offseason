package frc.robot.commands;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Subsystem;
import frc.robot.RobotContainer;
import frc.robot.subsystems.Drivetrain;
import frc.robot.OI;

import java.util.Set;

public class UseDrivetrain implements Command {
    private Set<Subsystem> subsystems;
    private final Drivetrain drivetrain;
    double throttle, turn, leftPower, rightPower;

    public UseDrivetrain(Drivetrain drivetrain) {
        this.drivetrain = drivetrain;
        subsystems.add(RobotContainer.drivetrain);
    }

    @Override
    public void execute() {
        throttle = OI.getXboxController().getY(GenericHID.Hand.kLeft);
        turn = OI.getXboxController().getX(GenericHID.Hand.kRight);

        leftPower = throttle - turn;
        rightPower = throttle + turn;

        drivetrain.getLeftMasterMotor().set(leftPower);
        drivetrain.getRightMasterMotor().set(rightPower);
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
