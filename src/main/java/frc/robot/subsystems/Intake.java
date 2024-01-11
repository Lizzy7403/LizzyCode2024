package frc.robot.subsystems;

// Importing the CANSparkMax class from the REV Robotics library
// This class provides methods to control the Spark MAX motor controller
import com.revrobotics.CANSparkMax;

// Importing the RelativeEncoder class from the REV Robotics library
// This class provides methods to get the position and velocity of the encoder
import com.revrobotics.RelativeEncoder;

// Importing the SparkMaxPIDController class from the REV Robotics library
// This class provides methods to control a motor using PID
import com.revrobotics.SparkMaxPIDController;

// Importing the ControlType enum from the REV Robotics library
// This enum provides constants to specify the control type for the PID controller
import com.revrobotics.CANSparkMax.ControlType;

// Importing the MotorType enum from the REV Robotics library
// This enum provides constants to specify the type of motor (brushed or brushless)
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

// Importing the SubsystemBase class from the WPILib library
// This class provides the base for creating subsystems, which are major parts of the robot
import edu.wpi.first.wpilibj2.command.SubsystemBase;

// Importing the Constants class from the robot's code
// This class contains constant values used throughout the robot's code
import frc.robot.Constants;

// The Intake class represents the intake subsystem of the robot
public class Intake extends SubsystemBase {

  // The motor that rotates the entire intake
  // This is a Spark MAX motor controller controlling a brushless motor
  private CANSparkMax m_intakeMotorRotate;

  // The motor that rolls the intake tape
  // This is also a Spark MAX motor controller controlling a brushless motor
  private CANSparkMax m_intakeMotorRoller;

  // The PID controller for the rotation motor
  // This is used to control the position of the intake
  private SparkMaxPIDController m_pidRotateController;

  // The encoder for the rotation motor
  // This is used to get the position and velocity of the intake
  private final RelativeEncoder m_rotateEncoder;

  // The constructor for the Intake class
  // This is called when an Intake object is created
  public Intake() {

    // Initializing the rotation motor with its ID and specifying that it's a brushless motor
    m_intakeMotorRotate = new CANSparkMax(Constants.IntakeConstants.ROTATE_MOTOR_ID ,MotorType.kBrushless);

    // Initializing the roller motor with its ID and specifying that it's a brushless motor
    m_intakeMotorRoller = new CANSparkMax(Constants.IntakeConstants.SPIN_MOTOR_ID ,MotorType.kBrushless);

    // Getting the encoder from the rotation motor
    m_rotateEncoder = m_intakeMotorRotate.getEncoder();

    // Getting the PID controller from the rotation motor
    m_pidRotateController = m_intakeMotorRotate.getPIDController();

    // Setting the encoder as the feedback device for the PID controller
    m_pidRotateController.setFeedbackDevice(m_rotateEncoder);

    // Setting the PID constants for the PID controller
    m_pidRotateController.setP(Constants.IntakeConstants.kP);
    m_pidRotateController.setI(Constants.IntakeConstants.kI);
    m_pidRotateController.setD(Constants.IntakeConstants.kD);
    m_pidRotateController.setIZone(Constants.IntakeConstants.kIz);
    m_pidRotateController.setFF(Constants.IntakeConstants.kFF);

    // Setting the output range for the PID controller
    m_pidRotateController.setOutputRange(-1. * Constants.IntakeConstants.kMaxAbsOutput, Constants.IntakeConstants.kMaxAbsOutput);
  }

  // Method to rotate the intake to a specific position
  // The setpoint parameter is the desired position of the intake
  public void rotateIntake(double setpoint) {
    m_pidRotateController.setReference(setpoint, ControlType.kPosition);
  }

  // Method to stop rotating the intake
  // This is done by setting the desired position of the intake to 0
  public void stopRotateIntake() {
    m_pidRotateController.setReference(0, ControlType.kPosition);
  }

  // Method to spin the intake at a specific speed
  // The speed parameter is a double value between -1.0 and 1.0
  public void spinIntake(double speed) {
    m_intakeMotorRoller.set(speed);
  }

  // Method to get the current position of the intake
  // This is done by getting the position from the encoder
  public double getRotateEncoderPosition() {
    return m_rotateEncoder.getPosition();
  }

  // Method to reset the position of the intake
  // This is done by setting the position of the encoder to 0
  public void resetRotateEncoder() {
    m_rotateEncoder.setPosition(0);
  }

  // This method will be called once per scheduler run
  // Currently, it does not perform any operations
  @Override
  public void periodic() {
  }
}