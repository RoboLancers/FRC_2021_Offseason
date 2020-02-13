package frc.robot.autonomous.enums;

import edu.wpi.first.wpilibj.geometry.Pose2d;
import frc.robot.Constants;

public enum StartingPosition {
    LOADING_STATION(Constants.Trajectory.LOADING_STATION_START), CENTER(Constants.Trajectory.CENTER_START), SHOOTING(Constants.Trajectory.SHOOTING_START);

    private Pose2d pose2d;

    StartingPosition(Pose2d pose2d) {
        this.pose2d = pose2d;
    }

    public Pose2d getPose2d() {
        return pose2d;
    }
}
