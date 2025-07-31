package org.cubexell.cubesolver.core;

import java.util.*;

public class CubeFixer {
    static CubeSolvingMethod solver = new OldPochmannMethod();

    // Assumed colors: 'W', 'Y', 'R', 'O', 'B', 'G' (White, Yellow, Red, Orange, Blue, Green)
    private static final char[] COLORS = {'W', 'Y', 'R', 'O', 'B', 'G'};

    public static char[][][] fixCube(char[][][] cube) {
        if(isCubeSolvable(cube)){
            return cube;
        }
        else{
            // Step 1: Count each color
            Map<Character, Integer> colorCounts = new HashMap<>();
            for (char color : COLORS) colorCounts.put(color, 0);

            for (int f = 0; f < 6; f++) {
                for (int r = 0; r < 3; r++) {
                    for (int c = 0; c < 3; c++) {
                        char col = cube[f][r][c];
                        colorCounts.put(col, colorCounts.getOrDefault(col, 0) + 1);
                    }
                }
            }

            // Step 2: Calculate overrepresented and underrepresented colors
            List<Character> over = new ArrayList<>();
            List<Character> under = new ArrayList<>();

            for (char color : COLORS) {
                int count = colorCounts.getOrDefault(color, 0);
                if (count > 9) {
                    for (int i = 0; i < count - 9; i++) over.add(color);
                } else if (count < 9) {
                    for (int i = 0; i < 9 - count; i++) under.add(color);
                }
            }

            // Step 3: Replace overrepresented with underrepresented colors on non-center tiles
            int replacementIndex = 0;
            char[][][] fixed = copyCube(cube);

            for (int f = 0; f < 6; f++) {
                for (int r = 0; r < 3; r++) {
                    for (int c = 0; c < 3; c++) {
                        // Skip center tiles
                        if (r == 1 && c == 1) continue;

                        char currentColor = fixed[f][r][c];
                        if (replacementIndex >= over.size()) break;

                        if (currentColor == over.get(replacementIndex)) {
                            // Replace this tile
                            fixed[f][r][c] = under.get(replacementIndex);
                            replacementIndex++;

                            // Stop early if we’ve fixed everything
                            if (replacementIndex >= over.size()) break;
                        }
                    }
                }
            }

            // Step 4: Test if fixed cube is solvable
            if (isCubeSolvable(fixed)) {
                return fixed;
            } else {
                System.out.println("Unable to fix cube");
                return fixed; // Return best effort
            }
        }

    }

    private static char[][][] copyCube(char[][][] cube) {
        char[][][] copy = new char[6][3][3];
        for (int f = 0; f < 6; f++)
            for (int r = 0; r < 3; r++)
                copy[f][r] = Arrays.copyOf(cube[f][r], 3);
        return copy;
    }

    private static boolean isCubeSolvable(char[][][] cube) {
        try{
            solver.solve(cube);
            return true;
        }
        catch (Exception e){
            return false;
        }
    }
}
