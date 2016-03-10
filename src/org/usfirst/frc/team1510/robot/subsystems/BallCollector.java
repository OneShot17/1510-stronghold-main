package org.usfirst.frc.team1510.robot.subsystems;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Counter;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DigitalOutput;
/**
 *
 */
public class BallCollector extends Subsystem {
    
	// Arm systems
    private Talon armMotor = new Talon(0);
    private Relay rollerMotor = new Relay(1);
    private DigitalOutput transmitter = new DigitalOutput(7);
    private DigitalInput receiver = new DigitalInput(8);
    private DigitalInput limitSwitch1 = new DigitalInput(5);
    private Counter counter1 = new Counter(limitSwitch1);
    private DigitalInput limitSwitch2 = new DigitalInput(6);
    private Counter counter2 = new Counter(limitSwitch2);

    
    public boolean extend() { 
	armMotor.set(-0.25);
	if (limitSwitch2.get()) {
	    armMotor.set(0);
	    return true;
	}
	return false;
    }
    public boolean retract() { 
	armMotor.set(0.25);
	if (limitSwitch1.get()) {
	    armMotor.set(0);
	    return true;
	}
		return false;
    }
    public void forward(){
    	rollerMotor.set(Relay.Value.kForward);
    	rollerMotor.set(Relay.Value.kOn);
    }
    
    public void reverse(){
    	rollerMotor.set(Relay.Value.kReverse);
    	rollerMotor.set(Relay.Value.kOn);
    }
    
    public void getBall(){
    	transmitter.set(true);
    	if(receiver.get() == false){
    		forward();
    	}
    	else {
    		off();
    	}
    }
    public void feedBall(){
    	transmitter.set(true);
    	if(receiver.get() == true){
    		forward();
    		try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
    	}
    	else {
    		off();
    	}
    }
    public void releaseBall(){
    	transmitter.set(true);
    	if(receiver.get() == true){
    		reverse();
    		try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
    	}
    	else {
    		off();
    	}
    }
    public void off() {
    	rollerMotor.set(Relay.Value.kOff);
    }
    
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}
