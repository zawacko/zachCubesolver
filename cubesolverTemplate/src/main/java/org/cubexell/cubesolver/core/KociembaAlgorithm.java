package org.cubexell.cubesolver.core;

import org.kociemba.twophase.Search;

import static org.cubexell.cubesolver.core.CubeConstants.*;

import java.util.ArrayList;
import java.util.StringTokenizer;


/**
 * <pre>
 * The names of the facelet positions of the cube
 *             |************|
 *             |*U1**U2**U3*|
 *             |************|
 *             |*U4**U5**U6*|
 *             |************|
 *             |*U7**U8**U9*|
 *             |************|
 * ************|************|************|************|
 * *L1**L2**L3*|*F1**F2**F3*|*R1**R2**F3*|*B1**B2**B3*|
 * ************|************|************|************|
 * *L4**L5**L6*|*F4**F5**F6*|*R4**R5**R6*|*B4**B5**B6*|
 * ************|************|************|************|
 * *L7**L8**L9*|*F7**F8**F9*|*R7**R8**R9*|*B7**B8**B9*|
 * ************|************|************|************|
 *             |************|
 *             |*D1**D2**D3*|
 *             |************|
 *             |*D4**D5**D6*|
 *             |************|
 *             |*D7**D8**D9*|
 *             |************|
 * </pre>
 *
 *A cube definition string "UBL..." means for example: In position U1 we have the U-color, in position U2 we have the
 * B-color, in position U3 we have the L color etc. according to the order U1, U2, U3, U4, U5, U6, U7, U8, U9, R1, R2,
 * R3, R4, R5, R6, R7, R8, R9, F1, F2, F3, F4, F5, F6, F7, F8, F9, D1, D2, D3, D4, D5, D6, D7, D8, D9, L1, L2, L3, L4,
 * L5, L6, L7, L8, L9, B1, B2, B3, B4, B5, B6, B7, B8, B9 of the enum constants.
 */
//DON'T CHANGE!!!
public class KociembaAlgorithm implements CubeSolvingMethod{

    private int maxDepth  = 24;
    private int maxTime  = 5;

    private boolean useSeparator = false;

    private void printColorCount(String facelets){
        int[] colorCount  = new int[6];
        for(int i=0; i<facelets.length(); i++){
            switch(facelets.charAt(i)){
                case 'U':
                    colorCount[UP_FACE_INDEX]++;
                    break;
                case 'D':
                    colorCount[DOWN_FACE_INDEX]++;
                    break;
                case 'L':
                    colorCount[LEFT_FACE_INDEX]++;
                    break;
                case 'R':
                    colorCount[RIGHT_FACE_INDEX]++;
                    break;
                case 'F':
                    colorCount[FRONT_FACE_INDEX]++;
                    break;
                case 'B':
                    colorCount[BACK_FACE_INDEX]++;
            }
        }
        for(int i=0; i<colorCount.length; i++){
            System.out.println("color "+i+" count: "+colorCount[i]);
        }
    }

    private char convertColors(char color){
        String s;
        switch (color) {
            case 'W':
                return 'U';
            case 'Y':
                return 'D';
            case 'O':
                return 'L';
            case 'R':
                return 'R';
            case 'G':
                return 'F';
            case 'B':
                return 'B';
            default:
                throw new IllegalArgumentException("Invalid color: "+color);
        }
    }

    private String[] convertSolution(String kociembaSolution) {
        ArrayList<String> moves = new ArrayList<>();
        StringTokenizer st = new StringTokenizer(kociembaSolution, ", ");
        while(st.hasMoreElements()){
            String move = st.nextToken();
            if (move.length() == 2 && move.charAt(1) == '\''){
                move = move.replace('\'', 'i');
            }
            moves.add(move);
        }
        return moves.toArray(new String[0]);
    }

    private void appendColorsOnFace(char[][] face, StringBuilder sb){
        for(int i = 0; i<3; i++){
            for(int j = 0; j<3; j++){
                sb.append(face[i][j]); //.append(", ");//cut last 2 characters of string later
            }
        }
    }

    public String[] solve(char[][][] cubeColors) {
        StringBuilder sb = new StringBuilder();
        for(int f = 0; f<6; f++){
            for(int r = 0; r<3; r++){
                for(int c = 0; c<3; c++){
                    cubeColors[f][r][c] = convertColors(cubeColors[f][r][c]);
                }
            }
        }
        appendColorsOnFace(cubeColors[UP_FACE_INDEX], sb);
        appendColorsOnFace(cubeColors[RIGHT_FACE_INDEX], sb);
        appendColorsOnFace(cubeColors[FRONT_FACE_INDEX], sb);
        appendColorsOnFace(cubeColors[DOWN_FACE_INDEX], sb);
        appendColorsOnFace(cubeColors[LEFT_FACE_INDEX], sb);
        appendColorsOnFace(cubeColors[BACK_FACE_INDEX], sb);
        String facelets = sb.toString(); //sb.substring(0,sb.length()-2);

        String solution = Search.solution(facelets, maxDepth, maxTime, useSeparator);
        String[] convertedSolution = convertSolution(solution);
//        for(int i = 0; i < convertedSolution.length; i++){
//            System.out.print(convertedSolution[i]+" ");
//        }
//        System.out.println();
        return convertedSolution;
    }
}
