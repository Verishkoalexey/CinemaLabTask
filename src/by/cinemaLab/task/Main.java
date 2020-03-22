package by.cinemaLab.task;

import by.cinemaLab.task.controllers.MainController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("fxml/main.fxml"));
        Parent fxmlMain = fxmlLoader.load();
        MainController mainController = fxmlLoader.getController();
        mainController.setMainStage(primaryStage);

        primaryStage.setTitle("List");
        primaryStage.setMinHeight(400);
        primaryStage.setMinWidth(520);
        primaryStage.setScene(new Scene(fxmlMain, 300, 275));
        primaryStage.show();
        
        testData();
    }

    private void testData(){
/*      HumanServiceImpl humanImpl = new HumanServiceImpl();
        humanImpl.fillTestData();
        humanImpl.print();*/
    }


    public static void main(String[] args) {
        launch(args);
    }
}
