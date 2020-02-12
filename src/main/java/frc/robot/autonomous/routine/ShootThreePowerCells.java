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
import frc.robot.subsystems.shooter.commands.UseShooter;

public class ShootThreePowerCells extends SequentialCommandGroup {

    public ShootThreePowerCells(Drivetrain drivetrain, Shooter shooter, Intake intake, Odometry odometry, Limelight limelight) {
        new Autonomous(odometry, drivetrain, RobotContainer.trajectories.backward());
        new ParallelCommandGroup(
                //reving up the shooter to get ready to shoot
                new AutoTargetAiming(drivetrain, limelight)
        );
        new UseShooter(shooter, intake, 468145685);
    }

}
