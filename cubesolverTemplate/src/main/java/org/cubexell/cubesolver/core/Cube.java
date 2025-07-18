package org.cubexell.cubesolver.core;

import java.util.ArrayList;
import java.util.Arrays;

import static org.cubexell.cubesolver.core.CubeConstants.*;

public class Cube {
    public char[][][] cubeColors;
    public transient char[][][] cubeColorsCopy = new char[6][3][3];
    public transient char[][] rotatingGrid = new char[3][3];

    public Cube(char[][][] cubeColors){
        this.cubeColors = cubeColors;
    }

    public static char convertFaceIndexToChar(int faceIndex) {

        if (faceIndex == UP_FACE_INDEX) {
            return 'U';
        } else if (faceIndex == DOWN_FACE_INDEX) {
            return 'D';
        } else if (faceIndex == RIGHT_FACE_INDEX) {
            return 'R';
        } else if (faceIndex == LEFT_FACE_INDEX) {
            return 'L';
        } else if (faceIndex == FRONT_FACE_INDEX) {
            return 'F';
        }

        return 'B';
    }

    public char[][][] getCubeColors(){
        return cubeColors;
    }

    public char[] findEdgePosition(char edgeColorOne, char edgeColorTwo) {
        char[] position = new char[2];
        int[] intPosition;

        String str = "" + edgeColorOne + edgeColorTwo;
        intPosition = locateEdge(str.trim());

        position[0] = convertFaceIndexToChar(intPosition[0]);
        position[1] = convertFaceIndexToChar(intPosition[1]);

        return position;
    }


    public char[] findCornerPosition(char cornerColorOne, char cornerColorTwo, char cornerColorThree) {
        char[] position = new char[3];
        int[] intPosition;

        String str = "" + cornerColorOne + cornerColorTwo + cornerColorThree;
        intPosition = locateCorner(str.trim());

        position[0] = convertFaceIndexToChar(intPosition[0]);
        position[1] = convertFaceIndexToChar(intPosition[1]);
        position[2] = convertFaceIndexToChar(intPosition[2]);

        System.out.println(str + " " + Arrays.toString(position));

        return position;
    }

    public boolean isFaceSolved(char[][] face, char color){
        for(int i = 0; i < face.length; i++){
            for(int j = 0; j < face[0].length; j++) {
                if(face[i][j] != color){
                    return false;
                }
            }
        }
        return true;
    }
    public boolean areFaceEdgesSolved(char[][] face, char color){
        return face[0][1] == color &&
                face[1][0] == color &&
                face[1][2] == color &&
                face[2][1] == color;
    }
    public boolean areEdgesSolved(){
        return areFaceEdgesSolved(cubeColors[UP_FACE_INDEX],'W') &&
                areFaceEdgesSolved(cubeColors[DOWN_FACE_INDEX],'Y') &&
                areFaceEdgesSolved(cubeColors[RIGHT_FACE_INDEX],'R') &&
                areFaceEdgesSolved(cubeColors[LEFT_FACE_INDEX],'O') &&
                areFaceEdgesSolved(cubeColors[FRONT_FACE_INDEX],'G') &&
                areFaceEdgesSolved(cubeColors[BACK_FACE_INDEX],'B');
    }
    public boolean areFaceCornersSolved(char[][] face, char color){
        return face[0][0] == color &&
                face[0][2] == color &&
                face[1][0] == color &&
                face[1][2] == color &&
                face[2][0] == color &&
                face[2][2] == color;
    }
    public boolean areCornersSolved(){
        return areFaceCornersSolved(cubeColors[UP_FACE_INDEX],'W') &&
                areFaceCornersSolved(cubeColors[DOWN_FACE_INDEX],'Y') &&
                areFaceCornersSolved(cubeColors[RIGHT_FACE_INDEX],'R') &&
                areFaceCornersSolved(cubeColors[LEFT_FACE_INDEX],'O') &&
                areFaceCornersSolved(cubeColors[FRONT_FACE_INDEX],'G') &&
                areFaceCornersSolved(cubeColors[BACK_FACE_INDEX],'B');
    }

    public boolean isCubeSolved(){
        return isFaceSolved(cubeColors[UP_FACE_INDEX],'W') &&
                isFaceSolved(cubeColors[DOWN_FACE_INDEX],'Y') &&
                isFaceSolved(cubeColors[RIGHT_FACE_INDEX],'R') &&
                isFaceSolved(cubeColors[LEFT_FACE_INDEX],'O') &&
                isFaceSolved(cubeColors[FRONT_FACE_INDEX],'G') &&
                isFaceSolved(cubeColors[BACK_FACE_INDEX],'B');
    }
    
