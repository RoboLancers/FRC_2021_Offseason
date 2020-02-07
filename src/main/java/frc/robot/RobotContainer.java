/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
//import edu.wpi.first.wpilibj.XboxController;
import frc.robot.autonomous.Odometry;
import frc.robot.subsystems.climber.Climber;
import frc.robot.subsystems.drivetrain.GearShifter;
import frc.robot.subsystems.drivetrain.commands.TargetAiming;
import frc.robot.subsystems.misc.commands.UseColorSensor;
import frc.robot.subsystems.drivetrain.commands.UseDrivetrain;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.drivetrain.Drivetrain;
import frc.robot.subsystems.shooter.Shooter;
import frc.robot.subsystems.intake.Intake;
import frc.robot.subsystems.intake.IntakePivot;
import frc.robot.subsystems.shooter.commands.ChangeShooterSpeed;
import frc.robot.subsystems.spinner.Spinner;
import frc.robot.subsystems.spinner.SpinnerPivot;
import frc.robot.subsystems.misc.ColorSensor;
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

  public static final Drivetrain drivetrain = new Drivetrain();
  public static final Limelight limelight = new Limelight();
  public static final ColorSensor colorSensor = new ColorSensor();
  public static final Gyro gyro = new Gyro();
  public static final Odometry odometry = new Odometry();
  public static final Climber climber = new Climber();
  public static final Spinner spinner = new Spinner();
  public static final Intake intake = new Intake();
  public static final IntakePivot intakePivot = new IntakePivot();
  public static final GearShifter gearShifter = new GearShifter();
  public static final SpinnerPivot spinnerPivot = new SpinnerPivot();

  public final Command useDrivetrain = new UseDrivetrain();
  public final Command targetAim = new TargetAiming(drivetrain);
  public final Command useColorSensor = new UseColorSensor();

  public static final XboxController xboxController = new XboxController(0);

  private final Shooter shooter;

  /**
   * The container for the robot.  Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    // Configure the button bindings
    drivetrain.setDefaultCommand(useDrivetrain);
    configureButtonBindings();
    this.shooter = new Shooter();
  }

  /**
   * Use this method to define your button->command mappings.  Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a
   * {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
  //buttonWhenPressed(new changeShooterSpeed(this.shooter, 0.05));
    //buttonWhenPressed(new changeShooterSpeed(this.shooter, -0.05));
    xboxController.whenPressed(XboxController.Button.RIGHT_BUMPER, new ChangeShooterSpeed(this.shooter, 0.05));
    xboxController.whenPressed(XboxController.Button.LEFT_BUMPER, new ChangeShooterSpeed(this.shooter, 0.05));

  }


  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return null;
  }
}
