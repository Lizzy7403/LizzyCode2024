// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.Arm;
//import frc.robot.subsystems.ScrewElevator;

public class elevatorManualDown extends CommandBase {

  private Arm arm;
  private double speed;
  /** Creates a new elevatorManual. */
  public elevatorManualDown(Arm arm) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.arm = arm;
    this.speed = Constants.elevatorManualDownSpeed;
    addRequirements(arm);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    
    arm.setArm(speed);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {

    arm.stopArm();

  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return !arm.getBottomLimitSwitch().get();
  }
}
