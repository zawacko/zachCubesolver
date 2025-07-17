package org.cubexell.cubesolver.core;

public class CubeConstants {

    public static final int LEFT_FACE_INDEX =  0;
    public static final int BACK_FACE_INDEX =  1;
    public static final int UP_FACE_INDEX =  2;
    public static final int FRONT_FACE_INDEX =  3;
    public static final int RIGHT_FACE_INDEX =  4;
    public static final int DOWN_FACE_INDEX =  5;

    public static final char[] CENTER_COLORS =
            {
                    'O',
                    'B',
                    'W',
                    'G',
                    'R',
                    'Y'
            };


    public static final String[] SEE_OPPOSITE_FACE_UP = {"F","B","R2","L2","Fi","Bi"};
    public static final String[] SEE_OPPOSITE_FACE_RIGHT = {"F","B","U2","D2","Fi","Bi"};
    public static final String[] SEE_OPPOSITE_FACE_FRONT = {"Ri","Li","U2","D2","R","L"};
    public static final String[] TPERM = {"R","U","Ri","Ui","Ri","F","R2","Ui","Ri","Ui","R","U","Ri","Fi"};
    public static final String[] CORNER_SWAP_ALGORITHM = {"R","Ui","Ri","Ui","R","U","Ri","Fi","R","U","Ri","Ui","Ri","F","R"};
    public static final String[] WB = {"R2","Ui","R2"};
    public static final String[] REV_WB = {"R2","U","R2"};
    public static final String[] WG = {"R2","U","R2"};
    public static final String[] REV_WG = {"R2","Ui","R2"};
    public static final String[] WO = {" "};
    public static final String[] REV_WO = {" "};
    public static final String[] OW = {"Li","U","Bi","Ui"};
    public static final String[] REV_OW = {"U","B","Ui","L"};
    public static final String[] OG = {"Ui","F","U"};
    public static final String[] REV_OG = {"Ui","Fi","U"};
    public static final String[] OY = {"Li","Ui","F","U"};
    public static final String[] REV_OY = {"Ui","Fi","U","L"};
    public static final String[] OB = {"U","Bi","Ui"};
    public static final String[] REV_OB = {"U","B","Ui"};
    public static final String[] GW = {"R","Fi","Ri","Li"};
    public static final String[] REV_GW = {"L","R","F","Ri"};
    public static final String[] GR = {"U2","R","U2"};
    public static final String[] REV_GR = {"U2","Ri","U2"};
    public static final String[] GY = {"R","F","Ri","Li"};
    public static final String[] REV_GY = {"L","R","Fi","Ri"};
    public static final String[] GO = {"Li"};
    public static final String[] REV_GO = {"L"};
    public static final String[] RB = {"U","B","Ui"};
    public static final String[] REV_RB = {"U","Bi","Ui"};
    public static final String[] RY = {"D2","Li","Ui","F","U"};
    public static final String[] REV_RY = {"Ui","Fi","U","L","D2"};
    public static final String[] RG = {"Ui","Fi","U"};
    public static final String[] REV_RG = {"Ui","F","U"};
    public static final String[] BW = {"Ri","B","R","L"};
    public static final String[] REV_BW = {"Li","Ri","Bi","R"};
    public static final String[] BO = {"L"};
    public static final String[] REV_BO = {"Li"};
    public static final String[] BY = {"Ri","Bi","R","L"};
    public static final String[] REV_BY = {"Li","Ri","B","R"};
    public static final String[] BR = {"U2","Ri","U2"};
    public static final String[] REV_BR = {"U2","R","U2"};
    public static final String[] YG = {"Di","L2"};
    public static final String[] REV_YG = {"L2","D"};
    public static final String[] YR = {"D2","L2"};
    public static final String[] REV_YR = {"L2","D2"};
    public static final String[] YB = {"D","L2"};
    public static final String[] REV_YB = {"L2","Di"};
    public static final String[] YO = {"L2"};
    public static final String[] REV_YO = {"L2"};
    //Check letter position a, then r, and then e(with corner in blue)

    public static final String[] WRB = {"R","Di"};
    public static final String[] REV_WRB = {"D","Ri"};
    public static final String[] WGR = {"F"};
    public static final String[] REV_WGR = {"Fi"};
    public static final String[] WOG = {"F","Ri"};
    public static final String[] REV_WOG = {"R","Fi"};
    public static final String[] OGW = {"F2"};
    public static final String[] REV_OGW = {"F2"};
    public static final String[] OYG = {"D2","R"};
    public static final String[] REV_OYG = {"Ri","D2"};
    public static final String[] OBY = {"D2"};
    public static final String[] REV_OBY = {"D2"};
    public static final String[] GWO = {"Fi","D"};
    public static final String[] REV_GWO = {"Di","F"};
    public static final String[] GRW = {"F2","D"};
    public static final String[] REV_GRW = {"Di","F2"};
    public static final String[] GYR= {"F","D"};
    public static final String[] REV_GYR = {"Di","Fi"};
    public static final String[] GOY = {"D"};
    public static final String[] REV_GOY = {"Di"};
    public static final String[] RWG = {"Ri"};
    public static final String[] REV_RWG = {"R"};
    public static final String[] RBW = {"R2"};
    public static final String[] REV_RBW = {"R2"};
    public static final String[] RYB = {"R"};
    public static final String[] REV_RYB = {"Ri"};
    public static final String[] RGY = {" "};
    public static final String[] REV_RGY = {" "};
    public static final String[] BWR = {"Ri","F"};
    public static final String[] REV_BWR = {"Fi","R"};
    public static final String[] BYO = {"Di","R"};
    public static final String[] REV_BYO = {"Ri","D"};
    public static final String[] BRY = {"Di"};
    public static final String[] REV_BRY = {"D"};
    public static final String[] YGO = {"Fi"};
    public static final String[] REV_YGO = {"F"};
    public static final String[] YRG = {"Ri","Di"};
    public static final String[] REV_YRG = {"D","R"};
    public static final String[] YBR = {"D2","Fi"};
    public static final String[] REV_YBR = {"F","D2"};
    public static final String[]  YOB = {"D","Fi"};
    public static final String[] REV_YOB = {"F","Di"};

    public static final String[] POSSIBLE_MOVES = {"R", "R2", "Ri", "L", "L2", "Li", "F", "F2", "Fi", "B", "B2", "Bi", "U", "U2", "Ui", "D", "D2", "Di"};

}
