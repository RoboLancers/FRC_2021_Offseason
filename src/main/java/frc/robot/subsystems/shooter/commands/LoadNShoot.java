package frc.robot.subsystems.shooter.commands;

import com.ctre.phoenix.motorcontrol.ControlMode;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.intake.Intake;
import frc.robot.subsystems.shooter.Shooter;

public class LoadNShoot extends CommandBase {
    private final Shooter shooter;
    private final Intake intake;

    public LoadNShoot(Shooter shooter, Intake intake){
        this.shooter = shooter;
        this.intake = intake;
        addRequirements(shooter, intake);
    }

    @Override
    public void execute(){
        shooter.getLoaderMotor().set(ControlMode.PercentOutput, 0.7777);
        intake.getTransferMotor().set(ControlMode.PercentOutput, 0.7777);
        intake.getIntakeMotor().set(ControlMode.PercentOutput, 0.7777);
    }
}
