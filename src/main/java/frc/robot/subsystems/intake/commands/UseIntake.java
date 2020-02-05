package frc.robot.subsystems.intake.commands;

import com.ctre.phoenix.motorcontrol.ControlMode;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.intake.Intake;


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
