import java.util.function.DoubleConsumer;
import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.CommandBase;

public class FeedbackBasedCommand extends CommandBase {
    private double minOutputMagnitude;
    private double maxOutputMagnitude;
    private double goalThreshold;

    private double kP;
    private double kD;

    DoubleSupplier errorFeedback;
    DoubleConsumer useOutput;

    private double lastError;

    public FeedbackBasedCommand(
        double minOutputMagnitude,
        double maxOutputMagnitude,
        double goalThreshold,
        double kP,
        double kD,
        DoubleSupplier errorFeedback,
        DoubleConsumer useOutput
    ){
        this.minOutputMagnitude = minOutputMagnitude;
        this.maxOutputMagnitude = maxOutputMagnitude;
        this.goalThreshold = goalThreshold;
        this.kP = kP;
        this.kD = kD;
        this.errorFeedback = errorFeedback;
        this.useOutput = useOutput;

        lastError = errorFeedback.getAsDouble();
    }

    @Override
    public void execute(){
        double error = errorFeedback.getAsDouble();
        double deltaError = error - lastError;
        lastError = error;
        
        double output = error * kP + deltaError * kD;
        int outputDirection = output < 0 ? -1 : 1;
        double outputMagnitude = Math.abs(output);

        double clampedOutput = Math.min(Math.max(outputMagnitude, minOutputMagnitude), maxOutputMagnitude);

        useOutput.accept(outputDirection * clampedOutput);
    }
    
    @Override
    public boolean isFinished(){
        return Math.abs(errorFeedback.getAsDouble()) < goalThreshold;
    }
}
