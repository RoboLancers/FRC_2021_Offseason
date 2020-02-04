/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.revrobotics.ColorMatch;
import edu.wpi.first.wpilibj.util.Color;

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
        public static final double RAMP_RATE = 0.30;
        public static final int REV_PRESSURE_SENSOR_PORT = 19;

    }
    public final class Drivetrain{
        public final class Left{
            public static final int MASTER = 4;
            public static final int SLAVE_ONE = 3;
            public static final int SLAVE_TWO = 5;
        }
        public final class Right{
            public static final int MASTER = 1;
            public static final int SLAVE_ONE = 2;
            public static final int SLAVE_TWO = 6;
        }
    }
    public static final class Manipulator {
        public final class Climber {
            public static final int NEO_PORT = 7;
            public static final int TALONSRX_PORT = 8;
            public static final double CLIMBER_RAMP_RATE = 0.30;
        }
        public final class Spinner {
            public static final int SPINNER_PORT = 9;
            public static final int SPINNER_PIVOT_PORT_DOWN = 10;
            public static final int SPINNER_PIVOT_PORT_UP = 11;
        }
        public final class Intake {
            public static final int INTAKE_PORT = 12;
            public static final int INTAKE2_PORT = 19;
            public static final int INTAKE_PIVOT_PORT_UP = 13;
            public static final int INTAKE_PIVOT_PORT_DOWN = 14;
        }
        public final class Shooter{
            public static final int SHOOTER_MASTER_PORT = 15;
            public static final int SHOOTER_SLAVE_PORT = 16;
            public static final int SHOOTER_LOADER_PORT = 18;
        }
        public final class GearShifter{
            public static final int GEAR_SHIFTER_HIGH = 17;
            public static final int GEAR_SHIFTER_LOW = 18;
        }
    }
    public static final class Sensors {
        public static final Color kBlueTarget = ColorMatch.makeColor(0.143, 0.427, 0.429);
        public static final Color kGreenTarget = ColorMatch.makeColor(0.197, 0.561, 0.240);
        public static final Color kRedTarget = ColorMatch.makeColor(0.413, 0.378, 0.162);
        public static final Color kYellowTarget = ColorMatch.makeColor(0.361, 0.524, 0.113);

        public static final int PIGEON_PORT = 1;
    }

    public final class Odometry{
        public static final double MAX_VELOCITY = 10;
        public static final double MAX_VELOCITY_SIDE = 0;
        public static final double ANGULAR_VELOCITY = 0;
        public static final double ROBOT_WIDTH = 2.3;
        public static final double kSTATIC = 0.173;
        public static final double kVELOCITY = 0.837;
        public static final double kACCELERATION = 0.0886;
        public static final double MAX_VOLTAGE = 10;
    }

    public final class Trajectory{
        public static final double MAX_VELOCITY_CONSTRAINT = 7;
        public static final double MAX_ACCELERATION_CONSTRAINT = 5;
        public static final double kBETA = 2.0;
        public static final double kZETA = 0.7;
    }
}
