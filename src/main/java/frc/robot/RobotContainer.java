/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj2.command.FunctionalCommand;
import edu.wpi.first.wpilibj2.command.RunCommand;
import frc.robot.autonomous.AutoTargetAiming;
import frc.robot.autonomous.Ramsete;
import frc.robot.autonomous.Odometry;
import frc.robot.autonomous.Trajectories;
import frc.robot.subsystems.climber.Climber;
import frc.robot.subsystems.climber.commands.UseClimber;
import frc.robot.subsystems.drivetrain.GearShifter;
import frc.robot.subsystems.drivetrain.commands.UseDrivetrain;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.drivetrain.Drivetrain;
import frc.robot.subsystems.intake.Intake;
import frc.robot.subsystems.intake.IntakePivot;
import frc.robot.subsystems.misc.*;
import frc.robot.subsystems.shooter.Shooter;
import frc.robot.subsystems.shooter.commands.LoadNShoot;
import frc.robot.subsystems.shooter.commands.RevUpShooter;
import frc.robot.subsystems.spinner.Spinner;
import frc.robot.subsystems.spinner.SpinnerPivot;
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
  public static Climber climber;
  public static Spinner spinner;
  public static Intake intake;
  public static IntakePivot intakePivot;
  public static GearShifter gearShifter;
  public static SpinnerPivot spinnerPivot;
  public static Shooter shooter;

  public static Gyro gyro;
  public static Pneumatics pneumatics;
  public static Limelight limelight;
  public static ColorSensor colorSensor;
  public static IRSensor irsensor;
  public static Odometry odometry;
  public static Trajectories trajectories;

  public static XboxController driverXboxController = new XboxController(0, 0.2);
  public static XboxController manipulatorXboxController = new XboxController(1, 0.2);

//  private final Shooter shooter;

  public RobotContainer() {
    drivetrain = new Drivetrain();
    gyro = new Gyro();
    odometry = new Odometry(drivetrain);
    trajectories = new Trajectories(odometry);
//    limelight = new Limelight();

//    colorSensor = new ColorSensor();
    climber = new Climber();
//    spinner = new Spinner();
//    intake = new Intake();
//    shooter = new Shooter();
//    intakePivot = new IntakePivot();
//    gearShifter = new GearShifter();
//    spinnerPivot = new SpinnerPivot();
//    pneumatics = new Pneumatics();
//    irsensor = new IRSensor();


    // Configure the button bindings
    drivetrain.setDefaultCommand(new UseDrivetrain(drivetrain, driverXboxController));
    configureButtonBindings();
//    this.shooter = new Shooter();
  }

  private void configureButtonBindings() {
//    driverXboxController.whileHeld(XboxController.Trigger.RIGHT_TRIGGER,
//            new RevUpShooter(shooter, 50) //Takes in inches Per Second
//            .alongWith(new AutoTargetAiming(drivetrain, limelight))
//            .andThen(new LoadNShoot(shooter, intake)));
    driverXboxController.whileHeld(XboxController.Button.B, new UseClimber(0.4));
    driverXboxController.whileHeld(XboxController.Button.A, new UseClimber(-0.1));


  }

  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return new Ramsete(odometry, drivetrain, trajectories.turnRight()).andThen(()-> drivetrain.setVoltage(0,0));
  }
}
