package frc.robot.subsystems.misc;

import com.ctre.phoenix.sensors.PigeonIMU;
import frc.robot.RobotMap;

public class Gyro {
    PigeonIMU pigeonIMU;
    double[] yawPitchRoll;

    public Gyro() {
        pigeonIMU = new PigeonIMU(RobotMap.Sensors.PIGEON_PORT);
        yawPitchRoll = new double[3];
    }

    private void updateYawPitchRoll() {
        pigeonIMU.getYawPitchRoll(yawPitchRoll);
    }

    public double getFusedHeading() {
        return Math.IEEEremainder(pigeonIMU.getFusedHeading(), 360);
    }

    public double getYaw() {
        updateYawPitchRoll();
        return yawPitchRoll[0];
    }

    public double getPitch() {
        updateYawPitchRoll();
        return yawPitchRoll[1];
    }

    public double getRoll() {
        updateYawPitchRoll();
        return yawPitchRoll[2];
    }

    public void resetHeading() {
        pigeonIMU.setFusedHeading(0);
    }

    public void setHeading(double degree) {
        pigeonIMU.setFusedHeading(degree);
    }
}
