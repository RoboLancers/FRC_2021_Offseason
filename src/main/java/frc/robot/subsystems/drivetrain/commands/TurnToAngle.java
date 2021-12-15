package frc.robot.subsystems.drivetrain.commands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj.controller.ProfiledPIDController;
import edu.wpi.first.wpilibj.trajectory.TrapezoidProfile;
import edu.wpi.first.wpilibj2.command.ProfiledPIDCommand;
import frc.robot.subsystems.drivetrain.Drivetrain;
import frc.robot.subsystems.misc.Gyro;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class TurnToAngle extends ProfiledPIDCommand {
    // private enum PID {
    //     P(0.0),
    //     I(0.0),
    //     D(0.0);

    //     private double tunedConstant;

    //     PID(double tunedConstant){
    //         this.tunedConstant = tunedConstant;
    //     }
    // }

    // PID constants
    public static double P = 0.0015;
    public static double I = 0.00;
    public static double D = 0.0005;

    private static double maxTurnVelocityPerSecond = 2;// 76.3358778626;

    private static double maxTurnAccelerationPerSecondSquared = 19.1938579655;

    public TurnToAngle(DoubleSupplier targetAngle, Drivetrain drivetrain, Gyro gyro){
        super(
            new ProfiledPIDController(
                // PID.P.tunedConstant,
                // PID.I.tunedConstant,
                // PID.D.tunedConstant,
                TurnToAngle.P,
                TurnToAngle.I,
                TurnToAngle.D,
                new TrapezoidProfile.Constraints(
                    TurnToAngle.maxTurnVelocityPerSecond,
                    TurnToAngle.maxTurnAccelerationPerSecondSquared
                )
            ),
            () -> {
                SmartDashboard.putNumber("heading", gyro.getYaw());
                return gyro.getYaw();
            },
            targetAngle,
            (output, setpoint) -> {
                SmartDashboard.putNumber("output", output);
                drivetrain.getLeftMainMotor().set(output.doubleValue());
                drivetrain.getRightMainMotor().set(-output.doubleValue());
            },
            drivetrain
        );
        this.getController().enableContinuousInput(-180, 180);
        this.getController().setTolerance(1.0, 1.0);
    }

    @Override
    public void end(boolean interrupted){
        SmartDashboard.putBoolean("running turn to angle", false);
    }

    @Override
    public boolean isFinished(){
        SmartDashboard.putBoolean("running turn to angle", true);
        return this.getController().atGoal();
    }
}
