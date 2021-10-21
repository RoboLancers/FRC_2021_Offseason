package frc.robot.subsystems.misc;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.cameraserver.CameraServer;

public class Camera {
    private static Camera camera;
    private UsbCamera frontLivecam, backLivecam;

    public Camera(){
        frontLivecam = CameraServer.getInstance().startAutomaticCapture();
        frontLivecam.setResolution(320, 240);
        frontLivecam.setFPS(15);
        /*
            Test start automatic capture first
        */
        // frontLivecam.addAxisCamera();
        backLivecam = CameraServer.getInstance().startAutomaticCapture();
        backLivecam.setResolution(240, 180);
        backLivecam.setFPS(15);
        // backLivecam.addAxisCamera();
    }

    public UsbCamera getFrontCamera(){
        return frontLivecam;
    }

    public UsbCamera getBackCamera(){
        return backLivecam;
    }
}
