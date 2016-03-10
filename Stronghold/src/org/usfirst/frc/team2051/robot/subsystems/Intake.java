package org.usfirst.frc.team2051.robot.subsystems;

import org.usfirst.frc.team2051.robot.RobotMap;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Intake extends Subsystem 
{
	private Relay motor;
	private int solenoidStatus = 0;
    private DoubleSolenoid intakeRamp = RobotMap.intakeRamp;

    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

	public Intake()
	{
		motor = new Relay(RobotMap.INTAKE_PORT);
		intakeRamp = new DoubleSolenoid(0, 0, 1);
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
    	if(solenoidStatus == 1)
    	{
    		intakeRamp.set(DoubleSolenoid.Value.kReverse);
    		solenoidStatus = 0;
    	}
    }
    
    public void stop()
    {
    	motor.set(Relay.Value.kOff);
    	intakeRamp.set(DoubleSolenoid.Value.kOff);
    }
    
    public void openRamp()
    {
    	if(solenoidStatus == 0)
    	{
    		intakeRamp.set(DoubleSolenoid.Value.kForward);
    		solenoidStatus = 1;
    	}
    }
    
    public void closeRamp()
    {
    	if(solenoidStatus == 0)
    	{
    		intakeRamp.set(DoubleSolenoid.Value.kReverse);
    		solenoidStatus = 1;
    	}
    }

}