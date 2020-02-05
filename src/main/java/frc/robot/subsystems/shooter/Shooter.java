package frc.robot.subsystems.shooter;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Shooter extends SubsystemBase {
    private TalonSRX master, slave, loader;
    private boolean running;
    private double speed;

    //WANT TO: Create encoders and PID
    public Shooter() {
        master = new TalonSRX(Constants.Manipulator.Shooter.SHOOTER_MASTER_PORT);
        slave = new TalonSRX(Constants.Manipulator.Shooter.SHOOTER_SLAVE_PORT);

        loader = new TalonSRX(Constants.Manipulator.Shooter.SHOOTER_LOADER_PORT);

        master.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder);
        slave.follow(master);

        this.running = false;
        this.speed = 0;
    }

    public TalonSRX getMaster() {
        return master;
    }

    public TalonSRX getLoader() {
        return loader;
    }

    public void setShooterSpeed(double speed){
        this.speed = speed;
    }

    public void doRunShooter(boolean running){
        this.running = running;
    }

    public void restEncoder(){
        master.setSelectedSensorPosition(0);
    }

    @Override
    public void periodic(){
        if(this.running){
            this.master.set(ControlMode.PercentOutput, this.speed);
        } else{
            this.master.set(ControlMode.PercentOutput, 0);
        }
    }

}
