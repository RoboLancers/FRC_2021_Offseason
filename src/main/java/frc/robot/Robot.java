package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.utilities.XboxController;

public class Robot extends TimedRobot {

    // None of the following code is ever used?

    private Command m_autonomousCommand;

    private RobotContainer m_robotContainer;

    @Override
    public void robotInit() {
        m_robotContainer = new RobotContainer();
    }

    @Override
    public void robotPeriodic() {
        CommandScheduler.getInstance().run();

        m_robotContainer.update();
        SmartDashboard.putNumber("Shooter RPM", m_robotContainer.shooter.getMaster().getEncoder().getVelocity());
        SmartDashboard.putString("GearShifter", m_robotContainer.gearShifter.getState().getValue().name());
        SmartDashboard.putBoolean("Trigger Pressed", RobotContainer.driverXboxController.getState(XboxController.Trigger.RIGHT_TRIGGER));

        SmartDashboard.putBoolean("IR1", m_robotContainer.irsensor.getIROne());
        SmartDashboard.putBoolean("IR2", m_robotContainer.irsensor.getIRTwo());
        SmartDashboard.putBoolean("IR3", m_robotContainer.irsensor.getIRThree());
        SmartDashboard.putBoolean("IR4", m_robotContainer.irsensor.getIRFour());
    }

    @Override
    public void disabledInit() {
    }

    @Override
    public void disabledPeriodic() {
    }

    @Override
    public void autonomousInit() {
        m_autonomousCommand = m_robotContainer.getAutonomousCommand();

        if (m_autonomousCommand != null) {
            m_autonomousCommand.schedule();
        }
    }

    @Override
    public void autonomousPeriodic() {
    }

    @Override
    public void teleopInit() {
        if (m_autonomousCommand != null) {
            m_autonomousCommand.cancel();
        }
    }

    @Override
    public void teleopPeriodic() {
    }

    @Override
    public void testInit() {
        CommandScheduler.getInstance().cancelAll();
    }

    @Override
    public void testPeriodic() {
    }
}
