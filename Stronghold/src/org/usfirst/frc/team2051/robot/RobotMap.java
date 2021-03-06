package org.usfirst.frc.team2051.robot;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap 
{
    // For example to map the left and right motors, you could define the
    // following variables to use with your drivetrain subsystem.
    // public static int leftMotor = 1;
    // public static int rightMotor = 2;

	//PWM ports
	public static final int DRIVE_RIGHT_A_PORT = 0;
	public static final int DRIVE_RIGHT_B_PORT = 1;
	public static final int DRIVE_LEFT_A_PORT = 2;
	public static final int DRIVE_LEFT_B_PORT = 3;
	//what is in port 4 ???
	public static final int SHOOTER_PORT = 5;
	public static final int CAM_PORT = 6;
		
	//CAN ports
	public static final int ARM_CAN_ID = 1;
	public static final int INTAKE_CAN_ID = 2;

	//DIO ports
	public static final int CAM_LEFT_LIMIT_PORT = 1;
	public static final int CAM_RIGHT_LIMIT_PORT = 2;

	//Other Stuff
	public static Compressor compressor;
    public static DoubleSolenoid intakeRamp;
	
    // If you are using multiple modules, make sure to define both the port
    // number and the module. For example you with a rangefinder:
    // public static int rangefinderPort = 1;
    // public static int rangefinderModule = 1;
	
}