package frc.robot.subsystems.climber.commands;

import com.ctre.phoenix.motorcontrol.ControlMode;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;
import frc.robot.subsystems.climber.Hooker;
import frc.robot.utilities.XboxController;

//using 775 pro
/**YOU HAVE AN UNUSED CLASS VARIABLE*/
public class HookUp extends CommandBase {
    private Hooker hooker;

    public HookUp(Hooker hooker){
        addRequirements(hooker);
        this.hooker = hooker;
    }

    @Override
    public void execute() {
//        DriverStation.getInstance().getMatchTime();
        double power = RobotContainer.manipulatorXboxController.getAxisValue(XboxController.Axis.LEFT_Y) / 2;
        if (power >= 0) {
            hooker.set(power);
        } else if (power <= 0){
            hooker.set(power / 2);
        }
    }
}
