package frc.robot.autonomous;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.autonomous.enums.StartingPosition;
import frc.robot.subsystems.drivetrain.Drivetrain;
import frc.robot.subsystems.misc.Gyro;

public class InitializeCommand extends InstantCommand {
    Drivetrain drivetrain;
    Odometry odometry;
    Gyro gyro;
    StartingPosition startingPosition;

    public InitializeCommand(Drivetrain drivetrain, Odometry odometry, Gyro gyro, StartingPosition startingPosition) {
        this.drivetrain = drivetrain;
        this.odometry = odometry;
        this.gyro = gyro;
        this.startingPosition = startingPosition;
    }

    @Override
    public void initialize() {
        drivetrain.getLeft().resetEncoder();
        drivetrain.getRight().resetEncoder();
        gyro.resetHeading();
        odometry.resetOdometry(startingPosition.getPose2d());
    }
}
