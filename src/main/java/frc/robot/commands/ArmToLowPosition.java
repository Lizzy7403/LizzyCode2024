// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj2.command.PIDCommand;
import frc.robot.Constants;
import frc.robot.subsystems.Arm;
//import frc.robot.subsystems.ScrewElevator;


//TODO extends PIDCOMMAND
public class ArmToLowPosition extends PIDCommand{ 

  private Arm arm;
  private double distance;
  

  // Crea un nuevo ArmToPosition 
  public ArmToLowPosition(double distance, Arm arm) {

    

    super(
        // El controlador que va a usar el comando
        new PIDController(0.1, 
        0.001,
        Preferences.getDouble("D - elevator", 0.0)),
        // Devuelve la medida del liftEncoder
        () -> arm.getArmEncoder(),
        // Deberia devolver el setpoint (puede ser una constante)
        () -> distance,
        // Esto usa el output
        output -> {
          arm.setArm(output * Constants.speedElevatorMotor);
          // Usa el output en este espacio
        });
    addRequirements(arm);
    // Usa addRequirements() para declarar las dependencias del subsistema.
    // Configura las opciones adicionales del PID llamando 'getController'
    this.arm = arm;
    this.distance = distance;

  }

  // Devuelve true cuando el comando acabe
  @Override
  public boolean isFinished() {
    if(distance > 10){
      
      return getController().atSetpoint() || !arm.getBottomLimitSwitch().get();

    }
    else{
      
      return false;

    }
  }

  
}
