package frc.robot.subsystems.shooter.commands;

import com.ctre.phoenix.motorcontrol.ControlMode;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.intake.Intake;
import frc.robot.subsystems.shooter.Loader;
import frc.robot.subsystems.shooter.Shooter;

public class LoadNShoot extends CommandBase {
    private final Loader loader;
    private final Intake intake;

    public LoadNShoot(Loader loader, Intake intake){
        this.loader = loader;
        this.intake = intake;

        addRequirements(loader, intake);
    }

    @Override
    public void execute(){
        loader.getLoaderMotor().set(ControlMode.PercentOutput, 0.7);
//        intake.getTransferMotor().set(ControlMode.PercentOutput, 0.7);
//        intake.getIntakeMotor().set(ControlMode.PercentOutput, 0.7);
    }

    @Override
    public void end(boolean interrupted) {
        loader.getLoaderMotor().set(ControlMode.PercentOutput, 0);
        intake.getIntakeMotor().set(ControlMode.PercentOutput, 0);
        intake.getTransferMotor().set(ControlMode.PercentOutput, 0);
    }
}
