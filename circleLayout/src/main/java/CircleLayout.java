import java.awt.*;

/**
 * Created by ScorpionOrange on 2016/09/19.
 * A layout manager that lays out components along a circle.
 */
public class CircleLayout implements LayoutManager{
    private int minWidth = 0;
    private int minHeight = 0;
    private int preferredWidth = 0;
    private int preferredHeight = 0;
    private boolean sizesSet = false;
    private int maxComponentWidth = 0;
    private int maxComponentHeight = 0;

    public void addLayoutComponent(String name, Component component){ }

    public void removeLayoutComponent(Component component){ }

    public void setSizes(Container parent){
        if(sizesSet) return;
        int n = parent.getComponentCount();

        preferredWidth = 0;
        preferredHeight = 0;
        minWidth = 0;
        minHeight = 0;
        maxComponentWidth = 0;
        maxComponentHeight = 0;

        //compute the maximum component widths and heights
        //and set the preferred size to the sum of the component sizes.
        for(int i = 0; i < n; i++){
            Component component = parent.getComponent(i);
            if(component.isVisible()){
                Dimension dimension = component.getPreferredSize();
                maxComponentWidth = Math.max(maxComponentWidth, dimension.width);
                maxComponentHeight = Math.max(maxComponentHeight, dimension.height);
                preferredWidth += dimension.width;
                preferredHeight += dimension.height;
            }
        }
        minWidth = preferredWidth / 2;
        minHeight = preferredHeight / 2;
        sizesSet = true;
    }

    public Dimension preferredLayoutSize(Container parent){
        setSizes(parent);
        Insets insets = parent.getInsets();
        int width = preferredWidth + insets.left + insets.right;
        int height = preferredHeight + insets.top + insets.bottom;
        return new Dimension(width, height);
    }

    public Dimension minimumLayoutSize(Container parent){
        setSizes(parent);
        Insets insets = parent.getInsets();
        int width = minWidth + insets.left + insets.right;
        int heigth = minHeight + insets.top + insets.bottom;
        return new Dimension(width, heigth);
    }

    public void layoutContainer(Container parent){
        setSizes(parent);
        //compute center of the circle
        Insets insets = parent.getInsets();
        int containerWidth = parent.getSize().width - insets.left - insets.right;
        int containerHeight = parent.getSize().height - insets.top - insets.bottom;

        int xCenter = insets.left + containerWidth / 2;
        int yCenter = insets.top + containerHeight / 2;

        //compute radius of the circle
        int xRadius = (containerWidth - maxComponentWidth) / 2;
        int yRadius = (containerHeight - maxComponentHeight) / 2;
        int radius = Math.min(xRadius, yRadius);

        //lay out components along the circle
        int n = parent.getComponentCount();
        for(int i = 0; i < n; i++){
            Component component = parent.getComponent(i);
            if(component.isVisible()){
                double angle = 2 * Math.PI * i / n;

                //center point of component
                int x = xCenter + (int)(Math.cos(angle) * radius);
                int y = yCenter + (int)(Math.sin(angle) * radius);

                //move component so that its center is (x, y)
                //and its size is its preferred size
                Dimension dimension = component.getPreferredSize();
                component.setBounds(x - dimension.width / 2, y-dimension.height / 2, dimension.width, dimension.height);
            }
        }
    }
}
