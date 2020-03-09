package frc.robot.subsystems.drivetrain.enums;

public enum AimingTarget {
    LINE(0), TRENCH(-16);

    double distance;

    AimingTarget(double distance) {
        this.distance = distance;
    }

    public double getDistance() {
        return distance;
    }
}
