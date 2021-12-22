package frc.robot.autonomous.routine;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.misc.Gyro;
import frc.robot.subsystems.misc.Limelight;
import frc.robot.autonomous.commands.SeekTargetDistance;
import frc.robot.autonomous.commands.SeekTargetHeader;
import frc.robot.subsystems.drivetrain.Drivetrain;
import frc.robot.subsystems.drivetrain.commands.TurnToAngle;

public class FullAutoShoot extends SequentialCommandGroup {
    // Fully aims heading toward target, sets rpm using target, then shoots the ball
    public FullAutoShoot(Gyro gyro, Limelight limelight, Drivetrain drivetrain){
        addCommands(
            //new SeekTargetHeader(limelight, drivetrain),
            new TurnToAngle((gyro.getYaw() + 30) % 360 - 180, drivetrain, gyro) //,
            // new SeekTargetDistance(limelight, drivetrain)
        );
    }
}
