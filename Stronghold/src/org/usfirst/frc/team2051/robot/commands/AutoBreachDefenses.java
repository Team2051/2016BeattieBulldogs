package org.usfirst.frc.team2051.robot.commands;

import org.usfirst.frc.team2051.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AutoBreachDefenses extends Command 
{
	double clock = 0;
	
    public AutoBreachDefenses() 
    {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.driveSystem);
    }

    // Called just before this Command runs the first time
    protected void initialize() 
    {  
    	clock = 0;
    }

	protected double getTime(double clock)
    {
    	//ticks in auton / ticks per second = seconds
    	return clock / 50;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() 
    {
    	double speed = 1;
    	Robot.driveSystem.forward(speed);
    	clock += 1;
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() 
    {
    	if(getTime(clock) <.5)
    	{
    		Robot.driveSystem.tiltArrayPop();
    		return false;
    	}
    	else if(getTime(clock) > 1)
    		return true;
    	else
    		return Robot.driveSystem.isLevel();
    }
    
    // Called once after isFinished returns true
    protected void end() 
    {
    	Robot.driveSystem.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() 
    {
    	end();
    }
}