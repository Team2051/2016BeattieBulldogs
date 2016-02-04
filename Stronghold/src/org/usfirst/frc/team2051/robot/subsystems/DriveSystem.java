package org.usfirst.frc.team2051.robot.subsystems;

import org.usfirst.frc.team2051.robot.RobotMap;
import org.usfirst.frc.team2051.robot.commands.DriveByJoystick;

import edu.wpi.first.wpilibj.BuiltInAccelerometer;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.interfaces.Accelerometer;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */    
public class DriveSystem extends Subsystem 
{
	private CANTalon driveLeftA;
	private CANTalon driveLeftB;
	private CANTalon driveLeftC;

	private CANTalon driveRightA;
	private CANTalon driveRightB;
	private CANTalon driveRightC;

	private Accelerometer tiltCont;

	private RobotDrive robotDrive;

	double encOffsetValue = 0;

	public DriveSystem() 
	{
		driveLeftA = new CANTalon(RobotMap.DRIVE_LEFT_A_CAN_ID);
		LiveWindow.addActuator("Drive System", "Drive Left A", driveLeftA);
		driveLeftB = new CANTalon(RobotMap.DRIVE_LEFT_B_CAN_ID);
		LiveWindow.addActuator("Drive System", "Drive Left B", driveLeftB);
		driveLeftC = new CANTalon(RobotMap.DRIVE_LEFT_C_CAN_ID);
		LiveWindow.addActuator("Drive System", "Drive Left C", driveLeftC);

		driveRightA = new CANTalon(RobotMap.DRIVE_RIGHT_A_CAN_ID);
		LiveWindow.addActuator("Drive System", "Drive Right A", driveRightA);
		driveRightB = new CANTalon(RobotMap.DRIVE_RIGHT_B_CAN_ID);
		LiveWindow.addActuator("Drive System", "Drive Right B", driveRightB);
		driveRightC = new CANTalon(RobotMap.DRIVE_RIGHT_C_CAN_ID);
		LiveWindow.addActuator("Drive System", "Drive Right C", driveRightC);

		//Accelerometer Tilt control
		tiltCont = new BuiltInAccelerometer(Accelerometer.Range.k4G);
		//LiveWindow.addSensor("Drive System", "Accel", tiltCont);

		// On each side, all three drive motors MUST run at the same speed.
		// Use the CAN Talon Follower mode to set the speed of B and C,
		// making always run at the same speed as A.
		driveLeftB.changeControlMode(CANTalon.TalonControlMode.Follower);
		driveLeftC.changeControlMode(CANTalon.TalonControlMode.Follower);
		driveRightB.changeControlMode(CANTalon.TalonControlMode.Follower);
		driveRightC.changeControlMode(CANTalon.TalonControlMode.Follower);
		driveLeftB.set(driveLeftA.getDeviceID());
		driveLeftC.set(driveLeftA.getDeviceID());
		driveRightB.set(driveRightA.getDeviceID());
		driveRightC.set(driveRightA.getDeviceID());

		// Define a robot drive object in terms of only the A motors.
		// The B and C motors will play along at the same speed (see above.)
		robotDrive = new RobotDrive(driveLeftA, driveRightA);

		// Set some safety controls for the drive system
		robotDrive.setSafetyEnabled(true);
		robotDrive.setExpiration(0.1);
		robotDrive.setSensitivity(0.5);
		robotDrive.setMaxOutput(1.0);
		robotDrive.setInvertedMotor(RobotDrive.MotorType.kRearLeft, true); 
		robotDrive.setInvertedMotor(RobotDrive.MotorType.kRearRight, true);
	}

	public void initDefaultCommand() 
	{
		// Set the default command for a subsystem here.
		setDefaultCommand(new DriveByJoystick());
	}

	// Put methods for controlling this subsystem
	// here. Call these from Commands.

	public void takeJoystickInputs(Joystick joystk, Joystick joystk2) 
	{
		robotDrive.tankDrive(joystk, joystk2);
		SmartDashboard.putNumber("accel.x", tiltCont.getX());
		SmartDashboard.putNumber("accel.y", tiltCont.getY());
		SmartDashboard.putNumber("accel.z", tiltCont.getZ());
	}

	public void forward(double speed) 
	{
		robotDrive.drive(speed, 0);
	}

	public void stop() 
	{
		robotDrive.drive(/* speed */0, /* curve */0);
	}
	
	public double xTilt(double x, double y, double z)
	{
		double result;
		result=Math.sqrt(x);//y^2 + z^2
		result=x/result;
		double accel_angle_x = Math.atan(result);
		return accel_angle_x;
	}
	
	public boolean isTiltedOnRamp()
	{
		return tiltCont.getX()>=.15;		
	}
//	public int xTilt(, y, z)
//	{
//	   result=sqrt(y2+z2);
//	   result=x_val/result;
//	   accel_angle_x = atan(result);
//	   return accel_angle_x;
//	}
//	
//	
//	   //Y Axis
//	   result=sqrt(x2+z2);
//	   result=y_val/result;
//	   accel_angle_y = atan(result);
}