    private boolean isEdgeBetweenFacesSolved(char faceColor1, char faceColor2){
        int faceIndex1 = getFaceIndexForColor(faceColor1);
        int faceIndex2 = getFaceIndexForColor(faceColor2);
        if(faceIndex1 > faceIndex2){
            //swap, so only need to check the case when faceIndex1 < faceIndex2
            faceIndex1 = faceIndex2;
            faceIndex2 = faceIndex1;
        }
        //12 cases
        //up edges
        if(faceIndex1 == UP_FACE_INDEX && faceIndex2 == FRONT_FACE_INDEX){
                return cubeColors[UP_FACE_INDEX][2][1] == CENTER_COLORS[faceIndex1] &&
                        cubeColors[FRONT_FACE_INDEX][0][1] == CENTER_COLORS[faceIndex2];
        }
        else if(faceIndex1 == UP_FACE_INDEX && faceIndex2 == RIGHT_FACE_INDEX){
            return cubeColors[UP_FACE_INDEX][1][2] == CENTER_COLORS[faceIndex1] &&
                    cubeColors[RIGHT_FACE_INDEX][0][1] == CENTER_COLORS[faceIndex2];
        }
        else if(faceIndex1 == LEFT_FACE_INDEX && faceIndex2 == UP_FACE_INDEX){
            return cubeColors[LEFT_FACE_INDEX][1][0] == CENTER_COLORS[faceIndex1] &&
                    cubeColors[UP_FACE_INDEX][0][1] == CENTER_COLORS[faceIndex2];
        }
        else if(faceIndex1 == BACK_FACE_INDEX && faceIndex2 == UP_FACE_INDEX){
            return cubeColors[BACK_FACE_INDEX][0][1] == CENTER_COLORS[faceIndex1] &&
                    cubeColors[UP_FACE_INDEX][0][1] == CENTER_COLORS[faceIndex2];
        }
        //down edges
        else if(faceIndex1 == FRONT_FACE_INDEX && faceIndex2 == DOWN_FACE_INDEX){
            return cubeColors[FRONT_FACE_INDEX][2][1] == CENTER_COLORS[faceIndex1] &&
                    cubeColors[DOWN_FACE_INDEX][0][1] == CENTER_COLORS[faceIndex2];
        }
        else if(faceIndex1 == RIGHT_FACE_INDEX && faceIndex2 == DOWN_FACE_INDEX){
            return cubeColors[RIGHT_FACE_INDEX][2][1] == CENTER_COLORS[faceIndex1] &&
                    cubeColors[DOWN_FACE_INDEX][1][2] == CENTER_COLORS[faceIndex2];
        }
        else if(faceIndex1 == BACK_FACE_INDEX && faceIndex2 == DOWN_FACE_INDEX){
            return cubeColors[BACK_FACE_INDEX][2][1] == CENTER_COLORS[faceIndex1] &&
                    cubeColors[DOWN_FACE_INDEX][2][1] == CENTER_COLORS[faceIndex2];
        }
        else if(faceIndex1 == LEFT_FACE_INDEX && faceIndex2 == DOWN_FACE_INDEX){
            return cubeColors[LEFT_FACE_INDEX][2][1] == CENTER_COLORS[faceIndex1] &&
                    cubeColors[DOWN_FACE_INDEX][1][0] == CENTER_COLORS[faceIndex2];
        }
        //side edges
        if(faceIndex1 == FRONT_FACE_INDEX && faceIndex2 == RIGHT_FACE_INDEX){
            return cubeColors[FRONT_FACE_INDEX][1][2] == CENTER_COLORS[faceIndex1] &&
                    cubeColors[RIGHT_FACE_INDEX][1][0] == CENTER_COLORS[faceIndex2];
        }
        else if(faceIndex1 == BACK_FACE_INDEX && faceIndex2 == RIGHT_FACE_INDEX){
            return cubeColors[BACK_FACE_INDEX][1][0] == CENTER_COLORS[faceIndex1] &&
                    cubeColors[RIGHT_FACE_INDEX][1][2] == CENTER_COLORS[faceIndex2];
        }
        else if(faceIndex1 == LEFT_FACE_INDEX && faceIndex2 == BACK_FACE_INDEX){
            return cubeColors[LEFT_FACE_INDEX][1][2] == CENTER_COLORS[faceIndex1] &&
                    cubeColors[BACK_FACE_INDEX][1][0] == CENTER_COLORS[faceIndex2];
        }
        else if(faceIndex1 == LEFT_FACE_INDEX && faceIndex2 == FRONT_FACE_INDEX){
            return cubeColors[LEFT_FACE_INDEX][1][2] == CENTER_COLORS[faceIndex1] &&
                    cubeColors[FRONT_FACE_INDEX][1][0] == CENTER_COLORS[faceIndex2];
        }
        return false;
    }

    public boolean isEdgeSolved(String edgeColor){
        return isEdgeBetweenFacesSolved(edgeColor.charAt(0), edgeColor.charAt(1));
    }

