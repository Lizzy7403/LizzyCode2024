package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

import frc.robot.autos.*;
import frc.robot.commands.*;
import frc.robot.subsystems.*;

/**
 * This class is where the bulk of the robot should be declared. Since
 * Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in
 * the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of
 * the robot (including
 * subsystems, commands, and button mappings) should be declared here.
*/

/*Esta clase es donde se debe declarar la mayor parte del robot. Desde
 * command-based es un
 * paradigma "declarativo", muy poca lógica de robot debería manejarse en realidad
 * el {@robot de enlace}
 * métodos periódicos (aparte de las llamadas del programador). En cambio, la estructura de
 * el robot (incluyendo
 * subsistemas, comandos y asignaciones de botones) deben declararse aquí.
 * */

//para informacion sobre el commandgroup link:https://docs.wpilib.org/en/stable/docs/software/commandbased/command-compositions.html
public class RobotContainer{
    /* Subsystems declaration */
    
    // Declaracion de subsistemas//

    //private final ScrewElevator m_screwElevator = new ScrewElevator();
    private final Intake m_intake = new Intake();
    private final Shooter m_shooter = new Shooter();
    // Controller
    private final Tele m_tele = new Tele();

    /* Controllers */
    // Controles//
    private final Joystick driver = new Joystick(0);

    /* Drive Controls */
    // Controles de conduccion//
    private final int translationAxis = 1; 
    private final int strafeAxis = 0; 
    private final int rotationAxis = 2;

    boolean isIntakeExtended = false;

    boolean isIntakeExtendedSpin = false;


    /* Driver Buttons */
    // Botones del controlador//
    private final JoystickButton zeroGyro = new JoystickButton(driver, Constants.ButtonOption);
    private final JoystickButton robotCentric = new JoystickButton(driver, Constants.TouchPad);

    //private final JoystickButton changeRotVelo = new JoystickButton(driver, Constants.ButtonR3);

    /* Intake Buttons */
    // Botones del ingreso//

    //spins the intake such that it intakes hoops
    private final JoystickButton intakeSpinButton = new JoystickButton(driver, Constants.ButtonSquare);

    //spins the arm of the intake to extend or retract arm
    private final JoystickButton intakeRotateButton = new JoystickButton(driver, Constants.ButtonTriangle);

    //spins the arm of the intake to extend at a controlled speed
    private final JoystickButton intakeExtendControlled = new JoystickButton(driver, Constants.ButtonR2);

    //spins the arm of the intake to retract at a controlled speed
    private final JoystickButton intakeRetractControlled = new JoystickButton(driver, Constants.ButtonL2);

    //shoots the hoops at a high speed,to reach the speaker
    private final JoystickButton shooterButtonHigh = new JoystickButton(driver, Constants.ButtonCircle);

    //shoots the hoops at a low speed, to reach the amp
    private final JoystickButton shooterButtonLow = new JoystickButton(driver, Constants.ButtonX);

    //lifts the lift to the top
    private final JoystickButton liftUpControlled = new JoystickButton(driver, Constants.ButtonR1);

    //lowers the lift to the bottom
    private final JoystickButton liftDownControlled = new JoystickButton(driver, Constants.ButtonL1);

    /* Subsystems */
    // Subsistemas//
    private final Swerve s_Swerve = new Swerve();

    // CHANGED
    /**
     * The container for the robot. Contains subsystems, OI devices, and commands.
     */
    // El contenedor del robot. Contiene subsistemas, OI dispositivos, y comandos //

    public boolean isIntakeExtended() {
        return isIntakeExtended;
    }

    public void toggleIntakeExtension() {
        isIntakeExtended = !isIntakeExtended;
        isIntakeExtendedSpin = !isIntakeExtendedSpin;
    }
    
    public RobotContainer() {
        s_Swerve.setDefaultCommand(
                new TeleopSwerve(
                        s_Swerve,
                        () -> driver.getRawAxis(translationAxis),
                        () -> driver.getRawAxis(strafeAxis),
                        () -> -driver.getRawAxis(rotationAxis),
                        () -> robotCentric.getAsBoolean()));

        // Configure the button bindings
        // Configura el boton bindings
        configureButtonBindings();
        m_intake.resetRotateEncoder();
    }

    /**
     * Use this method to define your button->command mappings. Buttons can be
     * created by
     * instantiating a {@link GenericHID} or one of its subclasses ({@link
     * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing
     * it to a {@link
     * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
     */
    private void configureButtonBindings() {
        /* Driver Buttons */
        // Botones de conduccion//
        zeroGyro.onTrue(new InstantCommand(() -> s_Swerve.zeroGyro()));

        //** INTAKE BUTTONS */
        intakeSpinButton.whileTrue(new Collect(m_intake,Constants.IntakeConstants.collectSpeed));
        
        //intakeSpinButton.whileTrue(new SpinIntakeCommand(m_intake, Constants.IntakeConstants.kMaxAbsOutputRBRetracted, Constants.IntakeConstants.kMaxAbsOutputRBExtended,   isIntakeExtendedSpin));
        intakeRotateButton.onTrue(new ToggleIntakeCommand(m_intake, Constants.IntakeConstants.kRotationSetpointHigh, Constants.IntakeConstants.kRotationSetpointLow, this::isIntakeExtended, this::toggleIntakeExtension));
        intakeExtendControlled.whileTrue(new RotateIntakeCommand(m_intake, Constants.IntakeConstants.kRotationSetpointHigh));
        intakeRetractControlled.whileTrue(new RotateIntakeCommand(m_intake, Constants.IntakeConstants.kRotationSetpointLow));

        //** SHOOTER BUTTONS */
        shooterButtonHigh.onTrue((Commands.parallel(Commands.waitSeconds(1).asProxy().andThen(new Feed(m_intake,0.2).withTimeout(1)),new RotateIntakeCommand(m_intake, Constants.IntakeConstants.kRotationSetpointHigh))));
        //shooterButtonHigh.onTrue(new ShootCommand(m_shooter, Constants.ShooterConstants.kMaxAbsOutputRBHigh));
        shooterButtonLow.onTrue(new ShootCommand(m_shooter, Constants.ShooterConstants.kMaxAbsOutputRBLow));

        //** LIFT BUTTONS */
        liftUpControlled.whileTrue(new LiftUpCommand());
        liftDownControlled.whileTrue(new LiftDownCommand());

    }

    /**
     * Use this to pass the autonomous command to the main {@link Robot} class.
     *
     * @return the command to run in autonomous
     */
    public Command getAutonomousCommand() {
        // An ExampleCommand will run in autonomous
        // Un ejemplo en comando se ejecutara en autonomo
        return new exampleAuto(s_Swerve);
    }

    public Command getAutonomousRight() {
        // An ExampleCommand will run in autonomous
        // Un ejemplo en comando se ejecutara en autonomo
        return new AutoRight(s_Swerve, m_tele);
    }

    public Command getAutonomousLeft() {
        // An ExampleCommand will run in autonomous
        // Un ejemplo en comando se ejecutara en autonomo
        return new AutoLeft(s_Swerve, m_tele);
    }

    public Command getAutonomousMiddle() {
        // An ExampleCommand will run in autonomous
        // Un ejemplo en comando se ejecutara en autonomo
        return new AutoMiddle(s_Swerve,m_tele);
    }
    public Command getAutonomousExp(){

        return new AutoExp(s_Swerve,m_tele);
    }


}