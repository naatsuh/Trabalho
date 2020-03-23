package Controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.ArrayList;

public class Main extends Application {

    private static Stage stage;

    private static Scene MainScene, ContatoScene;

    @Override
    public void start(Stage primaryStage) throws Exception {

        stage = primaryStage;

        primaryStage.setTitle("Agenda Tabajara");

        Parent fxmlMainScene = FXMLLoader.load(getClass().getResource("../View/Main_Screen.fxml"));
        MainScene = new Scene(fxmlMainScene, 600, 400);

        Parent fxmlContatosScene = FXMLLoader.load(getClass().getResource("../View/Contato_Screen.fxml"));
        ContatoScene = new Scene(fxmlContatosScene, 600, 400);

        primaryStage.setScene(MainScene);
        primaryStage.show();
    }

    public static void changeScreen(String scr, Object userData) {
        switch (scr) {
            case "MainScene":
                stage.setScene(MainScene);
                notifyAllListerners("MainScene", userData);
                break;
            case "ContatoScene":
                stage.setScene(ContatoScene);
                notifyAllListerners("ContatoScene", userData);
                break;
        }
    }

    public static void changeScreen(String scr) {
        changeScreen(scr, null);
    }

    public static void main(String[] args) {
        launch(args);
    }

    //------------------------------------------
    private static ArrayList<OnChangeScreen> listeners = new ArrayList<OnChangeScreen>();

    public static interface OnChangeScreen {
        void onScreenChanged(String newScreen, Object userData);
    }

    public static void addOnChangeScreenListener(OnChangeScreen newListener) {
        listeners.add(newListener);
    }

    private static void notifyAllListerners(String newScreen, Object userData) {
        for (OnChangeScreen l : listeners)
            l.onScreenChanged(newScreen, userData);
    }
}