    public boolean isCornerBetweenFacesSolved(
            char faceColor1, char faceColor2, char faceColor3){
        int faceIndex1 = getFaceIndexForColor(faceColor1);
        int faceIndex2 = getFaceIndexForColor(faceColor2);
        int faceIndex3 = getFaceIndexForColor(faceColor3);
        int[] faceIndexes = {faceIndex1, faceIndex2, faceIndex3};
        Arrays.sort(faceIndexes);
        faceIndex1 = faceIndexes[0];
        faceIndex2 = faceIndexes[1];
        faceIndex3 = faceIndexes[2];

        //8 cases
        //up corners
        if(faceIndex1 == UP_FACE_INDEX &&
                faceIndex2 == FRONT_FACE_INDEX &&
                faceIndex3 == RIGHT_FACE_INDEX){
            return cubeColors[UP_FACE_INDEX][2][2] == CENTER_COLORS[faceIndex1] &&
                    cubeColors[FRONT_FACE_INDEX][0][2] == CENTER_COLORS[faceIndex2] &&
                    cubeColors[RIGHT_FACE_INDEX][0][0] == CENTER_COLORS[faceIndex3] ;
        }
        else if(faceIndex1 == BACK_FACE_INDEX &&
                faceIndex2 == UP_FACE_INDEX &&
                faceIndex3 == RIGHT_FACE_INDEX){
            return cubeColors[BACK_FACE_INDEX][0][0] == CENTER_COLORS[faceIndex1] &&
                    cubeColors[UP_FACE_INDEX][0][2] == CENTER_COLORS[faceIndex2] &&
                    cubeColors[RIGHT_FACE_INDEX][0][2] == CENTER_COLORS[faceIndex3];
        }
        else if(faceIndex1 == LEFT_FACE_INDEX &&
                faceIndex2 == BACK_FACE_INDEX &&
                faceIndex3 == UP_FACE_INDEX){
            return cubeColors[LEFT_FACE_INDEX][0][0] == CENTER_COLORS[faceIndex1] &&
                    cubeColors[BACK_FACE_INDEX][0][2] == CENTER_COLORS[faceIndex2] &&
                    cubeColors[UP_FACE_INDEX][0][0] == CENTER_COLORS[faceIndex3] ;
        }
        else if(faceIndex1 == LEFT_FACE_INDEX &&
                faceIndex2 == UP_FACE_INDEX &&
                faceIndex3 == FRONT_FACE_INDEX){
            return cubeColors[LEFT_FACE_INDEX][0][2] == CENTER_COLORS[faceIndex1] &&
                    cubeColors[UP_FACE_INDEX][2][0] == CENTER_COLORS[faceIndex2] &&
                    cubeColors[FRONT_FACE_INDEX][0][0] == CENTER_COLORS[faceIndex3] ;
        }

        else if(faceIndex1 == FRONT_FACE_INDEX &&
                faceIndex2 == RIGHT_FACE_INDEX &&
                faceIndex3 == DOWN_FACE_INDEX){
            return cubeColors[FRONT_FACE_INDEX][2][2] == CENTER_COLORS[faceIndex1] &&
                    cubeColors[RIGHT_FACE_INDEX][2][0] == CENTER_COLORS[faceIndex2] &&
                    cubeColors[DOWN_FACE_INDEX][0][2] == CENTER_COLORS[faceIndex3] ;
        }
        else if(faceIndex1 == BACK_FACE_INDEX &&
                faceIndex2 == RIGHT_FACE_INDEX &&
                faceIndex3 == DOWN_FACE_INDEX){
            return cubeColors[BACK_FACE_INDEX][2][0] == CENTER_COLORS[faceIndex1] &&
                    cubeColors[RIGHT_FACE_INDEX][2][2] == CENTER_COLORS[faceIndex2] &&
                    cubeColors[DOWN_FACE_INDEX][2][2] == CENTER_COLORS[faceIndex3];
        }
        else if(faceIndex1 == LEFT_FACE_INDEX &&
                faceIndex2 == BACK_FACE_INDEX &&
                faceIndex3 == DOWN_FACE_INDEX){
            return cubeColors[LEFT_FACE_INDEX][2][0] == CENTER_COLORS[faceIndex1] &&
                    cubeColors[BACK_FACE_INDEX][2][2] == CENTER_COLORS[faceIndex2] &&
                    cubeColors[DOWN_FACE_INDEX][2][0] == CENTER_COLORS[faceIndex3] ;
        }
        else if(faceIndex1 == LEFT_FACE_INDEX &&
                faceIndex2 == FRONT_FACE_INDEX &&
                faceIndex3 == DOWN_FACE_INDEX){
            return cubeColors[LEFT_FACE_INDEX][2][2] == CENTER_COLORS[faceIndex1] &&
                    cubeColors[FRONT_FACE_INDEX][2][0] == CENTER_COLORS[faceIndex2] &&
                    cubeColors[DOWN_FACE_INDEX][0][0] == CENTER_COLORS[faceIndex3] ;
        }
        return false;
    }

    public boolean isCornerSolved(String cornerColor){
        return isCornerBetweenFacesSolved(
                cornerColor.charAt(0), cornerColor.charAt(1), cornerColor.charAt(2));
    }

    public String[] getScramble(){
        String[] scramble = randomScramble(20);
        return scramble;
    }

    public String toString(){
        return Helper.faceColorsToString(cubeColors);
    }

    protected void copyCubeFaces(){
        Helper.copy3dArray(cubeColors, cubeColorsCopy);
    }

    protected void reverseCopyCubeFaces(){
        Helper.copy3dArray(cubeColorsCopy, cubeColors);
    }

