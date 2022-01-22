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
//     private static double minTurn = 0.05;
//     private static double maxTurn = 0.65;
//     private static double threshold = 1;
//     private static double kP = 0.008;
//     private static double kD = 0.045;

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


public class TurnToAngle extends PIDCommand {
    public static double P = 0.036;
    public static double I = 0.00;
    public static double D = 0.011;

    private static double maxAbsoluteDegreesError = 1.0;

    public TurnToAngle(double targetAngle, Drivetrain drivetrain, Gyro gyro){
        super(
            new PIDController(
                TurnToAngle.P,
                TurnToAngle.I,
                TurnToAngle.D
            ),
            () -> {
                SmartDashboard.putNumber("GYRO", gyro.getYaw() % 360);
                return (gyro.getYaw() % 360);
            },
            () -> targetAngle,
            (output) -> {
                drivetrain.getLeftMainMotor().set(-output);
                drivetrain.getRightMainMotor().set(output);
            },
            drivetrain
        );
        this.getController().enableContinuousInput(0, 360);
        this.getController().setTolerance(maxAbsoluteDegreesError);
    }

    @Override
    public void end(boolean interrupted){
    }

    @Override
    public boolean isFinished(){
        return this.getController().atSetpoint();
    }
}
