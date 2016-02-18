package org.usfirst.frc.team2051.robot.subsystems;

import org.usfirst.frc.team2051.robot.RobotMap;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Shooter extends Subsystem 
{
    private CANTalon motor;
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    
    public Shooter()
    {
    	motor = new CANTalon(RobotMap.SHOOTER_CAN_ID);
    }

    public void initDefaultCommand() 
    {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void shoot()
    {
    	motor.set(1);
    }
    
    public void stop()
    {
    	motor.set(0);
    }
}