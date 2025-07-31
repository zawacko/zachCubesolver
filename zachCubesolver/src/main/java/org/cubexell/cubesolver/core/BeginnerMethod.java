package org.cubexell.cubesolver.core;

import java.util.ArrayList;

import static org.cubexell.cubesolver.core.CubeConstants.*;
//DON'T CHANGE
public class BeginnerMethod
        implements CubeSolvingMethod {
    Cube cube;
    ArrayList<String> solution;

    public BeginnerMethod() {
    }


    //Solve the cross
    public void solveCrossPieces() {
        solveWG();
        solveWR();
        solveWB();
        solveWO();
    }

    //these solve the cross pieces
    public void solveWG() {

        char[] position;
        position = cube.findEdgePosition('W', 'G');

        if (position[0] == 'U' && position[1] == 'F') {
            return;
        } else if (position[0] == 'F' && position[1] == 'U') {
            cube.recordMoves(solution, new String[]{"F", "R", "U"});
        } else if (position[0] == 'U' && position[1] == 'R') {
            cube.recordMoves(solution, new String[]{"U"});
        } else if (position[0] == 'R' && position[1] == 'U') {
            cube.recordMoves(solution, new String[]{"Ri", "Fi"});
        } else if (position[0] == 'U' && position[1] == 'B') {
            cube.recordMoves(solution, new String[]{"U2"});
        } else if (position[0] == 'B' && position[1] == 'U') {
            cube.recordMoves(solution, new String[]{"Bi", "Ri", "U"});
        } else if (position[0] == 'U' && position[1] == 'L') {
            cube.recordMoves(solution, new String[]{"Ui"});
        } else if (position[0] == 'L' && position[1] == 'U') {
            cube.recordMoves(solution, new String[]{"L", "F"});
        } else if (position[0] == 'F' && position[1] == 'R') {
            cube.recordMoves(solution, new String[]{"R", "U"});
        } else if (position[0] == 'R' && position[1] == 'F') {
            cube.recordMoves(solution, new String[]{"Fi"});
        } else if (position[0] == 'R' && position[1] == 'B') {
            cube.recordMoves(solution, new String[]{"R2", "Fi"});
        } else if (position[0] == 'B' && position[1] == 'R') {
            cube.recordMoves(solution, new String[]{"Ri", "U"});
        } else if (position[0] == 'B' && position[1] == 'L') {
            cube.recordMoves(solution, new String[]{"L", "Ui"});
        } else if (position[0] == 'L' && position[1] == 'B') {
            cube.recordMoves(solution, new String[]{"L2", "F"});
        } else if (position[0] == 'L' && position[1] == 'F') {
            cube.recordMoves(solution, new String[]{"F"});
        } else if (position[0] == 'F' && position[1] == 'L') {
            cube.recordMoves(solution, new String[]{"Li", "Ui"});
        } else if (position[0] == 'D' && position[1] == 'F') {
            cube.recordMoves(solution, new String[]{"F2"});
        } else if (position[0] == 'F' && position[1] == 'D') {
            cube.recordMoves(solution, new String[]{"Fi", "R", "U"});
        } else if (position[0] == 'D' && position[1] == 'R') {
            cube.recordMoves(solution, new String[]{"Di", "F2"});
        } else if (position[0] == 'R' && position[1] == 'D') {
            cube.recordMoves(solution, new String[]{"R", "Fi"});
        } else if (position[0] == 'D' && position[1] == 'B') {
            cube.recordMoves(solution, new String[]{"D2", "F2"});
        } else if (position[0] == 'B' && position[1] == 'D') {
            cube.recordMoves(solution, new String[]{"B", "Ri", "U"});
        } else if (position[0] == 'D' && position[1] == 'L') {
            cube.recordMoves(solution, new String[]{"D", "F2"});
        } else if (position[0] == 'L' && position[1] == 'D') {
            cube.recordMoves(solution, new String[]{"Li", "F"});
        }
        return;
    }

    public void solveWR() {

        char[] position;
        position = cube.findEdgePosition('W', 'R');

        if (position[0] == 'U' && position[1] == 'R') {
            return;
        } else if (position[0] == 'R' && position[1] == 'U') {
            cube.recordMoves(solution, new String[]{"Ri", "U", "Fi", "Ui"});
        } else if (position[0] == 'U' && position[1] == 'B') {
            cube.recordMoves(solution, new String[]{"F", "U", "Fi"});
        } else if (position[0] == 'B' && position[1] == 'U') {
            cube.recordMoves(solution, new String[]{"Bi", "Ri"});
        } else if (position[0] == 'U' && position[1] == 'L') {
            cube.recordMoves(solution, new String[]{"F", "U2", "Fi"});
        } else if (position[0] == 'L' && position[1] == 'U') {
            cube.recordMoves(solution, new String[]{"L", "F", "Ui", "Fi"});
        } else if (position[0] == 'F' && position[1] == 'R') {
            cube.recordMoves(solution, new String[]{"R"});
        } else if (position[0] == 'R' && position[1] == 'F') {
            cube.recordMoves(solution, new String[]{"Fi", "Ui", "F"});
        } else if (position[0] == 'R' && position[1] == 'B') {
            cube.recordMoves(solution, new String[]{"Ui", "B", "U"});
        } else if (position[0] == 'B' && position[1] == 'R') {
            cube.recordMoves(solution, new String[]{"Ri"});
        } else if (position[0] == 'B' && position[1] == 'L') {
            cube.recordMoves(solution, new String[]{"U2", "L", "U2"});
        } else if (position[0] == 'L' && position[1] == 'B') {
            cube.recordMoves(solution, new String[]{"Ui", "Bi", "U"});
        } else if (position[0] == 'L' && position[1] == 'F') {
            cube.recordMoves(solution, new String[]{"U", "F", "Ui"});
        } else if (position[0] == 'F' && position[1] == 'L') {
            cube.recordMoves(solution, new String[]{"U2", "Li", "U2"});
        } else if (position[0] == 'D' && position[1] == 'F') {
            cube.recordMoves(solution, new String[]{"D", "R2"});
        } else if (position[0] == 'F' && position[1] == 'D') {
            cube.recordMoves(solution, new String[]{"Fi", "R", "F"});
        } else if (position[0] == 'D' && position[1] == 'R') {
            cube.recordMoves(solution, new String[]{"R2"});
        } else if (position[0] == 'R' && position[1] == 'D') {
            cube.recordMoves(solution, new String[]{"R", "Fi", "Ui", "F"});
        } else if (position[0] == 'D' && position[1] == 'B') {
            cube.recordMoves(solution, new String[]{"Di", "R2"});
        } else if (position[0] == 'B' && position[1] == 'D') {
            cube.recordMoves(solution, new String[]{"B", "Ri"});
        } else if (position[0] == 'D' && position[1] == 'L') {
            cube.recordMoves(solution, new String[]{"D2", "R2"});
        } else if (position[0] == 'L' && position[1] == 'D') {
            cube.recordMoves(solution, new String[]{"Li", "F", "Ui", "Fi"});
        }
        return;
    }

    public void solveWB() {
        char[] position;
        position = cube.findEdgePosition('W', 'B');

        if (position[0] == 'U' && position[1] == 'B') {
            return;
        } else if (position[0] == 'B' && position[1] == 'U') {
            cube.recordMoves(solution, new String[]{"Bi", "U", "Ri", "Ui"});
        } else if (position[0] == 'U' && position[1] == 'L') {
            cube.recordMoves(solution, new String[]{"Li", "Ui", "L", "U"});
        } else if (position[0] == 'L' && position[1] == 'U') {
            cube.recordMoves(solution, new String[]{"Li", "Bi"});
        } else if (position[0] == 'F' && position[1] == 'R') {
            cube.recordMoves(solution, new String[]{"U", "R", "Ui"});
        } else if (position[0] == 'R' && position[1] == 'F') {
            cube.recordMoves(solution, new String[]{"U2", "Fi", "U2"});
        } else if (position[0] == 'R' && position[1] == 'B') {
            cube.recordMoves(solution, new String[]{"B"});
        } else if (position[0] == 'B' && position[1] == 'R') {
            cube.recordMoves(solution, new String[]{"U", "Ri", "Ui"});
        } else if (position[0] == 'B' && position[1] == 'L') {
            cube.recordMoves(solution, new String[]{"Ui", "L", "U"});
        } else if (position[0] == 'L' && position[1] == 'B') {
            cube.recordMoves(solution, new String[]{"Bi"});
        } else if (position[0] == 'L' && position[1] == 'F') {
            cube.recordMoves(solution, new String[]{"U2", "F", "U2"});
        } else if (position[0] == 'F' && position[1] == 'L') {
            cube.recordMoves(solution, new String[]{"Ui", "Li", "U"});
        } else if (position[0] == 'D' && position[1] == 'F') {
            cube.recordMoves(solution, new String[]{"D2", "B2"});
        } else if (position[0] == 'F' && position[1] == 'D') {
            cube.recordMoves(solution, new String[]{"D", "Ri", "B", "R"});
        } else if (position[0] == 'D' && position[1] == 'R') {
            cube.recordMoves(solution, new String[]{"D", "B2"});
        } else if (position[0] == 'R' && position[1] == 'D') {
            cube.recordMoves(solution, new String[]{"Ri", "B", "R"});
        } else if (position[0] == 'D' && position[1] == 'B') {
            cube.recordMoves(solution, new String[]{"B2"});
        } else if (position[0] == 'B' && position[1] == 'D') {
            cube.recordMoves(solution, new String[]{"B", "U", "Ri", "Ui"});
        } else if (position[0] == 'D' && position[1] == 'L') {
            cube.recordMoves(solution, new String[]{"Di", "B2"});
        } else if (position[0] == 'L' && position[1] == 'D') {
            cube.recordMoves(solution, new String[]{"L", "Bi"});
        }
        return;

    }

    public void solveWO() {
        char[] position;
        position = cube.findEdgePosition('W', 'O');

        if (position[0] == 'U' && position[1] == 'L') {
            return;
        } else if (position[0] == 'L' && position[1] == 'U') {
            cube.recordMoves(solution, new String[]{"Li", "U", "Bi", "Ui"});
        } else if (position[0] == 'F' && position[1] == 'R') {
            cube.recordMoves(solution, new String[]{"U2", "R", "U2"});
        } else if (position[0] == 'R' && position[1] == 'F') {
            cube.recordMoves(solution, new String[]{"Ui", "Fi", "U"});
        } else if (position[0] == 'R' && position[1] == 'B') {
            cube.recordMoves(solution, new String[]{"U", "B", "Ui"});
        } else if (position[0] == 'B' && position[1] == 'R') {
            cube.recordMoves(solution, new String[]{"U2", "Ri", "U2"});
        } else if (position[0] == 'B' && position[1] == 'L') {
            cube.recordMoves(solution, new String[]{"L"});
        } else if (position[0] == 'L' && position[1] == 'B') {
            cube.recordMoves(solution, new String[]{"U", "Bi", "Ui"});
        } else if (position[0] == 'L' && position[1] == 'F') {
            cube.recordMoves(solution, new String[]{"Ui", "F", "U"});
        } else if (position[0] == 'F' && position[1] == 'L') {
            cube.recordMoves(solution, new String[]{"Li"});
        } else if (position[0] == 'D' && position[1] == 'F') {
            cube.recordMoves(solution, new String[]{"Di", "L2"});
        } else if (position[0] == 'F' && position[1] == 'D') {
            cube.recordMoves(solution, new String[]{"F", "Li", "Fi"});
        } else if (position[0] == 'D' && position[1] == 'R') {
            cube.recordMoves(solution, new String[]{"D2", "L2"});
        } else if (position[0] == 'R' && position[1] == 'D') {
            cube.recordMoves(solution, new String[]{"Di", "F", "Li", "Fi"});
        } else if (position[0] == 'D' && position[1] == 'B') {
            cube.recordMoves(solution, new String[]{"D", "L2"});
        } else if (position[0] == 'B' && position[1] == 'D') {
            cube.recordMoves(solution, new String[]{"Bi", "L", "B"});
        } else if (position[0] == 'D' && position[1] == 'L') {
            cube.recordMoves(solution, new String[]{"L2"});
        } else if (position[0] == 'L' && position[1] == 'D') {
            cube.recordMoves(solution, new String[]{"D", "F", "Li", "Fi"});
        }
        return;

    }

    public void solve1stLayer() {
        solveWGR();
        solveWRB();
        solveWBO();
        solveWOG();
    }

    //these solve the white corners
    public void solveWGR() {
        char[] position;
        position = cube.findCornerPosition('W', 'G', 'R');

        if (position[0] == 'U' && position[1] == 'F' && position[2] == 'R') {
            return;
        } else if (position[0] == 'R' && position[1] == 'U' && position[2] == 'F') {
            cube.recordMoves(solution, new String[]{"Ri", "Di", "R", "D", "Ri", "Di", "R"});
        } else if (position[0] == 'F' && position[1] == 'R' && position[2] == 'U') {
            cube.recordMoves(solution, new String[]{"Ri", "D", "R", "Di", "Ri", "D", "R"});
        } else if (position[0] == 'U' && position[1] == 'R' && position[2] == 'B') {
            cube.recordMoves(solution, new String[]{"R", "D", "Ri", "D2", "Ri", "Di", "R"});
        } else if (position[0] == 'B' && position[1] == 'U' && position[2] == 'R') {
            cube.recordMoves(solution, new String[]{"R", "Di", "Ri", "Di", "Ri", "Di", "R"});
        } else if (position[0] == 'R' && position[1] == 'B' && position[2] == 'U') {
            cube.recordMoves(solution, new String[]{"R", "D", "Ri", "D", "Ri", "D", "R"});
        } else if (position[0] == 'U' && position[1] == 'B' && position[2] == 'L') {
            cube.recordMoves(solution, new String[]{"Li", "Ri", "D2", "R", "L"});
        } else if (position[0] == 'L' && position[1] == 'U' && position[2] == 'B') {
            cube.recordMoves(solution, new String[]{"Li", "D2", "L", "Ri", "Di", "R"});
        } else if (position[0] == 'B' && position[1] == 'L' && position[2] == 'U') {
            cube.recordMoves(solution, new String[]{"Li", "D", "L", "Ri", "D2", "R"});
        } else if (position[0] == 'U' && position[1] == 'L' && position[2] == 'F') {
            cube.recordMoves(solution, new String[]{"L", "D", "Li", "Ri", "Di", "R"});
        } else if (position[0] == 'F' && position[1] == 'U' && position[2] == 'L') {
            cube.recordMoves(solution, new String[]{"L", "Di", "Li", "D", "Ri", "Di", "R"});
        } else if (position[0] == 'L' && position[1] == 'F' && position[2] == 'U') {
            cube.recordMoves(solution, new String[]{"L", "D", "Li", "Di", "Ri", "D", "R"});
        } else if (position[0] == 'D' && position[1] == 'R' && position[2] == 'F') {
            cube.recordMoves(solution, new String[]{"Ri", "D2", "R", "D", "Ri", "Di", "R"});
        } else if (position[0] == 'F' && position[1] == 'D' && position[2] == 'R') {
            cube.recordMoves(solution, new String[]{"Di", "Ri", "D", "R"});
        } else if (position[0] == 'R' && position[1] == 'F' && position[2] == 'D') {
            cube.recordMoves(solution, new String[]{"Ri", "Di", "R"});
        } else if (position[0] == 'D' && position[1] == 'B' && position[2] == 'R') {
            cube.recordMoves(solution, new String[]{"R", "Di", "R2", "D", "R"});
        } else if (position[0] == 'R' && position[1] == 'D' && position[2] == 'B') {
            cube.recordMoves(solution, new String[]{"D2", "Ri", "D", "R"});
        } else if (position[0] == 'B' && position[1] == 'R' && position[2] == 'D') {
            cube.recordMoves(solution, new String[]{"Di", "Ri", "Di", "R"});
        } else if (position[0] == 'D' && position[1] == 'L' && position[2] == 'B') {
            cube.recordMoves(solution, new String[]{"Ri", "Di", "R2", "Fi", "Ri", "F"});
        } else if (position[0] == 'B' && position[1] == 'D' && position[2] == 'L') {
            cube.recordMoves(solution, new String[]{"Ri", "D2", "R"});
        } else if (position[0] == 'L' && position[1] == 'B' && position[2] == 'D') {
            cube.recordMoves(solution, new String[]{"D2", "Ri", "Di", "R"});
        } else if (position[0] == 'D' && position[1] == 'F' && position[2] == 'L') {
            cube.recordMoves(solution, new String[]{"Fi", "D", "F2", "Di", "Fi"});
        } else if (position[0] == 'L' && position[1] == 'D' && position[2] == 'F') {
            cube.recordMoves(solution, new String[]{"Ri", "D", "R"});
        } else if (position[0] == 'F' && position[1] == 'L' && position[2] == 'D') {
            cube.recordMoves(solution, new String[]{"D", "Ri", "Di", "R"});
        }
        return;
    }

    public void solveWRB() {
        char[] position;
        position = cube.findCornerPosition('W', 'R', 'B');

        if (position[0] == 'U' && position[1] == 'R' && position[2] == 'B') {
            return;
        } else if (position[0] == 'B' && position[1] == 'U' && position[2] == 'R') {
            cube.recordMoves(solution, new String[]{"R", "Di", "Ri", "D", "R", "Di", "Ri"});
        } else if (position[0] == 'R' && position[1] == 'B' && position[2] == 'U') {
            cube.recordMoves(solution, new String[]{"R", "D", "Ri", "Di", "R", "D", "Ri"});
        } else if (position[0] == 'U' && position[1] == 'B' && position[2] == 'L') {
            cube.recordMoves(solution, new String[]{"Li", "Di", "L", "R", "D", "Ri"});
        } else if (position[0] == 'L' && position[1] == 'U' && position[2] == 'B') {
            cube.recordMoves(solution, new String[]{"Li", "Di", "L", "D", "R", "Di", "Ri"});
        } else if (position[0] == 'B' && position[1] == 'L' && position[2] == 'U') {
            cube.recordMoves(solution, new String[]{"Li", "D", "L", "Di", "R", "D", "Ri"});
        } else if (position[0] == 'U' && position[1] == 'L' && position[2] == 'F') {
            cube.recordMoves(solution, new String[]{"L", "R", "D2", "Ri", "Li"});
        } else if (position[0] == 'F' && position[1] == 'U' && position[2] == 'L') {
            cube.recordMoves(solution, new String[]{"L", "Di", "Li", "R", "D2", "Ri"});
        } else if (position[0] == 'L' && position[1] == 'F' && position[2] == 'U') {
            cube.recordMoves(solution, new String[]{"L", "D2", "Li", "Di", "Bi", "D", "B"});
        } else if (position[0] == 'D' && position[1] == 'R' && position[2] == 'F') {
            cube.recordMoves(solution, new String[]{"Ri", "D", "R2", "Di", "R2", "Di", "R"});
        } else if (position[0] == 'F' && position[1] == 'D' && position[2] == 'R') {
            cube.recordMoves(solution, new String[]{"D", "R", "D", "Ri"});
        } else if (position[0] == 'R' && position[1] == 'F' && position[2] == 'D') {
            cube.recordMoves(solution, new String[]{"Di", "R", "D2", "Ri"});
        } else if (position[0] == 'D' && position[1] == 'B' && position[2] == 'R') {
            cube.recordMoves(solution, new String[]{"R", "D2", "Ri", "Di", "R", "D", "Ri"});
        } else if (position[0] == 'R' && position[1] == 'D' && position[2] == 'B') {
            cube.recordMoves(solution, new String[]{"R", "D", "Ri"});
        } else if (position[0] == 'B' && position[1] == 'R' && position[2] == 'D') {
            cube.recordMoves(solution, new String[]{"D", "R", "Di", "Ri"});
        } else if (position[0] == 'D' && position[1] == 'L' && position[2] == 'B') {
            cube.recordMoves(solution, new String[]{"B", "Di", "B2", "D", "B"});
        } else if (position[0] == 'B' && position[1] == 'D' && position[2] == 'L') {
            cube.recordMoves(solution, new String[]{"Di", "R", "D", "Ri"});
        } else if (position[0] == 'L' && position[1] == 'B' && position[2] == 'D') {
            cube.recordMoves(solution, new String[]{"R", "Di", "Ri"});
        } else if (position[0] == 'D' && position[1] == 'F' && position[2] == 'L') {
            cube.recordMoves(solution, new String[]{"R", "D", "Ri", "D", "R", "Di", "Ri"});
        } else if (position[0] == 'L' && position[1] == 'D' && position[2] == 'F') {
            cube.recordMoves(solution, new String[]{"D2", "R", "D", "Ri"});
        } else if (position[0] == 'F' && position[1] == 'L' && position[2] == 'D') {
            cube.recordMoves(solution, new String[]{"R", "D2", "Ri"});
        }
        return;

    }

    public void solveWBO() {
        char[] position;
        position = cube.findCornerPosition('W', 'B', 'O');

        if (position[0] == 'U' && position[1] == 'B' && position[2] == 'L') {
            return;
        } else if (position[0] == 'L' && position[1] == 'U' && position[2] == 'B') {
            cube.recordMoves(solution, new String[]{"Li", "Di", "L", "D", "Li", "Di", "L"});
        } else if (position[0] == 'B' && position[1] == 'L' && position[2] == 'U') {
            cube.recordMoves(solution, new String[]{"Li", "D", "L", "Di", "Li", "D", "L"});
        } else if (position[0] == 'U' && position[1] == 'L' && position[2] == 'F') {
            cube.recordMoves(solution, new String[]{"L", "D", "Li", "D2", "Li", "Di", "L"});
        } else if (position[0] == 'F' && position[1] == 'U' && position[2] == 'L') {
            cube.recordMoves(solution, new String[]{"Fi", "B", "Di", "F", "Bi"});
        } else if (position[0] == 'L' && position[1] == 'F' && position[2] == 'U') {
            cube.recordMoves(solution, new String[]{"L", "D", "L2", "D2", "L",});
        } else if (position[0] == 'D' && position[1] == 'R' && position[2] == 'F') {
            cube.recordMoves(solution, new String[]{"Li", "Di", "L", "Di", "Li", "D", "L"});
        } else if (position[0] == 'F' && position[1] == 'D' && position[2] == 'R') {
            cube.recordMoves(solution, new String[]{"Li", "D2", "L"});
        } else if (position[0] == 'R' && position[1] == 'F' && position[2] == 'D') {
            cube.recordMoves(solution, new String[]{"D2", "Li", "Di", "L"});
        } else if (position[0] == 'D' && position[1] == 'B' && position[2] == 'R') {
            cube.recordMoves(solution, new String[]{"Li", "D2", "L", "Di", "Li", "D", "L"});
        } else if (position[0] == 'R' && position[1] == 'D' && position[2] == 'B') {
            cube.recordMoves(solution, new String[]{"Li", "D", "L"});
        } else if (position[0] == 'B' && position[1] == 'R' && position[2] == 'D') {
            cube.recordMoves(solution, new String[]{"D", "Li", "Di", "L"});
        } else if (position[0] == 'D' && position[1] == 'L' && position[2] == 'B') {
            cube.recordMoves(solution, new String[]{"Li", "D2", "L", "D", "Li", "Di", "L"});
        } else if (position[0] == 'B' && position[1] == 'D' && position[2] == 'L') {
            cube.recordMoves(solution, new String[]{"Di", "Li", "D", "L"});
        } else if (position[0] == 'L' && position[1] == 'B' && position[2] == 'D') {
            cube.recordMoves(solution, new String[]{"Li", "Di", "L"});
        } else if (position[0] == 'D' && position[1] == 'F' && position[2] == 'L') {
            cube.recordMoves(solution, new String[]{"L", "Di", "L2", "D", "L"});
        } else if (position[0] == 'L' && position[1] == 'D' && position[2] == 'F') {
            cube.recordMoves(solution, new String[]{"D2", "Li", "D", "L"});
        } else if (position[0] == 'F' && position[1] == 'L' && position[2] == 'D') {
            cube.recordMoves(solution, new String[]{"Di", "Li", "Di", "L"});
        }
        return;

    }

    public void solveWOG() {
        char[] position;
        position = cube.findCornerPosition('W', 'O', 'G');

        if (position[0] == 'U' && position[1] == 'L' && position[2] == 'F') {
            return;
        } else if (position[0] == 'F' && position[1] == 'U' && position[2] == 'L') {
            cube.recordMoves(solution, new String[]{"L", "Di", "Li", "D", "L", "Di", "Li"});
        } else if (position[0] == 'L' && position[1] == 'F' && position[2] == 'U') {
            cube.recordMoves(solution, new String[]{"L", "D", "Li", "Di", "L", "D", "Li"});
        } else if (position[0] == 'D' && position[1] == 'R' && position[2] == 'F') {
            cube.recordMoves(solution, new String[]{"L", "D2", "Li", "D", "L", "Di", "Li"});
        } else if (position[0] == 'F' && position[1] == 'D' && position[2] == 'R') {
            cube.recordMoves(solution, new String[]{"Di", "L", "D", "Li"});
        } else if (position[0] == 'R' && position[1] == 'F' && position[2] == 'D') {
            cube.recordMoves(solution, new String[]{"L", "Di", "Li"});
        } else if (position[0] == 'D' && position[1] == 'B' && position[2] == 'R') {
            cube.recordMoves(solution, new String[]{"L", "D", "Li", "D", "L", "Di", "Li"});
        } else if (position[0] == 'R' && position[1] == 'D' && position[2] == 'B') {
            cube.recordMoves(solution, new String[]{"D2", "L", "D", "Li"});
        } else if (position[0] == 'B' && position[1] == 'R' && position[2] == 'D') {
            cube.recordMoves(solution, new String[]{"L", "D2", "Li"});
        } else if (position[0] == 'D' && position[1] == 'L' && position[2] == 'B') {
            cube.recordMoves(solution, new String[]{"Di", "L", "D", "Li", "D", "L", "Di", "Li"});
        } else if (position[0] == 'B' && position[1] == 'D' && position[2] == 'L') {
            cube.recordMoves(solution, new String[]{"D", "L", "D", "Li"});
        } else if (position[0] == 'L' && position[1] == 'B' && position[2] == 'D') {
            cube.recordMoves(solution, new String[]{"D2", "L", "Di", "Li"});
        } else if (position[0] == 'D' && position[1] == 'F' && position[2] == 'L') {
            cube.recordMoves(solution, new String[]{"D2", "L", "D", "Li", "D", "L", "Di", "Li"});
        } else if (position[0] == 'L' && position[1] == 'D' && position[2] == 'F') {
            cube.recordMoves(solution, new String[]{"L", "D", "Li"});
        } else if (position[0] == 'F' && position[1] == 'L' && position[2] == 'D') {
            cube.recordMoves(solution, new String[]{"D", "L", "Di", "Li"});
        }
        return;

    }

    public void solve2ndLayer() {
        solveGR();
        solveRB();
        solveBO();
        solveOG();

    }

    public void solveGR() {
        char[] position;
        position = cube.findEdgePosition('G', 'R');

        if (position[0] == 'F' && position[1] == 'R') {
            return;
        } else if (position[0] == 'R' && position[1] == 'F') {
            cube.recordMoves(solution, new String[]{"F2", "D2", "R", "F2", "Ri", "D2", "Fi", "D", "Fi"});
        } else if (position[0] == 'R' && position[1] == 'B') {
            cube.recordMoves(solution, new String[]{"Ui", "R", "D", "Ri", "U", "F", "D2", "Fi"});
        } else if (position[0] == 'B' && position[1] == 'R') {
            cube.recordMoves(solution, new String[]{"R2", "D2", "R2", "D2", "R2"});
        } else if (position[0] == 'B' && position[1] == 'L') {
            cube.recordMoves(solution, new String[]{"U2", "Li", "Di", "L", "U2", "D2", "Ri", "D", "R"});
        } else if (position[0] == 'L' && position[1] == 'B') {
            cube.recordMoves(solution, new String[]{"L2", "U", "L", "D", "Li", "Ui", "D", "F", "Di", "Fi", "Di", "L2"});
        } else if (position[0] == 'L' && position[1] == 'F') {
            cube.recordMoves(solution, new String[]{"U", "Fi", "Di", "F", "Ui", "Ri", "D2", "R"});
        } else if (position[0] == 'F' && position[1] == 'L') {
            cube.recordMoves(solution, new String[]{"F2", "D2", "F2", "D2", "F2"});
        } else if (position[0] == 'D' && position[1] == 'F') {
            cube.recordMoves(solution, new String[]{"D2", "F", "Di", "Fi", "Di", "Ri", "D", "R"});
        } else if (position[0] == 'F' && position[1] == 'D') {
            cube.recordMoves(solution, new String[]{"Di", "Ri", "D", "R", "D", "F", "Di", "Fi"});
        } else if (position[0] == 'D' && position[1] == 'R') {
            cube.recordMoves(solution, new String[]{"D", "F", "Di", "Fi", "Di", "Ri", "D", "R"});
        } else if (position[0] == 'R' && position[1] == 'D') {
            cube.recordMoves(solution, new String[]{"D2", "Ri", "D", "R", "D", "F", "Di", "Fi"});
        } else if (position[0] == 'D' && position[1] == 'B') {
            cube.recordMoves(solution, new String[]{"F", "Di", "Fi", "Di", "Ri", "D", "R"});
        } else if (position[0] == 'B' && position[1] == 'D') {
            cube.recordMoves(solution, new String[]{"D", "Ri", "D", "R", "D", "F", "Di", "Fi"});
        } else if (position[0] == 'D' && position[1] == 'L') {
            cube.recordMoves(solution, new String[]{"Di", "F", "Di", "Fi", "Di", "Ri", "D", "R"});
        } else if (position[0] == 'L' && position[1] == 'D') {
            cube.recordMoves(solution, new String[]{"Ri", "D", "R", "D", "F", "Di", "Fi"});
        }
        return;
    }

    public void solveRB() {
        char[] position;
        position = cube.findEdgePosition('R', 'B');

        if (position[0] == 'R' && position[1] == 'B') {
            return;
        } else if (position[0] == 'B' && position[1] == 'R') {
            cube.recordMoves(solution, new String[]{"R2", "D2", "B", "R2", "Bi", "D2", "Ri", "D", "Ri"});
        } else if (position[0] == 'B' && position[1] == 'L') {
            cube.recordMoves(solution, new String[]{"Ui", "B", "D", "Bi", "U", "R", "D2", "Ri"});
        } else if (position[0] == 'L' && position[1] == 'B') {
            cube.recordMoves(solution, new String[]{"B2", "D2", "B2", "D2", "B2"});
        } else if (position[0] == 'L' && position[1] == 'F') {
            cube.recordMoves(solution, new String[]{"U2", "Fi", "Di", "F", "U2", "D2", "Bi", "D", "B"});
        } else if (position[0] == 'F' && position[1] == 'L') {
            cube.recordMoves(solution, new String[]{"F2", "U", "Ri", "Di", "R", "D", "Ui", "Bi", "D", "B", "Di", "F2"});
        } else if (position[0] == 'D' && position[1] == 'F') {
            cube.recordMoves(solution, new String[]{"Di", "R", "Di", "Ri", "Di", "Bi", "D", "B"});
        } else if (position[0] == 'F' && position[1] == 'D') {
            cube.recordMoves(solution, new String[]{"Bi", "D", "B", "D", "R", "Di", "Ri"});
        } else if (position[0] == 'D' && position[1] == 'R') {
            cube.recordMoves(solution, new String[]{"D2", "R", "Di", "Ri", "Di", "Bi", "D", "B"});
        } else if (position[0] == 'R' && position[1] == 'D') {
            cube.recordMoves(solution, new String[]{"Di", "Bi", "D", "B", "D", "R", "Di", "Ri"});
        } else if (position[0] == 'D' && position[1] == 'B') {
            cube.recordMoves(solution, new String[]{"D", "R", "Di", "Ri", "Di", "Bi", "D", "B"});
        } else if (position[0] == 'B' && position[1] == 'D') {
            cube.recordMoves(solution, new String[]{"D2", "Bi", "D", "B", "D", "R", "Di", "Ri"});
        } else if (position[0] == 'D' && position[1] == 'L') {
            cube.recordMoves(solution, new String[]{"R", "Di", "Ri", "Di", "Bi", "D", "B"});
        } else if (position[0] == 'L' && position[1] == 'D') {
            cube.recordMoves(solution, new String[]{"D", "Bi", "D", "B", "D", "R", "Di", "Ri"});
        }
        return;

    }

    public void solveBO() {
        char[] position;
        position = cube.findEdgePosition('B', 'O');

        if (position[0] == 'B' && position[1] == 'L') {
            return;
        } else if (position[0] == 'L' && position[1] == 'B') {
            cube.recordMoves(solution, new String[]{"B2", "D2", "L", "B2", "Li", "D2", "Bi", "D", "Bi"});
        } else if (position[0] == 'L' && position[1] == 'F') {
            cube.recordMoves(solution, new String[]{"Ui", "L", "D", "Li", "U", "B", "D2", "Bi"});
        } else if (position[0] == 'F' && position[1] == 'L') {
            cube.recordMoves(solution, new String[]{"L2", "D2", "L2", "D2", "L2"});
        } else if (position[0] == 'D' && position[1] == 'F') {
            cube.recordMoves(solution, new String[]{"B", "Di", "Bi", "Di", "Li", "D", "L"});
        } else if (position[0] == 'F' && position[1] == 'D') {
            cube.recordMoves(solution, new String[]{"D", "Li", "D", "L", "D", "B", "Di", "Bi"});
        } else if (position[0] == 'D' && position[1] == 'R') {
            cube.recordMoves(solution, new String[]{"Di", "B", "Di", "Bi", "Di", "Li", "D", "L"});
        } else if (position[0] == 'R' && position[1] == 'D') {
            cube.recordMoves(solution, new String[]{"Li", "D", "L", "D", "B", "Di", "Bi"});
        } else if (position[0] == 'D' && position[1] == 'B') {
            cube.recordMoves(solution, new String[]{"D2", "B", "Di", "Bi", "Di", "Li", "D", "L"});
        } else if (position[0] == 'B' && position[1] == 'D') {
            cube.recordMoves(solution, new String[]{"Di", "Li", "D", "L", "D", "B", "Di", "Bi"});
        } else if (position[0] == 'D' && position[1] == 'L') {
            cube.recordMoves(solution, new String[]{"D", "B", "Di", "Bi", "Di", "Li", "D", "L"});
        } else if (position[0] == 'L' && position[1] == 'D') {
            cube.recordMoves(solution, new String[]{"D2", "Li", "D", "L", "D", "B", "Di", "Bi"});
        }
        return;

    }

    public void solveOG() {
        char[] position;
        position = cube.findEdgePosition('O', 'G');

        if (position[0] == 'L' && position[1] == 'F') {
            return;
        } else if (position[0] == 'F' && position[1] == 'L') {
            cube.recordMoves(solution, new String[]{"L2", "D2", "F", "L2", "Fi", "D2", "Li", "D", "Li"});
        } else if (position[0] == 'D' && position[1] == 'F') {
            cube.recordMoves(solution, new String[]{"D", "L", "Di", "Li", "Di", "Fi", "D", "F"});
        } else if (position[0] == 'F' && position[1] == 'D') {
            cube.recordMoves(solution, new String[]{"D2", "Fi", "D", "F", "D", "L", "Di", "Li"});
        } else if (position[0] == 'D' && position[1] == 'R') {
            cube.recordMoves(solution, new String[]{"L", "Di", "Li", "Di", "Fi", "D", "F"});
        } else if (position[0] == 'R' && position[1] == 'D') {
            cube.recordMoves(solution, new String[]{"D", "Fi", "D", "F", "D", "L", "Di", "Li"});
        } else if (position[0] == 'D' && position[1] == 'B') {
            cube.recordMoves(solution, new String[]{"Di", "L", "Di", "Li", "Di", "Fi", "D", "F"});
        } else if (position[0] == 'B' && position[1] == 'D') {
            cube.recordMoves(solution, new String[]{"Fi", "D", "F", "D", "L", "Di", "Li"});
        } else if (position[0] == 'D' && position[1] == 'L') {
            cube.recordMoves(solution, new String[]{"D2", "L", "Di", "Li", "Di", "Fi", "D", "F"});
        } else if (position[0] == 'L' && position[1] == 'D') {
            cube.recordMoves(solution, new String[]{"Di", "Fi", "D", "F", "D", "L", "Di", "Li"});
        }
        return;
    }

    public void solveYellowCross() {
        orientEdges();
        AlignEdges();
    }

    public void orientEdges() {
        if (cube.cubeColors[DOWN_FACE_INDEX][0][1] == 'Y' && cube.cubeColors[DOWN_FACE_INDEX][1][2] == 'Y' && cube.cubeColors[DOWN_FACE_INDEX][2][1] == 'Y' && cube.cubeColors[DOWN_FACE_INDEX][1][0] == 'Y') {
            return;
        } else if (cube.cubeColors[DOWN_FACE_INDEX][0][1] == 'Y' && cube.cubeColors[DOWN_FACE_INDEX][2][1] == 'Y') {
            cube.recordMoves(solution, new String[]{"L", "B", "D", "Bi", "Di", "Li"});
        } else if (cube.cubeColors[DOWN_FACE_INDEX][1][0] == 'Y' && cube.cubeColors[DOWN_FACE_INDEX][1][2] == 'Y') {
            cube.recordMoves(solution, new String[]{"F", "L", "D", "Li", "Di", "Fi"});
        } else if (cube.cubeColors[DOWN_FACE_INDEX][0][1] == 'Y' && cube.cubeColors[DOWN_FACE_INDEX][1][2] == 'Y') {
            cube.recordMoves(solution, new String[]{"L", "D", "B", "Di", "Bi", "Li"});
        } else if (cube.cubeColors[DOWN_FACE_INDEX][1][2] == 'Y' && cube.cubeColors[DOWN_FACE_INDEX][2][1] == 'Y') {
            cube.recordMoves(solution, new String[]{"F", "D", "L", "Di", "Li", "Fi"});
        } else if (cube.cubeColors[DOWN_FACE_INDEX][2][1] == 'Y' && cube.cubeColors[DOWN_FACE_INDEX][1][0] == 'Y') {
            cube.recordMoves(solution, new String[]{"R", "D", "F", "Di", "Fi", "Ri"});
        } else if (cube.cubeColors[DOWN_FACE_INDEX][1][0] == 'Y' && cube.cubeColors[DOWN_FACE_INDEX][0][1] == 'Y') {
            cube.recordMoves(solution, new String[]{"B", "D", "R", "Di", "Ri", "Bi"});
        } else {
            cube.recordMoves(solution, new String[]{"R", "D", "F", "Di", "Fi", "Ri", "F", "L", "D", "Li", "Di", "Fi"});
        }

    }

    public void AlignEdges() {
        char[] positionYG;
        char[] positionYR;
        char[] positionYB;
        char[] positionYO;

        positionYG = cube.findEdgePosition('Y', 'G');
        positionYR = cube.findEdgePosition('Y', 'R');
        positionYB = cube.findEdgePosition('Y', 'B');
        positionYO = cube.findEdgePosition('Y', 'O');

        if (positionYG[1] == 'F' && positionYR[1] == 'R' && positionYB[1] == 'B' && positionYO[1] == 'L') {
            return;
        }

        if (positionYG[1] != 'F' && positionYR[1] != 'R' && positionYB[1] != 'B' && positionYO[1] != 'L') {
            while (cube.cubeColors[FRONT_FACE_INDEX][2][1] != 'G') {
                cube.recordMoves(solution, new String[]{"D"});
            }

        }
        //update the positions after posible d moves
        positionYG = cube.findEdgePosition('Y', 'G');
        positionYR = cube.findEdgePosition('Y', 'R');
        positionYB = cube.findEdgePosition('Y', 'B');
        positionYO = cube.findEdgePosition('Y', 'O');

        if (positionYG[1] == 'F' && positionYR[1] == 'R' && positionYB[1] == 'B' && positionYO[1] == 'L') {
            return;
        }

        if (positionYG[1] == 'F') {
            if (positionYR[1] == 'R') {
                cube.recordMoves(solution, new String[]{"R", "D", "Ri", "D", "R", "D2", "Ri", "D"});
            } else if (positionYB[1] == 'B') {
                cube.recordMoves(solution, new String[]{"L", "D", "Li", "Di", "Li", "F", "L2", "Di", "Li", "Di", "L", "D", "Li", "Fi"});
            } else if (positionYO[1] == 'L') {
                cube.recordMoves(solution, new String[]{"F", "D", "Fi", "D", "F", "D2", "Fi", "D"});
            } else if (positionYR[1] == 'B') {
                cube.recordMoves(solution, new String[]{"L", "D", "Li", "D", "L", "D2", "Li"});
            } else if (positionYO[1] == 'B') {
                cube.recordMoves(solution, new String[]{"L", "D2", "Li", "Di", "L", "Di", "Li"});
            }
        } else if (positionYR[1] == 'R') {
            if (positionYB[1] == 'B') {
                cube.recordMoves(solution, new String[]{"B", "D", "Bi", "D", "B", "D2", "Bi", "D"});
            } else if (positionYO[1] == 'L') {
                cube.recordMoves(solution, new String[]{"F", "D", "Fi", "Di", "Fi", "R", "F2", "Di", "Fi", "Di", "F", "D", "Fi", "Ri"});
            } else if (positionYB[1] == 'L') {
                cube.recordMoves(solution, new String[]{"F", "D", "Fi", "D", "F", "D2", "Fi"});
            } else if (positionYG[1] == 'L') {
                cube.recordMoves(solution, new String[]{"F", "D2", "Fi", "Di", "F", "Di", "Fi"});
            }
        } else if (positionYB[1] == 'B') {
            if (positionYO[1] == 'L') {
                cube.recordMoves(solution, new String[]{"L", "D", "Li", "D", "L", "D2", "Li", "D"});
            } else if (positionYO[1] == 'F') {
                cube.recordMoves(solution, new String[]{"R", "D", "Ri", "D", "R", "D2", "Ri"});
            } else if (positionYR[1] == 'F') {
                cube.recordMoves(solution, new String[]{"R", "D2", "Ri", "Di", "R", "Di", "Ri"});
            }
        } else if (positionYO[1] == 'L') {
            if (positionYG[1] == 'R') {
                cube.recordMoves(solution, new String[]{"B", "D", "Bi", "D", "B", "D2", "Bi"});
            } else if (positionYB[1] == 'R') {
                cube.recordMoves(solution, new String[]{"B", "D2", "Bi", "Di", "B", "Di", "Bi"});
            }
        }


    }

    public void solveCorners() {
        positionCorners(0);
        FinishCorners();
    }

    public void positionCorners(int recursion) {

        System.out.println("positioning corners");

        char[] positionYRG;
        char[] positionYGO;
        char[] positionYOB;
        char[] positionYBR;

        positionYRG = cube.findCornerPosition('Y', 'R', 'G');
        positionYGO = cube.findCornerPosition('Y', 'G', 'O');
        positionYOB = cube.findCornerPosition('Y', 'O', 'B');
        positionYBR = cube.findCornerPosition('Y', 'B', 'R');

        if ((positionYRG[0] == 'D' || positionYRG[0] == 'R' || positionYRG[0] == 'F') && (positionYRG[1] == 'D' || positionYRG[1] == 'R' || positionYRG[1] == 'F') && (positionYRG[2] == 'D' || positionYRG[2] == 'R' || positionYRG[2] == 'F')) {
            if (positionYGO[0] == 'F' || positionYGO[1] == 'F' || positionYGO[2] == 'F') {
                return;
            }
            if (positionYGO[0] == 'R' || positionYGO[1] == 'R' || positionYGO[2] == 'R') {
                cube.recordMoves(solution, new String[]{"L", "Di", "Ri", "D", "Li", "Di", "R", "D"});
            } else if (positionYGO[0] == 'L' || positionYGO[1] == 'L' || positionYGO[2] == 'L') {
                cube.recordMoves(solution, new String[]{"Di", "Ri", "D", "L", "Di", "R", "D", "Li"});
            }
        } else if ((positionYGO[0] == 'D' || positionYGO[0] == 'F' || positionYGO[0] == 'L') && (positionYGO[1] == 'D' || positionYGO[1] == 'F' || positionYGO[1] == 'L') && (positionYGO[2] == 'D' || positionYGO[2] == 'F' || positionYGO[2] == 'L')) {
            if (positionYOB[0] == 'F' || positionYOB[1] == 'F' || positionYOB[2] == 'F') {
                cube.recordMoves(solution, new String[]{"D", "L", "Di", "Ri", "D", "Li", "Di", "R"});
            } else if (positionYOB[0] == 'R' || positionYOB[1] == 'R' || positionYOB[2] == 'R') {
                cube.recordMoves(solution, new String[]{"Ri", "D", "L", "Di", "R", "D", "Li", "Di"});
            }
        } else if ((positionYOB[0] == 'D' || positionYOB[0] == 'L' || positionYOB[0] == 'B') && (positionYOB[1] == 'D' || positionYOB[1] == 'L' || positionYOB[1] == 'B') && (positionYOB[2] == 'D' || positionYOB[2] == 'L' || positionYOB[2] == 'B')) {
            if (positionYBR[0] == 'L' || positionYBR[1] == 'L' || positionYBR[2] == 'L') {
                cube.recordMoves(solution, new String[]{"R", "Di", "Li", "D", "Ri", "Di", "L", "D"});
            } else if (positionYBR[0] == 'R' || positionYBR[1] == 'R' || positionYBR[2] == 'R') {
                cube.recordMoves(solution, new String[]{"Di", "Li", "D", "R", "Di", "L", "D", "Ri"});
            }
        } else if ((positionYBR[0] == 'D' || positionYBR[0] == 'B' || positionYBR[0] == 'R') && (positionYBR[1] == 'D' || positionYBR[1] == 'B' || positionYBR[1] == 'R') && (positionYBR[2] == 'D' || positionYBR[2] == 'B' || positionYBR[2] == 'R')) {
            if (positionYRG[0] == 'B' || positionYRG[1] == 'B' || positionYRG[2] == 'B') {
                cube.recordMoves(solution, new String[]{"D", "R", "Di", "Li", "D", "Ri", "Di", "L"});
            } else if (positionYRG[0] == 'L' || positionYRG[1] == 'L' || positionYRG[2] == 'L') {
                cube.recordMoves(solution, new String[]{"Li", "D", "R", "Di", "L", "D", "Ri", "Di"});
            }
        } else {
            if (cube.cubeColors[FRONT_FACE_INDEX][2][0] == 'B') {
                cube.recordMoves(solution, new String[]{"L2", "R2", "U", "L2", "R2", "D2", "L2", "R2", "U", "L2", "R2", "D2"});
            } else if (recursion < 3) {
                cube.recordMoves(solution, new String[]{"Li", "D", "R", "Di", "L", "D", "Ri", "Di"});
                recursion++;
                positionCorners(recursion);
            } else {
                System.out.println("impossible to solve corners");
                System.exit(1);
            }

        }

    }

    public void FinishCorners() {

        int counter = 0;

        while (cube.cubeColors[DOWN_FACE_INDEX][0][2] != 'Y' && counter < 20) {
            cube.recordMoves(solution, new String[]{"R", "U", "Ri", "Ui"});
            counter++;
        }
        cube.recordMoves(solution, new String[]{"D"});
        while (cube.cubeColors[DOWN_FACE_INDEX][0][2] != 'Y' && counter < 20) {
            cube.recordMoves(solution, new String[]{"R", "U", "Ri", "Ui"});
            counter++;
        }
        cube.recordMoves(solution, new String[]{"D"});
        while (cube.cubeColors[DOWN_FACE_INDEX][0][2] != 'Y' && counter < 20) {
            cube.recordMoves(solution, new String[]{"R", "U", "Ri", "Ui"});
            counter++;
        }
        cube.recordMoves(solution, new String[]{"D"});
        while (cube.cubeColors[DOWN_FACE_INDEX][0][2] != 'Y' && counter < 20) {
            cube.recordMoves(solution, new String[]{"R", "U", "Ri", "Ui"});
            counter++;
        }
        cube.recordMoves(solution, new String[]{"D"});

    }

    public String[] solve(char[][][] cubeColors) {
        char[][][] cubeColorsCopy = new char[6][3][3];
        Helper.copy3dArray(cubeColors, cubeColorsCopy);
        this.cube = new Cube(cubeColorsCopy);
        this.solution = new ArrayList<String>();
        solveCrossPieces();
        solve1stLayer();
        solve2ndLayer();
        solveYellowCross();
        solveCorners();
        return solution.toArray(new String[0]);

    }

}
