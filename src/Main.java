import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Main extends Application
{

    public static ObservableList<Node> child;
    //
    private static final String title = "JellyBeanci";
    public static final int width = 1200;
    public static final int height = 800;
    private static Color backcolor = Color.rgb(230, 230, 250);
    public static double noise = 0;
    private static double xoff = 0;
    private static Timeline update;

    @Override
    public void start(Stage stage) throws Exception
    {
        Pane root = new Pane();
        child = root.getChildren();
        for (int i = 0; i < 1024; i++)
        {
            //Create 1024 Drop Objects
            Drop a = new Drop();
        }

        for (Drop drop : Drop.drops)
        {
            //Add Drop Nodes to Child List for Drawing Screen
            child.add(drop.getNode());
        }
        //
        root.setOnKeyPressed(e -> {
            switch (e.getCode())
            {
                case F1:
                {
                    //PLAY
                    update.play();
                    break;
                }
                case F2:
                {
                    //PAUSE
                    update.pause();
                    break;
                }
                case F3:
                {
                    //Show Child Count
                    System.out.println("Child Count: " + child.size());
                    break;
                }
            }
        });
        update = new Timeline(new KeyFrame(Duration.millis(16), e -> {
            //60 fps
            //System.out.println("loop test");
            for (Drop drop : Drop.drops)
            {
                //Every Step fall() every Drop according their speeds and locations.
                drop.fall();
            }
            noise = PerlinNoise.noise(xoff,0,-5,5,true);
            xoff+=0.001;
        }));
        update.setCycleCount(Timeline.INDEFINITE);
        update.setRate(1);
        update.setAutoReverse(false);
        //update.play(); //uncomment for play when start
        //
        stage.setTitle(title);
        stage.setResizable(false);
        stage.setScene(new Scene(root, width - 10, height - 10, backcolor));
        stage.show();
        root.requestFocus();
    }

    public static void main(String[] args)
    {
        launch(args);
    }
}