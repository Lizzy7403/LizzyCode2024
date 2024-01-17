package frc.robot.commands;

// Importing the CommandBase class from the WPILib library
// This class provides the base for creating commands, which are actions that the robot can perform
import edu.wpi.first.wpilibj2.command.CommandBase;

// Importing the Intake class from the robot's code
// This class represents the intake subsystem of the robot
import frc.robot.subsystems.Intake;

// The RotateIntakeCommand class represents a command to rotate the intake
public class RotateIntakeCommand extends CommandBase {

    // The intake subsystem that this command will operate on
    private final Intake intake;

    // The desired position for the intake
    // This is a double value representing the setpoint for the intake's rotation
    private final double setpoint;

    // A boolean flag to indicate when the command is finished
    private boolean isFinished = false;

    private boolean isExtended = false;

    // The constructor for the RotateIntakeCommand class
    // This is called when a RotateIntakeCommand object is created
    // The Intake object and the setpoint passed as parameters are the subsystem and the desired position that the command will operate on
    public RotateIntakeCommand(Intake intake, double setpoint) {
        this.intake = intake;
        this.setpoint = setpoint;

        // This command requires the intake subsystem
        // This means that no other command that requires the intake subsystem can run at the same time as this command
        addRequirements(this.intake);
    }

    public RotateIntakeCommand(Intake intake, double setpoint, boolean isExtended) {
        this.intake = intake;
        this.setpoint = setpoint;
        this.isExtended = isExtended;

        // This command requires the intake subsystem
        // This means that no other command that requires the intake subsystem can run at the same time as this command
        //addRequirements(this.intake);
    }

    // The initialize method is called once when the command is started
    // For this command, the intake starts rotating to the specified setpoint when the command is started
    @Override
    public void initialize() {
        intake.rotateIntake(setpoint);
    }

    // The execute method is called repeatedly until the command ends
    // For this command, it checks if the intake has reached the setpoint
    // If the intake's position is within 5 units of the setpoint, the command is marked as finished
    @Override
    public void execute() {
        if (Math.abs(intake.getRotateEncoderPosition() - setpoint) < 1) {
            if(isExtended){
                intake.resetRotateEncoder();
            }
            isFinished = true;
        }
    }

    // The end method is called once when the command ends
    // For this command, if the command was interrupted, it stops the intake from rotating
    @Override
    public void end(boolean interrupted) {
        if (interrupted) {
            intake.stopRotateIntake(); // Optionally stop the rotation if the command is interrupted
        }
    }

    // The isFinished method is called to determine when the command is finished
    // For this command, it is finished when the intake has reached the setpoint
    @Override
    public boolean isFinished() {
        return isFinished;
    }
}