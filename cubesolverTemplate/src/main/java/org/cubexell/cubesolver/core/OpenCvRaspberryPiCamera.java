package org.cubexell.cubesolver.core;

// For core classes like Mat, Scalar, and Size:
import org.bytedeco.javacpp.FloatPointer;
import org.bytedeco.javacpp.indexer.FloatRawIndexer;
import org.bytedeco.javacpp.indexer.UByteIndexer;
import org.bytedeco.opencv.opencv_core.*;
import org.opencv.core.CvType;
import org.opencv.imgproc.Imgproc;

import static org.bytedeco.opencv.global.opencv_imgproc.cvtColor;
import static org.bytedeco.opencv.global.opencv_imgproc.GaussianBlur;
import static org.bytedeco.opencv.global.opencv_core.countNonZero;
import static org.bytedeco.opencv.global.opencv_imgproc.COLOR_BGR2HSV;
import static org.bytedeco.opencv.global.opencv_core.inRange;

// For global functions in image processing and reading images, use static imports:
import static org.bytedeco.opencv.global.opencv_imgcodecs.imread;
import static org.bytedeco.opencv.global.opencv_imgcodecs.imwrite;  // if you write images
import static org.bytedeco.opencv.global.opencv_imgproc.*;           // for image processing functions

// For other global core functions (if needed):
import static org.bytedeco.opencv.global.opencv_core.*;
import static org.cubexell.cubesolver.core.CubeConstants.*;
import static org.cubexell.cubesolver.core.CubeConstants.SEE_OPPOSITE_FACE_UP;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.awt.Graphics2D;
import java.util.*;
import java.util.Arrays;

public class OpenCvRaspberryPiCamera implements CubeColorInspector{
    int imageWidth = 3280;
    int imageHeight = 2464;
    public static int colorSquare = 0;

    Robot robot;

    String outputImage = "cubeColors.jpg";

    public OpenCvRaspberryPiCamera(Robot robot) {
        this.robot = robot;
    }

    public void startup() {

    }

    public void shutdown() {

    }

    public char[][][] inspect() {
        captureImage();

        char[][][] cubeColors = new char[6][3][3];

        System.out.println("Back Face");
        char[][] backFace = inspectBackFace('B');
        System.out.println();

        System.out.println("Left Face");
        char[][] leftFace = inspectLeftFace('O');
        System.out.println();

        System.out.println("Down Face");
        char[][] downFace = inspectDownFace('Y' );
        System.out.println();


        robot.executeMoves(SEE_OPPOSITE_FACE_FRONT);
        outputImage = "cubeColorsF.jpg";
        captureImage();
        char[][] frontFace = inspectBackFace('G');
        robot.executeMoves(SEE_OPPOSITE_FACE_FRONT);

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

        cubeColors[BACK_FACE_INDEX] = backFace;
        cubeColors[LEFT_FACE_INDEX] = leftFace;
        cubeColors[DOWN_FACE_INDEX] = downFace;
        cubeColors[FRONT_FACE_INDEX] = frontFace;
        cubeColors[RIGHT_FACE_INDEX] = rightFace;
        cubeColors[UP_FACE_INDEX] = upFace;

        return cubeColors;

    }

