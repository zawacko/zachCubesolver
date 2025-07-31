package org.cubexell.cubesolver.core;

import static org.cubexell.cubesolver.core.CubeConstants.*;

public class Helper {
    public static char[][][] createSolvedCubeColors(){
        char[][][] cubeColors = new char[6][][];
        cubeColors[FRONT_FACE_INDEX] = new char[][]
                {
                        {'G','G','G'},
                        {'G','G','G'},
                        {'G','G','G'}
                };
        cubeColors[BACK_FACE_INDEX] = new char[][]
                {
                        {'B','B','B'},
                        {'B','B','B'},
                        {'B','B','B'}
                };
        cubeColors[RIGHT_FACE_INDEX] = new char[][]
                {
                        {'R','R','R'},
                        {'R','R','R'},
                        {'R','R','R'}
                };
        cubeColors[LEFT_FACE_INDEX] = new char[][]
                {
                        {'O','O','O'},
                        {'O','O','O'},
                        {'O','O','O'}
                };
        cubeColors[UP_FACE_INDEX] = new char[][]
                {
                        {'W','W','W'},
                        {'W','W','W'},
                        {'W','W','W'}
                };
        cubeColors[DOWN_FACE_INDEX] = new char[][]
                {
                        {'Y','Y','Y'},
                        {'Y','Y','Y'},
                        {'Y','Y','Y'}
                };
        return cubeColors;
    }

    public static String faceColorsToString(char[][][] cubeColors){
        return new StringBuffer("up face: \n").append(matrixToString(cubeColors[UP_FACE_INDEX])).append('\n')
                .append("front face: \n").append(matrixToString(cubeColors[FRONT_FACE_INDEX])).append('\n')
                .append("left face: \n").append(matrixToString(cubeColors[LEFT_FACE_INDEX])).append('\n')
                .append("right face: \n").append(matrixToString(cubeColors[RIGHT_FACE_INDEX])).append('\n')
                .append("back face: \n").append(matrixToString(cubeColors[BACK_FACE_INDEX])).append('\n')
                .append("down face: \n").append(matrixToString(cubeColors[DOWN_FACE_INDEX])).append('\n')
                .toString();
    }

    public static String arrayToString(String[] array){
        StringBuffer sb = new StringBuffer();
        for(int i = 0; i<array.length; i++){
            sb.append(array[i]).append(" ");
        }
        return sb.toString();
    }
    
    public static String matrixToString(char[][] array){
        StringBuffer sb = new StringBuffer();
        for(int i = 0; i<array.length; i++){
            for(int j = 0; j<array[i].length; j++) {
                sb.append(array[i][j]);
            }
            sb.append("   ");
        }
        return sb.toString();
    }

    public static void copyArray(char[] f, char[] t) {
        for (int i = 0; i < f.length; i++) {
            t[i] = f[i];
        }
    }
    public static void copyMatrix(char[][] f, char[][] t) {
        for (int i = 0; i < f.length; i++) {
            for (int j = 0; j < f[i].length; j++) {
                t[i][j] = f[i][j];
            }
        }
    }
    protected static void copy3dArray(char[][][] f, char[][][] t){
        for(int i=0; i<f.length; i++){
            copyMatrix(f[i], t[i]);
        }
    }

    public static void rotate90(char[][] matrix, char[][] tmp) {
        tmp[0][0] = matrix[2][0];
        tmp[0][1] = matrix[1][0];
        tmp[0][2] = matrix[0][0];
        tmp[1][2] = matrix[0][1];
        tmp[2][2] = matrix[0][2];
        tmp[2][1] = matrix[1][2];
        tmp[2][0] = matrix[2][2];
        tmp[1][0] = matrix[2][1];
        tmp[1][1] = matrix[1][1];
        copyMatrix(tmp, matrix);
    }

    public static void rotate180(char[][] matrix, char[][] tmp) {
        tmp[0][0] = matrix[2][2];
        tmp[0][1] = matrix[2][1];
        tmp[0][2] = matrix[2][0];
        tmp[1][2] = matrix[1][0];
        tmp[2][2] = matrix[0][0];
        tmp[2][1] = matrix[0][1];
        tmp[2][0] = matrix[0][2];
        tmp[1][0] = matrix[1][2];
        tmp[1][1] = matrix[1][1];
        copyMatrix(tmp, matrix);
    }

    public static void rotate270(char[][] matrix, char[][] tmp) {
        tmp[0][0] = matrix[0][2];
        tmp[0][1] = matrix[1][2];
        tmp[0][2] = matrix[2][2];
        tmp[1][2] = matrix[2][1];
        tmp[2][2] = matrix[2][0];
        tmp[2][1] = matrix[1][0];
        tmp[2][0] = matrix[0][0];
        tmp[1][0] = matrix[0][1];
        tmp[1][1] = matrix[1][1];
        copyMatrix(tmp, matrix);
    }


}
