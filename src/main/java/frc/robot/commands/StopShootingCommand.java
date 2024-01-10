package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Shooter;

public class StopShootingCommand extends CommandBase {
    private final Shooter shooter;

    public StopShootingCommand(Shooter shooter) {
        this.shooter = shooter;
        addRequirements(this.shooter);
    }

    @Override
    public void initialize() {
        shooter.stopShooter();
    }

    @Override
    public boolean isFinished() {
        return true; // This command completes immediately after stopping the motors
    }
}