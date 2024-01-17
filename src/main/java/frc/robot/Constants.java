package frc.robot;

import com.ctre.phoenix.motorcontrol.NeutralMode;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import edu.wpi.first.math.trajectory.TrapezoidProfile;

import frc.lib.util.COTSFalconSwerveConstants;
import frc.lib.util.SwerveModuleConstants;

public final class Constants {

    public static final class IntakeConstants {
        public static final int ROTATE_MOTOR_ID = 14;
        public static final int SPIN_MOTOR_ID = 16;

        public static final double kP = 0.01;
        public static final double kI = 0.0000003;
        public static final double kD = 0.0000018;
        public static final double kIz = 30.0;
        public static final double kFF = 0.0;

        public static final double kRotationSetpointHigh = 300;
        public static final double kRotationSetpointLow = 0;

        //max output at intake class
        public static final double kMaxAbsOutput = 0.5;

        //max output at robot container class
        public static final double kMaxAbsOutputRBExtended = 0.1;
        public static final double kMaxAbsOutputRBRetracted = 0.5;
        public static double collectSpeed=0.3;
    }

     public static final class ShooterConstants {
        public static final int MOTOR_1_ID = 18;
        public static final int MOTOR_2_ID = 19;
        //max output at shooter class
        public static final double kMaxAbsOutput = 0.3;
        //max output at robot container class for high shot
        public static final double kMaxAbsOutputRBHigh = .3;
        //max output at robot container class for low shot
        public static final double kMaxAbsOutputRBLow = .15;
    }



    public static final double stickDeadband = 0.1;

    /*Autonomous Constants */
    public static final double autonomousArmToPositionValue = 180;
    public static final double autonomousArmToPositionValueExp = 210;
    public static final double autonomousArmToPositionValueExp2 = 20;
    public static final double autonomousArmToPositionTime = 3.5;
    public static final double autonomousTeleManualTime = 2;
    public static final double autonomousTeleManualTimeExp = 1.65; //was at 2
    public static final double autonomousUpDelayTime = 0.5;
    public static final double autonomousUpDelayTimeExp = 1.5;
    public static final double autonomousDownDelayTime = 1;
    

   /*ScrewElevator Constants */
       
   public static final double speedElevatorMotor = 0.1;
   public static final int telescopicId = 13;
   public static final double speedTelescopicMotor = 0.1;

   /*Manual Constants */
   public static final double elevatorManualUpSpeed = 0.6;
   public static final double elevatorManualDownSpeed = -0.4;


   /*ArmToZero */
   public static final double armToZeroVelocity = -0.2;


   /* Tele constants */
   public static final double teleManualUpVelocity = .8; 
   public static final double teleManualDownVelocity = -.8; 
  
/*Button Constants */

public static final int ButtonSquare = 1;
public static final int ButtonX = 2;
public static final int ButtonTriangle = 4;
public static final int ButtonCircle = 3;
public static final int ButtonR2 = 8;
public static final int ButtonL2 = 7;
public static final int ButtonL1 = 5;
public static final int ButtonR1 = 6;
public static final int ButtonL3 = 13;
public static final int ButtonR3 = 14;
public static final int ButtonShare = 9;
public static final int ButtonOption = 10;
public static final int PSButton = 15;
public static final int TouchPad = 16;


    /*Swerve Speed Constants */
    // Estas constantes riven para cambiar la velocidad y velocidad angular del robot
    public static final double dampenDriveSpeed = 0.7;
    public static double dampenRotationSpeed = 0.4;

    


    public static final class Swerve {
        public static final int pigeonID = 16;
        public static final boolean invertGyro = false; // Always ensure Gyro is CCW+ CW-

        public static final COTSFalconSwerveConstants chosenModule =  //TODO: This must be tuned to specific robot
            COTSFalconSwerveConstants.SDSMK4i(COTSFalconSwerveConstants.driveGearRatios.SDSMK4i_L2);

       
          
       
            /* Drivetrain Constants */
        public static final double trackWidth = 0.575; //TODO: This must be tuned to specific robot
        public static final double wheelBase = 0.575; //TODO: This must be tuned to specific robot
        public static final double wheelCircumference = chosenModule.wheelCircumference;

        /* Swerve Kinematics 
         * No need to ever change this unless you are not doing a traditional rectangular/square 4 module swerve */
         public static final SwerveDriveKinematics swerveKinematics = new SwerveDriveKinematics(
            new Translation2d(wheelBase / 2.0, trackWidth / 2.0),
            new Translation2d(wheelBase / 2.0, -trackWidth / 2.0),
            new Translation2d(-wheelBase / 2.0, trackWidth / 2.0),
            new Translation2d(-wheelBase / 2.0, -trackWidth / 2.0));

        /* Module Gear Ratios */
        public static final double driveGearRatio = chosenModule.driveGearRatio;
        public static final double angleGearRatio = chosenModule.angleGearRatio;

        /* Motor Inverts */
        public static final boolean angleMotorInvert = chosenModule.angleMotorInvert;
        public static final boolean driveMotorInvert = chosenModule.driveMotorInvert;

        /* Angle Encoder Invert */
        public static final boolean canCoderInvert = chosenModule.canCoderInvert;

