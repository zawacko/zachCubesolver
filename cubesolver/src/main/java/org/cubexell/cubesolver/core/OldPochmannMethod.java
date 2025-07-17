package org.cubexell.cubesolver.core;

import java.util.ArrayList;
import java.util.HashSet;

import static org.cubexell.cubesolver.core.CubeConstants.*;

public class OldPochmannMethod
	implements CubeSolvingMethod {
	
	Cube cube;
    ArrayList<String> solution;

	public OldPochmannMethod () {}
    
    public void executeEdgeSwap(String faceColor, ArrayList<String> solution) {
        if ("WB".equalsIgnoreCase(faceColor)) {//Face color is the buffer edge, this checks if White-blue edge is in the buffer spot
            cube.recordMoves(solution, WB);//if so, it does the setup moves to swap to its correct spot
            cube.recordMoves(solution, TPERM);//then it swaps using the t-perm
            cube.recordMoves(solution, REV_WB);//then undoes the setup moves to bring it into the right spot
        }
        else if ("WG".equalsIgnoreCase(faceColor)) {//checks for all edges
            cube.recordMoves(solution, WG);
            cube.recordMoves(solution, TPERM);
            cube.recordMoves(solution, REV_WG);
        }
        else if ("WO".equalsIgnoreCase(faceColor)) {
            cube.recordMoves(solution, WO);
            cube.recordMoves(solution, TPERM);
            cube.recordMoves(solution, REV_WO);
        }
        else if ("OW".equalsIgnoreCase(faceColor)) {
            cube.recordMoves(solution, OW);
            cube.recordMoves(solution, TPERM);
            cube.recordMoves(solution, REV_OW);
        }
        else if ("OG".equalsIgnoreCase(faceColor)) {
            cube.recordMoves(solution, OG);
            cube.recordMoves(solution, TPERM);
            cube.recordMoves(solution, REV_OG);
        }
        else if ("OY".equalsIgnoreCase(faceColor)) {
            cube.recordMoves(solution, OY);
            cube.recordMoves(solution, TPERM);
            cube.recordMoves(solution, REV_OY);
        }
        else if ("OB".equalsIgnoreCase(faceColor)) {
            cube.recordMoves(solution, OB);
            cube.recordMoves(solution, TPERM);
            cube.recordMoves(solution, REV_OB);
        }
        else if ("GW".equalsIgnoreCase(faceColor)) {
            cube.recordMoves(solution, GW);
            cube.recordMoves(solution, TPERM);
            cube.recordMoves(solution, REV_GW);
        }
        else if ("GR".equalsIgnoreCase(faceColor)) {
            cube.recordMoves(solution, GR);
            cube.recordMoves(solution, TPERM);
            cube.recordMoves(solution, REV_GR);
        }
        else if ("GY".equalsIgnoreCase(faceColor)) {
            cube.recordMoves(solution, GY);
            cube.recordMoves(solution, TPERM);
            cube.recordMoves(solution, REV_GY);
        }
        else if ("GO".equalsIgnoreCase(faceColor)) {
            cube.recordMoves(solution, GO);
            cube.recordMoves(solution, TPERM);
            cube.recordMoves(solution, REV_GO);
        }
        else if ("RB".equalsIgnoreCase(faceColor)) {
            cube.recordMoves(solution, RB);
            cube.recordMoves(solution, TPERM);
            cube.recordMoves(solution, REV_RB);
        }
        else if ("RY".equalsIgnoreCase(faceColor)) {
            cube.recordMoves(solution, RY);
            cube.recordMoves(solution, TPERM);
            cube.recordMoves(solution, REV_RY);
        }
        else if ("RG".equalsIgnoreCase(faceColor)) {
            cube.recordMoves(solution, RG);
            cube.recordMoves(solution, TPERM);
            cube.recordMoves(solution, REV_RG);
        }
        else if ("BW".equalsIgnoreCase(faceColor)) {
            cube.recordMoves(solution, BW);
            cube.recordMoves(solution, TPERM);
            cube.recordMoves(solution, REV_BW);
        }
        else if ("BO".equalsIgnoreCase(faceColor)) {
            cube.recordMoves(solution, BO);
            cube.recordMoves(solution, TPERM);
            cube.recordMoves(solution, REV_BO);
        }
        else if ("BY".equalsIgnoreCase(faceColor)) {
            cube.recordMoves(solution, BY);
            cube.recordMoves(solution, TPERM);
            cube.recordMoves(solution, REV_BY);
        }
        else if ("BR".equalsIgnoreCase(faceColor)) {
            cube.recordMoves(solution, BR);
            cube.recordMoves(solution, TPERM);
            cube.recordMoves(solution, REV_BR);
        }
        else if ("YG".equalsIgnoreCase(faceColor)) {
            cube.recordMoves(solution, YG);
            cube.recordMoves(solution, TPERM);
            cube.recordMoves(solution, REV_YG);
        }
        else if ("YR".equalsIgnoreCase(faceColor)) {
            cube.recordMoves(solution, YR);
            cube.recordMoves(solution, TPERM);
            cube.recordMoves(solution, REV_YR);
        }
        else if ("YB".equalsIgnoreCase(faceColor)) {
            cube.recordMoves(solution, YB);
            cube.recordMoves(solution, TPERM);
            cube.recordMoves(solution, REV_YB);
        }
        else if ("YO".equalsIgnoreCase(faceColor)) {
            cube.recordMoves(solution, YO);
            cube.recordMoves(solution, TPERM);
            cube.recordMoves(solution, REV_YO);
        }
        
    }

    public void executeCornerSwap(String faceColor, ArrayList<String> solution) {
        
        if ("WRB".equalsIgnoreCase(faceColor)) {//checks if white-red-blue corner is in the buffer spot
            cube.recordMoves(solution, WRB);//if so, does the setup moves for it
            cube.recordMoves(solution, CORNER_SWAP_ALGORITHM);//does corner swap algorithm
            cube.recordMoves(solution, REV_WRB);//undoes setup moves
        }//if this is false, it checks the next corner, white-green-red, and goes down the list
        else if ("WGR".equalsIgnoreCase(faceColor)) {
            cube.recordMoves(solution, WGR);
            cube.recordMoves(solution, CORNER_SWAP_ALGORITHM);
            cube.recordMoves(solution, REV_WGR);
        }
        else if ("WOG".equalsIgnoreCase(faceColor)) {
            cube.recordMoves(solution, WOG);
            cube.recordMoves(solution, CORNER_SWAP_ALGORITHM);
            cube.recordMoves(solution, REV_WOG);
        }
        else if ("OGW".equalsIgnoreCase(faceColor)) {
            cube.recordMoves(solution, OGW);
            cube.recordMoves(solution, CORNER_SWAP_ALGORITHM);
            cube.recordMoves(solution, REV_OGW);
        }
        else if ("OYG".equalsIgnoreCase(faceColor)) {
            cube.recordMoves(solution, OYG);
            cube.recordMoves(solution, CORNER_SWAP_ALGORITHM);
            cube.recordMoves(solution, REV_OYG);
        }
        else if ("OBY".equalsIgnoreCase(faceColor)) {
            cube.recordMoves(solution, OBY);
            cube.recordMoves(solution, CORNER_SWAP_ALGORITHM);
            cube.recordMoves(solution, REV_OBY);
        }
        else if ("GWO".equalsIgnoreCase(faceColor)) {
            cube.recordMoves(solution, GWO);
            cube.recordMoves(solution, CORNER_SWAP_ALGORITHM);
            cube.recordMoves(solution, REV_GWO);
        }
        else if ("GRW".equalsIgnoreCase(faceColor)) {
            cube.recordMoves(solution, GRW);
            cube.recordMoves(solution, CORNER_SWAP_ALGORITHM);
            cube.recordMoves(solution, REV_GRW);
        }
        else if ("GYR".equalsIgnoreCase(faceColor)) {
            cube.recordMoves(solution, GYR);
            cube.recordMoves(solution, CORNER_SWAP_ALGORITHM);
            cube.recordMoves(solution, REV_GYR);
        }
        else if ("GOY".equalsIgnoreCase(faceColor)) {
            cube.recordMoves(solution, GOY);
            cube.recordMoves(solution, CORNER_SWAP_ALGORITHM);
            cube.recordMoves(solution, REV_GOY);
        }
        else if ("RWG".equalsIgnoreCase(faceColor)) {
            cube.recordMoves(solution, RWG);
            cube.recordMoves(solution, CORNER_SWAP_ALGORITHM);
            cube.recordMoves(solution, REV_RWG);
        }
        else if ("RBW".equalsIgnoreCase(faceColor)) {
            cube.recordMoves(solution, RBW);
            cube.recordMoves(solution, CORNER_SWAP_ALGORITHM);
            cube.recordMoves(solution, REV_RBW);
        }
        else if ("RYB".equalsIgnoreCase(faceColor)) {
            cube.recordMoves(solution, RYB);
            cube.recordMoves(solution, CORNER_SWAP_ALGORITHM);
            cube.recordMoves(solution, REV_RYB);
        }
        else if ("RGY".equalsIgnoreCase(faceColor)) {
            cube.recordMoves(solution, RGY);
            cube.recordMoves(solution, CORNER_SWAP_ALGORITHM);
            cube.recordMoves(solution, REV_RGY);
        }
        else if ("BWR".equalsIgnoreCase(faceColor)) {
            cube.recordMoves(solution, BWR);
            cube.recordMoves(solution, CORNER_SWAP_ALGORITHM);
            cube.recordMoves(solution, REV_BWR);
        }
        else if ("BYO".equalsIgnoreCase(faceColor)) {
            cube.recordMoves(solution, BYO);
            cube.recordMoves(solution, CORNER_SWAP_ALGORITHM);
            cube.recordMoves(solution, REV_BYO);
        }
        else if ("BRY".equalsIgnoreCase(faceColor)) {
            cube.recordMoves(solution, BRY);
            cube.recordMoves(solution, CORNER_SWAP_ALGORITHM);
            cube.recordMoves(solution, REV_BRY);
        }
        else if ("YGO".equalsIgnoreCase(faceColor)) {
            cube.recordMoves(solution, YGO);
            cube.recordMoves(solution, CORNER_SWAP_ALGORITHM);
            cube.recordMoves(solution, REV_YGO);
        }
        else if ("YRG".equalsIgnoreCase(faceColor)) {
            cube.recordMoves(solution, YRG);
            cube.recordMoves(solution, CORNER_SWAP_ALGORITHM);
            cube.recordMoves(solution, REV_YRG);
        }
        else if ("YBR".equalsIgnoreCase(faceColor)) {
            cube.recordMoves(solution, YBR);
            cube.recordMoves(solution, CORNER_SWAP_ALGORITHM);
            cube.recordMoves(solution, REV_YBR);
        }
        else if ("YOB".equalsIgnoreCase(faceColor)) {
            cube.recordMoves(solution, YOB);
            cube.recordMoves(solution, CORNER_SWAP_ALGORITHM);
            cube.recordMoves(solution, REV_YOB);
        }
    }
    

    private void addUnsolvedEdge(HashSet<String> unsolvedEdges, String edge){
        if(!cube.isEdgeSolved(edge))
            unsolvedEdges.add(edge);
    }

    private HashSet<String> createUnsolvedEdges() {
        HashSet<String> unsolvedEdges = new HashSet<>(22);
        addUnsolvedEdge(unsolvedEdges, "WB");
        addUnsolvedEdge(unsolvedEdges, "WG");
        addUnsolvedEdge(unsolvedEdges, "WO");

        addUnsolvedEdge(unsolvedEdges, "OW");
        addUnsolvedEdge(unsolvedEdges, "OG");
        addUnsolvedEdge(unsolvedEdges, "OY");
        addUnsolvedEdge(unsolvedEdges, "OB");

        addUnsolvedEdge(unsolvedEdges, "GW");
        addUnsolvedEdge(unsolvedEdges, "GR");
        addUnsolvedEdge(unsolvedEdges, "GY");
        addUnsolvedEdge(unsolvedEdges, "GO");

        addUnsolvedEdge(unsolvedEdges, "RB");
        addUnsolvedEdge(unsolvedEdges, "RY");
        addUnsolvedEdge(unsolvedEdges, "RG");

        addUnsolvedEdge(unsolvedEdges, "BW");
        addUnsolvedEdge(unsolvedEdges, "BO");
        addUnsolvedEdge(unsolvedEdges, "BY");
        addUnsolvedEdge(unsolvedEdges, "BR");

        addUnsolvedEdge(unsolvedEdges, "YG");
        addUnsolvedEdge(unsolvedEdges, "YR");
        addUnsolvedEdge(unsolvedEdges, "YB");
        addUnsolvedEdge(unsolvedEdges, "YO");
        return unsolvedEdges;
    }

    private void addUnsolvedCorner(HashSet<String> unsolvedCorners, String corner){
        if(!cube.isCornerSolved(corner))
            unsolvedCorners.add(corner);
    }

    private HashSet<String> createUnsolvedCorners() {
        HashSet<String> unsolvedCorners = new HashSet<>(22);
        addUnsolvedCorner(unsolvedCorners, "WRB");
        addUnsolvedCorner(unsolvedCorners, "WGR");
        addUnsolvedCorner(unsolvedCorners, "WOG");

        addUnsolvedCorner(unsolvedCorners, "OGW");
        addUnsolvedCorner(unsolvedCorners, "OYG");
        addUnsolvedCorner(unsolvedCorners, "OBY");

        addUnsolvedCorner(unsolvedCorners, "GWO");
        addUnsolvedCorner(unsolvedCorners, "GRW");
        addUnsolvedCorner(unsolvedCorners, "GYR");
        addUnsolvedCorner(unsolvedCorners, "GOY");

        addUnsolvedCorner(unsolvedCorners, "RBW");
        addUnsolvedCorner(unsolvedCorners, "RYB");
        addUnsolvedCorner(unsolvedCorners, "RWG");
        addUnsolvedCorner(unsolvedCorners, "RGY");

        addUnsolvedCorner(unsolvedCorners, "BWR");
        addUnsolvedCorner(unsolvedCorners, "BYO");
        addUnsolvedCorner(unsolvedCorners, "BRY");

        addUnsolvedCorner(unsolvedCorners, "YGO");
        addUnsolvedCorner(unsolvedCorners, "YRG");
        addUnsolvedCorner(unsolvedCorners, "YBR");
        addUnsolvedCorner(unsolvedCorners, "YOB");
        return unsolvedCorners;
    }


    String getEdgeToBeSolved(){
        char b;//TODO: assign the color in the b spot to a character b
        char m;//TODO: assign the color in the m spot to a character m
        return String.valueOf(b) + String.valueOf(m);//return a combined string of the 2 characters, e.g. "WR", "BG", etc.
    }


    public void solveEdges() {
        HashSet<String> unsolvedEdges ;//TODO: setup a HASHSET of all edges to solve by the name of unsovedEdges
        while () {//TODO: inside the parentheses, check if the #unsovled edges>0 AND if the edges are solved
            System.out.println("num unsolved edges: " + unsolvedEdges.size());
            String faceColor;//TODO: define a String named faceColor to be the edge in the buffer spot

            System.out.println("faceColor: " + faceColor);
            while () {//TODO: check if the edge "WR" is NOT the face color, also check if "RW" is NOT the face color
                //TODO: execute the edge swap for the face color
                if (unsolvedEdges.contains(faceColor)) {
                    unsolvedEdges.remove(faceColor);//then it removes that edge from the list
                    unsolvedEdges.remove(cube.getReverseOfString(faceColor));
                }
                faceColor;//TODO: assign the new edge in the buffer to the variable faceColor

                System.out.println("faceColor: " + faceColor);
                System.out.println("num unsolved edges: " + unsolvedEdges.size());
                //if the next edge to solve is the buffer, it will exit this loop, otherwise, it repeats this process
            }
            //if we are here, it means we have exited the previous loop, and that the buffer(white-red) is in its spot
            if (unsolvedEdges.size() > 0) {
                System.out.println("new cycle, swapping with: " + unsolvedEdges.iterator().next());
                System.out.println("number of unsolved edges: " + unsolvedEdges.size());
                //TODO: execute edge swap with a random unsolved edge (hint: take the next value in unsolvedEdges)
            }
            //check if edges are solved, if it is, exit loop, if not, repeat for the new cycle
        }
        //if you're here, it means edges are done, and we can more on to corners
        System.out.println("Done with edges..., unsolved edges: " + unsolvedEdges.size());
    }

    public void solveCorners() {
        HashSet<String> unsolvedCorners = createUnsolvedCorners();//setup a list of all corners to solve, we will remove corners once they're solved
        while (unsolvedCorners.size() > 0  && !cube.areCornersSolved()) {//loops through all corners until there are none left to solve
            System.out.println("num unsolved corners: " + unsolvedCorners.size());
            char a = cube.cubeColors[UP_FACE_INDEX][0][0];
            char r = cube.cubeColors[BACK_FACE_INDEX][0][2];
            char e = cube.cubeColors[LEFT_FACE_INDEX][0][0];
            String faceColor = String.valueOf(a) + String.valueOf(r) + String.valueOf(e);//gets the color of the corner in the buffer spot, to solve
            while (!"WBO".equalsIgnoreCase(faceColor) &&//loops through a cycle, and ends it once the buffer is in its spot
                    !"OWB".equalsIgnoreCase(faceColor) &&
                    !"BOW".equalsIgnoreCase(faceColor)){
                executeCornerSwap(faceColor, solution);//does the corner swap for that specific edge
                if (unsolvedCorners.contains(faceColor)){
                    unsolvedCorners.remove(faceColor);//then it removes that corner from the list
                    unsolvedCorners.remove(cube.otherCorner1(a,r,e));//along with its 2 other ones that are twisted versions of it
                    unsolvedCorners.remove(cube.otherCorner2(a,r,e));
                }
                a = cube.cubeColors[UP_FACE_INDEX][0][0];
                r = cube.cubeColors[BACK_FACE_INDEX][0][2];
                e = cube.cubeColors[LEFT_FACE_INDEX][0][0];
                faceColor = String.valueOf(a) + String.valueOf(r) + String.valueOf(e);//get the next corner to solve
                System.out.println("faceColor: " + faceColor);
                System.out.println("num unsolved corners: " + unsolvedCorners.size());
                //if the next corner to solve is the buffer, it will exit this loop, otherwise, it repeats this process
            }
            if (unsolvedCorners.size() > 0) {
                executeCornerSwap(unsolvedCorners.iterator().next(), solution);
                System.out.println("new cycle, swapping with: " + unsolvedCorners.iterator().next());
                System.out.println("number of unsolved corners: " + unsolvedCorners.size());
            }
            //check if the cube is solved, if it is, exit loop, if not, repeat for the new cycle
        }
        System.out.println("Done with corners..., unsolved corners: " + unsolvedCorners.size());
        //CUBE IS SOLVED!!!
    }
    
    public String[] solve (char[][][] cubeColors) {
    	char[][][] cubeColorsCopy = new char[6][3][3];
    	Helper.copy3dArray(cubeColors, cubeColorsCopy);
    	this.cube = new Cube(cubeColorsCopy);
    	this.solution = new ArrayList<String>();
    	solveEdges();
    	solveCorners();
    	return solution.toArray(new String[0]);
    }
}
