package org.cubexell.cubesolver.core;

import com.pi4j.Pi4J;
import com.pi4j.context.Context;
import com.pi4j.io.gpio.digital.DigitalOutput;
import java.util.concurrent.locks.LockSupport;

public class RohsStepperMotor implements Motor{

    private final DigitalOutput in1;
    private final DigitalOutput in2;
    private final DigitalOutput in3;
    private final DigitalOutput in4;
    private final int[][] stepSequence = new int[][]//this is the order of the magnets turning on and off for each step.
            {
                    {1,0,0,1},//step one: in1.high, in2.low, in3.low, in4.high
                    {1,0,0,0},
                    {1,1,0,0},
                    {0,1,0,0},
                    {0,1,1,0},
                    {0,0,1,0},
                    {0,0,1,1},
                    {0,0,0,1}
            };

    public RohsStepperMotor(int in1, int in2, int in3, int in4){

        Context pi4j = Pi4J.newAutoContext();//all of this initializes the pins
        this.in1 = pi4j.digitalOutput().create(in1);
        this.in2 = pi4j.digitalOutput().create(in2);
        this.in3 = pi4j.digitalOutput().create(in3);
        this.in4 = pi4j.digitalOutput().create(in4);

    }

    int motorStepCounter = 0;
    public void doTurn(double stepsToTurn, boolean direction) throws InterruptedException {
        for (){//TODO for all steps to turn
            //TODO for all pins, set the corresponding "in" to high(if 1) or low(if 0) based on the step in the step sequence
            if (direction){
                motorStepCounter;//TODO go to the previous step
            } else{
                motorStepCounter;//TODO go to next step in the sequence
            }
            LockSupport.parkNanos(1000000);//wait 1 millisecond

        }
        reset();
    }

    public void turn(double numRotations){
        boolean direction = false;
        if (){//TODO if numRotations is negative
            direction;//TODO set direction to true
            numRotations = numRotations*-1;//make it positive now that we have adjusted direction.
        }
        try {
            doTurn((int) (Math.round(32 * 64 * 2 *numRotations)), direction);//the multiplication is just ticks per revolution x gear ratio x 2 because half stepping
        } catch (InterruptedException e) {
            System.out.println("Motor in trouble");
        } catch (Exception e) {
            System.out.println("caught exception" + e);
            e.printStackTrace();
        }

    }

    public void reset(){
        in1.low();//makes sure to stop sending power to motors so that they can cornercut
        in2.low();
        in3.low();
        in4.low();
    }

}
