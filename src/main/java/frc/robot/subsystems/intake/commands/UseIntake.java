package frc.robot.subsystems.intake.commands;

import com.ctre.phoenix.motorcontrol.ControlMode;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.intake.Intake;


public class UseIntake extends CommandBase {
    private Intake intake;
    private double intakePower;

    public UseIntake(Intake intake, double intakePower){
        this.intakePower = intakePower;
        addRequirements(intake);
        this.intake = intake;
    }

    @Override
    public void execute(){
        intake.getIntakeMotor().set(ControlMode.PercentOutput, intakePower);
        intake.getTransferMotor().set(ControlMode.PercentOutput, intakePower);
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
