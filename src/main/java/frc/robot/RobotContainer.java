package frc.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.autonomous.*;
import frc.robot.subsystems.climber.Hooker;
import frc.robot.subsystems.climber.Puller;
import frc.robot.subsystems.climber.commands.HookUp;
import frc.robot.subsystems.climber.commands.PullUp;
import frc.robot.subsystems.drivetrain.Drivetrain;
import frc.robot.subsystems.drivetrain.GearShifter;
import frc.robot.subsystems.drivetrain.commands.HoldTargetAiming;
import frc.robot.subsystems.drivetrain.commands.ToggleGearShifter;
import frc.robot.subsystems.drivetrain.commands.UseDrivetrain;
import frc.robot.subsystems.drivetrain.enums.AimingTarget;
import frc.robot.subsystems.intake.Intake;
import frc.robot.subsystems.intake.IntakePivot;
import frc.robot.subsystems.intake.commands.*;
import frc.robot.subsystems.misc.*;
import frc.robot.subsystems.misc.commands.UseCompressor;
import frc.robot.subsystems.shooter.Loader;
import frc.robot.subsystems.shooter.Shooter;
import frc.robot.subsystems.shooter.commands.LoadNShoot;
import frc.robot.subsystems.shooter.commands.RevUpShooter;
import frc.robot.subsystems.shooter.commands.ShooterSpeed;
import frc.robot.subsystems.shooter.commands.UseLoaderMotor;
import frc.robot.subsystems.spinner.Spinner;
import frc.robot.subsystems.spinner.SpinnerPivot;
import frc.robot.utilities.FlightController;
import frc.robot.utilities.XboxController;

public class RobotContainer {
    // The robot's subsystems and commands are defined here...
    public NetworkInterface networkInterface;

    public Drivetrain drivetrain;
    public Hooker hooker;
    public Puller puller;
    public Spinner spinner;
    public Intake intake;
    public IntakePivot intakePivot;
    public GearShifter gearShifter;
    public SpinnerPivot spinnerPivot;
    public Shooter shooter;
    public Loader loader;

    public Gyro gyro;
    public Pneumatics pneumatics;
    public Limelight limelight;
    public ColorSensor colorSensor;
    public IRSensor irsensor;
    public Odometry odometry;
    public Trajectories trajectories;

    public Autonomous autonomous;

    public static XboxController driverXboxController = new XboxController(0, 0.2);
    public static XboxController manipulatorXboxController = new XboxController(1, 0.2);
    public static FlightController flightController = new FlightController(0,0.2);

//  private final Shooter shooter;

    public RobotContainer() {
        networkInterface = new NetworkInterface();
        drivetrain = new Drivetrain();
        gyro = new Gyro();
        odometry = new Odometry(drivetrain, gyro);
        trajectories = new Trajectories(odometry);
        limelight = new Limelight();
        gearShifter = new GearShifter();
        pneumatics = new Pneumatics();

        intakePivot = new IntakePivot();
        intake = new Intake();
        shooter = new Shooter();
        loader = new Loader();
        irsensor = new IRSensor();
        hooker = new Hooker();
        puller = new Puller();

        colorSensor = new ColorSensor();
        spinner = new Spinner();
        spinnerPivot = new SpinnerPivot();

        autonomous = new Autonomous(this);

        // Configure the button bindings
        drivetrain.setDefaultCommand(new UseDrivetrain(drivetrain, driverXboxController));
        hooker.setDefaultCommand(new HookUp(hooker));
        puller.setDefaultCommand(new PullUp(puller));
        pneumatics.setDefaultCommand(new UseCompressor(pneumatics));
//        intakePivot.setDefaultCommand(new AutoIntakePivot(intakePivot, irsensor));
//        intake.setDefaultCommand(new AutoStopConveyor(intake, irsensor));

        configureButtonBindings();
    }

    private void configureButtonBindings() {
//        driverXboxController.whileHeld(XboxController.Button.RIGHT_BUMPER,
//                new RevUpShooter(shooter, 50)
//                        .alongWith(new AutoTargetAiming(drivetrain, limelight))
//                        .andThen(new LoadNShoot(loader, intake)));

        driverXboxController.whileHeld(XboxController.Button.RIGHT_BUMPER, new UseIntake(intake, 0.4, 0.4))
                .whileHeld(XboxController.Button.B, new LoadNShoot(loader, intake))
                .whenPressed(XboxController.Button.LEFT_BUMPER, new ToggleGearShifter(gearShifter))
                .whileHeld(XboxController.Button.Y, new HoldTargetAiming(drivetrain, limelight, AimingTarget.LINE))
                .whenPressed(XboxController.Trigger.RIGHT_TRIGGER, new ShooterSpeed(shooter, 1))
                .whenReleased(XboxController.Trigger.RIGHT_TRIGGER, new ShooterSpeed(shooter, 0));


        manipulatorXboxController.toggleWhenPressed(XboxController.POV.DOWN, new ToggleIntakePivot(intakePivot))
                .whileHeld(XboxController.Button.X, new UseIntake(intake, 0.4, 0))
                .whileHeld(XboxController.Button.A, new UseIntake(intake, -0.4, 0))
                .whileHeld(XboxController.Button.Y, new UseIntake(intake, 0, 0.4))
                .whileHeld(XboxController.Button.B, new UseIntake(intake, 0, -0.4))
                .whenPressed(XboxController.Button.RIGHT_BUMPER, new ToggleIntakePivot(intakePivot));


        //Only loader motor
//        manipulatorXboxController.toggleWhenPressed(XboxController.Trigger.LEFT_TRIGGER, new UseLoaderMotor(loader, 0.5));
//        manipulatorXboxController.toggleWhenPressed(XboxController.Trigger.RIGHT_TRIGGER, new UseLoaderMotor(loader, -1));

    }

    public Command getAutonomousCommand() {
        return new Ramsete(odometry, drivetrain, trajectories.centerStartToAimingPosition()).andThen(()-> drivetrain.setVoltage(0, 0));
    }

    public void update(){
        odometry.updateOdometry();
        autonomous.update();

        SmartDashboard.putNumber("Angle", gyro.getFusedHeading());
        SmartDashboard.putNumber("X", odometry.getPose2d().getTranslation().getX());
        SmartDashboard.putNumber("Y", odometry.getPose2d().getTranslation().getY());
        SmartDashboard.putNumber("left encoder", drivetrain.getLeft().getDistance());
        SmartDashboard.putNumber("right encoder", drivetrain.getRight().getDistance());

        SmartDashboard.putNumber("Turning Offset", limelight.getXOffset());
        SmartDashboard.putNumber("Distance Offset", limelight.getYOffset());

        SmartDashboard.putNumber("Shooter Encoder Velocity", shooter.getMaster().getEncoder().getVelocity());
    }
}
