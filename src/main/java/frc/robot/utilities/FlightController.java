package frc.robot.utilities;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.POVButton;

@SuppressWarnings({"unused", "WeakerAccess"})
public class FlightController extends BaseController{
    public enum Axis {
        X(0),
        Y(1, true),

        TWIST(2),
        RUDDER(3);

        int port;
        int inverted;

        Axis(int port){
            this(port, false);
        }

        Axis(int port, boolean inverted){
            this.port = port;
            this.inverted = inverted ? -1 : 1;
        }
    }

    public enum Button {
        TRIGGER(1),
        THUMB(2),

        BOTTOM_LEFT(3),
        BOTTOM_RIGHT(4),

        TOP_LEFT(5),
        TOP_RIGHT(6),

        OUTER_TOP(7),
        INNER_TOP(8),
        OUTER_MIDDLE(9),
        INNER_MIDDLE(10),
        OUTER_BOTTOM(11),
        INNER_BOTTOM(12);

        int port;

        Button(int port){
            this.port = port;
        }
    }

    public enum POV {
        UP(0,0),
        UP_RIGHT(1,45),
        RIGHT(2,90),
        DOWN_RIGHT(3,135),
        DOWN(4,180),
        DOWN_LEFT(5,225),
        LEFT(6,270),
        UP_LEFT(7,315);

        int index;
        int angle;

        POV(int index, int angle){
            this.index = index;
            this.angle = angle;
        }
    }

    public enum Trigger {
        X_FORWARD(Axis.X.port),
        X_BACKWARD(Axis.X.port, true),

        Y_FORWARD(Axis.Y.port),
        Y_BACKWARD(Axis.Y.port, true),

        TWIST_RIGHT(Axis.TWIST.port),
        TWIST_LEFT(Axis.TWIST.port, true),

        RUDDER_FORWARD(Axis.RUDDER.port),
        RUDDER_BACKWARD(Axis.RUDDER.port, true);

        int port;
        boolean negative;

        Trigger(int port){
            this(port, false);
        }

        Trigger(int port, boolean negative){
            this.port = port;
            this.negative = negative;
        }
    }

    public FlightController(int port) {
        this(port, 0.2);
    }

    public FlightController(int port, double deadzone) {
        super(port);

        buttons = new JoystickButton[Button.values().length + 1];

        for (int i = 0; i < buttons.length; i++) {
            buttons[i] = new JoystickButton(joystick, i);
        }

        triggerButtons = new TriggerButton[Trigger.values().length];

        for(int i = 0; i < triggerButtons.length; i++){
            triggerButtons[i] = new TriggerButton(joystick, i, Trigger.values()[i].negative);
        }

        povButtons = new POVButton[POV.values().length];

        for(int i = 0; i < povButtons.length; i++){
            povButtons[i] = new POVButton(joystick, POV.values()[i].angle);
        }

        this.deadzone = deadzone;
    }

    public double getAxisValue(Axis axis){
        return Utilities.applyDeadband(axis.inverted * joystick.getRawAxis(axis.port), deadzone);
    }

    public boolean getState(Button button){
        return joystick.getRawButton(button.port);
    }

    public boolean getState(Trigger trigger){
        return triggerButtons[trigger.port].get();
    }

    public boolean getState(POV pov){
        return joystick.getPOV() == pov.angle;
    }

    public FlightController whileHeld(Trigger trigger, Command command){
        triggerButtons[trigger.port].whileActiveContinuous(command);
        return this;
    }

    public FlightController whileHeld(Button button, Command command){
        buttons[button.port].whileHeld(command);
        return this;
    }

    public FlightController whileHeld(POV pov, Command command){
        povButtons[pov.index].whileHeld(command);
        return this;
    }

    public FlightController whenPressed(Trigger trigger, Command command){
        triggerButtons[trigger.port].whenActive(command);
        return this;
    }

    public FlightController whenPressed(Button button, Command command){
        buttons[button.port].whenPressed(command);
        return this;
    }

    public FlightController whenPressed(POV pov, Command command){
        povButtons[pov.index].whenPressed(command);
        return this;
    }

    public FlightController whenReleased(Trigger trigger, Command command){
        triggerButtons[trigger.port].whenInactive(command);
        return this;
    }

    public FlightController whenReleased(Button button, Command command){
        buttons[button.port].whenReleased(command);
        return this;
    }

    public FlightController whenReleased(POV pov, Command command){
        povButtons[pov.index].whenReleased(command);
        return this;
    }

    public FlightController toggleWhenPressed(Trigger trigger, Command command){
        triggerButtons[trigger.port].toggleWhenActive(command);
        return this;
    }

    public FlightController toggleWhenPressed(Button button, Command command){
        buttons[button.port].toggleWhenPressed(command);
        return this;
    }

    public FlightController toggleWhenPressed(POV pov, Command command){
        povButtons[pov.index].toggleWhenPressed(command);
        return this;
    }

    public FlightController cancelWhenPressed(Trigger trigger, Command command){
        triggerButtons[trigger.port].cancelWhenActive(command);
        return this;
    }

    public FlightController cancelWhenPressed(Button button, Command command){
        buttons[button.port].cancelWhenPressed(command);
        return this;
    }

    public FlightController cancelWhenPressed(POV pov, Command command){
        povButtons[pov.index].cancelWhenPressed(command);
        return this;
    }
}