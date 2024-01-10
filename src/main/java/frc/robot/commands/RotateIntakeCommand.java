package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Intake;


public class RotateIntakeCommand extends CommandBase {
    private final Intake intake;
    private final double setpoint;
    private boolean isFinished = false;

    public RotateIntakeCommand(Intake intake, double setpoint) {
        this.intake = intake;
        this.setpoint = setpoint;
        addRequirements(this.intake);
    }

    @Override
    public void initialize() {
        intake.rotateIntake(setpoint);
    }

    @Override
    public void execute() {
        if (Math.abs(intake.getRotateEncoderPosition() - setpoint) < 5) {
            isFinished = true;
        }
    }

    @Override
    public void end(boolean interrupted) {
        if (interrupted) {
            intake.stopRotateIntake(); // Optionally stop the rotation if the command is interrupted
        }
    }

    @Override
    public boolean isFinished() {
        return isFinished;
    }
}
