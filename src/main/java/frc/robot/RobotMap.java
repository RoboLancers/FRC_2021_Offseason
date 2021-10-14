package frc.robot;

public class RobotMap {
    public static final class Drivetrain {
        public static final class Left {
            public static final int FRONT = 1;
            public static final int BACK_ONE = 2;
            public static final int BACK_TWO = 3;
        }
        public static final class Right {
            public static final int FRONT = 4;
            public static final int BACK_ONE = 5;
            public static final int BACK_TWO = 6;
        }
    }
    // These mappings are wrong or electrical rewired incorrectly
    public static final class Manipulator {
        public static final class Climber {
            public static final int NEO_PORT = 9;
            public static final int TALONSRX_PORT = 10;
        }
        public static final class Spinner {
            public static final int SPINNER_PORT = 9;
            public static final int SPINNER_PIVOT_PORT_DOWN = 0;
            public static final int SPINNER_PIVOT_PORT_UP = 1;
        }
        public static final class Intake {
            public static final int INTAKE_PORT = 1;
            public static final int TRANSFER_PORT = 3;
            public static final int INTAKE_PIVOT_PORT_UP = 4;
            public static final int INTAKE_PIVOT_PORT_DOWN = 5;
        }
        public static final class Shooter {
            public static final int SHOOTER_MAIN_PORT = 7;
            public static final int SHOOTER_SECONDARY_PORT = 8;
            public static final int SHOOTER_LOADER_PORT = 2;
        }
        public static final class GearShifter {
            public static final int GEAR_SHIFTER_HIGH = 2;
            public static final int GEAR_SHIFTER_LOW = 3;
        }
    }
    public static final class Sensors {
        public static final int PIGEON_PORT = 1;

        public final class IR {
            public static final int THIRD_IR_PORT = 3;
            public static final int FIRST_IR_PORT = 1;
            public static final int FOURTH_IR_PORT = 4;
            public static final int SECOND_IR_PORT = 2;
        }
    }

}
