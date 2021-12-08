package frc.robot.autonomous.routine;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.autonomous.AimHeadingTarget;
import frc.robot.autonomous.RevUsingTarget;
import frc.robot.subsystems.drivetrain.Drivetrain;
import frc.robot.subsystems.intake.Intake;
import frc.robot.subsystems.misc.IRSensor;
import frc.robot.subsystems.misc.Limelight;
import frc.robot.subsystems.shooter.Loader;
import frc.robot.subsystems.shooter.Shooter;
import frc.robot.subsystems.shooter.commands.LoadNShoot;
import frc.robot.subsystems.shooter.commands.RevUpShooter;

public class FullAutoShoot extends SequentialCommandGroup {
    // Fully aims heading toward target, sets rpm using target, then shoots the ball
    public FullAutoShoot(Limelight limelight, Drivetrain drivetrain, Shooter shooter, Loader loader, Intake intake, IRSensor irSensor){
        addCommands(
            new AimHeadingTarget(limelight, drivetrain),
            new RevUsingTarget(limelight, drivetrain, shooter),
            new LoadNShoot(loader, intake, irSensor),
            new WaitCommand(2),
            new RevUpShooter(shooter, 0)
        );
    }
}
