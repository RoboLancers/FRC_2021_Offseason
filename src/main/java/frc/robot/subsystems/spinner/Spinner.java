package frc.robot.subsystems.spinner;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotMap;

public class Spinner extends SubsystemBase {
    private TalonSRX spinner;

    public Spinner() {
        spinner = new TalonSRX(RobotMap.Manipulator.Spinner.SPINNER_PORT);
        spinner.setNeutralMode(NeutralMode.Brake);
    }

    public void set(double power) {
        spinner.set(ControlMode.PercentOutput, power);
    }

    public TalonSRX getSpinner() {
        return spinner;
    }
}
