package test;

import component.Frame;
import component.Panel;
import constraints.PercentLayout;

import java.awt.*;

public class Example {

    public static void main(String[] args){

        Frame frame = new Frame();
        frame.setLayout(new PercentLayout(50,50));

        Panel panel = new Panel();
        panel.setGridx(1);
        panel.setGridy(1);
        panel.setGridWidth(3);
        panel.setGridHeight(3);
        panel.setBackground(Color.RED);

        frame.add(panel);

    }

}
