package frc.robot.commands;

// Importing the CommandBase class from the WPILib library
// This class provides the base for creating commands, which are actions that the robot can perform
import edu.wpi.first.wpilibj2.command.CommandBase;

// Importing the Shooter class from the robot's code
// This class represents the shooter subsystem of the robot
import frc.robot.subsystems.Shooter;

// The StopShootingCommand class represents a command to stop the shooter
public class StopShootingCommand extends CommandBase {

    // The shooter subsystem that this command will operate on
    private final Shooter shooter;

    // The constructor for the StopShootingCommand class
    // This is called when a StopShootingCommand object is created
    // The Shooter object passed as a parameter is the subsystem that the command will operate on
    public StopShootingCommand(Shooter shooter) {
        this.shooter = shooter;

        // This command requires the shooter subsystem
        // This means that no other command that requires the shooter subsystem can run at the same time as this command
        addRequirements(this.shooter);
    }

    // The initialize method is called once when the command is started
    // For this command, the shooter is stopped when the command is started
    @Override
    public void initialize() {
        shooter.stopShooter();
    }

    // The isFinished method is called to determine when the command is finished
    // For this command, it is finished immediately after the shooter is stopped
    @Override
    public boolean isFinished() {
        return true; // This command completes immediately after stopping the motors
    }
}