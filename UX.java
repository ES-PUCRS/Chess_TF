//Java default API controls
import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;


public class UX extends Application{
public static final int COLUMNS = 8;//Game.COLUMNS;
public static final int ROWS = 8;//Game.ROWS;
public static final int lPx = 300;
public static final int lPy = 400;
public static final int gPx = 600;
public static final int gPy = 650;
private static Picture picture;
private static Images images;
private Stage primaryStage;
private Scene fitScene;

    public static void main(String args[]){
        launch(args);
    }

    @Override
    public void start(Stage primaryStage){
        this.primaryStage = primaryStage;

        ScreenGx screen = new ScreenGx(primaryStage);
        
        images = new Images();
        picture = new Picture();
        picture.hold("MenuBackground");

        Group root = new Group();
        fitScene = new Scene(root, lPx, lPy);
        primaryStage.setTitle("Chess");
        primaryStage.setResizable(false);
      
        controlStage(fitScene);
        controlStage(screen.GameGx());
        primaryStage.show();
    }

    public void controlStage(Scene scene){
        controlStage(primaryStage, scene);
    }
    public static void controlStage(Stage primaryStage, Scene scene){
        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();

            //Revifying return
            //System.out.println(Screen.getPrimary().getVisualBounds());

        int px, py;
        px = (int) scene.getWidth();
        py = (int) scene.getHeight();

        //set Stage boundaries to the lower right corner of the visible bounds of the main screen
        primaryStage.setX((primaryScreenBounds.getWidth() - px)/2);
        primaryStage.setY((primaryScreenBounds.getHeight() - py)/2);
        primaryStage.setWidth(px);
        primaryStage.setHeight(py);

        primaryStage.setScene(scene);
    }
}