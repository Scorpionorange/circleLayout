import javax.swing.*;

/**
 * Created by ScorpionOrange on 2016/09/20.
 * A frame that shows buttons arranged along a circle.
 */
public class CircleLayoutFrame extends JFrame{
    public CircleLayoutFrame(){
        setLayout(new CircleLayout());
        add(new JButton("Red"));
        add(new JButton("Orange"));
        add(new JButton("Yellow"));
        add(new JButton("Green"));
        add(new JButton("Blue"));
        add(new JButton("Purple"));
        add(new JButton("Fuchsia"));
        add(new JButton("Indigo"));
        pack();
    }
}
