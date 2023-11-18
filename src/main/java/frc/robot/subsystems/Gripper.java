// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Gripper extends SubsystemBase {

  private DoubleSolenoid m_doubleSolenoid;

  /** Creates a new Gripper. */
  // Crea un Gripper nuevo
  public Gripper() {

    m_doubleSolenoid = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, 0, 1);

  }

  // Cerrar el Gripper
  public void closeGrip() {
    m_doubleSolenoid.set(DoubleSolenoid.Value.kForward);
  }

  // Abrir el Gripper
  public void openGrip() {
    m_doubleSolenoid.set(DoubleSolenoid.Value.kReverse);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}