        /* Swerve Current Limiting */
        public static final int angleContinuousCurrentLimit = 25;
        public static final int anglePeakCurrentLimit = 40;
        public static final double anglePeakCurrentDuration = 0.1;
        public static final boolean angleEnableCurrentLimit = true;

        public static final int driveContinuousCurrentLimit = 35;
        public static final int drivePeakCurrentLimit = 60;
        public static final double drivePeakCurrentDuration = 0.1;
        public static final boolean driveEnableCurrentLimit = true;

        /* These values are used by the drive falcon to ramp in open loop and closed loop driving.
         * We found a small open loop ramp (0.25) helps with tread wear, tipping, etc */
        public static final double openLoopRamp = 0.25;
        public static final double closedLoopRamp = 0.0;

        /* Angle Motor PID Values */
        //Valores PID del motor angular//
        public static final double angleKP = chosenModule.angleKP - 0.2;
        public static final double angleKI = chosenModule.angleKI;
        public static final double angleKD = chosenModule.angleKD;
        public static final double angleKF = chosenModule.angleKF;

        /* Drive Motor PID Values */
        //Valores PID del motor//
        public static final double driveKP = 0.1; //TODO: This must be tuned to specific robot
        public static final double driveKI = 0.0; //MIGHT CHANGE
        public static final double driveKD = 0.0;
        public static final double driveKF = 0.0;

        /* Drive Motor Characterization Values 
         * Divide SYSID values by 12 to convert from volts to percent output for CTRE */
        //Caracterizacion del motor divide los valores por 12 
        public static final double driveKS = (0.667 / 12); //TODO: This must be tuned to specific robot
        public static final double driveKV = (2.44 / 12);  //MIGHT CHANGE
        public static final double driveKA = (0.27 / 12);

        /* Swerve Profiling Values */
        /** Meters per Second */
        public static final double maxSpeed = 4.96824; //TODO: This must be tuned to specific robot
        /** Radians per Second */
        public static final double maxAngularVelocity = 11.5; //TODO: This must be tuned to specific robot

        /* Neutral Modes */
        public static final NeutralMode angleNeutralMode = NeutralMode.Coast;
        public static final NeutralMode driveNeutralMode = NeutralMode.Brake;

        /* Module Specific Constants */
        /* Front Left Module - Module 0 */
        //Constantes especificos del modulo
        // Modulo frontal izquierdo - Modulo 0
        public static final class Mod0 { 
            public static final int driveMotorID = 1;
            public static final int angleMotorID = 2;
            public static final int canCoderID = 9;
            public static final Rotation2d angleOffset = Rotation2d.fromDegrees(44.208984377);
            public static final SwerveModuleConstants constants = 
                new SwerveModuleConstants(driveMotorID, angleMotorID, canCoderID, angleOffset);
        }

        /* Front Right Module - Module 1 */
        // Modulo frontal derecho - Modulo 1
        public static final class Mod1 { 
            public static final int driveMotorID = 7;
            public static final int angleMotorID = 8;
            public static final int canCoderID = 12;
            public static final Rotation2d angleOffset = Rotation2d.fromDegrees(58.359375);
            public static final SwerveModuleConstants constants = 
                new SwerveModuleConstants(driveMotorID, angleMotorID, canCoderID, angleOffset);
        }
        
        /* Back Left Module - Module 2 */
        //Modulo trasero izquierdo-Modulo 2
        public static final class Mod2 { 
            public static final int driveMotorID = 3;
            public static final int angleMotorID = 4;
            public static final int canCoderID = 10;
            public static final Rotation2d angleOffset = Rotation2d.fromDegrees(77.34375);
            public static final SwerveModuleConstants constants = 
                new SwerveModuleConstants(driveMotorID, angleMotorID, canCoderID, angleOffset);
        }

        /* Back Right Module - Module 3 */
        //Modulo trasero dercho - modulo 3
        public static final class Mod3 { 
            public static final int driveMotorID = 5;
            public static final int angleMotorID = 6;
            public static final int canCoderID = 11;
            public static final Rotation2d angleOffset = Rotation2d.fromDegrees(183.594140625);
            public static final SwerveModuleConstants constants = 
                new SwerveModuleConstants(driveMotorID, angleMotorID, canCoderID, angleOffset);
        }
    }

    // Constantes del ejempllo auto, las cuales deben de estar especificadas a cada robot
    public static final class AutoConstants { //TODO: The below constants are used in the example auto, and must be tuned to specific robot
        public static final double kMaxSpeedMetersPerSecond = 3;
        public static final double kMaxAccelerationMetersPerSecondSquared = 3;
        public static final double kMaxAngularSpeedRadiansPerSecond = Math.PI;
        public static final double kMaxAngularSpeedRadiansPerSecondSquared = Math.PI;
    
        public static final double kPXController = 1;
        public static final double kPYController = 1;
        public static final double kPThetaController = 1;
    
        /* Constraint for the motion profilied robot angle controller */
        // Restriccion para el movimiento perfilado del robot en controlador angular
        public static final TrapezoidProfile.Constraints kThetaControllerConstraints =
            new TrapezoidProfile.Constraints(
                kMaxAngularSpeedRadiansPerSecond, kMaxAngularSpeedRadiansPerSecondSquared);
    }
}