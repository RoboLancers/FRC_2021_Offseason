package frc.robot.autonomous.routine;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.autonomous.*;
import frc.robot.autonomous.enums.StartingPosition;
import frc.robot.subsystems.drivetrain.Drivetrain;
import frc.robot.subsystems.drivetrain.commands.SetDrivePower;
import frc.robot.subsystems.intake.Intake;
import frc.robot.subsystems.misc.Gyro;
import frc.robot.subsystems.misc.IRSensor;
import frc.robot.subsystems.misc.Limelight;
import frc.robot.subsystems.shooter.Loader;
import frc.robot.subsystems.shooter.Shooter;
import frc.robot.subsystems.shooter.commands.LoadNShoot;
import frc.robot.subsystems.shooter.commands.RevUpShooter;


public class NewShootBall extends SequentialCommandGroup {
    public NewShootBall (Drivetrain drivetrain, Loader loader, Shooter shooter, IRSensor irSensor, Intake intake) {
        addCommands(new RevUpShooter(shooter, 3000));
        addCommands(new WaitCommand(2.0));
        addCommands(new LoadNShoot(loader, intake, irSensor));
        addCommands(new WaitCommand(1.0));
        addCommands(new RevUpShooter(shooter, 0));
       
        addCommands(new SetDrivePower(drivetrain, -0.5));
        addCommands(new WaitCommand(1.0));
        addCommands(new SetDrivePower(drivetrain, 0));  

        
    
    
    }   
}
