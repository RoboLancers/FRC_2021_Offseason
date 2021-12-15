package frc.robot.subsystems.drivetrain.commands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj.controller.ProfiledPIDController;
import edu.wpi.first.wpilibj.trajectory.TrapezoidProfile;
import edu.wpi.first.wpilibj2.command.ProfiledPIDCommand;
import frc.robot.subsystems.drivetrain.Drivetrain;
import frc.robot.subsystems.misc.Gyro;

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
    public static double P = 0.0;
    public static double I = 0.0;
    public static double D = 0.0;

    private static double maxTurnVelocityPerSecond = 76.3358778626;

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
            () -> gyro.getYaw(),
            targetAngle,
            (output, setpoint) -> {
                drivetrain.getLeftMainMotor().set(output.doubleValue());
                drivetrain.getRightMainMotor().set(-output.doubleValue());
            },
            drivetrain
        );
    }

    @Override
    public boolean isFinished(){
        return this.getController().atGoal();
    }
}