    public void turnOutsideUpFace90(){
        copyCubeFaces();
        Helper.copyArray(cubeColorsCopy[RIGHT_FACE_INDEX][0], cubeColors[FRONT_FACE_INDEX][0]);
        Helper.copyArray(cubeColorsCopy[FRONT_FACE_INDEX][0], cubeColors[LEFT_FACE_INDEX][0]);
        Helper.copyArray(cubeColorsCopy[LEFT_FACE_INDEX][0], cubeColors[BACK_FACE_INDEX][0]);
        Helper.copyArray(cubeColorsCopy[BACK_FACE_INDEX][0], cubeColors[RIGHT_FACE_INDEX][0]);
    }
    public void turnOutsideUpFace180(){
        turnOutsideUpFace90();
        turnOutsideUpFace90();
    }
    public void turnOutsideUpFace270(){
        turnOutsideUpFace90();
        turnOutsideUpFace90();
        turnOutsideUpFace90();
    }
    public void turnOutsideDownFace90(){
        copyCubeFaces();
        Helper.copyArray(cubeColorsCopy[LEFT_FACE_INDEX][2], cubeColors[FRONT_FACE_INDEX][2]);
        Helper.copyArray(cubeColorsCopy[BACK_FACE_INDEX][2], cubeColors[LEFT_FACE_INDEX][2]);
        Helper.copyArray(cubeColorsCopy[RIGHT_FACE_INDEX][2], cubeColors[BACK_FACE_INDEX][2]);
        Helper.copyArray(cubeColorsCopy[FRONT_FACE_INDEX][2], cubeColors[RIGHT_FACE_INDEX][2]);
    }
    public void turnOutsideDownFace180(){
        turnOutsideDownFace90();
        turnOutsideDownFace90();
    }
    public void turnOutsideDownFace270(){
        turnOutsideDownFace90();
        turnOutsideDownFace90();
        turnOutsideDownFace90();
    }
    public void turnOutsideLeftFace90() {
        copyCubeFaces();
        cubeColors[DOWN_FACE_INDEX][0][0] = cubeColorsCopy[FRONT_FACE_INDEX][0][0];
        cubeColors[DOWN_FACE_INDEX][1][0] = cubeColorsCopy[FRONT_FACE_INDEX][1][0];
        cubeColors[DOWN_FACE_INDEX][2][0] = cubeColorsCopy[FRONT_FACE_INDEX][2][0];

        cubeColors[BACK_FACE_INDEX][0][2] = cubeColorsCopy[DOWN_FACE_INDEX][2][0];
        cubeColors[BACK_FACE_INDEX][1][2] = cubeColorsCopy[DOWN_FACE_INDEX][1][0];
        cubeColors[BACK_FACE_INDEX][2][2] = cubeColorsCopy[DOWN_FACE_INDEX][0][0];

        cubeColors[UP_FACE_INDEX][0][0] = cubeColorsCopy[BACK_FACE_INDEX][2][2];
        cubeColors[UP_FACE_INDEX][1][0] = cubeColorsCopy[BACK_FACE_INDEX][1][2];
        cubeColors[UP_FACE_INDEX][2][0] = cubeColorsCopy[BACK_FACE_INDEX][0][2];

        cubeColors[FRONT_FACE_INDEX][0][0] = cubeColorsCopy[UP_FACE_INDEX][0][0];
        cubeColors[FRONT_FACE_INDEX][1][0] = cubeColorsCopy[UP_FACE_INDEX][1][0];
        cubeColors[FRONT_FACE_INDEX][2][0] = cubeColorsCopy[UP_FACE_INDEX][2][0];
    }

    public void turnOutsideLeftFace180() {
        turnOutsideLeftFace90();
        turnOutsideLeftFace90();
    }

    public void turnOutSideLeftFace_270() {
        turnOutsideLeftFace90();
        turnOutsideLeftFace90();
        turnOutsideLeftFace90();
    }

    public void turnOutSideRightFace_90() {
        copyCubeFaces();
        cubeColors[DOWN_FACE_INDEX][0][2] = cubeColorsCopy[BACK_FACE_INDEX][2][0];
        cubeColors[DOWN_FACE_INDEX][1][2] = cubeColorsCopy[BACK_FACE_INDEX][1][0];
        cubeColors[DOWN_FACE_INDEX][2][2] = cubeColorsCopy[BACK_FACE_INDEX][0][0];

        cubeColors[BACK_FACE_INDEX][2][0] = cubeColorsCopy[UP_FACE_INDEX][0][2];
        cubeColors[BACK_FACE_INDEX][1][0] = cubeColorsCopy[UP_FACE_INDEX][1][2];
        cubeColors[BACK_FACE_INDEX][0][0] = cubeColorsCopy[UP_FACE_INDEX][2][2];

        cubeColors[UP_FACE_INDEX][0][2] = cubeColorsCopy[FRONT_FACE_INDEX][0][2];
        cubeColors[UP_FACE_INDEX][1][2] = cubeColorsCopy[FRONT_FACE_INDEX][1][2];
        cubeColors[UP_FACE_INDEX][2][2] = cubeColorsCopy[FRONT_FACE_INDEX][2][2];

        cubeColors[FRONT_FACE_INDEX][0][2] = cubeColorsCopy[DOWN_FACE_INDEX][0][2];
        cubeColors[FRONT_FACE_INDEX][1][2] = cubeColorsCopy[DOWN_FACE_INDEX][1][2];
        cubeColors[FRONT_FACE_INDEX][2][2] = cubeColorsCopy[DOWN_FACE_INDEX][2][2];
    }

