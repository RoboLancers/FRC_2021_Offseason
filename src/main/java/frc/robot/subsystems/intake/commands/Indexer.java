package frc.robot.subsystems.intake.commands;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.ctre.phoenix.motorcontrol.ControlMode;
import edu.wpi.first.wpilibj.Joystick;

public class Indexer extends SubsystemBase{

    private Joystick driver = new Joystick(0);
    
    private TalonSRX IndexerMotor = new TalonSRX(3);

    // private final double maxAmps;
    private final double powerSet = 0.3;
    // private double transferMotorPower;
    // private final Error error;
    // Create a joystick

    /* public Indexer(double maxAmps, double powerSet) {
        this.maxAmps = maxAmps;
        this.powerSet = powerSet;
    } */

    @Override
    public void periodic() {
        if (driver.getRawButton(2)) { // set joystick pressed
            IndexerMotor.set(ControlMode.PercentOutput, powerSet);
        }
        else {
            IndexerMotor.set(ControlMode.PercentOutput, 0);
        }
    }
    /* @Override
    public void maxPower() {
        if (transferMotorPower > maxAmps) {
            DriverStation.reportError(error, stackTrace);
        }
    } */
}
