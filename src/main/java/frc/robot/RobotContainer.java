/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import frc.robot.autonomous.Autonomous;
import frc.robot.autonomous.Odometry;
import frc.robot.autonomous.Trajectories;
import frc.robot.subsystems.drivetrain.commands.HoldTargetAiming;
import frc.robot.subsystems.drivetrain.commands.UseDrivetrain;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.drivetrain.Drivetrain;
import frc.robot.subsystems.misc.Gyro;
import frc.robot.subsystems.misc.Limelight;
import frc.robot.utilities.XboxController;

/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...

  public static Drivetrain drivetrain;
  public static final Limelight limelight = new Limelight();
//  public static final ColorSensor colorSensor = new ColorSensor();
  public static final Gyro gyro = new Gyro();
//  public static final Climber climber = new Climber();
//  public static final Spinner spinner = new Spinner();
//  public static final Intake intake = new Intake();
//  public static final IntakePivot intakePivot = new IntakePivot();
//  public static final GearShifter gearShifter = new GearShifter();
//  public static final SpinnerPivot spinnerPivot = new SpinnerPivot();
  public static Odometry odometry;
  public static Trajectories trajectories;

  public static final XboxController driverXboxController = new XboxController(0, 0.2);
  public static final XboxController manipulatorXboxController = new XboxController(1, 0.2);

//  private final Shooter shooter;

  public RobotContainer() {
    drivetrain = new Drivetrain();
    odometry = new Odometry(drivetrain);
    trajectories = new Trajectories(odometry);

    // Configure the button bindings
    drivetrain.setDefaultCommand(new UseDrivetrain(drivetrain, driverXboxController));
    configureButtonBindings();
//    this.shooter = new Shooter();
  }

  private void configureButtonBindings() {
  //buttonWhenPressed(new changeShooterSpeed(this.shooter, 0.05));
    //buttonWhenPressed(new changeShooterSpeed(this.shooter, -0.05));
//    xboxController.whenPressed(XboxController.Button.RIGHT_BUMPER, new ChangeShooterSpeed(this.shooter, 0.05));
//    xboxController.whenPressed(XboxController.Button.LEFT_BUMPER, new ChangeShooterSpeed(this.shooter, 0.05));
    driverXboxController.whileHeld(XboxController.Button.A, new HoldTargetAiming(drivetrain));

  }

  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return new Autonomous(odometry, drivetrain, trajectories.circle()).andThen(()-> drivetrain.setVoltage(0,0));
  }
}
