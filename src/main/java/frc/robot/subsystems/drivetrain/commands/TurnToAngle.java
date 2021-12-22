package frc.robot.subsystems.drivetrain.commands;

import edu.wpi.first.wpilibj.controller.ProfiledPIDController;
import edu.wpi.first.wpilibj.trajectory.TrapezoidProfile;
import edu.wpi.first.wpilibj2.command.ProfiledPIDCommand;
import frc.robot.subsystems.drivetrain.Drivetrain;
import frc.robot.subsystems.misc.Gyro;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class TurnToAngle extends ProfiledPIDCommand {
    public static double P = 0.0015;
    public static double I = 0.00;
    public static double D = 0.0005;

    private static double maxTurnVelocityPerSecond = 2;

    private static double maxTurnAccelerationPerSecondSquared = 19.1938579655;

    public TurnToAngle(double targetAngle, Drivetrain drivetrain, Gyro gyro){
        super(
            new ProfiledPIDController(
                TurnToAngle.P,
                TurnToAngle.I,
                TurnToAngle.D,
                new TrapezoidProfile.Constraints(
                    TurnToAngle.maxTurnVelocityPerSecond,
                    TurnToAngle.maxTurnAccelerationPerSecondSquared
                )
            ),
            () -> {
                SmartDashboard.putNumber("heading", gyro.getYaw() % 360 - 180);
                return gyro.getYaw();
            },
            () -> targetAngle,
            (output, setpoint) -> {
                SmartDashboard.putNumber("output", output);
                drivetrain.getLeftMainMotor().set(output.doubleValue());
                drivetrain.getRightMainMotor().set(-output.doubleValue());
            },
            drivetrain
        );
        this.getController().enableContinuousInput(-180, 180);
        this.getController().setTolerance(3.0, 3.0);
    }

    @Override
    public void end(boolean interrupted){
    }

    @Override
    public boolean isFinished(){
        return this.getController().atGoal();
    }
}
