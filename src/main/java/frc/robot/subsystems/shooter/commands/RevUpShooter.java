package frc.robot.subsystems.shooter.commands;

import com.ctre.phoenix.motorcontrol.ControlMode;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.intake.Intake;
import frc.robot.subsystems.shooter.Shooter;

public class RevUpShooter extends CommandBase {
    private final Shooter shooter;
    private final Intake intake;
    private final PIDController pidController;
    private double targetRPM;

    public RevUpShooter(Shooter shooter, Intake intake, PIDController pidController, double targetRPM){
        this.shooter = shooter;
        this.intake = intake;
        this.pidController = pidController;
        this.targetRPM = targetRPM;
        addRequirements(shooter, intake);
    }

    //make it so PID makes targetSpeed stay on target
    @Override
    public void initialize(){
        this.pidController.reset();
    }

    @Override
    public void execute(){
        shooter.setTargetRPM(targetRPM);
        if(!shooter.fastEnough()){
            shooter.setMotorToTarget();
        } else {
            shooter.getLoaderMotor(ControlMode.PercentOutput, 1);
            intake.getTransferMotor().set(ControlMode.PercentOutput, 1);
            intake.getIntakeMotor().set(ControlMode.PercentOutput, 1);
        }
    }
//ADD: IF FAST ENOUGH AND AIMED, THEN SHOOT
//SEPERATE INTO DIFF COMMANDS

    @Override
    public boolean isFinished(){
        return false;
    }





}
