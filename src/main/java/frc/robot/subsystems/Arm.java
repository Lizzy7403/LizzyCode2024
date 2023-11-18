// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import frc.robot.Constants;

public class Arm extends SubsystemBase {

  private final DigitalInput topLimitSwitch = new DigitalInput(4);
  private final DigitalInput bottomLimitSwitch = new DigitalInput(6);

  private CANSparkMax elevatorMotor1;
  private CANSparkMax elevatorMotor2;


  private Encoder armEncoder = new Encoder(0, 1);
  
  /** Creates a new Arm. */
  public Arm() {

    super();
    elevatorMotor1 = new CANSparkMax(Constants.elevator1Id, MotorType.kBrushed);
    elevatorMotor2 = new CANSparkMax(Constants.elevator2Id, MotorType.kBrushed);
    armEncoder.setDistancePerPulse(1. / 2048);
    elevatorMotor2.follow(elevatorMotor1);

  }

  public DigitalInput getTopLimitSwitch() {
    return topLimitSwitch;
  }

  public DigitalInput getBottomLimitSwitch() {
    return bottomLimitSwitch;
  }

  public void setArm(double speed) {
    elevatorMotor1.set(speed);
  }

  public void stopArm() {
    elevatorMotor1.set(0);
  }

  public double getArmEncoder() {
    return armEncoder.getDistance();
  }

  public void resetArmEncoder() {
    armEncoder.reset();
  
  }


  @Override
  public void periodic() {
    if (!bottomLimitSwitch.get()) {
      resetArmEncoder();
    }

    SmartDashboard.putNumber("ArmEncoder", armEncoder.getDistance());
    SmartDashboard.putNumber("ArmMotorPower ", elevatorMotor1.getAppliedOutput());
    SmartDashboard.putNumber("ArmCurrent1 ", elevatorMotor1.getOutputCurrent());
    SmartDashboard.putNumber("ArmCurrent2 ", elevatorMotor2.getOutputCurrent());
    SmartDashboard.putBoolean("TopLimit", !topLimitSwitch.get());
    SmartDashboard.putBoolean("BottomLimit", !bottomLimitSwitch.get());

  }
}
