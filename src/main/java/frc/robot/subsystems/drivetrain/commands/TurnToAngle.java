package frc.robot.subsystems.drivetrain.commands;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.controller.ProfiledPIDController;
import edu.wpi.first.wpilibj.trajectory.TrapezoidProfile;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.PIDCommand;
import edu.wpi.first.wpilibj2.command.ProfiledPIDCommand;
import frc.robot.subsystems.drivetrain.Drivetrain;
import frc.robot.subsystems.misc.Gyro;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

// public class TurnToAngle extends CommandBase {
//     private static double minTurn = 0.1;
//     private static double maxTurn = 0.5;
//     private static double threshold = 1;
//     private static double kP = 0.004;
//     private static double kD = 0.03;

//     private Drivetrain drivetrain;
//     private Gyro gyro;

//     private double targetAngle;
//     private double lastAngle;

//     public TurnToAngle(double targetAngle, Drivetrain drivetrain, Gyro gyro){
//         this.targetAngle = targetAngle;
//         this.drivetrain = drivetrain;
//         this.gyro = gyro;
//         lastAngle = gyro.getYaw();
//     }

//     @Override
//     public void execute(){
//         double yaw = this.gyro.getYaw() % 360;
//         double error = (targetAngle - yaw) % 360;
//         if(error > 180){
//             error -= 360;
//         }
//         double deltaAngle = yaw - lastAngle;
//         lastAngle = yaw;
//         double output = error * TurnToAngle.kP - deltaAngle * TurnToAngle.kD;
//         double outputDirection = output < 0 ? -1 : 1;
//         double outputMagnitude = Math.abs(output);
//         double clampedOutput = Math.min(Math.max(outputMagnitude, TurnToAngle.minTurn), TurnToAngle.maxTurn);
//         this.drivetrain.getLeftMainMotor().set(-outputDirection * clampedOutput);
//         this.drivetrain.getRightMainMotor().set(outputDirection * clampedOutput);

//         SmartDashboard.putNumber("GYRO", yaw);
//         SmartDashboard.putNumber("ERROR", error);
//         SmartDashboard.putNumber("OUTPUT", clampedOutput * outputDirection);
//     }

//     @Override
//     public void end(boolean interrupted){
//         this.drivetrain.getLeftMainMotor().set(0);
//         this.drivetrain.getRightMainMotor().set(0);
//     }

//     @Override
//     public boolean isFinished(){
//         double yaw = this.gyro.getYaw() % 360;
//         double error = (targetAngle - yaw) % 360;
//         if(error > 180){
//             error -= 360;
//         }
//         return Math.abs(error) < TurnToAngle.threshold;
//     }
// }


public class TurnToAngle extends ProfiledPIDCommand {
    public static double P = 0.004;
    public static double I = 0.00;
    public static double D = 0.03;

    private static double maxTurnVelocityPerSecond = 2;
    private static double maxTurnAccelerationPerSecondSquared = 19.1938579655;

    private static double maxAbsoluteDegreesError = 2.0;

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
                double yaw = gyro.getYaw() % 360;
                double error = (targetAngle - yaw) % 360;
                if(error > 180){
                    error -= 360;
                }
                SmartDashboard.putNumber("GYRO", yaw);
                return error;
            },
            () -> 0,
            (output, setpoint) -> {
                int direction = output.intValue() < 0 ? -1 : 1;
                double magnitude = Math.min(Math.max(Math.abs(output.doubleValue()), 0.05), 0.5);
                SmartDashboard.putNumber("DELTA", magnitude);
                drivetrain.getLeftMainMotor().set(-direction * magnitude);
                drivetrain.getRightMainMotor().set(direction * magnitude);
            },
            drivetrain
        );
        this.getController().setTolerance(maxAbsoluteDegreesError);
    }

    @Override
    public void end(boolean interrupted){
    }

    @Override
    public boolean isFinished(){
        return this.getController().atGoal();
    }
}
