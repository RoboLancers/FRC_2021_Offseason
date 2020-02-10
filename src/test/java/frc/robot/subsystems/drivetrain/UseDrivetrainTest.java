package frc.robot.subsystems.drivetrain;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.subsystems.drivetrain.commands.UseDrivetrain;
import frc.robot.utilities.XboxController;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

public class UseDrivetrainTest {
    private Drivetrain drivetrain;
    private XboxController xboxController;
    private Command useDrivetrain;
    @Before
    public void setUp() {
        drivetrain = Mockito.spy(new Drivetrain(Mockito.mock(CommandScheduler.class), Mockito.mock(GearBox.class), Mockito.mock(GearBox.class)));
        xboxController = Mockito.mock(XboxController.class);
        useDrivetrain = new UseDrivetrain(drivetrain, xboxController);

        Mockito.doNothing().when(drivetrain).setLeft(Mockito.anyDouble());
        Mockito.doNothing().when(drivetrain).setRight(Mockito.anyDouble());
    }
    @Test
    public void TestMoveForward() {
        Mockito.doReturn(0.5).when(xboxController).getAxisValue(Mockito.eq(XboxController.Axis.LEFT_Y));
        useDrivetrain.execute();
        Mockito.verify(drivetrain, Mockito.times(1)).setLeft(Mockito.eq(0.5));
        Mockito.verify(drivetrain, Mockito.times(1)).setRight(Mockito.eq(0.5));
    }

    @Test
    public void TestDoNothing() {
        useDrivetrain.execute();
        Mockito.verify(drivetrain, Mockito.times(1)).setLeft(Mockito.eq(0.0));
        Mockito.verify(drivetrain, Mockito.times(1)).setRight(Mockito.eq(0.0));

    }
}
