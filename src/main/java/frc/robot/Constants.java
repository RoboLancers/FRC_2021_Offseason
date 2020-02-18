/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.revrobotics.ColorMatch;
import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj.util.Units;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants.  This class should not be used for any other purpose.  All constants should be
 * declared globally (i.e. public static).  Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
    public final class Robot{
        public static final int ENCODER_COUNT = 42;
        public static final double RAMP_RATE = 0.5;
        public static final double GEAR_RATIO = 10.75;
        public static final double WHEEL_DIAMETER = 0.1524;
        public static final double WHEEL_CIRCUMFERENCE = WHEEL_DIAMETER * Math.PI;
    }

    public static final class Sensors {
        public static final Color kBlueTarget = ColorMatch.makeColor(0.143, 0.427, 0.429);
        public static final Color kGreenTarget = ColorMatch.makeColor(0.197, 0.561, 0.240);
        public static final Color kRedTarget = ColorMatch.makeColor(0.413, 0.378, 0.162);
        public static final Color kYellowTarget = ColorMatch.makeColor(0.361, 0.524, 0.113);
    }

    public final class Intake {
        public static final double INTAKE_POWER = 0.2;
        public static final double MAX_CURRENT = 20;
    }
    public final class Shooter{
        public static final double SHOOTER_RPM_TOLERANCE = 5;
        public static final double CONVERSION_BOY = (600.0/409600) / 3;
        public static final double MAX_RPM = 18370;
        public static final double SHOOTER_RADIUS = 3; //inches
        public static final double ticksPerRev = 4096;
        public static final double kP = 0.01;
        public static final double kI = 0;
        public static final double kD = 0;

    }

    public final class Odometry{
        public static final double MAX_VELOCITY = 3.9624;
        public static final double MAX_VELOCITY_SIDE = 0;
        public static final double ANGULAR_VELOCITY = 0;
        public static final double ROBOT_WIDTH = 0.5991;
    }

    public static final class Trajectory{
        public static final double kSTATIC = 0.208;
        public static final double kVELOCITY = 2.72;
        public static final double kACCELERATION = 0.307;

        public static final double MAX_VOLTAGE = 10;
        public static final double MAX_VELOCITY_CONSTRAINT = 2.72;
        public static final double MAX_ACCELERATION_CONSTRAINT = 1.8288;

        public static final double kBETA = 2.0;
        public static final double kZETA = 0.7;

        private static final double START_X = Units.feetToMeters(11.0);
        private static final double LOADING_STATION_START_Y = Units.feetToMeters(0);
        private static final double CENTER_START_Y = Units.feetToMeters(12.0);
        private static final double SHOOTING_START_Y = Units.feetToMeters(18.5);

        public static final Pose2d LOADING_STATION_START = new Pose2d(START_X, LOADING_STATION_START_Y, new Rotation2d(Units.degreesToRadians(0)));
        public static final Pose2d CENTER_START = new Pose2d(START_X, CENTER_START_Y, new Rotation2d(Units.degreesToRadians(0)));
        public static final Pose2d SHOOTING_START = new Pose2d(START_X, SHOOTING_START_Y, new Rotation2d(Units.degreesToRadians(0)));
    }
}