    public void turnOutSideRightFace_180() {
        turnOutSideRightFace_90();
        turnOutSideRightFace_90();
    }

    public void turnOutSideRightFace_270() {
        turnOutSideRightFace_90();
        turnOutSideRightFace_90();
        turnOutSideRightFace_90();
    }

    public void turnOutSideFrontFace_90() {
        copyCubeFaces();
        cubeColors[DOWN_FACE_INDEX][0][2] = cubeColorsCopy[RIGHT_FACE_INDEX][0][0];
        cubeColors[DOWN_FACE_INDEX][0][1] = cubeColorsCopy[RIGHT_FACE_INDEX][1][0];
        cubeColors[DOWN_FACE_INDEX][0][0] = cubeColorsCopy[RIGHT_FACE_INDEX][2][0];

        cubeColors[LEFT_FACE_INDEX][2][2] = cubeColorsCopy[DOWN_FACE_INDEX][0][2];
        cubeColors[LEFT_FACE_INDEX][1][2] = cubeColorsCopy[DOWN_FACE_INDEX][0][1];
        cubeColors[LEFT_FACE_INDEX][0][2] = cubeColorsCopy[DOWN_FACE_INDEX][0][0];

        cubeColors[UP_FACE_INDEX][2][0] = cubeColorsCopy[LEFT_FACE_INDEX][2][2];
        cubeColors[UP_FACE_INDEX][2][1] = cubeColorsCopy[LEFT_FACE_INDEX][1][2];
        cubeColors[UP_FACE_INDEX][2][2] = cubeColorsCopy[LEFT_FACE_INDEX][0][2];

        cubeColors[RIGHT_FACE_INDEX][0][0] = cubeColorsCopy[UP_FACE_INDEX][2][0];
        cubeColors[RIGHT_FACE_INDEX][1][0] = cubeColorsCopy[UP_FACE_INDEX][2][1];
        cubeColors[RIGHT_FACE_INDEX][2][0] = cubeColorsCopy[UP_FACE_INDEX][2][2];
    }

    public void turnOutSideFrontFace_180() {
        turnOutSideFrontFace_90();
        turnOutSideFrontFace_90();
    }

    public void turnOutSideFrontFace_270() {
        turnOutSideFrontFace_90();
        turnOutSideFrontFace_90();
        turnOutSideFrontFace_90();
    }

    public void turnOutSideBackFace_90() {
        copyCubeFaces();
        cubeColors[DOWN_FACE_INDEX][2][2] = cubeColorsCopy[LEFT_FACE_INDEX][2][0];
        cubeColors[DOWN_FACE_INDEX][2][1] = cubeColorsCopy[LEFT_FACE_INDEX][1][0];
        cubeColors[DOWN_FACE_INDEX][2][0] = cubeColorsCopy[LEFT_FACE_INDEX][0][0];

        cubeColors[LEFT_FACE_INDEX][2][0] = cubeColorsCopy[UP_FACE_INDEX][0][0];
        cubeColors[LEFT_FACE_INDEX][1][0] = cubeColorsCopy[UP_FACE_INDEX][0][1];
        cubeColors[LEFT_FACE_INDEX][0][0] = cubeColorsCopy[UP_FACE_INDEX][0][2];

        cubeColors[UP_FACE_INDEX][0][0] = cubeColorsCopy[RIGHT_FACE_INDEX][0][2];
        cubeColors[UP_FACE_INDEX][0][1] = cubeColorsCopy[RIGHT_FACE_INDEX][1][2];
        cubeColors[UP_FACE_INDEX][0][2] = cubeColorsCopy[RIGHT_FACE_INDEX][2][2];

        cubeColors[RIGHT_FACE_INDEX][0][2] = cubeColorsCopy[DOWN_FACE_INDEX][2][2];
        cubeColors[RIGHT_FACE_INDEX][1][2] = cubeColorsCopy[DOWN_FACE_INDEX][2][1];
        cubeColors[RIGHT_FACE_INDEX][2][2] = cubeColorsCopy[DOWN_FACE_INDEX][2][0];
    }

    public void turnOutSideBackFace_180() {
        turnOutSideBackFace_90();
        turnOutSideBackFace_90();
    }

    public void turnOutSideBackFace_270() {
        turnOutSideBackFace_90();
        turnOutSideBackFace_90();
        turnOutSideBackFace_90();
    }

    public void rotate90(char[][] grid) {
        Helper.rotate90(grid, rotatingGrid);
    }

    public void rotate180(char[][] grid) {
        Helper.rotate180(grid, rotatingGrid);
    }

    public void rotate270(char[][] grid) {
        Helper.rotate270(grid, rotatingGrid);
    }
    
    public void recordMoves(ArrayList<String> solution, String[] moves) {
    	for(int i = 0; i < moves.length; i++) {
    		solution.add(moves[i]);
    	}
    	simulateMoves(moves);
    }

