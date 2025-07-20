package org.cubexell.cubesolver.core;

// For core classes like Mat, Scalar, and Size:
import org.bytedeco.javacpp.indexer.UByteIndexer;
import org.bytedeco.opencv.opencv_core.*;

import static org.bytedeco.opencv.global.opencv_imgproc.cvtColor;

// For global functions in image processing and reading images, use static imports:
import static org.bytedeco.opencv.global.opencv_imgcodecs.imread;
import static org.bytedeco.opencv.global.opencv_imgcodecs.imwrite;  // if you write images
import static org.bytedeco.opencv.global.opencv_imgproc.*;           // for image processing functions

// For other global core functions (if needed):
import static org.cubexell.cubesolver.core.CubeConstants.*;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.awt.Graphics2D;
import java.util.*;
import java.util.Arrays;

public class OpenCvRaspberryPiCamera implements CubeColorInspector{
    int imageWidth = 3280;//default image width
    int imageHeight = 2464;//default image height
    public static int colorSquare = 0;

    Robot robot;

    String outputImage = "cubeColors.jpg";//this will be the name of the image of the cube that is saved to the RaspberryPi

    public OpenCvRaspberryPiCamera(Robot robot) {
        this.robot = robot;
    }

    public void startup() {

    }

    public void shutdown() {

    }

    public char[][][] inspect() {
        captureImage();

        char[][][] cubeColors = new char[6][3][3];//creates a blank matrix of the cube

        System.out.println("Back Face");//tells which face the following colors are for
        char[][] backFace = inspectBackFace('B');//the 'B' is the center color, and it gets the colors of the rest of the pieces on the back face
        System.out.println();

        System.out.println("Left Face");
        char[][] leftFace = inspectLeftFace('O');
        System.out.println();

        System.out.println("Down Face");
        char[][] downFace = inspectDownFace('Y' );
        System.out.println();


        robot.executeMoves(SEE_OPPOSITE_FACE_FRONT);//turns the cube so that the front face is on the back side of the cube, and therefore the camera can see the front face
        outputImage = "cubeColorsF.jpg";// new name for the new picture
        captureImage();
        char[][] frontFace = inspectBackFace('G');
        robot.executeMoves(SEE_OPPOSITE_FACE_FRONT);//returns the cube to solved state

        robot.executeMoves(SEE_OPPOSITE_FACE_RIGHT);
        outputImage = "cubeColorsR.jpg";
        captureImage();
        char[][] rightFace = inspectLeftFace('R');
        robot.executeMoves(SEE_OPPOSITE_FACE_RIGHT);

        robot.executeMoves(SEE_OPPOSITE_FACE_UP);
        outputImage = "cubeColorsU.jpg";
        captureImage();
        char[][] upFace = inspectDownFace('W');
        robot.executeMoves(SEE_OPPOSITE_FACE_UP);

        cubeColors[BACK_FACE_INDEX] = backFace;//sets the cubeColors matrix to the colors that the camera saw
        cubeColors[LEFT_FACE_INDEX] = leftFace;
        cubeColors[DOWN_FACE_INDEX] = downFace;
        cubeColors[FRONT_FACE_INDEX] = frontFace;
        cubeColors[RIGHT_FACE_INDEX] = rightFace;
        cubeColors[UP_FACE_INDEX] = upFace;

        return cubeColors;

    }

    public char[][] inspectBackFace(char center) {
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

        drawSquare(squareX,squareY,squareWidth,squareHeight);//draws the rectangle in the image so that we can look at it and tune it's position

        return color;
    }

