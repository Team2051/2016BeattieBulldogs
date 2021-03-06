#pragma config(Sensor, in8,    speedCtrl,           sensorPotentiometer)
#pragma config(Sensor, in9,    liftUp,              sensorDigitalIn)
#pragma config(Sensor, in10,   liftDown,            sensorDigitalIn)
#pragma config(Sensor, in11,   liftTop,             sensorDigitalIn)
#pragma config(Sensor, in12,   liftBottom,          sensorDigitalIn)
#pragma config(Sensor, in13,   leftRevTrigger,      sensorDigitalIn)
#pragma config(Sensor, in14,   rightRevTrigger,     sensorDigitalIn)
#pragma config(Sensor, in15,   leftTrigger,         sensorDigitalIn)
#pragma config(Sensor, in16,   rightTrigger,        sensorDigitalIn)
#pragma config(Motor,  port2,  rightMotor,          tmotorNormal, openLoop)
#pragma config(Motor,  port3,  leftMotor,           tmotorNormal, openLoop)
#pragma config(Motor,  port4,  lift,                tmotorNormal, openLoop)
//*!!Code automatically generated by 'ROBOTC' configuration wizard               !!*//

task main()
{
	int sensorPressed = 1;
	int sensorNotPressed = 0;
	/* Minimum wheel speed. Must be in range [0..64] */
	int minWheelSpeed = 16;

	while(true)
	{
	  //pot range is 0 - 1023
	  //motor range is -127 - 127

	  // Scale pot reading [0..1023] down to half the forward speed range [0..63]
	  // by shifting right 4 bits. This is analogous to scaling decimal values [0..10000]
	  // to [0..100] by simply crossing off the last two digits.
	  int wheelSpeed = SensorValue(speedCtrl) >> 4;

	  // Bump up speed to avoid having the cart not move at all at low settings
	  // Don't add more than 64 so the max stays within the valid motor speed range 
	  // wheelSpeed range is [0..63]+[0..64] = [0..127]
	  wheelSpeed += minWheelSpeed;

	  //right wheel
		if(SensorValue(rightTrigger) == sensorPressed && SensorValue(rightRevTrigger) == sensorNotPressed)
		{
			motor[rightMotor] = wheelSpeed;
		}
		else if(SensorValue(rightRevTrigger) == sensorPressed && SensorValue(rightTrigger) == sensorNotPressed)
		{
			motor[rightMotor] = -wheelSpeed;
		}
		else
		{
		  motor[rightMotor] = 0;
		}

		//left wheel
		if(SensorValue(leftTrigger) == sensorPressed && SensorValue(leftRevTrigger) == sensorNotPressed)
		{
			motor[leftMotor] = wheelSpeed;
		}
		else if(SensorValue(leftRevTrigger) == sensorPressed && SensorValue(leftTrigger) == sensorNotPressed)
		{
		  motor[leftMotor] = -wheelSpeed;
		}
    else
		{
			motor[leftMotor] = 0;
		}

		//lifter
		if(SensorValue(liftUp) == sensorPressed && SensorValue(liftTop) == sensorNotPressed)
		{
			motor[lift] = liftSpeed;
		}
		else if(SensorValue(liftDown) == sensorPressed && SensorValue(liftBottom) == sensorNotPressed)
		{
			motor[lift] = -liftSpeed;
		}
		else
		{
			motor[lift] = 0;
		}
	}
}