    public void simulateMoves(String[] moves){
        System.out.println(Arrays.toString(moves));

        for (int i = 0; i < moves.length; i++){
            if ("R".equalsIgnoreCase(moves[i])){
                    rotate90(cubeColors[RIGHT_FACE_INDEX]);
                    turnOutSideRightFace_90();
            }
            else if ("R2".equalsIgnoreCase(moves[i])) {
                    rotate180(cubeColors[RIGHT_FACE_INDEX]);
                    turnOutSideRightFace_180();
            }
            else if ("Ri".equalsIgnoreCase(moves[i])) {
                    rotate270(cubeColors[RIGHT_FACE_INDEX]);
                    turnOutSideRightFace_270();
            }
            else if ("L".equalsIgnoreCase(moves[i])) {
                    rotate90(cubeColors[LEFT_FACE_INDEX]);
                    turnOutsideLeftFace90();
            }
            else if ("L2".equalsIgnoreCase(moves[i])) {
                    rotate180(cubeColors[LEFT_FACE_INDEX]);
                    turnOutsideLeftFace180();
            }
            else if ("Li".equalsIgnoreCase(moves[i])) {
                    rotate270(cubeColors[LEFT_FACE_INDEX]);
                    turnOutSideLeftFace_270();
            }
            else if ("U".equalsIgnoreCase(moves[i])) {
                    rotate90(cubeColors[UP_FACE_INDEX]);
                    turnOutsideUpFace90();
            }
            else if ("U2".equalsIgnoreCase(moves[i])) {
                    rotate180(cubeColors[UP_FACE_INDEX]);
                    turnOutsideUpFace180();
            }
            else if ("Ui".equalsIgnoreCase(moves[i])) {
                    rotate270(cubeColors[UP_FACE_INDEX]);
                    turnOutsideUpFace270();
            }
            else if ("D".equalsIgnoreCase(moves[i])) {
                    rotate90(cubeColors[DOWN_FACE_INDEX]);
                    turnOutsideDownFace90();
            }
            else if ("D2".equalsIgnoreCase(moves[i])) {
                    rotate180(cubeColors[DOWN_FACE_INDEX]);
                    turnOutsideDownFace180();
            }
            else if ("Di".equalsIgnoreCase(moves[i])) {
                    rotate270(cubeColors[DOWN_FACE_INDEX]);
                    turnOutsideDownFace270();
            }
            else if ("F".equalsIgnoreCase(moves[i])) {
                    rotate90(cubeColors[FRONT_FACE_INDEX]);
                    turnOutSideFrontFace_90();
            }
            else if ("F2".equalsIgnoreCase(moves[i])) {
                    rotate180(cubeColors[FRONT_FACE_INDEX]);
                    turnOutSideFrontFace_180();
            }
            else if ("Fi".equalsIgnoreCase(moves[i])) {
                    rotate270(cubeColors[FRONT_FACE_INDEX]);
                    turnOutSideFrontFace_270();
            }
            else if ("B".equalsIgnoreCase(moves[i])) {
                    rotate90(cubeColors[BACK_FACE_INDEX]);
                    turnOutSideBackFace_90();
            }
            else if ("B2".equalsIgnoreCase(moves[i])) {
                    rotate180(cubeColors[BACK_FACE_INDEX]);
                    turnOutSideBackFace_180();
            }
            else if ("Bi".equalsIgnoreCase(moves[i])) {
                    rotate270(cubeColors[BACK_FACE_INDEX]);
                    turnOutSideBackFace_270();
            }
        }
    }

    protected int getFaceIndexForColor(char color){
        if(color == CENTER_COLORS[UP_FACE_INDEX]) {
            return UP_FACE_INDEX;
        }
        else if(color == CENTER_COLORS[DOWN_FACE_INDEX]) {
            return DOWN_FACE_INDEX;
        }
        else if(color == CENTER_COLORS[LEFT_FACE_INDEX]) {
            return LEFT_FACE_INDEX;
        }
        else if(color == CENTER_COLORS[RIGHT_FACE_INDEX]) {
            return RIGHT_FACE_INDEX;
        }
        else if(color == CENTER_COLORS[FRONT_FACE_INDEX]) {
            return FRONT_FACE_INDEX;
        }
        else if(color == CENTER_COLORS[BACK_FACE_INDEX]) {
            return BACK_FACE_INDEX;
        }
        else {
            return -1;
        }
    }

    protected static String getReverseOfString(String s) {
        int len = s.length();
        char[] rev = new char[len];
        for(int i=0; i<len; i++){
            rev[i] = s.charAt(len-1-i);
        }
        return new String(rev);
    }

    public static String otherEdge(char a, char b){
        return String.valueOf(b) + String.valueOf(a);
    }
    public String otherCorner1(char a, char b, char c){
        return String.valueOf(c)+String.valueOf(a)+String.valueOf(b);
    }
    public String otherCorner2(char a, char b, char c){
        return String.valueOf(b)+String.valueOf(c)+String.valueOf(a);
    }

    public String otherEdge(String edgeColor){
        return otherEdge(edgeColor.charAt(0), edgeColor.charAt(1));
    }
    public String otherCorner1(String cornerColor){
        return otherCorner1(cornerColor.charAt(0), cornerColor.charAt(1), cornerColor.charAt(2));
    }
    public String otherCorner2(String cornerColor){
        return otherCorner2(cornerColor.charAt(0), cornerColor.charAt(1), cornerColor.charAt(2));
    }


    protected String randomScrambleMove() {
        int randomIndex = (int)(18*Math.random());
        return POSSIBLE_MOVES[randomIndex];
    }

