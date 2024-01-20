package frc.robot.commands;

// Importing the CommandBase class from the WPILib library
// This class provides the base for creating commands, which are actions that the robot can perform
import edu.wpi.first.wpilibj2.command.CommandBase;

// Importing the Intake class from the robot's code
// This class represents the intake subsystem of the robot
import frc.robot.subsystems.Intake;

// Importing the BooleanSupplier and Runnable interfaces from the Java standard library
// These interfaces represent a supplier of boolean values and a block of code to run, respectively
import java.util.function.BooleanSupplier;

public class ToggleIntakeCommand extends CommandBase {

    // The intake subsystem that this command will operate on
    private final Intake intake;

    // The setpoints for the extended and retracted positions of the intake
    private final double setpointExtended;
    private final double setpointRetracted;

    // A supplier of boolean values that indicates whether the intake is extended
    private final BooleanSupplier isIntakeExtended;

    // A block of code to run that toggles the extension of the intake
    private final Runnable toggleIntakeExtension;

    // The constructor for the ToggleIntakeCommand class
    // This is called when a ToggleIntakeCommand object is created
    // The parameters are the subsystem, the setpoints, the supplier, and the block of code that the command will operate on
    public ToggleIntakeCommand(Intake intake, double setpointExtended, double setpointRetracted, BooleanSupplier isIntakeExtended, Runnable toggleIntakeExtension) {
        this.intake = intake;
        this.setpointExtended = setpointExtended;
        this.setpointRetracted = setpointRetracted;
        this.isIntakeExtended = isIntakeExtended;
        this.toggleIntakeExtension = toggleIntakeExtension;

        // This command requires the intake subsystem
        // This means that no other command that requires the intake subsystem can run at the same time as this command
        //addRequirements(this.intake);
    }

    // The initialize method is called once when the command is started
    // For this command, it checks if the intake is extended
    // If the intake is extended, it schedules a command to rotate the intake to the retracted position
    // If the intake is not extended, it schedules a command to rotate the intake to the extended position
    // Then it runs the block of code that toggles the extension of the intake
    @Override
    public void initialize() {
        if (isIntakeExtended.getAsBoolean()) {
            new RotateIntakeCommand(intake, setpointRetracted, true).schedule();
        } else {
            new RotateIntakeCommand(intake, setpointExtended, false).schedule();
        }
        toggleIntakeExtension.run();
    }

    // The isFinished method is called to determine when the command is finished
    // For this command, it is finished immediately after the intake's extension is toggled
    @Override
    public boolean isFinished() {
        return true;
    }
}