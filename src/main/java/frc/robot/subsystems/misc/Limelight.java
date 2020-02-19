package frc.robot.subsystems.misc;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Subsystem;

public class Limelight implements Subsystem {
    NetworkTable table;
    private double target, x, y, area;

    public Limelight() {
        table = NetworkTableInstance.getDefault().getTable("limelight");
        target = table.getEntry("tv").getDouble(0.0);
        x = table.getEntry("tx").getDouble(0.0);
        y = table.getEntry("ty").getDouble(0.0);
        area = table.getEntry("ta").getDouble(0.0);
    }

    public void updateValues() {
        target = table.getEntry("tv").getDouble(0.0);
        x = table.getEntry("tx").getDouble(0.0);
        y = table.getEntry("ty").getDouble(0.0);
        area = table.getEntry("ta").getDouble(0.0);
        SmartDashboard.putNumber("Target", target);
        SmartDashboard.putNumber("LimelightX", x);
        SmartDashboard.putNumber("LimelightY", y);
        SmartDashboard.putNumber("LimelightArea", area);
    }

    public boolean hasTarget() {
        return table.getEntry("tv").getDouble(0.0) == 1.0;
    }

    public double getXOffset() {
        return table.getEntry("tx").getDouble(0.0);
    }

    public double getYOffset() {
        return table.getEntry("ty").getDouble(0.0);
    }

    public double getArea() {
        return table.getEntry("ta").getDouble(0.0);
    }
}
