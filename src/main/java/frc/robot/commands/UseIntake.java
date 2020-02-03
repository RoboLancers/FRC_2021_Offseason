package frc.robot.commands;

import com.ctre.phoenix.motorcontrol.ControlMode;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.Subsystem;
import frc.robot.RobotContainer;
import frc.robot.subsystems.manipulators.intake.Intake;


public class UseIntake extends CommandBase {
    private final Intake intake = RobotContainer.intake;
    public UseIntake(){
        addRequirements(RobotContainer.intake);
    }

    @Override
    public void execute(){
        intake.getIntakeMotor().set(ControlMode.PercentOutput, 0.7);
        intake.getTransferMotor().set(ControlMode.PercentOutput, 0.7);
        }

    @Override
    public boolean isFinished(){
        return false;
    }

}
