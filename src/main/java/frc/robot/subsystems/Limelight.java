package frc.robot.subsystems;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Subsystem;

public class Limelight implements Subsystem {

    NetworkTable table;
    private static Limelight instance;
    private double target, x, y, area;

    Limelight() {
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
        target = table.getEntry("tv").getDouble(0.0);
        return target == 1.0;
    }

    public double getXOffset() {
        x = table.getEntry("tx").getDouble(0.0);
        return x;
    }

    public double getYOffset() {
        y = table.getEntry("ty").getDouble(0.0);
        return y;
    }

    public double getArea() {
        area = table.getEntry("ta").getDouble(0.0);
        return area;
    }

    public static synchronized Limelight getInstance() {
        if (instance == null) {
            instance = new Limelight();
        }
        return instance;
    }
}