    public static char classifyColor(Mat square) {

        Mat labSquare = new Mat();//initializes a mat
        cvtColor(square, labSquare, COLOR_BGR2Lab);//converts the original square from BGR to LAB colorspace and saves it to labSquare

        List<Integer> listOfL = new ArrayList<>();//initializeds a list that will hold all of the l values of the pixles
        List<Integer> listOfA = new ArrayList<>();
        List<Integer> listOfB = new ArrayList<>();

        UByteIndexer indexer = labSquare.createIndexer();//creates an indexer that allows us to access the lab values of each pixel

        for () {//TODO use the for loop to go through every row
            for () {//TODO use a for loop to go through every column
                int pixelL = indexer.get();//TODO get the L value of that pixel by putting row, col, and 0 because L is the first value
                int pixelA = indexer.get();//TODO A is second value, so 1
                int pixelB = indexer.get();//TODO B is 2

                listOfL.add();//TODO add the value to the list
                listOfA.add();//TODO A value
                listOfB.add();//TODO B value

            }

        }

        int medianL;//TODO assign the median L value
        int medianA;//TODO A value
        int medianB;//TODO B value

        System.out.println("Median values: L: " + medianL + " A: " + medianA + " B: " + medianB);//prints out the final median values for tuning

        char medianLabColor;//TODO figure out what color the square is given the median lab values

        System.out.println("Median color is: " + medianLabColor);//prints out the color
        System.out.println();

        imwrite("/home/amahl/cubesolver/cubesolver/square" + colorSquare + ".jpg", square);//saves the square for tuning
        colorSquare ++;

        return medianLabColor;//returns the final color

    }

    public static char classifyColorDeltaELab(float l, float a, float b){
        // Real world values
        Map<Character, double[]> referenceColors = new HashMap<>();//this initializes a map that matches characters to an array of unique LAB values. Each character represents one of the colors on the cube, and each color may have multiple characters and therfore LAB values that deal with different lighting conditions.
        referenceColors.put('W', new double[]{210, 130, 137});//TODO change/add all the values to tune the color detection
        referenceColors.put('R', new double[]{85, 165, 160});
        referenceColors.put('O', new double[]{145, 165, 175});
        referenceColors.put('Y', new double[]{165, 105, 194});
        referenceColors.put('G', new double[]{160, 80, 165});
        referenceColors.put('B', new double[]{130, 123, 106});
        referenceColors.put('r', new double[]{175, 165, 150});//another red
        referenceColors.put('w', new double[]{130, 130, 135});//another white
        referenceColors.put('o', new double[]{226, 138, 142});//another orange
        referenceColors.put('s', new double[]{133, 190, 160});//another red
        referenceColors.put('S', new double[]{210, 158, 110});//another red
        referenceColors.put('x', new double[]{182, 122, 126});//another white
        referenceColors.put('q', new double[]{181, 155, 164});//another orange

        char bestColor = 'U';//Set to U so that if something goes wrong and no color is detected, U is returned to signify unknown
        double minDeltaE = Double.MAX_VALUE;//sets it to the maximum possible value that can be stored in a double so that it doesn't end up being less than the minimum distance from the reference color
        for(Map.Entry<Character, double[]> entry : referenceColors.entrySet()){//goes through all reference colors
            double[] ref = entry.getValue();//gets the LAB values from the reference color
            double deltaE = Math.sqrt(Math.pow(l - /*TODO add the l value of the reference value*/, 2)/5 + Math.pow(a - /*TODO add the a value of the reference value*/, 2) + Math.pow(b - /*TODO add the b value of the reference value*/, 2));//uses the pythagorean theorem to calculate the distance of the actual color to the reference color
            if (deltaE < minDeltaE){//if the distance is the least that has been tested so far
                minDeltaE;//TODO set the new distance as the minimum
                bestColor = entry.getKey();//sets the new color as the best color so far
            }
        }
        //the following if statements return the color that should be associated with the extra letters
        if (bestColor == 'r'|| bestColor == 's'|| bestColor == 'S'){
            return 'R';
        }
        if (bestColor == 'w'|| bestColor == 'x'){
            return 'W';
        }
        if (bestColor == 'b'){
            return 'B';
        }
        if (bestColor == 'o' || bestColor == 'q'){
            return 'O';
        }
        return bestColor;
    }

    public void drawSquare(int squareX, int squareY, int squareWidth, int squareHeight) {
        try {
            BufferedImage image = ImageIO.read(new File(outputImage));//loads the image

            Graphics2D g2d = image.createGraphics();//sets up the graphics of the image

            g2d.setColor(Color.RED);//sets the drawing color to red

            g2d.drawRect(squareX, squareY, squareWidth, squareHeight);//draws the rectangle given the coordinates of the top left corner, the width, and the height

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

}
