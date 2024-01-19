package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Lift;

public class MoveLiftCommand extends CommandBase {

    private final Lift lift;

    private final double position;

    public MoveLiftCommand(Lift lift, double position) {
        this.lift = lift;
        this.position = position;
        addRequirements(lift);    
    }

    @Override
    public void initialize() {
        lift.setPosition(position);
    }

    @Override
    public void end(boolean interrupted) {
        lift.stopLift();
    }

    @Override
    public boolean isFinished() {
        return true; // This command completes immediately after setting the speed
    }
}
