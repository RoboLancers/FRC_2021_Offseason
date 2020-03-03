package frc.robot.subsystems.drivetrain.enums;

public enum AimingTarget {
    LINE(-10), TRENCH(-28);

    double distance;

    AimingTarget(double distance) {
        this.distance = distance;
    }

    public double getDistance() {
        return distance;
    }
}
