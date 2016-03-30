package org.usfirst.frc.team2051.robot.subsystems;

import org.usfirst.frc.team2051.robot.RobotMap;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class CameraMotor extends Subsystem 
{	
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	private VictorSP camMotor;
	private double speed = .5;
	DigitalInput leftLimit;
	DigitalInput rightLimit;
	
	public CameraMotor()
    {
		camMotor = new VictorSP(RobotMap.CAM_PORT);
		leftLimit = new DigitalInput(RobotMap.CAM_LEFT_LIMIT_PORT);
		rightLimit = new DigitalInput(RobotMap.CAM_RIGHT_LIMIT_PORT);

    }
    public void initDefaultCommand() 
    {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void turnRight()
    {
    	camMotor.set(speed);
    }
    
    public void turnLeft()
    {
    	camMotor.set(-speed);
    }
    
    public void stop()
    {
    	camMotor.set(0);
    }
    
    public boolean atLeftLimit()
    {
    	return leftLimit.get();
    }  
    
    public boolean atRightLimit()
    {
    	return rightLimit.get();
    }
}

