package frc.robot.subsystems.shooter.commands;

import com.ctre.phoenix.motorcontrol.ControlMode;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.subsystems.intake.Intake;
import frc.robot.subsystems.misc.IRSensor;
import frc.robot.subsystems.shooter.Loader;
import frc.robot.subsystems.shooter.Shooter;

public class NewShoot extends Commandbase {
    private final Loader loader;
    private final Intake intake;
    private IRSensor irSensor;

    public NewShoot (Loader loader, Intake intake, IRSensor irSensor) {
        this.loader = loader;
        this.intake = intake;
        this.irSensor = irSensor;

        addRequirements(loader, intake);
    }

    @Override
    public void execute() {
        loader.getLoaderMotor().set(ControlMode.PercentOutput, 0.6);
        intake.getIntakeMotor().set(ControlMode.PercentOutput, 0.3);
        intake.getTransferMotor().set(ControlMode.PercentOutput, 0.3);
    }

    @Override
    public void end() {
        loader.getLoaderMotor().set(ControlMode.PercentOutput, 0);
        intake.getIntakeMotor().set(ControlMode.PercentOutput, 0);
        intake.getTransferMotor().set(ControlMode.PercentOutput, 0);
    }

    @Override
    public boolean isFinished() {
        return true;
    }

} 