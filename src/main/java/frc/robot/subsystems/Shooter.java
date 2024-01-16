package frc.robot.subsystems;

// Importing the CANSparkMax class from the REV Robotics library
// This class provides methods to control the Spark MAX motor controller
import com.revrobotics.CANSparkMax;

// Importing the MotorType enum from the REV Robotics library
// This enum provides constants to specify the type of motor (brushed or brushless)
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

// Importing the SubsystemBase class from the WPILib library
// This class provides the base for creating subsystems, which are major parts of the robot
import edu.wpi.first.wpilibj2.command.SubsystemBase;

// Importing the Constants class from the robot's code
// This class contains constant values used throughout the robot's code
import frc.robot.Constants;

// The Shooter class represents the shooter subsystem of the robot
public class Shooter extends SubsystemBase {

  // The first motor of the shooter subsystem
  // This is a Spark MAX motor controller controlling a brushless motor
  private final CANSparkMax shooterMotor1;

  // The second motor of the shooter subsystem
  // This is also a Spark MAX motor controller controlling a brushless motor
  private final CANSparkMax shooterMotor2;

  // The constructor for the Shooter class
  // This is called when a Shooter object is created
  public Shooter() {
    // Initializing the first shooter motor with its ID and specifying that it's a brushless motor
    shooterMotor1 = new CANSparkMax(Constants.ShooterConstants.MOTOR_1_ID, MotorType.kBrushless);

    // Initializing the second shooter motor with its ID and specifying that it's a brushless motor
    shooterMotor2 = new CANSparkMax(Constants.ShooterConstants.MOTOR_2_ID, MotorType.kBrushless);

    shooterMotor2.follow(shooterMotor1);
  }

  // Method to set the speed of the shooter motors
  // The speed parameter is a double value between -1.0 and 1.0
  public void setShooterSpeed(double speed) {
    // If the absolute value of the speed is greater than the maximum allowed,
    // set the speed to the maximum in the same direction
    if(Math.abs(speed) > Constants.ShooterConstants.kMaxAbsOutput) {
      speed = Math.signum(speed) * Constants.ShooterConstants.kMaxAbsOutput;
    }

    // Set the speed of the first shooter motor and second shooter motor to the specified speed
    shooterMotor1.set(speed);
  }

  // Method to stop the shooter motors
  // This is done by setting the speed of both motors to 0
  public void stopShooter() {
    shooterMotor1.set(0);
  }

  // This method will be called once per scheduler run
  // Currently, it does not perform any operations
  @Override
  public void periodic() {
  }
}