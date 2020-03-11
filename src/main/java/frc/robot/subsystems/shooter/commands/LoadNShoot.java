package frc.robot.subsystems.shooter.commands;

import com.ctre.phoenix.motorcontrol.ControlMode;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.subsystems.intake.Intake;
import frc.robot.subsystems.misc.IRSensor;
import frc.robot.subsystems.shooter.Loader;
import frc.robot.subsystems.shooter.Shooter;

public class LoadNShoot extends CommandBase {
    private final Loader loader;
    private final Intake intake;
    private IRSensor irSensor;

    public LoadNShoot(Loader loader, Intake intake, IRSensor irSensor) {
        this.loader = loader;
        this.intake = intake;
        this.irSensor = irSensor;

        addRequirements(loader, intake);
    }

    @Override
    public void initialize() {
        loader.getTimer().reset();
    }

    @Override
    public void execute() {
        loader.getLoaderMotor().set(ControlMode.PercentOutput, 0.6);
//        if(!irSensor.getIRFour()){
//            intake.getTransferMotor().set(ControlMode.PercentOutput, 0);
//            intake.getIntakeMotor().set(ControlMode.PercentOutput, 0);
//        } else {
        intake.getTransferMotor().set(ControlMode.PercentOutput, 0.3);
        intake.getIntakeMotor().set(ControlMode.PercentOutput, 0.3);
//        }

        if (!irSensor.getIRFour()) {
            loader.getTimer().start();
        }
    }

    @Override
    public boolean isFinished() {
        return !irSensor.getIRFour() && loader.getTimer().hasPeriodPassed(3);
    }

    @Override
    public void end(boolean interrupted) {
        loader.getLoaderMotor().set(ControlMode.PercentOutput, 0);
        intake.getIntakeMotor().set(ControlMode.PercentOutput, 0);
        intake.getTransferMotor().set(ControlMode.PercentOutput, 0);
    }
}
