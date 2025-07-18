import org.cubexell.cubesolver.core.*;
import com.pi4j.Pi4J;

import com.pi4j.io.gpio.*;

import java.util.Arrays;


public class Main {
	public void main(String[] args) {
        String cubeSolvingMethod = "Beginner";

		if(args.length > 0 && args[0]!=null){
			cubeSolvingMethod = args[0];
		}

		Motor upMotor = new RohsStepperMotor (24, 25, 8, 7);
		Motor downMotor = new RohsStepperMotor (1, 12, 16, 20);
		Motor rightMotor = new RohsStepperMotor (21, 26, 19, 13);
		Motor leftMotor = new RohsStepperMotor (2, 3, 4, 17);
		Motor frontMotor = new RohsStepperMotor (6, 5, 0, 11);
		Motor backMotor = new RohsStepperMotor (9, 10, 22, 27);

		Robot robot = new RaspberryPiRobot(upMotor, downMotor, rightMotor, leftMotor, frontMotor, backMotor);

		//robot.executeMoves(new String[] {"L"});
		//System.exit(0);

		System.out.println("Cube solving method: "+cubeSolvingMethod);

		CubeScrambler scrambler = new CubeScrambler(robot);;
//		String[] scrambleMoves = {"Ri","Bi","Ri","F2","L","F2","B","R","Ui","R","Fi","U","Li","B","L2","U2","B2","F2","L2","F"};
//		scrambler.randomScramble();
//		System.out.println ("------Scramble------------");
//		for (int i = 0; i < scrambleMoves.length; i++) {
//			System.out.print (scrambleMoves[i]+",");
//		}
//		System.out.println();
		//char[][][] cubeColors = scrambler.scramble(scrambleMoves);

		CubeColorInspector inspector = new OpenCvRaspberryPiCamera(robot);

		char[][][] cubeColors = inspector.inspect();
		//char[][][] cubeColors = scrambler.randomScramble();
		Cube cube = new Cube(cubeColors);
		System.out.println ("------After scramble------------");
		System.out.println(cube.toString());
		
		CubeSolvingMethod solver;

		if("Beginner".equalsIgnoreCase(cubeSolvingMethod)){
			solver = new BeginnerMethod();
		}
		else if("Kociemba".equalsIgnoreCase(cubeSolvingMethod)) {
			solver = new KociembaAlgorithm();
		}
		else {
			solver = new OldPochmannMethod();
		}

		String[] solution = solver.solve(cubeColors);
		System.out.println ("------solution num moves: "+solution.length + "------------");
		for (int i = 0; i < solution.length; i++) {
			System.out.print (solution[i]+",");
		}
		System.out.println();
		String[] simplifiedSolution = cube.simplifySolution(solution);
		System.out.println ("------simplified solution num moves: "+simplifiedSolution.length + "------------");
		for (int i = 0; i < simplifiedSolution.length; i++) {
			System.out.print (simplifiedSolution[i]+",");
			}

		cube.simulateMoves(simplifiedSolution);
		System.out.println ("------AfterSolving------------");
		System.out.println(cube.toString());

		robot.executeMoves(simplifiedSolution);
		robot.resetMotors();

		System.out.println ("finished");

	}

}
