// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.Arm;

public class ArmToZero extends CommandBase {
  
  Arm arm;
  
  
  

  /** Creates a new ArmToZero. */
  public ArmToZero(Arm arm) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.arm = arm;
  
    addRequirements(arm);


  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {arm.setArm(Constants.armToZeroVelocity);}

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    arm.setArm(0);
    
 }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return !arm.getBottomLimitSwitch().get();
  }
}