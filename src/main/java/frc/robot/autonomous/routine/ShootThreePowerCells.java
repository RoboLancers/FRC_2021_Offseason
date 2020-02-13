package frc.robot.autonomous.routine;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.RobotContainer;
import frc.robot.autonomous.Autonomous;
import frc.robot.autonomous.Odometry;
import frc.robot.subsystems.drivetrain.Drivetrain;
import frc.robot.autonomous.AutoTargetAiming;
import frc.robot.subsystems.intake.Intake;
import frc.robot.subsystems.misc.Limelight;
import frc.robot.subsystems.shooter.Shooter;
import frc.robot.subsystems.shooter.commands.LoadNShoot;
import frc.robot.subsystems.shooter.commands.RevUpShooter;

public class ShootThreePowerCells extends SequentialCommandGroup {
    public ShootThreePowerCells(Drivetrain drivetrain, Shooter shooter, Intake intake, Odometry odometry, Limelight limelight) {
        new Autonomous(odometry, drivetrain, RobotContainer.trajectories.backward());
        new ParallelCommandGroup(
                new RevUpShooter(shooter, 54316351),
                new AutoTargetAiming(drivetrain, limelight)
        );
        new LoadNShoot(shooter, intake);
    }

}
