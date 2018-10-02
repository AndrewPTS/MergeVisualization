import acm.graphics.GLabel;
import acm.graphics.GRect;
import java.awt.*;
import java.awt.Color;

public class ArrayBox extends GRect {

    /*
        value - value of the ArrayBox (int value)
        x1 - the x coordinate of the top left corner
        y1 - the y coordinate of the top left corner
        size - the size (width and height) of the box
        stringValue - string representation of the value
        label - the label that displays the value of the array on the screen
        fontSize - font size of the label; scales with program size
        status - the current color of the ArrayBox
     */
    private int value;
    private int x1;
    private int y1;
    private int size;
    private String stringValue;
    private GLabel label;
    private int fontSize;
    private Color status = Color.WHITE;

    public ArrayBox(int val, int x1, int y1, int width) {

        super(x1, y1, width, width);
        setFillColor(status);
        setFilled(true);
        value = val;
        this.x1 = x1;
        this.y1 = y1;
        size = width;
        setValue(val);

    }

    public ArrayBox(int val, int x1, int y1, int width, Color status) {
        super(x1, y1, width, width);
        this.status = status;
        setFillColor(status);
        setFilled(true);
        value = val;
        this.x1 = x1;
        this.y1 = y1;
        size = width;
        setValue(val);
    }

    /*
        sets the value of the box, as well as the font size, before adding it to the grpahics program
     */
    public void setValue(int val) {
        value = val;
        fontSize = size/2;
        if (value != 0) {
            stringValue = Integer.toString(value);
        } else {
            stringValue = "";
        }
        label = new GLabel(stringValue, x1+(size/2), y1+(size/2));
        label.setFont(new Font("Serif", Font.BOLD, fontSize));
        label.sendToFront();
        MergeVisualization.getGraphicsProgram().add(label);
    }

    /*
        returns the GLabel object
     */
    public GLabel getLabel() {
        return label;
    }

    /*
        sets the background color of the box
     */
    public void setStatus(Color color) {

        status = color;
        this.setFillColor(color);
        this.sendToBack();

    }

    /*
        returns the color of the box
     */
    public Color getStatus() {
        return status;
    }

}
