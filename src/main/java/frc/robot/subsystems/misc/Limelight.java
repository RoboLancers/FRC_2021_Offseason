package frc.robot.subsystems.misc;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Subsystem;

public class Limelight implements Subsystem {
    private NetworkTable table;

    public Limelight() {
        table = NetworkTableInstance.getDefault().getTable("limelight");
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

    public double getDistance(){
        return (106.0) / Math.tan(getYOffset() + 35 + 24.85);
    }

    public void turnOnLight(boolean on) {
        table.getEntry("ledMde").setNumber(on ? 3 : 1);
    }
}
