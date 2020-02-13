package frc.robot.subsystems.climber.commands;

import com.ctre.phoenix.motorcontrol.ControlMode;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.climber.Climber;
import frc.robot.utilities.XboxController;

//using 775 pro
/**YOU HAVE AN UNUSED CLASS VARIABLE*/
public class HookUp extends CommandBase {
    private Climber climber;
    private double hookPower;

    public HookUp(){
        addRequirements(RobotContainer.climber);
    }

    @Override
    public void execute() {
        hookPower = RobotContainer.manipulatorXboxController.getAxisValue(XboxController.Axis.LEFT_Y);

        climber.getHookUpMotor().set(ControlMode.PercentOutput, hookPower);
    }
}
