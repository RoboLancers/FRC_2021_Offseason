package frc.robot;

public class RobotMap {
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
    public static final class Manipulator{
        public final class Climber {
            public static final int NEO_PORT = 7;
            public static final int TALONSRX_PORT = 2;
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
    public static final class Sensors{
        public static final int PIGEON_PORT = 1;

        public final class IR{
        public static final int TOP_IR_PORT = 2;
        public static final int BOTTOM_IR_PORT = 3;
        public static final int LEFT_IR_PORT = 4;
        public static final int RIGHT_IR_PORT = 5;
        }
    }
}
