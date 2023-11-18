// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj2.command.PIDCommand;
import frc.robot.Constants;
//import frc.robot.subsystems.ScrewElevator;
import frc.robot.subsystems.Tele;


//TODO extends PIDCOMMAND
public class TeleToPosition extends PIDCommand{ 


  // Crea un nuevo ArmToPosition 
  public TeleToPosition(double distance, Tele tele) {

    super(
        // El controlador que va a usar el comando
        new PIDController(0.1, 
        0.001,
        Preferences.getDouble("D - telescopic", 0.0)),
        // Devuelve la medida del liftEncoder
        () -> tele.getTeleEncoder(),
        // Deberia devolver el setpoint (puede ser una constante)
        () -> distance,
        // Esto usa el output
        output -> {
          tele.setTeleMotor(output * Constants.speedTelescopicMotor);
          // Usa el output en este espacio
        });
    //addRequirements(elevator);
    // Usa addRequirements() para declarar las dependencias del subsistema.
    // Configura las opciones adicionales del PID llamando 'getController'
    
  }

  // Devuelve true cuando el comando acabe
  @Override
  public boolean isFinished() {
    return getController().atSetpoint();
  }

  
}
