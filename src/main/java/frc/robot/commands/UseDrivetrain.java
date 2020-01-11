package frc.robot.commands;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Subsystem;
import frc.robot.subsystems.Drivetrain;
import frc.robot.OI;

import java.util.Set;

public class UseDrivetrain implements Command {
    double throttle, turn, leftPower, rightPower;

    public UseDrivetrain() {
        hasRequirement(Drivetrain.getInstance());
    }

    @Override
    public void execute() {
        throttle = OI.getXboxController().getY(GenericHID.Hand.kLeft);
        turn = OI.getXboxController().getX(GenericHID.Hand.kRight);

        leftPower = throttle - turn;
        rightPower = throttle + turn;

        Drivetrain.getInstance().getLeftMasterMotor().set(leftPower);
        Drivetrain.getInstance().getRightMasterMotor().set(rightPower);
    }
    @Override
    public boolean isFinished() {
        return false;
    }
    @Override
    public Set<Subsystem> getRequirements() {
        return null;
    }
}
