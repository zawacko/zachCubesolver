package org.cubexell.cubesolver.core;

// For core classes like Mat, Scalar, and Size:
import org.bytedeco.javacpp.indexer.UByteIndexer;
import org.bytedeco.opencv.opencv_core.*;

import static org.bytedeco.opencv.global.opencv_core.CV_8UC3;
import static org.bytedeco.opencv.global.opencv_imgproc.cvtColor;

// For global functions in image processing and reading images, use static imports:
import static org.bytedeco.opencv.global.opencv_imgcodecs.imread;
import static org.bytedeco.opencv.global.opencv_imgcodecs.imwrite;  // if you write images
import static org.bytedeco.opencv.global.opencv_imgproc.*;           // for image processing functions

// For other global core functions (if needed):
import static org.cubexell.cubesolver.core.CubeConstants.*;
import static org.cubexell.cubesolver.core.CubeConstants.ROTATE_AROUND_CORNER;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;
import java.util.*;
import java.util.Arrays;
import java.util.List;

public class OpenCvRaspberryPiCamera implements CubeColorInspector{
    int imageWidth = 3280;//default image width
    int imageHeight = 2464;//default image height
    public static boolean autoTune = false;
    public static int[][][][] realReferenceColors = new int[3][8][6][3];
    public static int face;
    public static int piece;
    public static int color;

    Robot robot;

    String outputImage = "cubeColors.jpg";//this will be the name of the image of the cube that is saved to the RaspberryPi

    public OpenCvRaspberryPiCamera(Robot robot, boolean autoTune) {
        this.robot = robot;
        this.autoTune = autoTune;
    }

    public void startup() {

    }

    public void shutdown() {

    }

    public char[][][] inspect() {
        if(!autoTune){
            readLabReferenceValues();
        }

        //TODO done
        captureImage();


        char[][][] cubeColors = new char[6][3][3];//creates a blank matrix of the cube

        System.out.println("Back Face");//tells which face the following colors are for
        char[][] backFace = inspectBackFace('B');//TODO done
        System.out.println();

        System.out.println("Left Face");
        char[][] leftFace = inspectLeftFace('0');//TODO done
        System.out.println();

        System.out.println("Down Face");
        char[][] downFace = inspectDownFace('Y');//TODO done
        System.out.println();


        robot.executeMoves(SEE_OPPOSITE_FACE_FRONT);//turns the cube so that the front face is on the back side of the cube, and therefore the camera can see the front face
        outputImage = "cubeColorsF.jpg";// new name for the new picture
        captureImage();
        char[][] frontFace = inspectBackFace('G');//TODO done
        robot.executeMoves(SEE_OPPOSITE_FACE_FRONT);//returns the cube to solved state

        robot.executeMoves(SEE_OPPOSITE_FACE_RIGHT);
        outputImage = "cubeColorsR.jpg";
        captureImage();
        char[][] rightFace = inspectLeftFace('R');//TODO done
        robot.executeMoves(SEE_OPPOSITE_FACE_RIGHT);

        robot.executeMoves(SEE_OPPOSITE_FACE_UP);
        outputImage = "cubeColorsU.jpg";
        captureImage();
        char[][] upFace = inspectDownFace('W');//TODO up face, (on the down face) center 'W'
        robot.executeMoves(SEE_OPPOSITE_FACE_UP);

        cubeColors[BACK_FACE_INDEX] = backFace;//sets the cubeColors matrix to the colors that the camera saw
        cubeColors[LEFT_FACE_INDEX] = leftFace;
        cubeColors[DOWN_FACE_INDEX] = downFace;
        cubeColors[FRONT_FACE_INDEX] = frontFace;
        cubeColors[RIGHT_FACE_INDEX] = rightFace;
        cubeColors[UP_FACE_INDEX] = upFace;

        if(autoTune){
            robot.executeMoves(ROTATE_AROUND_CORNER);//effectively change orientation to tune more colors for each square

            outputImage = "tuningStuff1.jpg";

            captureImage();

            inspectBackFace('O');//orange is now on back, so center is orange

            inspectLeftFace('Y');

            inspectDownFace('B');


            robot.executeMoves(SEE_OPPOSITE_FACE_FRONT);
            outputImage = "tuningStuff1F.jpg";
            captureImage();
            inspectBackFace('R');
            robot.executeMoves(SEE_OPPOSITE_FACE_FRONT);

            robot.executeMoves(SEE_OPPOSITE_FACE_RIGHT);
            outputImage = "tuningStuff1R.jpg";
            captureImage();
            inspectLeftFace('W');
            robot.executeMoves(SEE_OPPOSITE_FACE_RIGHT);

            robot.executeMoves(SEE_OPPOSITE_FACE_UP);
            outputImage = "tuningStuff1U.jpg";
            captureImage();
            inspectDownFace('G');
            robot.executeMoves(SEE_OPPOSITE_FACE_UP);

            robot.executeMoves(ROTATE_AROUND_CORNER);

            outputImage = "tuningStuff2.jpg";

            captureImage();

            inspectBackFace('Y');

            inspectLeftFace('B');

            inspectDownFace('O');


            robot.executeMoves(SEE_OPPOSITE_FACE_FRONT);
            outputImage = "tuningStuff2F.jpg";
            captureImage();
            inspectBackFace('W');
            robot.executeMoves(SEE_OPPOSITE_FACE_FRONT);

            robot.executeMoves(SEE_OPPOSITE_FACE_RIGHT);
            outputImage = "tuningStuff2R.jpg";
            captureImage();
            inspectLeftFace('G');
            robot.executeMoves(SEE_OPPOSITE_FACE_RIGHT);

            robot.executeMoves(SEE_OPPOSITE_FACE_UP);
            outputImage = "tuningStuff2U.jpg";
            captureImage();
            inspectDownFace('R');
            robot.executeMoves(SEE_OPPOSITE_FACE_UP);

            robot.executeMoves(ROTATE_AROUND_CORNER);

            saveLabReferenceValues(realReferenceColors);//write the values to file to be compared to later values instead of manually tuning each value
        }

        return cubeColors;

    }

