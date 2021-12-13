package frc.robot.autonomous.routine;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.misc.Limelight;
import frc.robot.autonomous.commands.AimHeading;
import frc.robot.autonomous.commands.RevShooter;
import frc.robot.subsystems.drivetrain.Drivetrain;
import frc.robot.subsystems.shooter.Shooter;

public class FullAutoShoot extends SequentialCommandGroup {
    // Fully aims heading toward target, sets rpm using target, then shoots the ball
    public FullAutoShoot(Limelight limelight, Drivetrain drivetrain, Shooter shooter){
        addCommands(
            new AimHeading(limelight, drivetrain),
            new RevShooter(limelight, drivetrain, shooter)
            // ! set shooter rpm to 0 after shot
        );
    }
}
