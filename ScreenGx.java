
//Java default API controls
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.GridPane;
import javafx.scene.text.FontWeight;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.geometry.Insets;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.event.EventHandler;
import javafx.scene.Scene;


public class ScreenGx{
    public static final int gPxBorded = UX.gPx - 10;
    public static final int gPyBorded = UX.gPy - 58;
    private AlertList alert;
    private GridPane gridMaster;
    private GridPane chessmen;
    private GridPane Background;
    private MenuBar menuBar;
    private GridPane board;
    private Stage s;

    public ScreenGx(Stage s) {
        this.s = s;
    }

    public Stage getStage() {
        return s;
    }

    public static Scene Save() {
        Picture picture = new Picture("MenuBackground");
        GridPane gridMaster = new GridPane();
        GridPane grid = new GridPane();
        grid.add(picture, 0, 0);
        picture.setOnAction(e -> System.out.println("Try"));
        Scene save = new Scene(gridMaster, UX.lPx, UX.lPy);
        return save;
    }

    public Scene MenuGx() {
        Picture picture = new Picture("MenuBackground");

        Button singlePlay = new Button("Single player");
        Button multiPlay = new Button("Multi  player");

        GridPane gridMaster = new GridPane();
        GridPane grid = new GridPane();

        gridMaster.add(picture.getView(), 0, 0);
        gridMaster.add(grid, 0, 0);

        Text menuTxt = new Text("Menu");
        menuTxt.setFont(Font.font("Times new roman", FontWeight.MEDIUM, 30));

        HBox hb = new HBox();
        VBox vb = new VBox();
        vb.setSpacing(10);
        hb.setAlignment(Pos.CENTER);

        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(20);
        grid.setPadding(new Insets(25));

        hb.getChildren().add(menuTxt);
        vb.getChildren().add(singlePlay);
        vb.getChildren().add(multiPlay);
        grid.add(hb, 0, 0);
        grid.add(vb, 0, 1);

        singlePlay.setOnAction(e -> UX.controlStage(s, Select()));
        // multiPlay.setOnAction(e -> UX.controlStage(multiplayerGame));

        Scene menu = new Scene(gridMaster, UX.lPx, UX.lPy);
        return menu;
    }

    public Scene Select() {
        Picture picture = new Picture("MenuBackground");

        Button newGame = new Button("New  game");
        Button loadGame = new Button("Load game");

        Text space = new Text("  ");
        space.setFont(Font.font("Times new roman", FontWeight.MEDIUM, 20));

        GridPane gridMaster = new GridPane();
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(25));

        gridMaster.add(picture.getView(), 0, 0);
        gridMaster.add(grid, 0, 0);

        HBox hb = new HBox();
        hb.setAlignment(Pos.CENTER);
        hb.getChildren().add(space);

        grid.setHgap(10);
        grid.setVgap(20);

        VBox vb = new VBox();
        vb.setSpacing(10);
        grid.setAlignment(Pos.CENTER);

        vb.getChildren().add(newGame);
        vb.getChildren().add(loadGame);

        grid.add(hb, 0, 0);
        grid.add(vb, 0, 1);

        newGame.setOnAction(e -> UX.controlStage(s, GameGx()));
        // loadGame.setOnAction(e -> trataAcao(e));

        Scene select = new Scene(gridMaster, UX.lPx, UX.lPy);
        return select;
    }

    /**
     * Alert set -> *Case try to leave game without saving \-> Throw Alert with
     * possible go options
     * 
     * Create all grids needed; * MasterPane = Contains every grid; * Background =
     * Hold background image; * Board = Hold every board button; * Chessmen =
     * Contains every chessmen image;
     * 
     * Set grid settings; Put background image into Background grid; Create menu bar
     * and set dependencies; Get setOnAction event buttons Put every grid into
     * Master and organize layers;
     * 
     * @return Scene game
     */
    public Scene GameGx() {
        alert = new AlertList(s);
        Picture picture = new Picture();

        gridMaster = new GridPane();
        Background = new GridPane();
        board = new Board();
        chessmen = ((Board) board).onBoard();

        gridMaster.setAlignment(Pos.CENTER);

        picture.hold("Board");
        Background.add(picture.getView(gPxBorded + 2, gPyBorded + 2, 0.1), 0, 1);

        menuBar = new MenuBar();
        // Menu File
        Menu menuGame = new Menu("Game");
        MenuItem save = new MenuItem("Save");
        MenuItem load = new MenuItem("Load");
        MenuItem menu = new MenuItem("Menu");
        menuGame.getItems().addAll(save, load, menu);
        menuBar.getMenus().addAll(menuGame);

        save.setOnAction(e -> UX.controlStage(s, Save()));
        // load.setOnAction(e -> controlStage(screenSave()));
        menu.setOnAction(e -> alert.LeftGame());

        gridMaster.add(Background, 0, 1);
        gridMaster.add(chessmen, 0, 1);
        gridMaster.add(menuBar, 0, 0);
        gridMaster.add(board, 0, 1);
                 
        board.getChildren().forEach(item -> {
            item.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    if (event.getClickCount() == 1) {
                        GridPane refreshGrid = new GridPane();
                        chessmen = ((Board) board).onBoard();
                        refreshGrid.add(Background, 0, 1);
                        refreshGrid.add(chessmen, 0, 1);
                        refreshGrid.add(menuBar, 0, 0);
                        refreshGrid.add(board, 0, 1);
                        gridMaster.getChildren().setAll(refreshGrid);
                    }
                }
            });
        });

        Scene game = new Scene(gridMaster, UX.gPx, UX.gPy);
        return game;
    } 

}
//((Board) board).boardGeThread().setDefaultUncaughtExceptionHandler(new ExceptionHandler());
    