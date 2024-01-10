package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Intake;

public class SpinIntakeCommand extends CommandBase {
    private final Intake intake;
    private final double speed;

    public SpinIntakeCommand(Intake intake, double speed) {
        this.intake = intake;
        this.speed = speed;
        addRequirements(this.intake);
    }

    @Override
    public void initialize() {
        intake.spinIntake(speed);
    }

    @Override
    public void end(boolean interrupted) {
        intake.spinIntake(0); // Stop the intake roller when the command ends
    }

    @Override
    public boolean isFinished() {
        return false; // This command will run until it's explicitly interrupted
    }
}
