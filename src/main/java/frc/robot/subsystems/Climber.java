package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Climber extends SubsystemBase {
    private CANSparkMax climber;

    Climber(){
        climber = new CANSparkMax(Constants.Manipulator.Climber.CLIMBER_PORT, CANSparkMaxLowLevel.MotorType.kBrushless);

        climber.setOpenLoopRampRate(Constants.Manipulator.Climber.CLIMBER_RAMP_RATE);
    }

    public double getPosition() {
        return climber.getEncoder().getPosition();
    }

    public void resetEncoders() {
        climber.getEncoder().setPosition(0);
    }

    public CANSparkMax getMaster() {
        return climber;
    }
}