    protected char getFace(String move) {
        return move.charAt(0);
    }

    public String[] randomScramble(int numMoves) {
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

    public String getEdgeColor(int faceIndex1, int faceIndex2){
        //edge between top face and neighbors
        if(faceIndex1==UP_FACE_INDEX && faceIndex2==LEFT_FACE_INDEX){
            return "" + cubeColors[UP_FACE_INDEX][1][0] + cubeColors[LEFT_FACE_INDEX][0][1];
        }
        if(faceIndex1==UP_FACE_INDEX && faceIndex2==FRONT_FACE_INDEX){
            return "" + cubeColors[UP_FACE_INDEX][2][1] + cubeColors[FRONT_FACE_INDEX][0][1];
        }
        if(faceIndex1==UP_FACE_INDEX && faceIndex2==RIGHT_FACE_INDEX){
            return "" + cubeColors[UP_FACE_INDEX][1][2] + cubeColors[RIGHT_FACE_INDEX][0][1];
        }
        if(faceIndex1==UP_FACE_INDEX && faceIndex2==BACK_FACE_INDEX){
            return "" + cubeColors[UP_FACE_INDEX][0][1] + cubeColors[BACK_FACE_INDEX][0][1];
        }
        //edge between bottom face and neighbors
        if(faceIndex1==DOWN_FACE_INDEX && faceIndex2==LEFT_FACE_INDEX){
            return "" + cubeColors[DOWN_FACE_INDEX][1][0] + cubeColors[LEFT_FACE_INDEX][2][1];
        }
        if(faceIndex1==DOWN_FACE_INDEX && faceIndex2==FRONT_FACE_INDEX){
            return "" + cubeColors[DOWN_FACE_INDEX][0][1] + cubeColors[FRONT_FACE_INDEX][2][1];
        }
        if(faceIndex1==DOWN_FACE_INDEX && faceIndex2==RIGHT_FACE_INDEX){
            return "" + cubeColors[DOWN_FACE_INDEX][1][2] + cubeColors[RIGHT_FACE_INDEX][2][1];
        }
        if(faceIndex1==DOWN_FACE_INDEX && faceIndex2==BACK_FACE_INDEX){
            return "" + cubeColors[DOWN_FACE_INDEX][2][1] + cubeColors[BACK_FACE_INDEX][2][1];
        }
        //edge between side faces
        if(faceIndex1==LEFT_FACE_INDEX && faceIndex2==FRONT_FACE_INDEX){
            return "" + cubeColors[LEFT_FACE_INDEX][1][2] + cubeColors[FRONT_FACE_INDEX][1][0];
        }
        if(faceIndex1==FRONT_FACE_INDEX && faceIndex2==RIGHT_FACE_INDEX){
            return "" + cubeColors[FRONT_FACE_INDEX][1][2] + cubeColors[RIGHT_FACE_INDEX][1][0];
        }
        if(faceIndex1==RIGHT_FACE_INDEX && faceIndex2==BACK_FACE_INDEX){
            return "" + cubeColors[RIGHT_FACE_INDEX][1][2] + cubeColors[BACK_FACE_INDEX][1][0];
        }
        if(faceIndex1==BACK_FACE_INDEX && faceIndex2==LEFT_FACE_INDEX){
            return "" + cubeColors[BACK_FACE_INDEX][1][2] + cubeColors[LEFT_FACE_INDEX][1][0];
        }
        return null;
    }

    public String getCornerColor(int faceIndex1, int faceIndex2, int faceIndex3){
        if(faceIndex1==UP_FACE_INDEX && faceIndex2==LEFT_FACE_INDEX && faceIndex3==FRONT_FACE_INDEX){
            return "" + cubeColors[UP_FACE_INDEX][2][0] +
                    cubeColors[LEFT_FACE_INDEX][0][2] +
                    cubeColors[FRONT_FACE_INDEX][0][0];
        }
        if(faceIndex1==UP_FACE_INDEX && faceIndex2==FRONT_FACE_INDEX && faceIndex3==RIGHT_FACE_INDEX){
            return "" + cubeColors[UP_FACE_INDEX][2][2] +
                    cubeColors[FRONT_FACE_INDEX][0][2] +
                    cubeColors[RIGHT_FACE_INDEX][0][0];
        }
        if(faceIndex1==UP_FACE_INDEX && faceIndex2==RIGHT_FACE_INDEX && faceIndex3==BACK_FACE_INDEX){
            return "" + cubeColors[UP_FACE_INDEX][0][2] +
                    cubeColors[RIGHT_FACE_INDEX][0][2] +
                    cubeColors[BACK_FACE_INDEX][0][0];
        }
        if(faceIndex1==UP_FACE_INDEX && faceIndex2==BACK_FACE_INDEX && faceIndex3==LEFT_FACE_INDEX){
            return "" + cubeColors[UP_FACE_INDEX][0][0] +
                    cubeColors[BACK_FACE_INDEX][0][2] +
                    cubeColors[LEFT_FACE_INDEX][0][0];
        }

        if(faceIndex1==DOWN_FACE_INDEX && faceIndex2==FRONT_FACE_INDEX && faceIndex3==LEFT_FACE_INDEX){
            return "" + cubeColors[DOWN_FACE_INDEX][0][0] +
                    cubeColors[FRONT_FACE_INDEX][2][0] +
                    cubeColors[LEFT_FACE_INDEX][2][2];
        }
        if(faceIndex1==DOWN_FACE_INDEX && faceIndex2==RIGHT_FACE_INDEX && faceIndex3==FRONT_FACE_INDEX){
            return "" + cubeColors[DOWN_FACE_INDEX][0][2] +
                    cubeColors[RIGHT_FACE_INDEX][2][0] +
                    cubeColors[FRONT_FACE_INDEX][2][2];
        }
        if(faceIndex1==DOWN_FACE_INDEX && faceIndex2==BACK_FACE_INDEX && faceIndex3==RIGHT_FACE_INDEX){
            return "" + cubeColors[DOWN_FACE_INDEX][2][2] +
                    cubeColors[BACK_FACE_INDEX][2][0] +
                    cubeColors[RIGHT_FACE_INDEX][2][2];
        }
        if(faceIndex1==DOWN_FACE_INDEX && faceIndex2==LEFT_FACE_INDEX && faceIndex3==BACK_FACE_INDEX){
            return "" + cubeColors[DOWN_FACE_INDEX][2][0] +
                    cubeColors[LEFT_FACE_INDEX][2][0] +
                    cubeColors[BACK_FACE_INDEX][2][2];
        }
        return null;
    }

    public int[] locateEdge(String edgeColor){
    	
    	String currEdgeColor = "";
//        System.out.println("edgeColor = " + edgeColor);

        for(int i=0; i<6; i++){
            for(int j=0; j<6; j++){
                currEdgeColor = getEdgeColor(i, j);
//                System.out.println("Inside loop - edgeColor = " + edgeColor + " currEdgeColor = " + currEdgeColor);
//                System.out.println( "i = " + i + " j= " + j);

                if(currEdgeColor==null){
                	continue;
                }
                if(edgeColor.equalsIgnoreCase(currEdgeColor)){
                    return new int[]{i, j};
                }
                else if(edgeColor.equalsIgnoreCase(otherEdge(currEdgeColor))){
                    return new int[]{j, i};
                }
            }
        }
//        System.out.println("edgeColor = " + edgeColor + " currEdgeColor = " + currEdgeColor);
        return null;
    }

    public int[] locateCorner(String cornerColor){
    	
    	String currCornerColor = "";
        for(int i=0; i<6; i++){
            for(int j=0; j<6; j++){
                for(int k=0; k<6; k++) {
                     currCornerColor = getCornerColor(i, j, k);
                    if(currCornerColor==null){
                        continue;
                    }
                    if (cornerColor.equalsIgnoreCase(currCornerColor)) {
                        return new int[]{i, j, k};
                    }
                    else if(otherCorner1(cornerColor).equalsIgnoreCase(currCornerColor)){
                    	//the corner is being rotated clockwise in other corner, but it is being 
                    	//returned as if the faces are being rotated around the corner. this causes
                    	//the corner to be returned as if though it is twisted, so I switched the
                    	//order that the faces are returned and that fixed it.
                        return new int[]{j, k, i};
                    }
                    else if(otherCorner2(cornerColor).equalsIgnoreCase(currCornerColor)){
                        return new int[]{k, i, j};
                    }
                }
            }
        }
//        System.out.println("cornerColor = " + cornerColor + " currCornerColor = " + currCornerColor);
        return null;
    }
    public String findMoveLayer(String move){
        return Character.toString(move.charAt(0));
    }

    public int findMoveRotations(String move){
        if(move.length()==1){
            return 1;
        }else {
            char rotationsOfMove = move.charAt(1);
            if(rotationsOfMove=='i'){
                return 3;
            } else { //only option left is 2
                return 2;
            }
        }
    }

    public String[] simplifySolution(String[] solution){
        ArrayList<String> newSolution = new ArrayList<>();
        for(int i=0; i<(solution.length); i++){
            if(solution[i]!=null){
                if(i+1<solution.length){
                    if(solution[i+1]!=null){
                        String newMove;
                        String originalMove = solution[i];
                        String moveLayer = findMoveLayer(originalMove);
                        int moveRotations = findMoveRotations(originalMove);
                        String nextOriginalMove = solution[i+1];
                        String nextMoveLayer = findMoveLayer(nextOriginalMove);
                        int nextMoveRotations = findMoveRotations(nextOriginalMove);
                        if(moveLayer.equalsIgnoreCase(nextMoveLayer)){
                            int newRotationNum = (moveRotations+nextMoveRotations)%4;
                            String newRotations;
                            if(newRotationNum>0){
                                if(newRotationNum==1){
                                    newMove=moveLayer;
                                } else if(newRotationNum==2){
                                    newMove=moveLayer+"2";
                                } else {
                                    newMove=moveLayer+"i";
                                }
                                newSolution.add(newMove);

                            }
                            i++;

                        } else {
                            newSolution.add(solution[i]);
                        }
                    }
                } else {
                    newSolution.add(solution[i]);
                }
            } else {
                System.out.println("solution["+i+"] is null");
                return newSolution.toArray(new String[0]);
            }
        }
        return newSolution.toArray(new String[0]);
    }

}



