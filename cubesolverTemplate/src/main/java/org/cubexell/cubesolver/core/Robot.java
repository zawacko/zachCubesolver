package org.cubexell.cubesolver.core;

public abstract class Robot {

    protected Motor upMotor   = null;
    protected Motor downMotor  = null;
    protected Motor rightMotor   = null;
    protected Motor leftMotor  = null;
    protected Motor frontMotor = null;
    protected Motor backMotor = null;

    public Robot(){}

    public Robot(Motor upMotor,
                 Motor downMotor,
                 Motor rightMotor,
                 Motor leftMotor,
                 Motor frontMotor,
                 Motor backMotor){
        this.upMotor = upMotor;
        this.downMotor = downMotor;
        this.rightMotor = rightMotor;
        this.leftMotor = leftMotor;
        this.frontMotor = frontMotor;
        this.backMotor = backMotor;
    }

    public Motor getUpMotor() {
        return upMotor;
    }

    public void setUpMotor(Motor upMotor) {
        this.upMotor = upMotor;
    }

    public Motor getDownMotor() {
        return downMotor;
    }

    public void setDownMotor(Motor downMotor) {
        this.downMotor = downMotor;
    }

    public Motor getRightMotor() {
        return rightMotor;
    }

    public void setRightMotor(Motor rightMotor) {
        this.rightMotor = rightMotor;
    }

    public Motor getLeftMotor() {
        return leftMotor;
    }

    public void setLeftMotor(Motor leftMotor) {
        this.leftMotor = leftMotor;
    }

    public Motor getFrontMotor() {
        return frontMotor;
    }

    public void setFrontMotor(Motor frontMotor) {
        this.frontMotor = frontMotor;
    }

    public Motor getBackMotor() {
        return backMotor;
    }

    public void setBackMotor(Motor backMotor) {
        this.backMotor = backMotor;
    }

    public void resetMotors(){//so doesn't hold position so corner cutting works better
        upMotor.reset();
        downMotor.reset();
        rightMotor.reset();
        leftMotor.reset();
        frontMotor.reset();
        backMotor.reset();
    }

    public void U(){
        upMotor.turn(0.25);
    }
    public void Ui(){
        upMotor.turn(-0.25);
    }
    public void U2(){
        upMotor.turn(0.5);
    }
    public void D(){
        downMotor.turn(0.25);
    }
    public void Di(){
        downMotor.turn(-0.25);
    }
    public void D2(){
        downMotor.turn(0.5);
    }
    public void R(){
        rightMotor.turn(0.25);
    }
    public void Ri(){
        rightMotor.turn(-0.25);
    }
    public void R2(){
        rightMotor.turn(0.5);
    }
    public void L(){
        leftMotor.turn(0.25);
    }
    public void Li(){
        leftMotor.turn(-0.25);
    }
    public void L2(){
        leftMotor.turn(0.5);
    }
    public void F(){
        frontMotor.turn(0.25);
    }
    public void Fi(){
        frontMotor.turn(-0.25);
    }
    public void F2(){
        frontMotor.turn(0.5);
    }
    public void B(){
        backMotor.turn(0.25);
    }
    public void Bi(){
        backMotor.turn(-0.25);
    }
    public void B2(){
        backMotor.turn(0.5);
    }

    private void invokeMoveMethod(String move) {
        try {
            this.getClass().getMethod(move).invoke(this);
        }
        catch(Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    public void executeMoves(String[] moves) {
        for (int i = 0; i < moves.length; i++){
            invokeMoveMethod(moves[i]);
            actionBetweenTurns();
//            if ("R".equalsIgnoreCase(moves[i])){
//                R();
//            }
//            else if ("R2".equalsIgnoreCase(moves[i])) {
//                R2();
//            }
//            else if ("Ri".equalsIgnoreCase(moves[i])) {
//                Ri();
//            }
//            else if ("L".equalsIgnoreCase(moves[i])) {
//                L();
//            }
//            else if ("L2".equalsIgnoreCase(moves[i])) {
//                L2();
//            }
//            else if ("Li".equalsIgnoreCase(moves[i])) {
//                Li();
//            }
//            else if ("U".equalsIgnoreCase(moves[i])) {
//                U();
//            }
//            else if ("U2".equalsIgnoreCase(moves[i])) {
//                U2();
//            }
//            else if ("Ui".equalsIgnoreCase(moves[i])) {
//                Ui();
//            }
//            else if ("D".equalsIgnoreCase(moves[i])) {
//                D();
//            }
//            else if ("D2".equalsIgnoreCase(moves[i])) {
//                D2();
//            }
//            else if ("Di".equalsIgnoreCase(moves[i])) {
//                Di();
//            }
//            else if ("F".equalsIgnoreCase(moves[i])) {
//                F();
//            }
//            else if ("F2".equalsIgnoreCase(moves[i])) {
//                F2();
//            }
//            else if ("Fi".equalsIgnoreCase(moves[i])) {
//                Fi();
//            }
//            else if ("B".equalsIgnoreCase(moves[i])) {
//                B();
//            }
//            else if ("B2".equalsIgnoreCase(moves[i])) {
//                B2();
//            }
//            else if ("Bi".equalsIgnoreCase(moves[i])) {
//                Bi();
//            }
            //long moveEndTime = System.nanoTime();
            //long fullMoveTime = moveEndTime - moveStartTime;
            //System.out.println("fullMoveTime " +fullMoveTime);
        }
        //long endTime = System.nanoTime();
        //long actualSolutionTime = endTime - startTime; // time in nanoseconds
        //System.out.println("Actual solution time: " + actualSolutionTime + " nanoseconds");
    }

    public abstract void actionBetweenTurns();

    public abstract void log(String message);

}
