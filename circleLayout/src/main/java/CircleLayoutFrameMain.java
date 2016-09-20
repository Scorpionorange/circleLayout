import javax.swing.*;
import java.awt.*;

/**
 * Created by ScorpionOrange on 2016/09/20.
 */
public class CircleLayoutFrameMain {
    public static void main(String[] args){
        EventQueue.invokeLater(() -> {
            JFrame frame = new CircleLayoutFrame();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);

            frame.setTitle("Circle Layout Frame Main");
            frame.setSize(600, 600);
        });
    }
}
