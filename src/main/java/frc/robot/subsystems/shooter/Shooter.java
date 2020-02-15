package frc.robot.subsystems.shooter;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.RobotMap;
import org.opencv.core.Mat;

public class Shooter extends SubsystemBase {
    private TalonSRX master, slave, loader;
    public PIDController pidController;
    private boolean running;
    private double speed;
    private double targetRPM, targetVelocity;
    private double maths = Constants.Shooter.CONVERSION_BOY;

    //WANT TO: Create encoders and PID
    /**CHANGE TO VELOCITY OR POSITION CONTROL*/
    public Shooter() {
        master = new TalonSRX(RobotMap.Manipulator.Shooter.SHOOTER_MASTER_PORT);
        slave = new TalonSRX(RobotMap.Manipulator.Shooter.SHOOTER_SLAVE_PORT);
        loader = new TalonSRX(RobotMap.Manipulator.Shooter.SHOOTER_LOADER_PORT);

        master.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder);

        slave.follow(master);

        pidController = new PIDController(Constants.Shooter.kP, Constants.Shooter.kI, Constants.Shooter.kD);

        this.running = false;
        this.speed = 0.5;

    }

    public TalonSRX getMaster(){
        return master;
    }

    public void setTargetRPM(double targetRPM){
        this.targetRPM = targetRPM;
    }

    public double inchesPerSecToTicksPer100ms(double inchesPerSec){
        return (inchesPerSec * Constants.Shooter.ticksPerRev) / (1000 * 2 * Constants.Shooter.SHOOTER_RADIUS * Math.PI);
    }

    public void setTargetInchesPerSec(double targetVelocity) {
        this.targetVelocity = targetVelocity;
    }

    public void setMotorToVelocity(){
        pidController.setSetpoint(targetVelocity);
        master.set(ControlMode.Velocity, inchesPerSecToTicksPer100ms(this.targetVelocity));
    }

    public void setMotorToTarget(){
        pidController.setSetpoint(targetRPM);

        double output = pidController.calculate(getMaster().getSelectedSensorVelocity()*maths);

        this.speed = output/Constants.Shooter.MAX_RPM;

        //Control Mode Velocity takes in ticks / 100 ms.
        this.master.set(ControlMode.PercentOutput, this.speed);
    }

    public void resetPID(){
        this.pidController.reset();
    }

    public void setShooterSpeed(double speed){
        if(this.speed <= 0){
            this.speed = 0;
        } else if (this.speed >= 1){
            this.speed = 1;
        } else {
            this.speed = speed;
        }
    }

    public void changeShooterSpeed(double speed){
        if(this.speed <= 0){
            this.speed = 0;
        } else if (this.speed >= 1){
            this.speed = 1;
        } else {
            this.speed += speed;
        }
    }

    public boolean fastEnough(){
        return Math.abs(master.getSelectedSensorVelocity() - this.targetRPM) < Constants.Shooter.SHOOTER_RPM_TOLERANCE;
    }

    public TalonSRX getLoaderMotor() {
        return loader;
    }

    public void resetEncoder(){
        master.setSelectedSensorPosition(0);
    }

    @Override
    public void periodic(){
    }
}
//when autoing, use command groups, cant incorporate auto with periodic
//when autoing or teleoping, ill have a while loop for whilepressed in order for me to set motor value to get rpm
//when prompted to shoot call it multiple times