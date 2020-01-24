package frc.robot.subsystems;

import com.ctre.phoenix.sensors.PigeonIMU;

public class Gyro {
    PigeonIMU pigeonIMU;
    double[] yawPitchRoll;

    public Gyro() {
        pigeonIMU = new PigeonIMU(0);
        yawPitchRoll = new double[3];
    }

    private void updateYawPitchRoll() {
        pigeonIMU.getYawPitchRoll(yawPitchRoll);
    }

    public double getFusedHeading() {
        return pigeonIMU.getFusedHeading();
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
}
