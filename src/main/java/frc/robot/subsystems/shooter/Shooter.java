package frc.robot.subsystems.shooter;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.subsystems.LancerSubsystem;

public class Shooter extends LancerSubsystem {
    private TalonSRX master, slave, loader;
    public PIDController pidController;
    private boolean running;
    private double speed;
    private double kP, kI, kD;
    private final double targetRPM;
    private double maths = (600.0/409600) * 3;

    //WANT TO: Create encoders and PID
    public Shooter(CommandScheduler cm, TalonSRX master, TalonSRX slave, TalonSRX loader) {
        super(cm);

        this.master = master;
        this.slave = slave;
        this.loader = loader;

        master.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder);

        slave.follow(master);

        pidController = new PIDController(kP, kI, kD);

        this.running = false;
        this.speed = 0.5;
        this.targetRPM = 57600;
    }

    public TalonSRX getMaster(){
        return master;
    }

    public void setShooterSpeed(double speed){
        if(this.speed <= 0){
            this.speed = 0;
        } else if (this.speed >= 1){
            this.speed = 1;
        } else {
            this.speed += speed;
        }
    }

    public void doRunShooter(boolean running){
        this.running = running;
        if(running){
            this.pidController.reset();
            this.pidController.enableContinuousInput(0, 115200);
        }
    }

    public TalonSRX getLoaderMotor() {
        return loader;
    }
    public void resetEncoder(){
        master.setSelectedSensorPosition(0);
    }

    @Override
    public void periodic(){
        if(this.running){
            pidController.setSetpoint(targetRPM);

            double output = pidController.calculate(getMaster().getSelectedSensorVelocity()*maths);
            System.out.println(output);

            this.speed = output/115200;

            this.master.set(ControlMode.PercentOutput, this.speed);
        } else{
            this.master.set(ControlMode.PercentOutput, 0);
        }
    }

}
