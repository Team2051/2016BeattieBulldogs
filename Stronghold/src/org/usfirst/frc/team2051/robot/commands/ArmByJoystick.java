
package org.usfirst.frc.team2051.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team2051.robot.Robot;

/**
 *
 */
public class ArmByJoystick extends Command 
{

	    public ArmByJoystick() 
	    {
	        // Use requires() here to declare subsystem dependencies
	        requires(Robot.arm);
	    }

	    // Called just before this Command runs the first time
	    protected void initialize() 
	    {
	    	
	    }

	    // Called repeatedly when this Command is scheduled to run
	    protected void execute() 
	    {
	    	Robot.arm.showPosition();
	    	Robot.arm.takeJoystickInputs(Robot.oi.getShooterStick());
	    }

	    // Make this return true when this Command no longer needs to run execute()
	    protected boolean isFinished() 
	    {
	        return false;
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