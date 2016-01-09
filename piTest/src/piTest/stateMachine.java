package piTest;

import java.util.Date;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalInput;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinPullResistance;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;
import com.pi4j.io.gpio.event.GpioPinDigitalStateChangeEvent;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;

public class stateMachine {
	public void ManageState(GpioController gpio) throws InterruptedException
	{		
		// create gpio controller instance
		
		
		GpioPinDigitalInput myButton = gpio.provisionDigitalInputPin(RaspiPin.GPIO_02,             // PIN NUMBER
                "MyButton",                   // PIN FRIENDLY NAME (optional)
                PinPullResistance.PULL_DOWN); // PIN RESISTANCE (optional)
		
		GpioPinDigitalOutput signalLed = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_04,
				"signalLed");

		states state = states.init;
		boolean active = true;
		boolean entering = true;
		while(active)
		{		
			switch(state)
			{
			case init:
				// Initialize state
				if(entering)
				{					
					System.out.println("Initializing State Machine");
					
					entering = false;
				}
				
				entering = true;
				state = states.inactive;
				break;
			case inactive:
				// wait here
				if(entering)
				{
					signalLed.low();
					System.out.println("Off State");
					entering = false;
				}
				if(myButton.isHigh())
				{
					entering = true;
					state = states.active;
				}
				break;
			case active:
				// wait here
				if(entering)
				{
					System.out.println("Active State");
					entering = false;
					signalLed.high();
				}
				if(myButton.isLow())
				{
					entering = true;
					state = states.inactive;
				}
				break;
			case shutdown:
				break;
			default:
				break;
			}
			Thread.sleep(5);
		}
	}
	
	public enum states
	{
		init,
		inactive,
		active,
		shutdown
	}
}
