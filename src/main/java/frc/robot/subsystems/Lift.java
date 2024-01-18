package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.TalonSRXControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRXConfiguration;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
// Importing the SubsystemBase class from the WPILib library
// This class provides the base for creating subsystems, which are major parts of the robot
import edu.wpi.first.wpilibj2.command.SubsystemBase;

// Importing the Constants class from the robot's code
// This class contains constant values used throughout the robot's code
import frc.robot.Constants;


public class Lift extends SubsystemBase {


  private final WPI_TalonSRX motor;


  public Lift() {
    motor = new WPI_TalonSRX(Constants.LiftConstants.MOTOR_ID);
    TalonSRXConfiguration config = new TalonSRXConfiguration();
    config.peakCurrentLimit = Constants.LiftConstants.peakCurrentLimit; // the peak current, in amps
    config.peakCurrentDuration = Constants.LiftConstants.peakCurrentDuration; // the time at the peak current before the limit triggers, in ms
    config.continuousCurrentLimit = Constants.LiftConstants.continuousCurrentLimit; // the current to maintain if the peak limit is triggered

    motor.configAllSettings(config);
    motor.setNeutralMode(NeutralMode.Brake);// apply the config settings; this selects the quadrature encoder

    motor.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, Constants.LiftConstants.TIMEOUT_MS);
    motor.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 1, Constants.LiftConstants.TIMEOUT_MS);
    //moving PID (slot 0)
    motor.config_kP(0, Constants.LiftConstants.kPMoving);
    motor.config_kI(0, Constants.LiftConstants.kIMoving);
    motor.config_kD(0, Constants.LiftConstants.kDMoving);
    motor.config_kF(0, Constants.LiftConstants.kFMoving);

    //holding PID (slot 1)
    motor.config_kP(1, Constants.LiftConstants.kPHolding);
    motor.config_kI(1, Constants.LiftConstants.kIHolding);
    motor.config_kD(1, Constants.LiftConstants.kDHolding);
    motor.config_kF(1, Constants.LiftConstants.kFHolding);

    motor.setSensorPhase(Constants.LiftConstants.REVERSE_ENCODER);
    motor.setSelectedSensorPosition(0, 0, Constants.LiftConstants.TIMEOUT_MS);
    motor.setSelectedSensorPosition(0, 1, Constants.LiftConstants.TIMEOUT_MS);

  }

  // Method to set the speed of the lift motors
  // The speed parameter is a double value between -1.0 and 1.0
public void setLiftToPosition(double position) {
    _deactivateHoldMode();
    motor.set(TalonSRXControlMode.Position, position);
}

public double getLiftPosition() {
  return motor.getSelectedSensorPosition(0);
}

public void stopLift() {
  motor.set(TalonSRXControlMode.PercentOutput, 0);
}

public void _activateHoldMode() {
  // Switch to the secondary PID loop for holding
  motor.selectProfileSlot(1, 0);
}

public void _deactivateHoldMode() {
  // Switch back to the primary PID loop
  motor.selectProfileSlot(0, 0);
}

public void setHoldPosition() {
  double currentPosition = motor.getSelectedSensorPosition(0);
  _activateHoldMode();
  motor.set(TalonSRXControlMode.Position, currentPosition);
}



  // This method will be called once per scheduler run
  // Currently, it does not perform any operations
  @Override
  public void periodic() {
  }
}