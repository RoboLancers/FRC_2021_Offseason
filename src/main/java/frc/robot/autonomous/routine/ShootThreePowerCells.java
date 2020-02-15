package frc.robot.autonomous.routine;

import edu.wpi.first.wpilibj.trajectory.Trajectory;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.autonomous.AutoTargetAiming;
import frc.robot.autonomous.Odometry;
import frc.robot.autonomous.Ramsete;
import frc.robot.autonomous.enums.StartingPosition;
import frc.robot.subsystems.drivetrain.Drivetrain;
import frc.robot.subsystems.intake.Intake;
import frc.robot.subsystems.misc.Limelight;
import frc.robot.subsystems.shooter.Shooter;
import frc.robot.subsystems.shooter.commands.LoadNShoot;
import frc.robot.subsystems.shooter.commands.RevUpShooter;

public class ShootThreePowerCells extends SequentialCommandGroup {
    public ShootThreePowerCells(Drivetrain drivetrain, Shooter shooter, Intake intake, Odometry odometry, Limelight limelight, Trajectory trajectory, StartingPosition startingPosition) {
        new Ramsete(odometry, drivetrain, trajectory, startingPosition.getPose2d());
        new ParallelCommandGroup(
                new RevUpShooter(shooter, 54316351),
                new AutoTargetAiming(drivetrain, limelight)
        );
        new LoadNShoot(shooter, intake);
    }

}
