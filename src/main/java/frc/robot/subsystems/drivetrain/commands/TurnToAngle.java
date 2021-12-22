package frc.robot.subsystems.drivetrain.commands;

import edu.wpi.first.wpilibj.controller.ProfiledPIDController;
import edu.wpi.first.wpilibj.trajectory.TrapezoidProfile;
import edu.wpi.first.wpilibj2.command.ProfiledPIDCommand;
import frc.robot.subsystems.drivetrain.Drivetrain;
import frc.robot.subsystems.misc.Gyro;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class TurnToAngle extends ProfiledPIDCommand {
    public static double P = 0.001;
    public static double I = 0.00;
    public static double D = 0.00;

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
                double yaw = gyro.getYaw();
                SmartDashboard.putNumber("heading", Math.IEEERemainder(yaw, 360.0) - 180);
                SmartDashboard.putNumber("error", Math.IEEERemainder(targetAngle - yaw, 360.0) - 180);
                return Math.IEEERemainder(yaw, 360.0) - 180;
            },
            () -> targetAngle,
            (output, setpoint) -> {
                SmartDashboard.putNumber("output", output);
                drivetrain.getLeftMainMotor().set(Math.min(Math.abs(output.doubleValue()), 1.0) * Math.signum(output.doubleValue()));
                drivetrain.getRightMainMotor().set(-Math.min(Math.abs(output.doubleValue()), 1.0) * Math.signum(output.doubleValue()));
            },
            drivetrain
        );
        this.getController().enableContinuousInput(-180, 180);
        this.getController().setTolerance(-10.0, 10.0);
    }

    @Override
    public void end(boolean interrupted){
    }

    @Override
    public boolean isFinished(){
        return this.getController().atGoal();
    }
}