    public char[][] inspectBackFace(char center) {
        //TODO done
        face = 0;
        //TODO done
        piece = 0;
        //TODO done
        color = convertFaceColorToIndex(center);
        return new char[][]{//returns a 2 dimensional array of the colors of the back face
                {
                        findColor(1000,585,90,85),//gets the color of the top-left piece of the back face. coordinates are of the top-left corner, width, and height.
                        findColor(1160,470,240,90),
                        findColor(1490,140,290,90),
                },
                {
                        findColor(850,1025,140,250),
                        center,
                        findColor(1500,515,280,215)
                },
                {
                        findColor(745,1500,155,250),
                        findColor(1010,1375,220,250),
                        findColor(1390,1080,390,290)
                },
        };
    }

    public char[][] inspectLeftFace(char center) {
        //TODO done
        face = 1;
        //TODO done
        piece = 0;
        //TODO done
        color = convertFaceColorToIndex(center);
        return new char[][]{
                {
                        findColor(1950,75,225,100),
                        findColor(2320,450,200,125),
                        findColor(2600,640,90,50),
                },
                {
                        findColor(1925,500,300,215),
                        center,
                        findColor(2715,1060,125,275)
                },
                {
                        findColor(1910,1040,375,315),
                        findColor(2425,1325,200,275),
                        findColor(2800,1515,150,300)
                },
        };
    }

    public char[][] inspectDownFace(char center) {
        //TODO done
        face = 2;
        //TODO done
        piece = 0;
        //TODO done
        color = convertFaceColorToIndex(center);
        return new char[][]{
                {
                        findColor(2490,1975,375,95),
                        findColor(2020,2030,300,65),
                        findColor(1870,2110,80,40),
                },
                {
                        findColor(2165,1800,375,125),
                        center,
                        findColor(1400,2015,150,90)
                },
                {
                        findColor(1590,1565,500,180),
                        findColor(1230,1740,250,190),
                        findColor(800,1880,280,75)
                },
        };
    }

