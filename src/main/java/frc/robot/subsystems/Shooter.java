package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Shooter extends SubsystemBase {

 private final CANSparkMax shooterMotor1;
 private final CANSparkMax shooterMotor2;

  
  public Shooter() {

    shooterMotor1 = new CANSparkMax(Constants.ShooterConstants.MOTOR_1_ID, MotorType.kBrushless);
    shooterMotor2 = new CANSparkMax(Constants.ShooterConstants.MOTOR_2_ID, MotorType.kBrushless);
   
  }

  public void setShooterSpeed(double speed) {
    if(Math.abs(speed) > Constants.ShooterConstants.kMaxAbsOutput) {
      speed = Math.signum(speed) * Constants.ShooterConstants.kMaxAbsOutput;
    }
    shooterMotor1.set(speed);
    shooterMotor2.set(speed);
}

public void stopShooter() {
    shooterMotor1.set(0);
    shooterMotor2.set(0);
}

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

}