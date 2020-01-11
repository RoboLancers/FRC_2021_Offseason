package frc.robot.commands;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Subsystem;
import frc.robot.Drivetrain;
import frc.robot.OI;

import java.util.Set;

public class UseDrivetrain implements Command {
    private Drivetrain instance;

    public UseDrivetrain() {
        this.instance = Drivetrain.getInstance();
    }

    @Override
    public void execute() {
        double throttle = OI.getXboxController().getY(GenericHID.Hand.kLeft);
        double turn = OI.getXboxController().getX(GenericHID.Hand.kRight);

        double leftPower = throttle - turn;
        double rightPower = throttle + turn;

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
