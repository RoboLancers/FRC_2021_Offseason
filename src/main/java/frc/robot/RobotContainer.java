package frc.robot;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.autonomous.AutoTargetAiming;
import frc.robot.autonomous.Odometry;
import frc.robot.autonomous.Trajectories;
import frc.robot.subsystems.climber.HookUp;
import frc.robot.subsystems.climber.PullUp;
import frc.robot.subsystems.climber.commands.UseHookUp;
import frc.robot.subsystems.climber.commands.UsePullUp;
import frc.robot.subsystems.drivetrain.Drivetrain;
import frc.robot.subsystems.drivetrain.GearShifter;
import frc.robot.subsystems.drivetrain.commands.HoldTargetAiming;
import frc.robot.subsystems.drivetrain.commands.ToggleGearShifter;
import frc.robot.subsystems.drivetrain.commands.UseDrivetrain;
import frc.robot.subsystems.intake.Intake;
import frc.robot.subsystems.intake.IntakePivot;
import frc.robot.subsystems.misc.*;
import frc.robot.subsystems.shooter.Shooter;
import frc.robot.subsystems.shooter.commands.ChangeShooterSpeed;
import frc.robot.subsystems.shooter.commands.LoadNShoot;
import frc.robot.subsystems.shooter.commands.RevUpShooter;
import frc.robot.subsystems.spinner.Spinner;
import frc.robot.subsystems.spinner.SpinnerPivot;
import frc.robot.utilities.XboxController;

public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  public static NetworkInterface networkInterface;

  public static Drivetrain drivetrain;
  public static PullUp pullUp;
  public static HookUp hookUp;
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
    networkInterface = new NetworkInterface();
    drivetrain = new Drivetrain();
    gyro = new Gyro();
    odometry = new Odometry(drivetrain);
    trajectories = new Trajectories(odometry);
    limelight = new Limelight();
    gearShifter = new GearShifter();
    pneumatics = new Pneumatics();

    intakePivot = new IntakePivot();
    intake = new Intake();
    shooter = new Shooter();
    //    irsensor = new IRSensor();
    pullUp = new PullUp();
    hookUp = new HookUp();


//    colorSensor = new ColorSensor();
    //    spinner = new Spinner();
    //    spinnerPivot = new SpinnerPivot();


    // Configure the button bindings
    drivetrain.setDefaultCommand(new UseDrivetrain(drivetrain, driverXboxController));
    //pullUp.setDefaultCommand(new UseHookUp());
    pullUp.setDefaultCommand(new UsePullUp());
    configureButtonBindings();
//    this.shooter = new Shooter();
  }

  private void configureButtonBindings() {
    driverXboxController.whileHeld(XboxController.Trigger.RIGHT_TRIGGER,
            new RevUpShooter(shooter, 50)
            .alongWith(new AutoTargetAiming(drivetrain, limelight))
            .andThen(new LoadNShoot(shooter, intake)));
    driverXboxController.whenPressed(XboxController.Button.B, new ChangeShooterSpeed(shooter, 1));
    driverXboxController.whenReleased(XboxController.Button.B, new ChangeShooterSpeed(shooter, 0));
    driverXboxController.whileHeld(XboxController.Button.A, new LoadNShoot(shooter, intake));
    driverXboxController.whileHeld(XboxController.Button.RIGHT_BUMPER, new HoldTargetAiming(drivetrain, limelight));
    driverXboxController.whenPressed(XboxController.Button.LEFT_BUMPER, new ToggleGearShifter(gearShifter));
    driverXboxController.whileHeld(XboxController.Button.Y, new UsePullUp());
//    driverXboxController.whileHeld(XboxController.Button.X, new UseIntake());


  }

  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
//    return new Ramsete(odometry, drivetrain, trajectories.turnRight()).andThen(()-> drivetrain.setVoltage(0,0));
    return null;
  }
}
