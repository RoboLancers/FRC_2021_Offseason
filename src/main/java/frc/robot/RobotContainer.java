package frc.robot;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.util.Units;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.autonomous.*;
import frc.robot.autonomous.enums.StartingPosition;
import frc.robot.autonomous.routine.ShootThreePowerCells;
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
import frc.robot.subsystems.intake.commands.ToggleIntakePivot;
import frc.robot.subsystems.intake.commands.UseIntake;
import frc.robot.subsystems.misc.*;
import frc.robot.subsystems.misc.commands.UseCompressor;
import frc.robot.subsystems.shooter.Loader;
import frc.robot.subsystems.shooter.Shooter;
import frc.robot.subsystems.shooter.commands.LoadNShoot;
import frc.robot.subsystems.shooter.commands.RevUpShooter;
import frc.robot.subsystems.shooter.commands.UseLoaderMotor;
import frc.robot.subsystems.spinner.Spinner;
import frc.robot.subsystems.spinner.SpinnerPivot;
import frc.robot.utilities.FlightController;
import frc.robot.utilities.XboxController;

public class RobotContainer {
    // Robot subsystems and commands
    public NetworkInterface networkInterface;
    public NetworkTableEntry shooterSpeed;
    public Camera camera;

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
    public static XboxController testController = new XboxController(2,0.2);

    public RobotContainer() {
        networkInterface = new NetworkInterface();
        camera = new Camera();

        // SmartDashboard.putStr
        // ^ ?
        shooterSpeed = NetworkTableInstance.getDefault().getEntry("Shooter Speed");

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
        // intake.setDefaultCommand(new AutoStopConveyor(intake, irsensor));

        configureButtonBindings();
    }

    private void configureButtonBindings() {
        // Issues:
        /*
            Nothing actually toggles the puller, so that means the ports have to be wrong
            For driver controller, we should made right trigger toggle target shooter rpm, so the button config is simpler
            Driver controller doesn't do anything with X, which seems inefficient
            Manipulator controller has right bumper pressed mapped to the same command as dpad down pressed
            What does left bumper on test controller do?
        */
        driverXboxController
            // Buttons
                // A Held           ->      HoldTargetAiming(aimingTarget: AimingTarget.TRENCH)
                // B Held           ->      LoadNShoot()
                // Y Held           ->      HoldTargetAiming(aimingTarget: AimingTarget.LINE)
                // X Held           ->      UseLoaderMotor(loaderMotorPower: 0.6)
                .whileHeld(XboxController.Button.A, new HoldTargetAiming(drivetrain, limelight, AimingTarget.TRENCH))
                .whileHeld(XboxController.Button.B, new LoadNShoot(loader, intake, irsensor))
                .whileHeld(XboxController.Button.Y, new HoldTargetAiming(drivetrain, limelight, AimingTarget.LINE))
                .whileHeld(XboxController.Button.X, new UseLoaderMotor(loader, 0.6))
            // Bumpers
                // Left Pressed     ->      ToggleGearShift()
                // Right Held       ->      UseIntake(intakeMotorPower: 0.6, transferMotorPower: 0.6)
                .whenPressed(XboxController.Button.LEFT_BUMPER, new ToggleGearShifter(gearShifter))
                .whileHeld(XboxController.Button.RIGHT_BUMPER, new UseIntake(intake, irsensor, 0.6, 0.6))
            // Triggers
                // Left Pressed     ->      RevUpShooter(targetRPM: 5500)
                // Left Released    ->      RevUpShooter(targetRPM: 0)
                // Right Pressed    ->      RevUpShooter(targetRPM: 5000)
                // Right Released   ->      RevUpShooter(targetRPM: 0)
                .whenPressed(XboxController.Trigger.LEFT_TRIGGER, new RevUpShooter(shooter, shooterSpeed.getDouble(5500)))
                .whenReleased(XboxController.Trigger.LEFT_TRIGGER, new RevUpShooter(shooter, 0))
                .whenPressed(XboxController.Trigger.RIGHT_TRIGGER, new RevUpShooter(shooter, shooterSpeed.getDouble(5000)))
                .whenReleased(XboxController.Trigger.RIGHT_TRIGGER, new RevUpShooter(shooter, 0));

        manipulatorXboxController
            // Buttons
                // A Held           ->      UseIntake(intakeMotorPower: -1, transferMotorPower: 0)
                // B Held           ->      UseIntake(intakeMotorPower: 0, transferMotorPower: -0.6)
                // X Held           ->      UseIntake(intakeMotorPower: 1, transferMotorPower: 0)
                // Y Held           ->      UseIntake(intakeMotorPower: 0, transferMotorPower: 0.6)
                .whileHeld(XboxController.Button.A, new UseIntake(intake, irsensor,-1, 0))
                .whileHeld(XboxController.Button.B, new UseIntake(intake, irsensor,0, -0.6))
                .whileHeld(XboxController.Button.X, new UseIntake(intake, irsensor,1, 0))
                .whileHeld(XboxController.Button.Y, new UseIntake(intake, irsensor, 0, 0.6))
            // Bumpers
                // Left Held        ->      UseLoaderMotor(loaderMotorPower: 0.6)
                // Right Pressed    ->      ToggleIntakePivot()
                .whileHeld(XboxController.Button.LEFT_BUMPER, new UseLoaderMotor(loader,  0.6))
                .whenPressed(XboxController.Button.RIGHT_BUMPER, new ToggleIntakePivot(intakePivot))
            // Pov (Dpad)
                // Down Pressed     ->      ToggleIntakePivot()
                .toggleWhenPressed(XboxController.POV.DOWN, new ToggleIntakePivot(intakePivot));

        testController
            // Bumpers
                // Left Pressed     ->      Reset Odometry
                // Right Pressed    ->      ?
                .whenPressed(XboxController.Button.RIGHT_BUMPER,  new InstantCommand(() -> odometry.resetOdometry(new Pose2d(0, 0, new Rotation2d(0)))))
                .whenPressed(XboxController.Button.LEFT_BUMPER, new InitializeCommand(drivetrain, odometry, gyro, StartingPosition.SHOOTING));
    }

    public Command getAutonomousCommand() {
        return new ShootThreePowerCells(drivetrain, gyro, loader, shooter, irsensor, intake, odometry, limelight, StartingPosition.SHOOTING, trajectories).andThen(()-> drivetrain.setVoltage(0, 0));
    }

    // Update odometry and autonomous, then update smart dashboard
    public void update(){
        odometry.updateOdometry();
        autonomous.update();

        SmartDashboard.putNumber("Angle", gyro.getFusedHeading());
        SmartDashboard.putNumber("X", Units.metersToFeet(odometry.getPose2d().getTranslation().getX()));
        SmartDashboard.putNumber("Y", Units.metersToFeet(odometry.getPose2d().getTranslation().getY()));
        SmartDashboard.putNumber("left encoder", drivetrain.getLeft().getDistance());
        SmartDashboard.putNumber("right encoder", drivetrain.getRight().getDistance());

        SmartDashboard.putNumber("Turning Offset", limelight.getXOffset());
        SmartDashboard.putNumber("Distance Offset", limelight.getYOffset());
        SmartDashboard.putNumber("Shooter Encoder Velocity", shooter.getMaster().getEncoder().getVelocity());
        SmartDashboard.putNumber("Timer", loader.getTimer().get());
        SmartDashboard.putNumber("Shooter Current 1", shooter.getMaster().getOutputCurrent());
        SmartDashboard.putNumber("Shooter Current 2", shooter.getSlave().getOutputCurrent());
    }
}
