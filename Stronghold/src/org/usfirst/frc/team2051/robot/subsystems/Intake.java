package org.usfirst.frc.team2051.robot.subsystems;

import org.usfirst.frc.team2051.robot.RobotMap;

import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Intake extends Subsystem 
{
	private Relay motor;
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

	public Intake()
	{
		motor = new Relay(RobotMap.INTAKE_PORT);
	}

	public void initDefaultCommand() 
    {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void intake()
    {
    	motor.set(Relay.Value.kReverse);
    }
    
    public void release()
    {
    	motor.set(Relay.Value.kForward);
    }
    
    public void stop()
    {
    	motor.set(Relay.Value.kOff);
    }

}