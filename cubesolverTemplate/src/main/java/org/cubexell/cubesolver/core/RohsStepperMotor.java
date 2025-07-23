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
    private final int[][] stepSequence = new int[][]
            {
                    {1,0,0,1},//each step sequence corresponds to a magnet, i.e. a specific position
                    {1,0,0,0},
                    {1,1,0,0},
                    {0,1,0,0},
                    {0,1,1,0},
                    {0,0,1,0},
                    {0,0,1,1},
                    {0,0,0,1}
            };

    public RohsStepperMotor(int in1, int in2, int in3, int in4){

        Context pi4j = Pi4J.newAutoContext();
        this.in1 = pi4j.digitalOutput().create(in1);//define a variable that holds a value for each wire input
        this.in2 = pi4j.digitalOutput().create(in2);
        this.in3 = pi4j.digitalOutput().create(in3);
        this.in4 = pi4j.digitalOutput().create(in4);

    }

    int motorStepCounter = 0;
    public void step(){
        if (stepSequence[motorStepCounter][0] == 1){
            in1.high();//doing stuff
        }
        else{
            in1.low();//not doing stuff
        }
        if (stepSequence[motorStepCounter][1] == 1){
            in2.high();
        }
        else{
            in2.low();
        }
        if (stepSequence[motorStepCounter][2] == 1){
            in3.high();
        }
        else{
            in3.low();
        }
        if (stepSequence[motorStepCounter][3] == 1){
            in4.high();
        }
        else{
            in4.low();
        }

    }
    public void doSteps(double stepsToTurn, boolean direction) throws InterruptedException {
        for (int i=0; i<stepsToTurn; i++){
            step();
            if (direction){
                motorStepCounter = ((motorStepCounter-1)+stepSequence.length)%stepSequence.length;
            }
            else{
                motorStepCounter = (motorStepCounter+1)%stepSequence.length;
            }
            LockSupport.parkNanos(1000000);//wait 1 millisecond

        }
        reset();
    }

    public void turn(double numRotations){
        boolean direction = false;
        if (numRotations < 0){
            direction = true;
            numRotations = numRotations*-1;
        }
        try {
            doSteps((int) (Math.round(32 * 64 * 2 *numRotations)), direction);
        }
        catch (InterruptedException e) {
            System.out.println("Motor in trouble");
        }
        catch (Exception e) {
            System.out.println("caught exception" + e);
            e.printStackTrace();
        }

    }

    public void reset(){
        in1.low();
        in2.low();
        in3.low();
        in4.low();
    }

}
