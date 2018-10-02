import java.awt.Color;
import java.util.ArrayList;

public class ArrayGraphics {
    /*
        BUFFER - horizontal buffer
        YBUFFER - vertical buffer
        width - width of the array graphic as a whole
        indexWidth - width of each array box
        arr - array of values for the graphics
        x1 - starting x value of the graphic
        y1 - starting y value of the graphic
        boxes - array of ArrayBox objects (the actual graphics)
        location - string to determine the position of the array (and therefore what type of array it is
     */
    private int BUFFER;
    private int YBUFFER;
    private int width;
    private int indexWidth;
    private int[] arr;
    private int x1;
    private int y1;
    public ArrayBox[] boxes;
    private String location;

    public ArrayGraphics(int size, String location) {
        YBUFFER = (int)((double)MergeVisualization.getGraphicsProgram().getHeight() * 0.025);
        this.location = location;
        this.boxes = new ArrayBox[size];
        this.arr = new int[size];
        if(!location.equals("center") && !location.equals("bottom")) {
            this.indexWidth = MergeVisualization.getGraphicsProgram().getWidth() / 2 / size;
        } else {
            this.indexWidth = MergeVisualization.getGraphicsProgram().getWidth() / size;
        }

        this.indexWidth = indexWidth-(int)(MergeVisualization.getGraphicsProgram().getWidth() * .005);
        if (indexWidth > ((MergeVisualization.getGraphicsProgram().getHeight()/2) - (YBUFFER*2))) {
            indexWidth = MergeVisualization.getGraphicsProgram().getHeight()/2 - (YBUFFER*2);
        }
        this.width = this.indexWidth * size;
        if(!location.equals("center") || !location.equals("bottom")) {
            this.BUFFER = (MergeVisualization.getGraphicsProgram().getWidth() / 2 - this.width) / 2;
        } else {
            this.BUFFER = (MergeVisualization.getGraphicsProgram().getWidth() - this.width) / 2;
        }

        if(location.equals("left")) {
            this.y1 = MergeVisualization.getGraphicsProgram().getHeight()/2 + MergeVisualization.getGraphicsProgram().getHeight()/4 - indexWidth/2;
            this.x1 = 0;
        } else if(location.equals("right")) {
            this.y1 = MergeVisualization.getGraphicsProgram().getHeight()/2 + MergeVisualization.getGraphicsProgram().getHeight()/4 - indexWidth/2;
            this.x1 = MergeVisualization.getGraphicsProgram().getWidth() / 2;
        } else if (location.equals("center")){
            this.y1 = MergeVisualization.getGraphicsProgram().getHeight()/2 - MergeVisualization.getGraphicsProgram().getHeight()/4 - indexWidth/2;
            this.x1 = 0;
        } else {
            this.y1 = MergeVisualization.getGraphicsProgram().getHeight()/2 + MergeVisualization.getGraphicsProgram().getHeight()/4 - indexWidth/2;
            this.x1 = 0;
        }

        for(int i = 0; i < size; i++) {
            if(location.equals("center")) {
                this.arr[i] = (int)((Math.random() * 99) + 1);
            } else {
                this.arr[i] = 0;
            }
        }

        for(int i = 0; i < size; ++i) {
            this.boxes[i] = new ArrayBox(this.arr[i], this.BUFFER + this.x1 + this.indexWidth * i, this.y1, this.indexWidth);
        }

    }

    public ArrayGraphics(int[] arr, String location) {
        YBUFFER = (int)((double)MergeVisualization.getGraphicsProgram().getHeight() * 0.025);
        int size = arr.length;
        this.location = location;
        this.boxes = new ArrayBox[size];
        this.arr = arr;
        if(!location.equals("center") && !location.equals("bottom")) {
            this.indexWidth = MergeVisualization.getGraphicsProgram().getWidth() / 2 / size;
        } else {
            this.indexWidth = MergeVisualization.getGraphicsProgram().getWidth() / size;
        }
        if (indexWidth > ((MergeVisualization.getGraphicsProgram().getHeight()/2) - (YBUFFER*2))) {
            indexWidth = MergeVisualization.getGraphicsProgram().getHeight()/2 - (YBUFFER*2);
        }

        this.indexWidth = indexWidth-(int)(MergeVisualization.getGraphicsProgram().getWidth() * .005);
        this.width = this.indexWidth * size;
        if(!location.equals("center") && !location.equals("bottom")) {
            this.BUFFER = (MergeVisualization.getGraphicsProgram().getWidth() / 2 - this.width) / 2;
        } else {
            this.BUFFER = (MergeVisualization.getGraphicsProgram().getWidth() - this.width) / 2;
        }

        if(location.equals("left")) {
            y1 = MergeVisualization.getGraphicsProgram().getHeight()/2 + MergeVisualization.getGraphicsProgram().getHeight()/4 - indexWidth/2;
            x1 = 0;
        } else if(location.equals("right")) {
            y1 = MergeVisualization.getGraphicsProgram().getHeight()/2 + MergeVisualization.getGraphicsProgram().getHeight()/4 - indexWidth/2;
            x1 = MergeVisualization.getGraphicsProgram().getWidth() / 2;
        } else if (location.equals("center")){
            y1 = MergeVisualization.getGraphicsProgram().getHeight()/2 - MergeVisualization.getGraphicsProgram().getHeight()/4 - indexWidth/2;
            x1 = 0;
        } else {
            y1 = MergeVisualization.getGraphicsProgram().getHeight()/2 + MergeVisualization.getGraphicsProgram().getHeight()/4 - indexWidth/2;
            x1 = 0;
        }

        for(int i = 0; i < size; ++i) {
            boxes[i] = new ArrayBox(arr[i], BUFFER + x1 + indexWidth * i, y1, indexWidth);
        }

    }

