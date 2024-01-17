package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;

public class LiftUpCommand extends CommandBase {

    public LiftUpCommand() {

        //addRequirements();
    }

    @Override
    public void initialize() {
    }

    @Override
    public boolean isFinished() {
        return true; // This command completes immediately after setting the speed
    }
}
