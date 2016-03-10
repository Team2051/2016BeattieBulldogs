package org.usfirst.frc.team2051.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team2051.robot.commands.*;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI 
{
    //// CREATING BUTTONS
    // One type of button is a joystick button which is any button on a joystick.
    // You create one by telling it which joystick it's on and which button
    // number it is.
    // Joystick stick = new Joystick(port);
    // Button button = new JoystickButton(stick, buttonNumber);
    
    // There are a few additional built in buttons you can use. Additionally,
    // by subclassing Button you can create custom triggers and bind those to
    // commands the same as any other Button.
    
    //// TRIGGERING COMMANDS WITH BUTTONS
    // Once you have a button, it's trivial to bind it to a button in one of
    // three ways:
    
    // Start the command when the button is pressed and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenPressed(new ExampleCommand());
    
    // Run the command while the button is being held down and interrupt it once
    // the button is released.
    // button.whileHeld(new ExampleCommand());
    
    // Start the command when the button is released  and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenReleased(new ExampleCommand());
	
	public Joystick driveStick;
	public Joystick shooterStick;
	
	public JoystickButton autoApproachBtn;
	public JoystickButton autoBreachBtn;
	
	public JoystickButton intakeBtn;
	public JoystickButton shootLowBtn;
	public JoystickButton shootHighBtn;
	public JoystickButton openRampBtn;
	public JoystickButton closeRampBtn;
	
	public OI() 
	{
		driveStick = new Joystick(0);
		shooterStick = new Joystick(1);
		
		//Driver Joystick Controls
		autoApproachBtn = new JoystickButton(driveStick, 3);
		autoApproachBtn.whenPressed(new AutoApproachDefenses());
		
		autoBreachBtn = new JoystickButton(driveStick, 4);
		autoBreachBtn.whenPressed(new AutoBreachDefenses());
		
		
		//Shooter Joystick Controls
		intakeBtn = new JoystickButton(shooterStick, 1);
		intakeBtn.whileHeld(new IntakeBall());
		
		shootLowBtn = new JoystickButton(shooterStick, 2);
		shootLowBtn.whileHeld(new ShootLow());
		
		shootHighBtn = new JoystickButton(shooterStick, 3);
		shootHighBtn.whileHeld(new ShootHigh());
		
		openRampBtn = new JoystickButton(shooterStick, 4);
		openRampBtn.whenPressed(new OpenRamp());
		
		closeRampBtn = new JoystickButton(shooterStick, 5);
		closeRampBtn.whenPressed(new CloseRamp());
		
		SmartDashboard.putData("AutoApproach", new AutoApproachDefenses());
	}
	
	public Joystick getDriveStick() 
	{
		return driveStick;
	}
	
	public Joystick getShooterStick()
	{
		return shooterStick;
	}
	
	/**
	 * Joystick deadband function that behaves consistently with positive and
	 * negative inputs Uses the curve f(x) = x ^ (1/x) Type this into Google
	 * Search to graph it: graph x^(1/x) Returns zero for values less than about
	 * 0.25, then scales up to return 1 for an input of 1
	 */
	public static double deadBand(double x)
	{
		if (x > 0)
			return Math.pow(x, 1.0 / x);
		else if (x < 0)
			return -deadBand(-x);
		else
			return 0;
	}

	/** 
	 * Translate throttle value from joystick (range -1..+1) into motor speed (range 0..+1)
	 * The joystick value in the top position is -1, opposite of what might be expected,
	 * so multiply by negative one to invert the readings.
	 */
	public static double throttleSpeed(Joystick stick)
	{
		final double min = 0.125;
		double throttle = (-stick.getThrottle() + 1) / 2;
		if(throttle < min)
			throttle = min;
//		SmartDashboard.putNumber("Throttle Speed Value", throttle);
		return throttle;
	}
}