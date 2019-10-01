import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

import java.util.ArrayList;

public class Drop
{

    private double x;
    private double y;
    private double z = Utils.getRandom(0, 20);
    private static Color color = Color.rgb(138, 43, 226);
    public static ArrayList<Drop> drops = new ArrayList<>();
    private Line node;
    private double len = Utils.map(z, 0, 20, 10, 30);
    private double ySpeed = Utils.map(z, 0, 20, 4, 20);

    public Drop()
    {
        //Set initial Locations
        x = Utils.getRandom(-200, Main.width + 200);
        y = Utils.getRandom(-1000, -50);
        //Set Node Object for drawing
        node = new Line(x, y, x + Main.noise, y + len);
        node.setStrokeWidth(Utils.map(z, 0, 20, 3, 8));
        node.setStroke(color);
        drops.add(this);//add Universal Drop List
    }

    public Node getNode()
    {
        return this.node;
    }

    public void fall()
    {
        //location = time * speed;
        y += ySpeed;
        //speed = time * acceration (gravity)
        double grav = Utils.map(z, 0, 20, 0.09, 0.3);
        ySpeed += grav;
        //Set Locations of startY and EndY (EndY = StartY + Length)
        node.setStartY(y);
        node.setEndY(y + len);
        node.setStartX(x);
        x += Main.noise;
        node.setEndX(x);
        if (node.getStartY() > Main.height)
        {
            //if Drop falls outrange of Screens Height
            //Reset X, Y and Z values
            x = Utils.getRandom(-200, Main.width + 200);
            y = Utils.getRandom(-550, -150);
            z = Utils.getRandom(0, 20);
            node.setStartX(x);
            node.setEndX(x + Main.noise);
            //Rearrange ySpeed value according Z value
            ySpeed = Utils.map(z, 0, 20, 4, 10);
            this.node.setStrokeWidth(Utils.map(z, 0, 20, 2, 6));
        }

    }
}
