import acm.graphics.GLabel;
import acm.program.GraphicsProgram;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class MergeVisualization extends GraphicsProgram {

    /*
        instance - single instance of acm graphics program (singleton)
        array1 - bottom left test
        array2 - bottom right test
        ogArray - ArrayGraphics to store the original array (used at the end of the program)
        sortedArray - the top array
        sortedLabel - label for the sorted array (used at the end of the program)
        origrinalLabel - label for the original array (used at the end of the program)
        width - stores current width of program (used in scaling)
        height - stores current height of the program (used in scaling)
     */
    private static MergeVisualization instance;
    private ArrayGraphics array1;
    private ArrayGraphics array2;
    private ArrayGraphics ogArray;
    private ArrayGraphics sortedArray;
    private GLabel sortedLabel;
    private GLabel originalLabel;
    private int width;
    private int height;

    /*
        method to retrn the instance of the graphic program
     */
    public static MergeVisualization getGraphicsProgram() {
        if (instance == null) {
            instance = new MergeVisualization();
        }
        return instance;
    }

    public void run() {

        width = getWidth();
        height = getHeight();
        sortedArray = new ArrayGraphics((int) ((Math.random() * 9) + 8), "center");
        ogArray = sortedArray;
        array1 = new ArrayGraphics(sortedArray.getArr().length/2, "left");
        array2 = new ArrayGraphics((sortedArray.getArr().length - sortedArray.getArr().length/2), "right");
        refresh();
        System.out.println(ogArray.toString());
        System.out.println(sort(sortedArray).toString());
        pause(1000);
        removeAll();
        array1 = null;
        array2 = null;
        ogArray = new ArrayGraphics(ogArray.getArr(), "bottom");
        ogArray.addAllBoxes();
        sortedArray.addAllBoxes();
        //sortedLabel goes in the top middle
        sortedLabel = new GLabel("Sorted Array", getWidth()/2, 0);
        originalLabel = new GLabel("Original Array", getWidth()/2, getHeight()/2);
        add(sortedLabel); add(originalLabel);
        //originalLabel goes in the very middle
        sortedLabel.setFont(new Font("Arial", Font.BOLD, (int)(getHeight()*.084)));
        originalLabel.setFont(new Font("Arial", Font.BOLD, (int)(getHeight()*.084)));
        sortedLabel.setLocation(getWidth()/2-sortedLabel.getWidth()/2, sortedLabel.getHeight());
        originalLabel.setLocation(getWidth()/2-originalLabel.getWidth()/2, getHeight()/2 + originalLabel.getHeight());

    }

    public static void main(String[] args) {

        intro();
        getGraphicsProgram().start();

    }

    /*
        displays info about the program
     */
    public static void intro() {

        JLabel introText = new JLabel("<html>MergeVisualizer takes a fresh approach to visualizing the merge sort algorithm<br>Rather than having " +
                "bars being moved around a screen, MergeVisulizer uses a more tangible approach with " +
                "numbers and graphical arrays.<br>The program has two main steps: split and merge, which can be easily identified " +
                "using the color legend below: <br><br><b>Split</b><br>Yellow - the left half of the split array<br>Orange - the right half of the split " +
                "array<br><br><b>Merge</b><br>Blue - the iterator that goes through each shorter list and determines which values are being compared<br>" +
                "Green - shows that the value at the iterator is the lesser of the two numbers being compared" +
                "<br>Red - shows that the value at the iterator is the lesser of the two numbers being compared" +
                "<br>Cyan - the iterator through the larger array, determining where the chosen value will be placed.</html>");
        introText.setFont(new Font("Serif", Font.PLAIN, 20));
        introText.setSize(new Dimension(720, 480));
        JOptionPane.showMessageDialog(null, introText, "MergeVisualizer Intro", JOptionPane.INFORMATION_MESSAGE);
    }

    /*
        method for S C A L I N G
     */
    public void refresh() {
        Timer timer = new Timer(1, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (width != getWidth() || height != getHeight()) {

                    removeAll();
                    try {
                        array1.refreshGraphics();
                        array2.refreshGraphics();
                    } catch (NullPointerException err) {}
                    sortedArray.refreshGraphics();
                    if (array1 == null) {
                        sortedLabel.setLocation(getWidth()/2-sortedLabel.getWidth()/2, sortedLabel.getHeight());
                        originalLabel.setLocation(getWidth()/2-originalLabel.getWidth()/2, getHeight()/2 + originalLabel.getHeight());
                        sortedLabel.setFont(new Font("Arial", Font.BOLD, (int)(getHeight()*.084)));
                        originalLabel.setFont(new Font("Arial", Font.BOLD, (int)(getHeight()*.084)));
                        ogArray.refreshGraphics();
                        add(sortedLabel); add(originalLabel);
                    }
                    width = getWidth(); height = getHeight();

                }
            }
        });
        timer.start();
    }

    /*
        method to sort the array
        @Params
        sortedArr - array to be sorted
     */
    public ArrayGraphics sort(ArrayGraphics sortedArr) {

        ArrayGraphics[] splitArrs = split(sortedArr);

        if (splitArrs[0].getArr().length > 1 && splitArrs[1].getArr().length > 1) {
            sortedArr = merge(sort(splitArrs[0]), sort(splitArrs[1]));
        } else if (splitArrs[0].getArr().length == 1 && splitArrs[1].getArr().length > 1) {
            sortedArr = merge(splitArrs[0], sort(splitArrs[1]));
        } else if (splitArrs[0].getArr().length > 1 && splitArrs[1].getArr().length == 1){
            sortedArr = merge(sort(splitArrs[0]), splitArrs[1]);
        } else {
            sortedArr = merge(splitArrs[0], splitArrs[1]);
        }
        return sortedArr;

    }

    /*
        splits the array down to one element
        @Params
        arr - array to be split
     */
    public ArrayGraphics[] split(ArrayGraphics arr) {

        removeAll();
        arr = new ArrayGraphics(arr.getArr(), "center");
        arr.addAllBoxes();
        ArrayGraphics[] splitArrs = new ArrayGraphics[2];
        splitArrs[0] = new ArrayGraphics(new int[arr.getArr().length/2], "left");
        splitArrs[1] = new ArrayGraphics(new int[arr.getArr().length - arr.getArr().length/2], "right");
        sortedArray = arr;
        array1 = splitArrs[0];
        array2 = splitArrs[1];

        for(int i = 0; i < arr.getArr().length/2; i++) {
            arr.colorBox(i, Color.YELLOW);
        }
        pause(400);
        splitArrs[0] = new ArrayGraphics(Arrays.copyOfRange(arr.getArr(), 0, arr.getArr().length/2), "left");
        array1 = splitArrs[0];
        splitArrs[0].addAllBoxes();
        for (int i =0; i < splitArrs[0].getArr().length; i++) {
            splitArrs[0].colorBox(i, Color.YELLOW);
        }

        pause(400);

        for(int i = arr.getArr().length/2; i < arr.getArr().length; i++) {
            arr.colorBox(i, Color.ORANGE);
        }
        pause(400);
        splitArrs[1] = new ArrayGraphics(Arrays.copyOfRange(arr.getArr(), arr.getArr().length/2, arr.getArr().length), "right");
        array2 = splitArrs[1];
        splitArrs[1].addAllBoxes();
        for (int i = 0; i < splitArrs[1].getArr().length; i++) {
            splitArrs[1].colorBox(i, Color.ORANGE);
        }

        pause(500);
        return splitArrs;

    }

    /*
        method to merge the two arrays
        @Params
        arr1 - left array (sorted)
        arr2 - right array (sorted)
     */
    public ArrayGraphics merge(ArrayGraphics arr1, ArrayGraphics arr2) {

        int lasti = 0;
        int lastj = 0;
        removeAll();
        arr1 = new ArrayGraphics(arr1.getArr(), "left");
        arr2 = new ArrayGraphics(arr2.getArr(), "right");
        array1 = arr1;
        array2 = arr2;
        arr1.refreshGraphics();
        arr2.refreshGraphics();
        arr1.addAllBoxes();
        arr2.addAllBoxes();
        ArrayGraphics sortedArr = new ArrayGraphics(new int[arr1.getArr().length + arr2.getArr().length], "center");
        sortedArray = sortedArr;
        System.out.println("arr1: " + arr1.toString());
        System.out.println("arr2: " + arr2.toString());
        sortedArr.addAllBoxes();
        pause(500);
        for (int i = 0, j = 0, x = 0; i < arr1.getArr().length && j < arr2.getArr().length && x < sortedArr.getArr().length; x++) {
            arr1.colorBox(i, Color.BLUE);
            arr2.colorBox(j, Color.BLUE);
            sortedArr.colorBox(x, Color.CYAN);
            pause(500);
            if (arr1.getArr()[i] <= arr2.getArr()[j]) {
                arr1.colorBox(i, Color.GREEN);
                arr2.colorBox(j, Color.RED);
                sortedArr.setValue(x, arr1.getArr()[i]);pause(200);
                arr1.colorBox(i, Color.WHITE);
                arr2.colorBox(j, Color.WHITE);
                sortedArr.colorBox(x, Color.WHITE);
                i++;
                lasti = i;
            } else {
                arr1.colorBox(i, Color.RED);
                arr2.colorBox(j, Color.GREEN);
                sortedArr.setValue(x, arr2.getArr()[j]);
                pause(200);
                arr1.colorBox(i, Color.WHITE);
                arr2.colorBox(j, Color.WHITE);
                sortedArr.colorBox(x, Color.WHITE);
                j++;
                lastj = j;
            }
            pause(500);
        }
        if (lastj != arr2.getArr().length) {
            for (int j = lastj, x = (arr1.getArr().length+lastj); j < arr2.getArr().length; j++) {
                arr2.colorBox(j, Color.BLUE);
                sortedArr.colorBox(x, Color.CYAN);
                pause(500);
                arr2.colorBox(j, Color.GREEN);
                sortedArr.setValue(x, arr2.getArr()[j]);
                pause(200);
                arr2.colorBox(j, Color.WHITE);
                sortedArr.colorBox(x, Color.WHITE);
                x++;
                pause(500);
            }
        } else if (lasti != arr1.getArr().length) {
            for (int i = lasti, x = (arr2.getArr().length+lasti); i < arr1.getArr().length; i++) {
                arr1.colorBox(i, Color.BLUE);
                sortedArr.colorBox(x, Color.CYAN);
                pause(500);
                arr1.colorBox(i, Color.GREEN);
                sortedArr.setValue(x, arr1.getArr()[i]);
                pause(200);
                arr1.colorBox(i, Color.WHITE);
                sortedArr.colorBox(x, Color.WHITE);
                x++;
                pause(500);
            }
        }
        System.out.println("sorted: " + sortedArr);
        return sortedArr;
    }
}