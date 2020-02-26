package frc.robot.subsystems.shooter.commands;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj2.command.CommandBase;
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
        shooter.getMaster().set(pidController.calculate(shooter.getMaster().getEncoder().getVelocity()));

//    @Override
//    public boolean isFinished(){
////        return pidController.atSetpoint();
//        return false;
    }
//
    @Override
    public void end(boolean interrupted) {
        shooter.getMaster().set(0);
    }
}
