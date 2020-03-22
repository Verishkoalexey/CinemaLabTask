package by.cinemaLab.task.controllers;

import by.cinemaLab.task.model.Human;
import by.cinemaLab.task.service.impl.HumanServiceImpl;
import by.cinemaLab.task.utils.DialogManager;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class MainController {

    private HumanServiceImpl humanServiceImpl = new HumanServiceImpl();

    private Stage mainStage;

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnEdit;

    @FXML
    private Button btnDelete;

    @FXML
    private TableView tableHumanList;

    @FXML
    private TableColumn<Human, String> columnName;

    @FXML
    private TableColumn<Human, Integer> columnAge;

    @FXML
    private TableColumn<Human, String> columnBirthday;

    private Parent fxmlEdit;
    private FXMLLoader fxmlLoader = new FXMLLoader();
    private EditDialogController editDialogController;
    private Stage editDialogStage;

    public void setMainStage(Stage mainStage){
        this.mainStage = mainStage;
    }

    @FXML
    private void initialize(){
        columnName.setCellValueFactory(new PropertyValueFactory<Human, String>("name"));
        columnAge.setCellValueFactory(new PropertyValueFactory<Human, Integer>("age"));
        columnBirthday.setCellValueFactory(new PropertyValueFactory<Human, String>("birthday"));
        initListeners();
        fillData();
        initLoader();
    }

    private void fillData() {
        humanServiceImpl.fillTestData();
        tableHumanList.setItems(humanServiceImpl.getHumanList().sorted());

    }

    private void initListeners(){
        Human selectedHuman = (Human) tableHumanList.getSelectionModel().getSelectedItem();
        tableHumanList.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(event.getClickCount() == 2){
                    editDialogController.setHuman((Human)tableHumanList.getSelectionModel().getSelectedItem());
                    showDialog();
                }
            }
        });
    }

    private void initLoader(){
        try{
            fxmlLoader.setLocation(getClass().getResource("../fxml/edit.fxml"));
            fxmlEdit = fxmlLoader.load();
            editDialogController = fxmlLoader.getController();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void  actionButtonPressed(ActionEvent actionEvent) {

        Object source = actionEvent.getSource();

        if (!(source instanceof Button)) {
            return;
        }

        Human selectedHuman = (Human) tableHumanList.getSelectionModel().getSelectedItem();

        Button clickedButton = (Button) source;

        switch (clickedButton.getId()) {
            case "btnAdd":
                editDialogController.setHuman(new Human());
                showDialog();
                if(editDialogController.isSaveClicked()) {
                    humanServiceImpl.add(editDialogController.getHuman());
                }
                break;
            case "btnEdit":
                if(!humanIsSelected(selectedHuman)){
                    return;
                }
                editDialogController.setHuman(selectedHuman);
                showDialog();
                break;
            case "btnDelete":
                if(!humanIsSelected(selectedHuman)){
                    return;
                }
                humanServiceImpl.delete(selectedHuman);
                break;
        }
    }

    private boolean humanIsSelected(Human selectedHuman){
        if(selectedHuman == null){
            DialogManager.showErrorDialog("Error", "select human");
            return false;
        }
        return true;
    }

    private boolean humanIsBirthdayToday(Human selectedHuman){
        if(selectedHuman == null){
            DialogManager.showInfoBirthdayDialog("Happy Birthday!", "Human, Happy Birthday!");
            return false;
        }
        return true;
    }

    private void showDialog(){

        if (editDialogStage == null){
            editDialogStage = new Stage();
            editDialogStage.setTitle("Редактирование записи");
            editDialogStage.setMinHeight(150);
            editDialogStage.setMinWidth(300);
            editDialogStage.setResizable(false);
            editDialogStage.setScene(new Scene(fxmlEdit));
            editDialogStage.initModality(Modality.WINDOW_MODAL);
            editDialogStage.initOwner(mainStage);
        }

        editDialogStage.showAndWait();
    }
}
