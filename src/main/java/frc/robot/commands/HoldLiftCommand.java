package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Lift;

public class HoldLiftCommand extends CommandBase {

    private final double kP;

    private final double kI;

    private final double kD;

    private final double kIz;

    private final double kFF;

    private final Lift lift;

    public HoldLiftCommand(Lift lift, double kP, double kI, double kD, double kIz, double kFF) {
        this.lift = lift;
        this.kP = kP;
        this.kI = kI;
        this.kD = kD;
        this.kIz = kIz;
        this.kFF = kFF;

        addRequirements(lift);    
    }

    @Override
    public void initialize() {
        lift.lockPosition(kP, kI, kD, kIz, kFF);
    }

    @Override
    public void end(boolean interrupted) {
        lift.resetPID();
        lift.stopLift();
    }

    @Override
    public boolean isFinished() {
        return true; // This command completes immediately after setting the speed
    }
}
