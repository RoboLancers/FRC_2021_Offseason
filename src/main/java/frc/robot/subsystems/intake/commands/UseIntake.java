package frc.robot.subsystems.intake.commands;

import com.ctre.phoenix.motorcontrol.ControlMode;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;
import frc.robot.subsystems.intake.Intake;


public class UseIntake extends CommandBase {
    private Intake intake;

    public UseIntake(){
        addRequirements(RobotContainer.intake);
        intake = RobotContainer.intake;
    }

    @Override
    public void execute(){
        intake.getIntakeMotor().set(ControlMode.PercentOutput, Constants.Intake.INTAKE_POWER);
        intake.getTransferMotor().set(ControlMode.PercentOutput, Constants.Intake.INTAKE_POWER);

        if(intake.spiked()){
            intake.getIntakeMotor().set(ControlMode.PercentOutput, -Constants.Intake.INTAKE_POWER);
            intake.getTransferMotor().set(ControlMode.PercentOutput, -Constants.Intake.INTAKE_POWER);
        }
    }

    @Override
    public boolean isFinished(){
        return false;
    }

    @Override
    public void end(boolean interrupted) {
        intake.getIntakeMotor().set(ControlMode.PercentOutput, 0);
        intake.getTransferMotor().set(ControlMode.PercentOutput, 0);
    }
}
