package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.utilities.XboxController;

public class Robot extends TimedRobot {
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
        SmartDashboard.putNumber("Climber Axis", RobotContainer.manipulatorXboxController.getAxisValue(XboxController.Axis.LEFT_Y));
        SmartDashboard.putBoolean("Stale?", m_robotContainer.intake.isStale());
        SmartDashboard.putNumber("Shooter RPM", m_robotContainer.shooter.getMaster().getSelectedSensorVelocity());
        SmartDashboard.putNumber("Shooter Velocity", (m_robotContainer.shooter.getMaster().getSelectedSensorVelocity()) * Constants.Shooter.CONVERSION_BOY);
        SmartDashboard.putString("GearShifter", m_robotContainer.gearShifter.getState().getValue().name());
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
