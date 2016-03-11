package org.usfirst.frc.team2051.robot.commands;

import org.usfirst.frc.team2051.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *Rotate the arm towards the electronics board
 */
public class ArmChevalDeFrise extends Command 
{

    public ArmChevalDeFrise() 
    {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.arm);
    }

    // Called just before this Command runs the first time
    protected void initialize() 
    {
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() 
    {
    	Robot.arm.teeterTotter();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() 
    {
    	return false;
//        return Robot.arm.getPosition();
    }

    // Called once after isFinished returns true
    protected void end() 
    {
    	Robot.arm.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() 
    {
    	end();
    }
}
