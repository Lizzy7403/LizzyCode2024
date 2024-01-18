package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Lift;

public class HoldLiftCommand extends CommandBase {

    private final Lift lift;

    public HoldLiftCommand(Lift lift) {
        this.lift = lift;

        addRequirements(lift);    
    }

    @Override
    public void initialize() {
        lift.setHoldPosition();
    }

    @Override
    public void end(boolean interrupted) {
        lift.stopLift();
        lift._deactivateHoldMode();
    }

    @Override
    public boolean isFinished() {
        return true; // This command completes immediately after setting the speed
    }
}
