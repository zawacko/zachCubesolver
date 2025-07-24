package org.cubexell.cubesolver.core;

import static org.cubexell.cubesolver.core.CubeConstants.POSSIBLE_MOVES;

public class CubeScrambler {
	private Robot robot;
	public CubeScrambler() {
	}

	public CubeScrambler(Robot robot) {
		this.robot = robot;
	}

	protected String randomScrambleMove() {
		int randomIndex = (int)(18*Math.random());
		return POSSIBLE_MOVES[randomIndex];
	}

	protected char getFace(String move) {
		return move.charAt(0);
	}

	public String[] getScramble(int numMoves) {
		String[] scramble = new String[numMoves];
		scramble[0] = randomScrambleMove();

		for(int i=1; i<numMoves; i++){
			char prevMoveFace = getFace(scramble[i-1]);
			String move = randomScrambleMove();
			char moveFace = getFace(move);
			while(moveFace == prevMoveFace) {
				move = randomScrambleMove();
				moveFace = getFace(move);
			}
			scramble[i] = move;
		}
		return scramble;
	}

	public char[][][] scramble(String[] scrambleMoves){
		Cube cube = new Cube(Helper.createSolvedCubeColors());
		cube.simulateMoves(scrambleMoves);
		if(robot!=null){
			robot.executeMoves(scrambleMoves);
		}
		return cube.getCubeColors();
	}

	public char[][][] randomScramble(){
		Cube cube = new Cube(Helper.createSolvedCubeColors());
		String[] scrambleMoves = getScramble(20);
		cube.simulateMoves(scrambleMoves);
		if(robot!=null){
			robot.executeMoves(scrambleMoves);
		}
		return cube.getCubeColors();
	}

}
