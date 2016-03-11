package org.usfirst.frc.team2051.robot.subsystems;

import org.usfirst.frc.team2051.robot.OI;
import org.usfirst.frc.team2051.robot.RobotMap;
import org.usfirst.frc.team2051.robot.commands.DriveByJoystick;

import edu.wpi.first.wpilibj.BuiltInAccelerometer;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.interfaces.Accelerometer;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */    
public class DriveSystem extends Subsystem 
{
	private VictorSP driveLeftA;
	private VictorSP driveLeftB;

	private VictorSP driveRightA;
	private VictorSP driveRightB;

	private Accelerometer tiltCont;

	private RobotDrive robotDrive;

	double encOffsetValue = 0;

	int maxArrayIndex = 49;
	private double tiltArray[] = new double[maxArrayIndex + 1];

	int i = 0;
	
	int invert = 0;

	public DriveSystem() 
	{
		driveLeftA = new VictorSP(RobotMap.DRIVE_LEFT_A_PORT);
		LiveWindow.addActuator("Drive System", "Drive Left A", driveLeftA);
		driveLeftB = new VictorSP(RobotMap.DRIVE_LEFT_B_PORT);
		LiveWindow.addActuator("Drive System", "Drive Left B", driveLeftB);

		driveRightA = new VictorSP(RobotMap.DRIVE_RIGHT_A_PORT);
		LiveWindow.addActuator("Drive System", "Drive Right A", driveRightA);
		driveRightB = new VictorSP(RobotMap.DRIVE_RIGHT_B_PORT);
		LiveWindow.addActuator("Drive System", "Drive Right B", driveRightB);

		//Accelerometer Tilt control
		tiltCont = new BuiltInAccelerometer(Accelerometer.Range.k4G);
		//LiveWindow.addSensor("Drive System", "Accel", tiltCont);

		// On each side, all three drive motors MUST run at the same speed.
		// Use the CAN Talon Follower mode to set the speed of B and C,
		// making always run at the same speed as A.
		//driveLeftB.changeControlMode(CANTalon.TalonControlMode.Follower);
		//driveLeftB.set(driveLeftA.getDeviceID());

		robotDrive = new RobotDrive(driveLeftA, driveLeftB, driveRightA, driveRightB);
		
		// Set some safety controls for the drive system
		robotDrive.setSafetyEnabled(false);
		robotDrive.setExpiration(0.1);
		robotDrive.setSensitivity(0.5);
		robotDrive.setMaxOutput(1.0);
	}

	public void initDefaultCommand() 
	{
		// Set the default command for a subsystem here.
		setDefaultCommand(new DriveByJoystick());
	}

	// Put methods for controlling this subsystem
	// here. Call these from Commands.

	public void takeJoystickInputs(Joystick joystk) 
	{
		if(invert == 0)
		{
			robotDrive.arcadeDrive(OI.throttleSpeed(joystk) * OI.deadBand(joystk.getY()), OI.throttleSpeed(joystk) * OI.deadBand(joystk.getX()));
			SmartDashboard.putNumber("accel.x", tiltCont.getX());
			SmartDashboard.putNumber("accel.y", tiltCont.getY());
			SmartDashboard.putNumber("accel.z", tiltCont.getZ());
			SmartDashboard.putNumber("avg.x", getTiltAvg());
		}
		else
		{
			robotDrive.arcadeDrive(OI.throttleSpeed(joystk) * -OI.deadBand(joystk.getY()), OI.throttleSpeed(joystk) * -OI.deadBand(joystk.getX()));
			SmartDashboard.putNumber("accel.x", tiltCont.getX());
			SmartDashboard.putNumber("accel.y", tiltCont.getY());
			SmartDashboard.putNumber("accel.z", tiltCont.getZ());
			SmartDashboard.putNumber("avg.x", getTiltAvg());
		}
	}

	public void forward(double speed) 
	{
		robotDrive.drive(speed, 0);
	}

	public void stop() 
	{
		robotDrive.drive(/* speed */0, /* curve */0);
	}
	
	public void tiltArrayPop()
	{
		tiltArray[i]=tiltCont.getX();
		i++;
		if(i > maxArrayIndex)
		{
			i=0;
		}
	}
	
	public double getTiltAvg()
	{
		tiltArrayPop();
		double total = 0;
		for(int q = 0; q <= maxArrayIndex; q++)
		{
			total = tiltArray[q] + total;
		}
		//total / 25
		return total / (maxArrayIndex + 1);
	}
	
	public boolean isTiltedOnRamp()
	{
		return getTiltAvg() >= .15;
	}
	
	public boolean isLevel()
	{
		return getTiltAvg() >= -0.05 && getTiltAvg() <= 0.05;
	}
	
	public void invertJoystick()
	{
		if(invert == 0)
			invert = 1;
		else
			invert = 0;
	}
}