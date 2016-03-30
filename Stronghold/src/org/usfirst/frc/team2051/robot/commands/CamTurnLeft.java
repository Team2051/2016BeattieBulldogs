package org.usfirst.frc.team2051.robot.commands;

import org.usfirst.frc.team2051.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *Rotate the arm away from the electronics board
 */
public class CamTurnLeft extends Command 
{

    public CamTurnLeft() 
    {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.camMotor);
    }

    // Called just before this Command runs the first time
    protected void initialize() 
    {
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() 
    {
    	Robot.camMotor.turnLeft();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() 
    {
    	return Robot.camMotor.atLeftLimit();
    }

    // Called once after isFinished returns true
    protected void end() 
    {
    	Robot.camMotor.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() 
    {
    	end();
    }
}
