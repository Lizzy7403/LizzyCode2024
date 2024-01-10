package frc.robot.autos;

import frc.robot.Constants;
import frc.robot.subsystems.Swerve;
import frc.robot.subsystems.Tele;
import frc.robot.commands.*;

import java.util.List;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.controller.ProfiledPIDController;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
//import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.trajectory.Trajectory;
import edu.wpi.first.math.trajectory.TrajectoryConfig;
import edu.wpi.first.math.trajectory.TrajectoryGenerator;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.SwerveControllerCommand;
import edu.wpi.first.wpilibj2.command.WaitCommand;

public class AutoMiddle extends SequentialCommandGroup {
    public AutoMiddle(Swerve s_Swerve, Tele m_tele){
        TrajectoryConfig config =
            new TrajectoryConfig(
                    Constants.AutoConstants.kMaxSpeedMetersPerSecond,
                    Constants.AutoConstants.kMaxAccelerationMetersPerSecondSquared)
                .setKinematics(Constants.Swerve.swerveKinematics);

        // An example trajectory to follow.  All units in meters.
        Trajectory exampleTrajectory =
            TrajectoryGenerator.generateTrajectory(
                // Start at the origin facing the +X direction
                new Pose2d(0, 0, new Rotation2d(0)),
                // Pass through these two interior waypoints, making an 's' curve path
                List.of(),//5.3//new Translation2d(5, 0), new Translation2d(5, 5)
                // End 3 meters straight ahead of where we started, facing forward
                new Pose2d(5.3, .3, new Rotation2d(0)), //2.28
                config);
            Trajectory exampleTrajectory2 =
                TrajectoryGenerator.generateTrajectory(
                    // Start at the origin facing the +X direction
                    new Pose2d(5.3, 0, new Rotation2d(0)),
                    // Pass through these two interior waypoints, making an 's' curve path
                    List.of(new Translation2d(5.3,1)),//5.3//new Translation2d(5, 0), new Translation2d(5, 5)
                    // End 3 meters straight ahead of where we started, facing forward
                    new Pose2d(2.28, 0, new Rotation2d(0)), //2.28
                    config);

        var thetaController =
            new ProfiledPIDController(
                Constants.AutoConstants.kPThetaController, 0, 0, Constants.AutoConstants.kThetaControllerConstraints);
        thetaController.enableContinuousInput(-Math.PI, Math.PI);

        SwerveControllerCommand swerveControllerCommand =
            new SwerveControllerCommand(
                exampleTrajectory,
                s_Swerve::getPose,
                Constants.Swerve.swerveKinematics,
                new PIDController(Constants.AutoConstants.kPXController, 0, 0),
                new PIDController(Constants.AutoConstants.kPYController, 0, 0),
                thetaController,
                s_Swerve::setModuleStates,
                s_Swerve);


        addCommands(
           // new InstantCommand(() -> s_Swerve.resetOdometry(exampleTrajectory.getInitialPose())), new InstantCommand(() -> s_Swerve.resetOdometry(exampleTrajectory2.getInitialPose())),
           // Commands.parallel(new ArmToPosition(Constants.autonomousArmToPositionValueExp, m_arm).withTimeout(Constants.autonomousArmToPositionTime) , new WaitCommand(Constants.autonomousUpDelayTimeExp).andThen(new teleManualUp(m_tele).withTimeout(Constants.autonomousTeleManualTimeExp))),
           // new openGripper(m_gripper).andThen(new WaitCommand(0.5)),
           // Commands.parallel(new WaitCommand(Constants.autonomousDownDelayTime).andThen(new ArmToZero(m_arm)), new teleManualDown(m_tele).withTimeout(Constants.autonomousTeleManualTimeExp)),
           // swerveControllerCommand
        );
    }
}