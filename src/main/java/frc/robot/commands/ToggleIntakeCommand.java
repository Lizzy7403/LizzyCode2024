package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Intake;

import java.util.function.BooleanSupplier;

public class ToggleIntakeCommand extends CommandBase {
    private final Intake intake;
    private final double setpointExtended;
    private final double setpointRetracted;
    private final BooleanSupplier isIntakeExtended;
    private final Runnable toggleIntakeExtension;

    public ToggleIntakeCommand(Intake intake, double setpointExtended, double setpointRetracted, BooleanSupplier isIntakeExtended, Runnable toggleIntakeExtension) {
        this.intake = intake;
        this.setpointExtended = setpointExtended;
        this.setpointRetracted = setpointRetracted;
        this.isIntakeExtended = isIntakeExtended;
        this.toggleIntakeExtension = toggleIntakeExtension;
        addRequirements(this.intake);
    }

    @Override
    public void initialize() {
        if (isIntakeExtended.getAsBoolean()) {
            new RotateIntakeCommand(intake, setpointRetracted).schedule();
        } else {
            new RotateIntakeCommand(intake, setpointExtended).schedule();
        }
        toggleIntakeExtension.run();
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}
