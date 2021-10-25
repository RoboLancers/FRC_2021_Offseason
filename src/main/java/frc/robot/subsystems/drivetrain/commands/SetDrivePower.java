package frc.robot.subsystems.drivetrain.commands;
 
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.drivetrain.Drivetrain;
 
public class SetDrivePower extends CommandBase{
    private final Drivetrain drivetrain;
    private final double power;
 
    public SetDrivePower(Drivetrain subsystem, double power) {
        this.drivetrain = subsystem;
        this.power = power;
        addRequirements(drivetrain);
    }
 
    @Override
    public void execute(){
        drivetrain.getLeft().getMain().set(power);
        drivetrain.getRight().getMain().set(power);
    }
 
    @Override
    public boolean isFinished(){
      return true;
    }
}