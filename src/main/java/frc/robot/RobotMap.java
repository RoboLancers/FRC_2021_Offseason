package frc.robot;

public class RobotMap {
    public final class Drivetrain{
        public final class Left{
            public static final int MASTER = 1;
            public static final int SLAVE_ONE = 2;
            public static final int SLAVE_TWO = 3;
        }
        public final class Right{
            public static final int MASTER = 4;
            public static final int SLAVE_ONE = 5;
            public static final int SLAVE_TWO = 6;
        }
    }
    public static final class Manipulator{
        public final class Climber {
            public static final int NEO_PORT = 7;
            public static final int TALONSRX_PORT = 5;
            public static final double CLIMBER_RAMP_RATE = 0.30;
        }
        public final class Spinner {
            public static final int SPINNER_PORT = 4;
            public static final int SPINNER_PIVOT_PORT_DOWN = 10;
            public static final int SPINNER_PIVOT_PORT_UP = 11;
        }
        public final class Intake {
            public static final int INTAKE_PORT = 6;
            public static final int INTAKE2_PORT = 3;
            public static final int INTAKE_PIVOT_PORT_UP = 2;
            public static final int INTAKE_PIVOT_PORT_DOWN = 3;
        }
        public final class Shooter{
            public static final int SHOOTER_MASTER_PORT = 2;
            public static final int SHOOTER_SLAVE_PORT = 1;
            public static final int SHOOTER_LOADER_PORT = 4;
        }
        public final class GearShifter{
            public static final int GEAR_SHIFTER_HIGH = 5;
            public static final int GEAR_SHIFTER_LOW = 4;
        }
    }
    public static final class Sensors{
        public static final int PIGEON_PORT = 1;

        public final class IR{
        public static final int THIRD_IR_PORT = 2;
        public static final int FIRST_IR_PORT = 3;
        public static final int FOURTH_IR_PORT = 4;
        public static final int SECOND_IR_PORT = 5;
        }
    }
}
