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
import frc.robot.utilities.math.InterpolatingDouble;
import frc.robot.utilities.math.InterpolatingTreeMap;
import frc.robot.utilities.math.PolynomialRegression;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants.  This class should not be used for any other purpose.  All constants should be
 * declared globally (i.e. public static).  Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
    public static final class Robot{
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

    public static final class Intake {
        public static final double INTAKE_POWER = 0.2;
        public static final double MAX_CURRENT = 20;
    }
    public static final class Shooter{
        public static final double SHOOTER_RPM_TOLERANCE = 100;
        public static final double MAX_RPM = 5560;
        public static final double kP = 0.0001;
        public static final double kI = 0;
        public static final double kD = 0;
        public static final double kFF = 1 / MAX_RPM;

        public static InterpolatingTreeMap<InterpolatingDouble, InterpolatingDouble> kFlywheelAutoAimMap = new InterpolatingTreeMap<>();
        public static PolynomialRegression kFlywheelAutoAimPolynomial;

        public static double[][] kFlywheelOffsetRpmValues = {
                { -24, 2890.0 },
                { -20.0, 2940.0 },
                { -16.0, 2990.0 },
                { -12.0, 3025.0 },
                { -8.0, 3075.0 },
                { -4.0, 3125.0 },
                { 0.0, 3175.0 },
                { 4.0, 3225.0 },
                { 8.0, 3275.0 },
        };

        static {
            for (double[] pair : kFlywheelOffsetRpmValues) {
                kFlywheelAutoAimMap.put(new InterpolatingDouble(pair[0]), new InterpolatingDouble(pair[1]));
            }
            kFlywheelAutoAimPolynomial = new PolynomialRegression(kFlywheelOffsetRpmValues, 2);
        }

    }

    public static final class Climber{
        public static final double K_GRAVITY = 0.3;
        public static final double PULLER_RAMP_RATE = 0.30;
        public static final double HOOKER_RAMP_RATE = 0.3;
    }

    public static final class Odometry{
        public static final double MAX_VELOCITY = 3.9624;
        public static final double MAX_VELOCITY_SIDE = 0;
        public static final double ANGULAR_VELOCITY = 0;
        public static final double ROBOT_WIDTH = 0.8763 ;
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
        private static final double LOADING_STATION_START_Y = Units.feetToMeters(6);
        private static final double CENTER_START_Y = Units.feetToMeters(11);
        private static final double SHOOTING_START_Y = Units.feetToMeters(17);

        public static final Pose2d LOADING_STATION_START = new Pose2d(START_X, LOADING_STATION_START_Y, new Rotation2d(Units.degreesToRadians(0)));
        public static final Pose2d CENTER_START = new Pose2d(START_X, CENTER_START_Y, new Rotation2d(Units.degreesToRadians(0)));
        public static final Pose2d SHOOTING_START = new Pose2d(START_X, SHOOTING_START_Y, new Rotation2d(Units.degreesToRadians(0)));
    }
}
