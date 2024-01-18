package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.TalonSRXControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.TalonSRXConfiguration;

// Importing the SubsystemBase class from the WPILib library
// This class provides the base for creating subsystems, which are major parts of the robot
import edu.wpi.first.wpilibj2.command.SubsystemBase;

// Importing the Constants class from the robot's code
// This class contains constant values used throughout the robot's code
import frc.robot.Constants;

// The Shooter class represents the shooter subsystem of the robot
public class Lift extends SubsystemBase {

  // The first motor of the shooter subsystem
  // This is a Spark MAX motor controller controlling a brushless motor
  private final TalonSRX motor;

  // The constructor for the Shooter class
  // This is called when a Shooter object is created
  public Lift() {
    // Initializing the first shooter motor with its ID and specifying that it's a brushless motor
    motor = new TalonSRX(Constants.LiftConstants.MOTOR_ID);
    TalonSRXConfiguration config = new TalonSRXConfiguration();
    config.peakCurrentLimit = Constants.LiftConstants.peakCurrentLimit; // the peak current, in amps
    config.peakCurrentDuration = Constants.LiftConstants.peakCurrentDuration; // the time at the peak current before the limit triggers, in ms
    config.continuousCurrentLimit = Constants.LiftConstants.continuousCurrentLimit; // the current to maintain if the peak limit is triggered
    motor.configAllSettings(config); // apply the config settings; this selects the quadrature encoder
  }

  // Method to set the speed of the shooter motors
  // The speed parameter is a double value between -1.0 and 1.0
  public void setLiftToSpeed(double speed) {
    // If the absolute value of the speed is greater than the maximum allowed,
    // set the speed to the maximum in the same direction
    if(Math.abs(speed) > Constants.LiftConstants.kMaxAbsOutput) {
        motor.set(TalonSRXControlMode.PercentOutput, Constants.LiftConstants.kMaxAbsOutput * Math.signum(speed));
    }

    // Set the speed of the first shooter motor and second shooter motor to the specified speed
    motor.set(TalonSRXControlMode.PercentOutput, speed);
  }

  // Method to stop the shooter motors
  // This is done by setting the speed of both motors to 0
  public void stopLift() {
    motor.set(TalonSRXControlMode.PercentOutput, 0);
  }

  // This method will be called once per scheduler run
  // Currently, it does not perform any operations
  @Override
  public void periodic() {
  }
}