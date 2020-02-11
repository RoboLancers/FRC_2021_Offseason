package frc.robot.subsystems.misc;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Pneumatics extends SubsystemBase {

    //private REVAnalogPressureSensor revAnalogPressureSensor;

    private Compressor compressor;

    private Pneumatics() {
        //revAnalogPressureSensor = new REVAnalogPressureSensor(RobotMap.MISC.REV_PRESSURE_SENSOR_PORT);

        compressor = new Compressor();
    }

    public double getPressure() {
        return 0; //revAnalogPressureSensor.getPressure();
    }

    public void start() {
        compressor.start();
    }

    public void stop() {
        compressor.stop();
    }

    //@Override
    protected void initDefaultCommand() {
        //setDefaultCommand(new PressureLimit());
    }

}
