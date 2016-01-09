package piTest;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalInput;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinPullResistance;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;
import com.pi4j.io.gpio.event.GpioPinDigitalStateChangeEvent;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;

public class Main {

  public static void main(String[] args) throws Exception {
    System.out.println("Starting the heartbeat demo...");
    final GpioController gpio = GpioFactory.getInstance();	
    (new Thread(new heartbeat(gpio))).start();
    
    stateMachine sm = new stateMachine();
    sm.ManageState(gpio);
    
    
  }
}