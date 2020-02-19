package frc.robot.subsystems.climber.commands;

import com.ctre.phoenix.motorcontrol.ControlMode;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.Subsystem;
import frc.robot.RobotContainer;
import frc.robot.subsystems.climber.HookUp;
import frc.robot.subsystems.climber.PullUp;
import frc.robot.utilities.XboxController;

//using 775 pro
/**YOU HAVE AN UNUSED CLASS VARIABLE*/
public class UseHookUp extends CommandBase {
    private HookUp hookUp;
    private double hookPower;

    public UseHookUp(){
        hookUp = RobotContainer.hookUp;
        addRequirements(hookUp);
    }

    @Override
    public void execute() {
        hookPower = RobotContainer.manipulatorXboxController.getAxisValue(XboxController.Axis.LEFT_Y);
        hookUp.getHookUpMotor().set(ControlMode.PercentOutput, hookPower);
    }

    @Override
    public void end(boolean interrupted) {
        hookUp.getHookUpMotor().set(ControlMode.PercentOutput, 0);
    }
}
