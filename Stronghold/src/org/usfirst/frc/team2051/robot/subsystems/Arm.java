

package org.usfirst.frc.team2051.robot.subsystems;

import org.usfirst.frc.team2051.robot.OI;
import org.usfirst.frc.team2051.robot.RobotMap;
import org.usfirst.frc.team2051.robot.commands.ArmByJoystick;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.FeedbackDevice;
import edu.wpi.first.wpilibj.CANTalon.TalonControlMode;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Arm extends Subsystem 
{
    private CANTalon motor;
    private static final int zero = 0;
    private int potMin = 0;
    private int potMax = 1;
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    
    public Arm()
    {
    	motor = new CANTalon(RobotMap.ARM_CAN_ID);
    	
    	motor.setEncPosition(zero);
    	motor.setFeedbackDevice(FeedbackDevice.QuadEncoder);
    	motor.reverseSensor(false);
    	//7 pulse per rot * 1:71 gear ratio
    	motor.configEncoderCodesPerRev(7*71);
    	motor.configNominalOutputVoltage(0.0, 0.0);
    	motor.configPeakOutputVoltage(12.0, 12.0);
    	
    	motor.setAllowableClosedLoopErr(zero);
    	
    	motor.setProfile(zero);
    	
    	motor.setF(0.0);
    	motor.setP(0.1);
    	motor.setI(0.0);
    	motor.setD(0.0);
    	
    	motor.changeControlMode(TalonControlMode.Position);
    	motor.set(0);
    }
    
    public void initDefaultCommand() 
    {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new ArmByJoystick());
    }
    
    public void rotateAway() // rotate the arm away from the electronic's board
    {
    	motor.changeControlMode(TalonControlMode.PercentVbus);
    	motor.set(1);
    }
    
    public void rotateTowards() // rotate the arm towards from the electronic's board
    {
    	motor.changeControlMode(TalonControlMode.PercentVbus);
    	motor.set(-1);
    }
    
    public void stop()
    {
    	motor.changeControlMode(TalonControlMode.PercentVbus);
    	motor.set(0);
    }
    
    public void back()
    {
    	motor.changeControlMode(TalonControlMode.Position);
    	motor.set(.15);
    }
    
    public void teeterTotter()
    {
    	motor.changeControlMode(TalonControlMode.Position);
    	motor.set(-.33);
    }
    
    public void down()
    {
    	motor.changeControlMode(TalonControlMode.Position);
    	motor.set(-.5);
    }
    
    public double getPosition()
    {
    	return motor.getPosition();
    }
    
    public void showPosition()
    {
    	SmartDashboard.putNumber("Arm Position", getPosition());
    	SmartDashboard.putNumber("Arm Target Position", motor.getSetpoint());
    }
    
    public void takeJoystickInputs(Joystick joystk)
    {
    	motor.changeControlMode(TalonControlMode.PercentVbus);
    	motor.set(OI.throttleArmSpeed(joystk) * OI.deadBand(joystk.getY()));
    }
}