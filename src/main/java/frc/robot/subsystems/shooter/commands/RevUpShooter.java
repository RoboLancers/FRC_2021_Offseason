package frc.robot.subsystems.shooter.commands;

import com.ctre.phoenix.motorcontrol.ControlMode;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.shooter.Shooter;

/**
 * SHOULD NOT REQUIRE A TARGET RPM
 */
public class RevUpShooter extends CommandBase {
    private final Shooter shooter;
    private final PIDController pidController;
    private double targetRPM;

    public RevUpShooter(Shooter shooter, double targetRPM) {
        this.shooter = shooter;
        this.targetRPM = targetRPM;
        this.pidController = shooter.getPidController();

        addRequirements(shooter);
    }

    @Override
    public void initialize() {
        pidController.reset();
        pidController.setSetpoint(targetRPM);
    }

    @Override
    public void execute() {
        shooter.getMaster().set(ControlMode.PercentOutput, pidController.calculate(shooter.getMaster().getSelectedSensorVelocity() * Constants.Shooter.CONVERSION_BOY));
//        if (shooter.getMaster().getSelectedSensorVelocity() * Constants.Shooter.CONVERSION_BOY < targetRPM) {
//            shooter.getMaster().set(ControlMode.PercentOutput, 1);
//        } else {
//            shooter.getMaster().set(ControlMode.PercentOutput, 0.5);
//        }

//    @Override
//    public boolean isFinished(){
////        return pidController.atSetpoint();
//        return false;
//    }
//
//    @Override
//    public void end(boolean interrupted) {
//        shooter.getMaster().set(ControlMode.PercentOutput, 0);
//    }
    }
}
