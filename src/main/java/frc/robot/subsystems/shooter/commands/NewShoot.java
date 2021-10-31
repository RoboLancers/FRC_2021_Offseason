 package frc.robot.subsystems.shooter.commands;

 import com.revrobotics.ControlType;
 import com.ctre.phoenix.motorcontrol.ControlMode;
 import edu.wpi.first.wpilibj.Timer;
 import edu.wpi.first.wpilibj2.command.CommandBase;
 import edu.wpi.first.wpilibj2.command.WaitCommand;
 import frc.robot.subsystems.intake.Intake;
 import frc.robot.subsystems.misc.IRSensor;
 import frc.robot.subsystems.shooter.Loader;
 import frc.robot.subsystems.shooter.Shooter;

 public class NewShoot extends CommandBase {
     private final Loader loader;
     private final Intake intake;
     double power;

     public NewShoot (Loader loader, Intake intake, double power) {
        this.loader = loader;
        this.intake = intake;
        this.power = power;
        addRequirements(loader, intake);
     }

     @Override
     public void execute() {
         loader.getLoaderMotor().set(ControlMode.PercentOutput, power);
         intake.getIntakeMotor().set(ControlMode.PercentOutput, power/2);
         intake.getTransferMotor().set(ControlMode.PercentOutput, power/2);
     }

     @Override
     public boolean isFinished() {
         return true;
     }
 } 