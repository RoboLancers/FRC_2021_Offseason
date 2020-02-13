package frc.robot.autonomous;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.drivetrain.Drivetrain;
import frc.robot.subsystems.intake.Intake;
import frc.robot.subsystems.misc.Limelight;
import frc.robot.subsystems.shooter.Shooter;

public class Autonomous {
    private Command autonomousCommand;

    private Drivetrain drivetrain;
    private Shooter shooter;
    private Intake intake;
    private Odometry odometry;
    private Limelight limelight;

    public Autonomous(Drivetrain drivetrain, Shooter shooter, Intake intake, Odometry odometry, Limelight limelight) {
        this.drivetrain = drivetrain;
        this.shooter = shooter;
        this.intake = intake;
        this.odometry = odometry;
        this.limelight = limelight;
    }

    public void update() {

    }
}
