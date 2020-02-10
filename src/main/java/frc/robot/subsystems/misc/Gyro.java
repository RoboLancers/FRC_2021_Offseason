package frc.robot.subsystems.misc;

import com.ctre.phoenix.sensors.PigeonIMU;
import frc.robot.Constants;
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
        double angle = pigeonIMU.getFusedHeading();
//        while(Math.abs(angle) > 180) {
//            angle = angle > 0 ? angle - 360 : angle + 360;
//        }
        return angle;
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
