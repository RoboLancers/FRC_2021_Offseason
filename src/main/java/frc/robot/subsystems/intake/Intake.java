package frc.robot.subsystems.intake;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotMap;

public class Intake extends SubsystemBase {
    private TalonSRX intake, transfer;

    public Intake() {
        intake = new TalonSRX(RobotMap.Manipulator.Intake.INTAKE_PORT);
        transfer = new TalonSRX(RobotMap.Manipulator.Intake.INTAKE2_PORT);

        intake.setInverted(true);

        intake.setNeutralMode(NeutralMode.Coast);
        transfer.setNeutralMode(NeutralMode.Coast);
    }

    public TalonSRX getIntakeMotor() {
        return intake;
    }

    public TalonSRX getTransferMotor() {return transfer;}

    public boolean isStale(){
        return (intake.getStatorCurrent() > 50);
    }
}