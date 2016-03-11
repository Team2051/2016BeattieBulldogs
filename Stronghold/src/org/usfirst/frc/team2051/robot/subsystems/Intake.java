package org.usfirst.frc.team2051.robot.subsystems;

import org.usfirst.frc.team2051.robot.RobotMap;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Intake extends Subsystem 
{
	private CANTalon motor;
	int solenoidStatus = 0;
    private DoubleSolenoid intakeRamp = RobotMap.intakeRamp;

    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

	public Intake()
	{
		motor = new CANTalon(RobotMap.INTAKE_PORT);
		intakeRamp = new DoubleSolenoid(0, 0, 1);
	}

	public void initDefaultCommand() 
    {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void intake()
    {
    	motor.set(-1);
    }
    
    public void release()
    {
    	motor.set(1);
//    	if(solenoidStatus == 1)
//    	{
//    		intakeRamp.set(DoubleSolenoid.Value.kReverse);
//    		solenoidStatus = 0;
//    	}
    }
    
    public void stop()
    {
    	motor.set(0);
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