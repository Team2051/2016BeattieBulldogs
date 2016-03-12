package org.usfirst.frc.team2051.robot.subsystems;

import org.usfirst.frc.team2051.robot.RobotMap;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Shooter extends Subsystem 
{
    private VictorSP motor;
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    
    public Shooter()
    {
    	motor = new VictorSP(RobotMap.SHOOTER_PORT);
    }

    public void initDefaultCommand() 
    {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void shootLow()
    {
    	motor.set(-1);
    }
    
    public void stop()
    {
    	motor.set(0);
    }
}