package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Lift;

public class LiftCommand extends CommandBase {

    private final Lift lift;

    private final double speed;

    public LiftCommand(Lift lift, double speed) {
        this.lift = lift;
        this.speed = speed;
        addRequirements(lift);    
    }

    @Override
    public void initialize() {
        lift.setLiftToSpeed(speed);
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