    /*
        adds all ArrayBox objects to the MergeVisulaization_CastRosen graphics program
     */
    public void addAllBoxes() {
        for (int i = 0; i < arr.length; ++i) {
            MergeVisualization.getGraphicsProgram().add(boxes[i]);
            MergeVisualization.getGraphicsProgram().add(boxes[i].getLabel());
        }
    }

    /*
        refreshes size and position of each ArrayBox; used for scaling
     */
    public void refreshGraphics() {
        YBUFFER = (int)((double)MergeVisualization.getGraphicsProgram().getHeight() * 0.025);
        if(!location.equals("center") && !location.equals("bottom")) {
            indexWidth = MergeVisualization.getGraphicsProgram().getWidth() / 2 / arr.length;
        } else {
            indexWidth = MergeVisualization.getGraphicsProgram().getWidth() / arr.length;
        }
        indexWidth = indexWidth-(int)(MergeVisualization.getGraphicsProgram().getWidth() * .005);
        if (indexWidth > ((MergeVisualization.getGraphicsProgram().getHeight()/2) - (YBUFFER*2))) {
            indexWidth = MergeVisualization.getGraphicsProgram().getHeight()/2 - (YBUFFER*2);
        }
        width = indexWidth * arr.length;
        if(!location.equals("center") && !location.equals("bottom")) {
            BUFFER = (MergeVisualization.getGraphicsProgram().getWidth() / 2 - width) / 2;
        } else {
            BUFFER = (MergeVisualization.getGraphicsProgram().getWidth() - width) / 2;
        }

        if(location.equals("left")) {
            y1 = MergeVisualization.getGraphicsProgram().getHeight()/2 + MergeVisualization.getGraphicsProgram().getHeight()/4 - indexWidth/2;
            x1 = 0;
        } else if(location.equals("right")) {
            y1 = MergeVisualization.getGraphicsProgram().getHeight()/2 + MergeVisualization.getGraphicsProgram().getHeight()/4 - indexWidth/2;
            x1 = MergeVisualization.getGraphicsProgram().getWidth() / 2;
        } else if (location.equals("center")){
            y1 = MergeVisualization.getGraphicsProgram().getHeight()/2 - MergeVisualization.getGraphicsProgram().getHeight()/4 - indexWidth/2;
            x1 = 0;
        } else {
            y1 = MergeVisualization.getGraphicsProgram().getHeight()/2 + MergeVisualization.getGraphicsProgram().getHeight()/4 - indexWidth/2;
            x1 = 0;
        }

        for(int i = 0; i < arr.length; ++i) {
            boxes[i] = new ArrayBox(arr[i], BUFFER + x1 + indexWidth * i, y1, indexWidth, boxes[i].getStatus());
            MergeVisualization.getGraphicsProgram().add(boxes[i]);
            MergeVisualization.getGraphicsProgram().add(boxes[i].getLabel());
        }

    }

    /*
        returns the array of values
     */
    public int[] getArr() {
        return arr;
    }

    /*
        sets the value of an array at a given index
        @Params
        index - index to be set
        val - value the index is going to be set to
     */
    public void setValue(int index, int val) {
        arr[index] = val;
        boxes[index].setValue(val);
    }

    /*
        changes the color of the ArrayBox;
        @Params
        index - index to change color of
        color - the color the ArrayBox will be set to
     */
    public void colorBox(int index, Color color) {
        boxes[index].setStatus(color);
    }

    /*
        toString method that returns a string representation of the values of the array
     */
    public String toString() {
        String str ="";
        for (int i = 0; i < arr.length; i++) {
            str = str + "" + arr[i] + ", ";
        }
        str = str.substring(0, str.length()-2);
        return str;

    }
}
