package frc.robot.subsystems.manipulators.intake;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.revrobotics.CANSparkMax;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Intake extends SubsystemBase {
    private TalonSRX intake, intake2;

    public Intake() {
        intake = new TalonSRX(Constants.Manipulator.Intake.INTAKE_PORT);
        intake2 = new TalonSRX(Constants.Manipulator.Intake.INTAKE2_PORT);

        intake.setNeutralMode(NeutralMode.Brake);
        intake2.setNeutralMode(NeutralMode.Brake);
    }

    public TalonSRX getMainMotor() {
        return intake;
    }
    public TalonSRX getMotor() {return intake2;}


}