    public void captureImage() {
        try {
            ProcessBuilder processBuilder = new ProcessBuilder("libcamera-jpeg", "-o", outputImage, "--width", Integer.toString(imageWidth), "--height", Integer.toString(imageHeight), "--timeout", "1000");//this is basically running a command in terminal that takes a picture with these peramiters.

            Process process = processBuilder.start();

            int exitCode = process.waitFor();

            if (exitCode == 0) {//makes sure it was able to take the picture
                System.out.println("Image captured successfully: " + outputImage);
            } else {
                System.out.println("Error capturing image, exit code: " + exitCode);
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();//stops program if error occurs and shows where it happened
        }
    }

    public char findColor(int squareX, int squareY, int squareWidth, int squareHeight) {//the peramiters are the x and y coordinates of the top-left corner, width, and height of the rectangle. (0,0) is the top-left corner of the entire image.
        Mat image = imread(outputImage);//turns the image into a "Mat" so that opencv can proccess it

        Mat square = new Mat(image, new Rect(squareX, squareY, squareWidth, squareHeight));//creates a new mat of just the rectangle

        char color = classifyColor(square);//gets the color of that rectangle

        drawSquare(squareX,squareY,squareWidth,squareHeight, color);//draws the rectangle in the image so that we can look at it and tune it's position

        return color;
    }

    public static char classifyColor(Mat square) {

        Mat labSquare = new Mat();//initializes a mat
        cvtColor(square, labSquare, COLOR_BGR2Lab);//converts the original square from BGR to LAB colorspace and saves it to labSquare

        List<Integer> listOfL = new ArrayList<>();//initializeds a list that will hold all of the l values of the pixles
        List<Integer> listOfA = new ArrayList<>();
        List<Integer> listOfB = new ArrayList<>();

        UByteIndexer indexer = labSquare.createIndexer();//creates an indexer that allows us to access the lab values of each pixel

        for(int row = 0; row < labSquare.rows(); row++) {//TODO done
            for (int col = 0; col < labSquare.cols(); row++) {//TODO done
                int pixelL = indexer.get(row, col, 0);//TODO done
                int pixelA = indexer.get(row, col, 1);//TODO done
                int pixelB = indexer.get(row, col, 2);//TODO done

                listOfL.add(pixelL);//TODO done
                listOfA.add(pixelA);//TODO done
                listOfB.add(pixelB);//TODO done

            }

        }

        int medianL = getListMedian(listOfL);//TODO done
        int medianA = getListMedian(listOfA);//TODO done
        int medianB = getListMedian(listOfB);//TODO done

        System.out.println("Median values: L: " + medianL + " A: " + medianA + " B: " + medianB);//prints out the final median values for tuning

        char medianLabColor = classifyColorDeltaELab(medianL, medianA, medianB);//TODO done

        System.out.println("Median color is: " + medianLabColor);//prints out the color
        System.out.println();

        return medianLabColor;//returns the final color

    }

    public static char classifyColorDeltaELab(int l, int a, int b){
        Map<Character, int[]> referenceColors = new HashMap<>();//this initializes a map that matches characters to an array of unique LAB values. Each character represents one of the colors on the cube, and each color may have multiple characters and therfore LAB values that deal with different lighting conditions.

        if(autoTune){
            //TODO done
            realReferenceColors[face][piece][color][0] = l;
            //TODO done
            realReferenceColors[face][piece][color][1] = a;
            //TODO done
            realReferenceColors[face][piece][color][2] = b;
            piece++;
            return 'U';
        }else{
            referenceColors.put('W', realReferenceColors[face][piece][UP_FACE_INDEX]) /*TODO done)*/;
            referenceColors.put('Y', realReferenceColors[face][piece][DOWN_FACE_INDEX])/*TODO done*/;
            referenceColors.put('G', realReferenceColors[face][piece][FRONT_FACE_INDEX])/*TODO done*/;
            referenceColors.put('B', realReferenceColors[face][piece][BACK_FACE_INDEX])/*TODO done*/;
            referenceColors.put('R', realReferenceColors[face][piece][RIGHT_FACE_INDEX])/*TODO done*/;
            referenceColors.put('O', realReferenceColors[face][piece][LEFT_FACE_INDEX])/*TODO done*/;
        }

        char bestColor = 'U';//Set to U so that if something goes wrong and no color is detected, U is returned to signify unknown
        double minDeltaE = Double.MAX_VALUE;//sets it to the maximum possible value that can be stored in a double so that it doesn't end up being less than the minimum distance from the reference color
        for(Map.Entry<Character, int[]> entry : referenceColors.entrySet()){//goes through all reference colors
            int[] ref = entry.getValue();//gets the LAB values from the reference color
            double deltaE = Math.sqrt(Math.pow(l - ref[0]/*TODO done*/, 2)/5 + Math.pow(a - ref[1]/*TODO done*/, 2) + Math.pow(b - ref[2]/*TODO done*/, 2));//uses the pythagorean theorem to calculate the distance of the actual color to the reference color
            if (deltaE < minDeltaE){//if the distance is the least that has been tested so far
                minDeltaE = deltaE;//TODO done
                bestColor = entry.getKey();//sets the new color as the best color so far
            }
        }

        Mat lab = new Mat(1, 1, CV_8UC3);
        lab.ptr(0, 0).put((byte) l, (byte) a, (byte) b);//put the lab values into a new 1x1 mat
        Mat bgr = new Mat();
        cvtColor(lab, bgr, COLOR_Lab2BGR);//convert it to bgr

        byte[] bgrPixel = new byte[3];
        bgr.ptr(0, 0).get(bgrPixel);//extract the bgr values to an array

        int blue = Byte.toUnsignedInt(bgrPixel[0]);//get each bgr value
        int green = Byte.toUnsignedInt(bgrPixel[1]);
        int red = Byte.toUnsignedInt(bgrPixel[2]);

        if (bestColor == 'R' || bestColor == 'O'){
            blue = blue*255/red;//scale up the blue value to perfect lighting
            green = green*255/red;//scale up green value
            red = 255;//scale up red value
            if(green - blue > 25){//if there is a lot more green, it must be orange
                return 'O';
            }else{
                return 'R';
            }
        }

        return bestColor;
    }

    public void drawSquare(int squareX, int squareY, int squareWidth, int squareHeight, char color) {
        try {
            BufferedImage image = ImageIO.read(new File(outputImage));//loads the image

            Graphics2D g2d = image.createGraphics();//sets up the graphics of the image

            g2d.setFont(new Font("TimesRoman", Font.BOLD, 20));

            g2d.setColor(Color.BLACK);//sets the drawing color to red

            g2d.drawRect(squareX, squareY, squareWidth, squareHeight);//draws the rectangle given the coordinates of the top left corner, the width, and the height

            g2d.drawString(String.valueOf(color),squareX + (squareWidth/2),squareY +(squareHeight/2));

            g2d.dispose();//gets rid of the graphics when its done to reduce clutter

            ImageIO.write(image, "jpg", new File(outputImage));//replaces the original image with the new image that has the rectangle drawn on it

        } catch (IOException e) {//handles any errors
            e.printStackTrace();
        }
    }

    public static int getListMedian(List<Integer> list){
        list.sort(Comparator.naturalOrder());//puts all items in the list into numerical order
        int n = list.size();//gets the number of items in the list
        if (n<1) throw new IllegalArgumentException("List is empty");//makes sure there is stuff in the list, if not it will give an error

        if(n % 2 == 1){//if it is odd
            return list.get((n-1)/2);//returns the middle number as the median
        } else{//if it is even
            return (list.get(n/2-1) + list.get(n/2))/2;//takes the mean of the two middle numbers to return as the median
        }
    }
    public static int convertFaceColorToIndex(char faceColor){
        if(faceColor == 'W'){
            return UP_FACE_INDEX;
        }else if(faceColor == 'Y'){
            return DOWN_FACE_INDEX;
        }else if(faceColor == 'G'){
            return FRONT_FACE_INDEX;
        }else if(faceColor == 'B'){
            return BACK_FACE_INDEX;
        }else if(faceColor == 'R'){
            return RIGHT_FACE_INDEX;
        }else{
            return LEFT_FACE_INDEX;
        }
    }
    public static void saveLabReferenceValues(int[][][][] labValues){
        try(FileWriter writer = new FileWriter("labValues.txt")){
            for(int i = 0; i < labValues.length; i++){
                for(int j = 0; j < labValues[i].length; j++){
                    for(int k = 0; k < labValues[i][j].length; k++){
                        int[] lab = labValues[i][j][k];

                        writer.write(i + " " + j + " " + k + " "
                                + lab[0] + " " + lab[1] + " " + lab[2] + "\n");
                    }
                }
            }

            System.out.println("Saved Lab Values to labValues.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void readLabReferenceValues(){
        int[][][][] tunedLabValues = new int[3][8][6][3];
        try (BufferedReader reader = new BufferedReader(new FileReader("labValues.txt"))){
            String line;
            while((line = reader.readLine()) != null){
                String[] parts = line.trim().split(" ");
                int i = Integer.parseInt(parts[0]);
                int j = Integer.parseInt(parts[1]);
                int k = Integer.parseInt(parts[2]);

                realReferenceColors[i][j][k][0] = Integer.parseInt(parts[3]);
                realReferenceColors[i][j][k][1] = Integer.parseInt(parts[4]);
                realReferenceColors[i][j][k][2] = Integer.parseInt(parts[5]);
            }
            System.out.println("Loaded Lab values");
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

}
