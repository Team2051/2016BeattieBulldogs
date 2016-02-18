package org.usfirst.frc.team2051.robot.subsystems;

import org.usfirst.frc.team2051.robot.RobotMap;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Arm extends Subsystem 
{
    private CANTalon motor;
 
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    
    public Arm()
    {
    	motor = new CANTalon(RobotMap.ARM_CAN_ID);
    }
    
    public void initDefaultCommand() 
    {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void rotateAway() // rotate the arm away from the driver's POV
    {
    	motor.set(1);
    }
    
    public void rotateTowards() // rotate the arm towards from the driver's POV
    {
    	motor.set(-1);
    }
    
    public void stop()
    {
    	motor.set(0);
    }

}