package frc.robot.subsystems.misc;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.cameraserver.CameraServer;

public class Camera {
    private static Camera camera;
    private UsbCamera frontLivecam, backLivecam, startAutomaticCapture;
    public Camera(){
        frontLivecam = CameraServer.getInstance().startAutomaticCapture();
        frontLivecam.setResolution(320, 240);
        frontLivecam.setFPS(15);
        backLivecam = CameraServer.getInstance().startAutomaticCapture();
        backLivecam.setResolution(240, 180);
        backLivecam.setFPS(15);
        startAutomaticCapture = CameraServer.startAutomaticCapture();

    }

    public UsbCamera getFrontCamera(){
        return frontLivecam;
    }

    public UsbCamera getBackCamera(){
        return backLivecam;
    }

    public UsbCamera startAutomaticCapture(){
        return startAutomaticCapture;
    }



}
