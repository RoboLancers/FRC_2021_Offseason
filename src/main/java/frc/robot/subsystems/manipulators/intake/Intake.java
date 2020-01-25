package frc.robot.subsystems.manipulators.intake;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.revrobotics.CANSparkMax;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Intake extends SubsystemBase {
    private TalonSRX intake;

    public Intake() {
        intake = new TalonSRX(Constants.Manipulator.Intake.INTAKE_PORT);
        intake.setNeutralMode(NeutralMode.Brake);
    }

    public TalonSRX getMotor() {
        return intake;
    }


}