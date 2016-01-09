package piTest;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.RaspiPin;

public class heartbeat implements Runnable {
	GpioController gpio;
	
    public heartbeat(GpioController gpioparam){
    	gpio = gpioparam;
    }
	public void run() {
    	//final GpioController gpio = GpioFactory.getInstance();	
    	GpioPinDigitalOutput signalLed = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_05,
				"signalLed");
        while(true)
        {      
        	
        	try {
        		Thread.sleep(2000);
        		System.out.println("Toggle off");
        		signalLed.high();        		
				Thread.sleep(250);
				System.out.println("Toggle on");
				signalLed.low(); 
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
    }
}
