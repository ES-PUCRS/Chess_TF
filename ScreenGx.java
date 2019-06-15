
//Java default API controls
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Menu;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import chessmen.*;
public class ScreenGx {
public static final int gPxBorded = UX.gPx - 10;
public static final int gPyBorded = UX.gPy - 58;
private Stage s;

    public ScreenGx(Stage s){
        this.s = s;
    }

    public static Scene Save() {
        Picture picture = new Picture("MenuBackground");
        GridPane gridMaster = new GridPane();
        GridPane grid = new GridPane();
        grid.add(picture,0,0);
        picture.setOnAction(e -> System.out.println("Try"));
        Scene save = new Scene(gridMaster, UX.lPx, UX.lPy);
        return save;
    }
 
    
    public Scene MenuGx(){
        Picture picture = new Picture("MenuBackground");

        Button singlePlay = new Button("Single player");
        Button multiPlay = new Button("Multi  player");
        
        GridPane gridMaster = new GridPane();
        GridPane grid = new GridPane();

        gridMaster.add(picture.getView(),0,0);
        gridMaster.add(grid,0,0);

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
        grid.add(hb,0,0);
        grid.add(vb,0,1);

        singlePlay.setOnAction(e -> UX.controlStage(s, Select()));
        //multiPlay.setOnAction(e -> UX.controlStage(multiplayerGame));

        Scene menu = new Scene(gridMaster, UX.lPx, UX.lPy);
        return menu;
    }

    public Scene Select(){
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

        grid.add(hb,0,0);
        grid.add(vb,0,1);

        newGame.setOnAction(e -> UX.controlStage(s, GameGx()));
        //loadGame.setOnAction(e -> trataAcao(e));

        Scene select = new Scene(gridMaster, UX.lPx, UX.lPy);
        return select;
    }

    public Scene GameGx(){
        Picture picture = new Picture();
        //Board board = new Board();

        //Set alert back
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Your game was not save.");
        alert.setHeaderText("Leave will lost running game.");
        alert.setContentText("Are you sur that you want to leave?");
        ButtonType sure, saveBefore, cancel;
        sure = new ButtonType("Leave anyway");
        saveBefore = new ButtonType("Save before");
        cancel = new ButtonType("Nevermind");
        alert.getButtonTypes().setAll(sure, saveBefore, cancel);
            
        
        GridPane gridMaster = new GridPane();
        GridPane grid = new GridPane();
        GridPane board = new Board();
        //GridPane chessmen  = ((Board) board).onBoard();

        gridMaster.setAlignment(Pos.CENTER);


        MenuBar menuBar = new MenuBar();
            // Menu File
            Menu menuGame = new Menu("Game");
                MenuItem save = new MenuItem("Save");
                MenuItem load = new MenuItem("Load");
                MenuItem menu = new MenuItem("Menu");
        
            menuGame.getItems().addAll(save, load, menu);
            menuBar.getMenus().addAll(menuGame);
            
            save.setOnAction(e -> UX.controlStage(s, Save()));
            //load.setOnAction(e -> controlStage(screenSave()));
            menu.setOnAction(e -> alert.showAndWait().ifPresent(b ->{
                if(b == sure)
                    UX.controlStage(s, MenuGx());
                else if(b == saveBefore)
                    UX.controlStage(s, Save());
                else alert.close();
            }));

            picture.hold("Board");
            grid.add(picture.getView(gPxBorded+2, gPyBorded+2, 0.1),0,1); 
           
            //gridMaster.add(chessmen, 0, 1);
            gridMaster.add(menuBar, 0, 0);
            gridMaster.add(board, 0, 1);
            gridMaster.add(grid,0 ,1);
            //chessmen.toBack();
            board.toFront();
            grid.toBack();
            
            
        Chessman piece = new Pawn(Team.BLACK);
        System.out.println(piece);

        Scene game = new Scene(gridMaster, UX.gPx, UX.gPy);
        return game;
    }

    public Scene testScene(){
        Picture picture = new Picture("Board");

        GridPane grid = new GridPane();
        Pane board = new Board();
        
        grid.add(picture.getView(gPxBorded, gPyBorded), 0, 0);
        grid.add(board, 0, 0);
        
        grid.setAlignment(Pos.CENTER);

     /*
        for(int i = 0; i < 64; i ++){
            chessman = Game.UnboxChessmen(i);
            coordinate = new Coordinate(i);
            square = new BoardSquare(coordinate, chessman);
            grid.add(square, coordinate.getColumn(), coordinate.getRow());
        }
    */

    Scene teste = new Scene(grid, UX.gPx,UX.gPy);
        return teste;
    }
}