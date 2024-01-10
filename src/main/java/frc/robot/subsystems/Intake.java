package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxPIDController;
import com.revrobotics.CANSparkMax.ControlType;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Intake extends SubsystemBase {

 //motor that rotates the entire intake
 private CANSparkMax m_intakeMotorRotate;

 //motor that rolls the intake tape
 private CANSparkMax m_intakeMotorRoller;
 
 private SparkMaxPIDController m_pidRotateController;

 private final RelativeEncoder m_rotateEncoder;

  
  public Intake() {

    m_intakeMotorRotate = new CANSparkMax(Constants.IntakeConstants.ROTATE_MOTOR_ID ,MotorType.kBrushless);
    m_intakeMotorRoller = new CANSparkMax(Constants.IntakeConstants.SPIN_MOTOR_ID ,MotorType.kBrushless);

    m_rotateEncoder = m_intakeMotorRotate.getEncoder();

    m_pidRotateController = m_intakeMotorRotate.getPIDController();

    m_pidRotateController.setFeedbackDevice(m_rotateEncoder);

    m_pidRotateController.setP(Constants.IntakeConstants.kP);
    m_pidRotateController.setI(Constants.IntakeConstants.kI);
    m_pidRotateController.setD(Constants.IntakeConstants.kD);
    m_pidRotateController.setIZone(Constants.IntakeConstants.kIz);
    m_pidRotateController.setFF(Constants.IntakeConstants.kFF);
    m_pidRotateController.setOutputRange(-1. * Constants.IntakeConstants.kMaxAbsOutput, Constants.IntakeConstants.kMaxAbsOutput);
  }

  
    public void rotateIntake(double setpoint) {
        m_pidRotateController.setReference(setpoint, ControlType.kPosition);
    }

    public void stopRotateIntake() {
        m_pidRotateController.setReference(0, ControlType.kPosition);
    }

    public void spinIntake(double speed) {
        m_intakeMotorRoller.set(speed);
    }

    public double getRotateEncoderPosition() {
        return m_rotateEncoder.getPosition();
    }

    public void resetRotateEncoder() {
        m_rotateEncoder.setPosition(0);
    }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

}