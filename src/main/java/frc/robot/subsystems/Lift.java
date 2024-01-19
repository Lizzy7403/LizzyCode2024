package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.ControlType;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxPIDController;

// Importing the SubsystemBase class from the WPILib library
// This class provides the base for creating subsystems, which are major parts of the robot
import edu.wpi.first.wpilibj2.command.SubsystemBase;

// Importing the Constants class from the robot's code
// This class contains constant values used throughout the robot's code
import frc.robot.Constants;


public class Lift extends SubsystemBase {


  private final CANSparkMax motor;

  private SparkMaxPIDController pidRotateController;

  private final RelativeEncoder encoder;
  


  public Lift() {
    motor = new CANSparkMax(Constants.LiftConstants.MOTOR_ID, MotorType.kBrushless);

    encoder = motor.getEncoder();

    pidRotateController = motor.getPIDController();

    pidRotateController.setFeedbackDevice(encoder);
  
    pidRotateController.setP(Constants.LiftConstants.kP);
    pidRotateController.setI(Constants.LiftConstants.kI);
    pidRotateController.setD(Constants.LiftConstants.kD);
    pidRotateController.setIZone(Constants.LiftConstants.kIz);
    pidRotateController.setFF(Constants.LiftConstants.kFF);

    motor.setClosedLoopRampRate(1);

    //output range
    pidRotateController.setOutputRange( -1 * Constants.LiftConstants.kMaxAbsOutput, Constants.LiftConstants.kMaxAbsOutput);
  }

  public void setPosition(double position) {
    pidRotateController.setReference(position, ControlType.kPosition);
  }

  public void resetRotateEncoder() {
    encoder.setPosition(0);
  }

  public void stopLift() {
    motor.set(0);
  }

  public void lockPosition(double kP, double kI, double kD, double kIz, double kFF) {
    setPID(kP, kI, kD, kIz, kFF);
    pidRotateController.setReference(encoder.getPosition(), ControlType.kPosition);
  }

  public void setPID(double kP, double kI, double kD, double kIz, double kFF){
    pidRotateController.setP(kP);
    pidRotateController.setI(kI);
    pidRotateController.setD(kD);
    pidRotateController.setIZone(kIz);
    pidRotateController.setFF(kFF);

  }

  public void resetPID(){
    pidRotateController.setP(Constants.LiftConstants.kP);
    pidRotateController.setI(Constants.LiftConstants.kI);
    pidRotateController.setD(Constants.LiftConstants.kD);
    pidRotateController.setIZone(Constants.LiftConstants.kIz);
    pidRotateController.setFF(Constants.LiftConstants.kFF);
  }

  

  @Override
  public void periodic() {
  }
}