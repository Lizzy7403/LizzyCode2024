// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Tele extends SubsystemBase {

  private CANSparkMax teleMotor;

  public Encoder teleEncoder = new Encoder(2, 3);


  /** Creates a new Tele. */
  public Tele() {

    super();
    teleMotor = new CANSparkMax(Constants.telescopicId, MotorType.kBrushed);
    teleEncoder.setDistancePerPulse(1. / 2048);
    teleMotor.setInverted(true);
    

  }

  public void setTeleMotor(double speed) {
    teleMotor.set(speed);
  }

  public void stopTele() {
    teleMotor.set(0);
  }

  public double getTeleEncoder() {
    return teleEncoder.getDistance();
  }

  public void resetTeleEncoder() {
    teleEncoder.reset();
    
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    SmartDashboard.putNumber("TeleEncoder", teleEncoder.getDistance());
    SmartDashboard.putNumber("TeleMotorPower ", teleMotor.getAppliedOutput() * 100);
  

   
  }
}