    public char[][] inspectBackFace(char center) {
        return new char[][]{
                {
                        findColor(1000,585,90,85),
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
            ProcessBuilder processBuilder = new ProcessBuilder("libcamera-jpeg", "-o", outputImage, "--width", Integer.toString(imageWidth), "--height", Integer.toString(imageHeight), "--timeout", "1000");

            Process process = processBuilder.start();

            int exitCode = process.waitFor();

            if (exitCode == 0) {
                System.out.println("Image captured successfully: " + outputImage);
            } else {
                System.out.println("Error capturing image, exit code: " + exitCode);
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public char findColor(int squareX, int squareY, int squareWidth, int squareHeight) {
        Mat image = imread(outputImage);
        
        Mat square = new Mat(image, new Rect(squareX, squareY, squareWidth, squareHeight));
 
        char color = classifyColor(square);
        
        drawSquare(squareX,squareY,squareWidth,squareHeight);

        return color;
    }

    public static char classifyColor(Mat square) {
        // Convert the square to HSV color space
        Mat hsvSquare = new Mat();
        cvtColor(square, hsvSquare, COLOR_BGR2HSV);
        Mat labSquare = new Mat();
        cvtColor(square, labSquare, COLOR_BGR2Lab);

        // Define color ranges for classification
        List<Scalar[]> colorRanges = new ArrayList<>();
        colorRanges.add(new Scalar[]{new Scalar(0, 50, 30, 0), new Scalar(40, 255, 255, 0)}); // Red
        colorRanges.add(new Scalar[]{new Scalar(0, 80, 80, 0), new Scalar(50, 255, 255, 0)}); // Orange
        colorRanges.add(new Scalar[]{new Scalar(15, 80, 80, 0), new Scalar(60, 255, 255, 0)}); // Yellow
        colorRanges.add(new Scalar[]{new Scalar(25, 80, 80, 0), new Scalar(100, 255, 255, 0)}); // Green
        colorRanges.add(new Scalar[]{new Scalar(60, 80, 80, 0), new Scalar(150, 255, 255, 0)}); // Blue
        colorRanges.add(new Scalar[]{new Scalar(0, 0, 200, 0), new Scalar(180, 30, 255, 0)}); // White

        Mat equalizedSquare = equalizeLighting(hsvSquare);

        // Apply Gaussian blur to reduce glare and noise
        //equalizedSquare.convertTo(equalizedSquare, CvType.CV_32F);
        Mat blurredSquare = new Mat();
        GaussianBlur(equalizedSquare, blurredSquare, new Size(5, 5), 0);

        //Mat labEqualizedSquare = equalizeLighting(labSquare);

        // Apply Gaussian blur to reduce glare and noise
        //labEqualizedSquare.convertTo(labEqualizedSquare, CvType.CV_32F);
//        Mat labBlurredSquare = new Mat();
//        GaussianBlur(labSquare, labBlurredSquare, new Size(5, 5), 0);
        Mat labBlurredSquare = labSquare;

        //Mat bgrEqualizedSquare = equalizeLighting(square);
        Mat bgrBlurredSquare = new Mat();
        GaussianBlur(square, bgrBlurredSquare, new Size(5, 5), 0);
        
        int red=0, orange=0, white=0, yellow=0, green=0, blue=0;

        float hue = 0, saturation = 0, value = 0;
        int pixelCounter = 0;
        UByteIndexer hsvIndexer = blurredSquare.createIndexer();
        for (int row = 0; row < blurredSquare.rows(); row++) {
            for (int col = 0; col < blurredSquare.cols(); col++) {
                //FloatPointer fp = new FloatPointer(blurredSquare.ptr(row, col));
                hue += hsvIndexer.get(row, col, 0);
                saturation += hsvIndexer.get(row, col, 1);
                value += hsvIndexer.get(row, col, 2);

//                hue += fp.get(0);
//                saturation += fp.get(1);
//                value += fp.get(2);

                //char pixelColor = classifyColorHSV(fp.get(0), fp.get(1), fp.get(2));
                char pixelColor = 'R';

                switch (pixelColor) {
                    case 'R': red++; break;
                    case 'O': orange++; break;
                    case 'W': white++; break;
                    case 'Y': yellow++; break;
                    case 'G': green++; break;
                    case 'B': blue++; break;
                    }

                pixelCounter++;

            }

        }

        float avgHue = hue/pixelCounter;
        float avgSaturation = saturation/pixelCounter;
        float avgValue = value/pixelCounter;

        System.out.println("hue: " + avgHue + " saturation: " + avgSaturation + " value: " + avgValue);

//        char dominantColor = findDominantColor(red, orange, white, yellow, green, blue);
//        System.out.println("red = "+red + " orange = "+orange + " white = "+white + " yellow = "+yellow + " green = "+green + "blue = "+blue);
//        System.out.println(dominantColor);
//        return dominantColor;

//        Mat normalizedSquare = new Mat();
//        blurredSquare.convertTo(normalizedSquare, CV_32FC3, 1.0/255.0, 0);

//        Mat bgrSquare = new Mat();
//        cvtColor(normalizedSquare, bgrSquare, COLOR_HSV2BGR);
//        Mat finalLabSquare = new Mat();
//        cvtColor(bgrSquare, finalLabSquare, COLOR_BGR2Lab);


        List<Integer> listOfL = new ArrayList<>();
        List<Integer> listOfA = new ArrayList<>();
        List<Integer> listOfB = new ArrayList<>();

        UByteIndexer indexer = labBlurredSquare.createIndexer();
        float l = 0, a = 0, b = 0;
        int labPixelCounter = 0;
        int labRed=0, labOrange=0, labWhite=0, labYellow=0, labGreen=0, labBlue=0;
        for (int row = 0; row < labBlurredSquare.rows(); row++) {
            for (int col = 0; col < labBlurredSquare.cols(); col++) {
                int pixelL = indexer.get(row, col, 0);
                int pixelA = indexer.get(row, col, 1);
                int pixelB = indexer.get(row, col, 2);
                l += pixelL;
                a += pixelA;
                b += pixelB;

                char pixelColor = classifyColorDeltaELab(pixelL, pixelA, pixelB);

                switch (pixelColor) {
                    case 'R': labRed++; break;
                    case 'O': labOrange++; break;
                    case 'W': labWhite++; break;
                    case 'Y': labYellow++; break;
                    case 'G': labGreen++; break;
                    case 'B': labBlue++; break;
                }

                labPixelCounter++;

                listOfL.add(pixelL);
                listOfA.add(pixelA);
                listOfB.add(pixelB);

            }

        }

        int medianL = getListMedian(listOfL);
        int medianA = getListMedian(listOfA);
        int medianB = getListMedian(listOfB);

        System.out.println("Median values: L: " + medianL + " A: " + medianA + " B: " + medianB);

        char medianLabColor = classifyColorDeltaELab(medianL, medianA, medianB);

        System.out.println("Median color is: " + medianLabColor);
        System.out.println();

        float avgL = l/labPixelCounter;
        float avgA = a/labPixelCounter;
        float avgB = b/labPixelCounter;

        System.out.println("L: "+avgL+" A: "+avgA+" B: "+avgB);

        char labDominantColor = findDominantColor(labRed, labOrange, labWhite, labYellow, labGreen, labBlue);
        System.out.println("red = "+labRed + " orange = "+labOrange + " white = "+labWhite + " yellow = "+labYellow + " green = "+labGreen + "blue = "+labBlue);
        System.out.println(labDominantColor);
        //return labDominantColor;

        Mat labSavingSquare = new Mat();
        cvtColor(square, labSavingSquare, COLOR_BGR2Lab);
        Mat savingSquare = new Mat();

        cvtColor(labSavingSquare, savingSquare, COLOR_Lab2BGR);

        imwrite("/home/amahl/cubesolver/cubesolver/square" + colorSquare + ".jpg", savingSquare);
        colorSquare ++;

        return medianLabColor;
//
//        char avgColor = classifyColorHSV(avgHue, avgSaturation, avgValue);
//        return avgColor;

        // Classify based on color ranges
//        for (int i = 0; i < colorRanges.size(); i++) {
//            Scalar lower = colorRanges.get(i)[0];
//            Scalar upper = colorRanges.get(i)[1];
//
//            // Create a mask for the color range
//            Mat mask = new Mat();
//            // Create 1x1 Mats for lower and upper bounds
//            Mat lowerMat = new Mat(1, 1, blurredSquare.type(), lower);
//            Mat upperMat = new Mat(1, 1, blurredSquare.type(), upper);
//            inRange(blurredSquare, lowerMat, upperMat, mask);
//
//            // Count the number of non-zero pixels in the mask
//            int nonZeroCount = (int) countNonZero(mask);
//            double totalPixels = square.total();
//            System.out.print(" " + (nonZeroCount/totalPixels));
//
//            // If a significant portion of the square matches the color range, classify it as that color
//            if (nonZeroCount > totalPixels * 0.001) {  // Threshold to match color (adjust as needed)
//                switch (i) {
//                    case 0:
//                        System.out.println();
//                        return 'R';
//                    case 1:
//                        System.out.println();
//                        return 'O';
//                    case 2:
//                        System.out.println();
//                        return 'Y';
//                    case 3:
//                        System.out.println();
//                        return 'G';
//                    case 4:
//                        System.out.println();
//                        return 'B';
//                    case 5:
//                        System.out.println();
//                        return 'W';
//                    default:
//                        System.out.println();
//                        return 'U';
//                }
//            }
//        }
//        System.out.println();
//        return 'U';  // If no color is detected
    }
    public static Mat equalizeLighting(Mat hsvImage) {
        // Split the HSV image into its channels
        MatVector hsvChannels = new MatVector();
        split(hsvImage, hsvChannels);

        // Apply histogram equalization on the V (Value) channel
        Mat vChannel = hsvChannels.get(2);
        equalizeHist(vChannel, vChannel);

        // Merge the channels back
        merge(hsvChannels, hsvImage);

        return hsvImage;
    }

    private static char findDominantColor (int red, int orange, int white, int yellow, int green, int blue){
        if (red>orange && red>white && red>yellow && red>green && red>blue){
            return 'R';
        } else if (orange>red && orange>white && orange>yellow && orange>green && orange>blue){
            return 'O';
        } else if (white>red && white>orange && white>yellow && white>green && white>blue){
            return 'W';
        } else if (yellow>red && yellow>orange && yellow>white && yellow>green && yellow>blue){
            return 'Y';
        } else if (green>red && green>orange && green>white && green>yellow && green>blue){
            return 'G';
        } else {
            return 'B';
        }
    }

    public static char classifyColorHSV(float hue, float saturation, float value) {

        if (saturation < 70) {
//            System.out.println(" W");
            return 'W';
        } else if ((hue <= 25 && value < 185 && saturation < 220)||(hue > 120 && hue < 210)) {
//            System.out.println(" R");
            return 'R';
        } else if (hue < 25) {
//            System.out.println(" O");
            return 'O';
        } else if (hue >= 25 && hue < 40 && value <= 260) {
//            System.out.println(" Y");
            return 'Y';
        } else if (hue >= 40 && hue < 70 && saturation < 220) {
//            System.out.println(" G");
            return 'G';
        } else if (hue >= 70 && hue < 120) {
//            System.out.println(" B");
            return 'B';
        } else {
//            System.out.println(" U");
            return 'U';
        }
    }

    public static char classifyColorLab(float l, float a, float b) {

        if (l > 45 && l <= 100 && a > 0 && a < 10 && b > 0 && b < 10) {
//            System.out.println(" W");
            return 'W';
        } else if (a > 35 && a < 40 && b > 18 && b < 22) {
//            System.out.println(" R");
            return 'R';
        } else if (a > 38 && a < 45 && b > 37 && b < 45) {
//            System.out.println(" O");
            return 'O';
        } else if (a > 40 && a < 48 && b > 33 && b < 37) {
//            System.out.println(" Y");
            return 'Y';
        } else if (a > 28 && a < 35 && b > 10 && b < 17) {
//            System.out.println(" G");
            return 'G';
        } else if (a > 30 && a < 35 && b > 17 && b < 20) {
//            System.out.println(" B");
            return 'B';
        } else {
//            System.out.println(" U");
            return 'U';
        }
    }

    public static char classifyColorDeltaELab(float l, float a, float b){
        // Real world values
        Map<Character, double[]> referenceColors = new HashMap<>();
        referenceColors.put('W', new double[]{210, 130, 137});
        referenceColors.put('R', new double[]{85, 165, 160});
        referenceColors.put('O', new double[]{145, 165, 175});
        referenceColors.put('Y', new double[]{165, 105, 194});
        referenceColors.put('G', new double[]{160, 80, 165});
        referenceColors.put('B', new double[]{130, 123, 106});
        referenceColors.put('r', new double[]{175, 165, 150});
        referenceColors.put('w', new double[]{130, 130, 135});
        //referenceColors.put('b', new double[]{198, 124, 121});
        referenceColors.put('o', new double[]{226, 138, 142});
        referenceColors.put('s', new double[]{133, 190, 160});
        referenceColors.put('S', new double[]{210, 158, 110});
        referenceColors.put('x', new double[]{182, 122, 126});
        referenceColors.put('q', new double[]{181, 155, 164});

        // Values for a utopia
//        Map<Character, double[]> referenceColors = new HashMap<>();
//        referenceColors.put('W', new double[]{255, 128, 128});
//        referenceColors.put('R', new double[]{135, 208, 195});
//        referenceColors.put('O', new double[]{179, 151, 206});
//        referenceColors.put('Y', new double[]{247, 107, 222});
//        referenceColors.put('G', new double[]{224, 42, 211});
//        referenceColors.put('B', new double[]{82, 207, 20});

        char bestColor = 'U';
        double minDeltaE = Double.MAX_VALUE;
        for(Map.Entry<Character, double[]> entry : referenceColors.entrySet()){
            double[] ref = entry.getValue();
            double deltaE = Math.sqrt(Math.pow(l - ref[0], 2)/5 + Math.pow(a - ref[1], 2) + Math.pow(b - ref[2], 2));
            if (deltaE < minDeltaE){
                minDeltaE = deltaE;
                bestColor = entry.getKey();
            }
        }

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
            BufferedImage image = ImageIO.read(new File(outputImage));

            Graphics2D g2d = image.createGraphics();

            g2d.setColor(Color.RED);

            g2d.drawRect(squareX, squareY, squareWidth, squareHeight);

            g2d.dispose();

            ImageIO.write(image, "jpg", new File(outputImage));
            //System.out.println("squares drawn and image saved.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static int getListMedian(List<Integer> list){
        list.sort(Comparator.naturalOrder());
        int n = list.size();
        if (n<1) throw new IllegalArgumentException("List is empty");

        if(n % 2 == 1){
            return list.get((n-1)/2);
        } else{
            return (list.get(n/2-1) + list.get(n/2))/2;
        }
    }

}
