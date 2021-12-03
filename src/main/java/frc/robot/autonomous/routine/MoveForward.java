package frc.robot.autonomous.routine;
 
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.autonomous.*;
import frc.robot.autonomous.enums.StartingPosition;
import frc.robot.subsystems.drivetrain.Drivetrain;
import frc.robot.subsystems.drivetrain.commands.SetDrivePower;
import frc.robot.subsystems.drivetrain.commands.UseDrivetrain;
import frc.robot.subsystems.intake.Intake;
import frc.robot.subsystems.misc.Gyro;
import frc.robot.subsystems.misc.IRSensor;
import frc.robot.subsystems.misc.Limelight;
import frc.robot.utilities.XboxController;
 
public class MoveForward extends SequentialCommandGroup {
    public MoveForward(Drivetrain drivetrain, Gyro gyro, IRSensor irSensor, Intake intake, Odometry odometry, Limelight limelight, StartingPosition startingPosition, Trajectories trajectories, XboxController xboxController) {
        addCommands(new SetDrivePower(drivetrain, 0.5));
        addCommands(new WaitCommand(0.69));
        addCommands(new SetDrivePower(drivetrain, 0));  
    }
}
