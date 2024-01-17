package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Shooter;

public class ShootCommand extends CommandBase {
    private final Shooter shooter;
    private final double speed;

    public ShootCommand(Shooter shooter, double speed) {
        this.shooter = shooter;
        this.speed = speed;
        addRequirements(this.shooter);
    }

    @Override
    public void initialize() {
        shooter.setShooterSpeed(speed);
    }

    @Override
    public boolean isFinished() {
        shooter.stopShooter();
        return true; // This command completes immediately after setting the speed
    }
}
