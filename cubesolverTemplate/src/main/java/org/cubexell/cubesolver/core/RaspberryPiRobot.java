package org.cubexell.cubesolver.core;

public class RaspberryPiRobot extends Robot {
    public RaspberryPiRobot(Motor upMotor,
                            Motor downMotor,
                            Motor rightMotor,
                            Motor leftMotor,
                            Motor frontMotor,
                            Motor backMotor){
        super(upMotor,
                downMotor,
                rightMotor,
                leftMotor,
                frontMotor,
                backMotor);
    }


    public void actionBetweenTurns(){

    }

    public void log(String message){

    }

}
