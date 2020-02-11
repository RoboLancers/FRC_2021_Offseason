package frc.robot.subsystems.misc.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.misc.Pneumatics;

public class UseCompressor extends CommandBase {
    private Pneumatics pneumatics;
    UseCompressor(Pneumatics pneumatics) {
        this.pneumatics = pneumatics;
        addRequirements(pneumatics);
    }

    @Override
    public void execute() {
        pneumatics.regulateCompressor();
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
