package by.cinemaLab.task.controllers;

import by.cinemaLab.task.model.Human;
import by.cinemaLab.task.utils.DialogManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.time.LocalDate;

public class EditDialogController {
    @FXML
    private Button btnOk;

    @FXML
    private Button btnCancel;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtAge;

    @FXML
    private DatePicker datePickerBirthday;

    private Human human;
    private boolean saveClicked = false;

    public void setHuman(Human human){
        if(human == null){
            return;
        }
        saveClicked = false;
        this.human = human;
        txtName.setText(human.getName());
        txtAge.setText(String.valueOf(human.getAge()));
        datePickerBirthday.setValue(LocalDate.parse(human.getBirthday()));
    }

    public Human getHuman(){return human;}

    public void actionClose(ActionEvent actionEvent){
        Node source = (Node) actionEvent.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.hide();
    }

    public void actionSave(ActionEvent actionEvent) {
        if(!checkValues()){
            return;
        }
        human.setName(txtName.getText());
        human.setAge(Integer.parseInt(txtAge.getText()));
        human.setBirthday(datePickerBirthday.getValue().toString());
        saveClicked = true;
        actionClose(actionEvent);
    }

    private boolean checkValues() {
        if (txtName.getText().trim().length()==0 || txtAge.getText().equals("0") || datePickerBirthday.getValue().equals(LocalDate.now())){
            DialogManager.showErrorDialog("Error", "Check the entered data");
            return false;
        }
        return true;
    }

    public boolean isSaveClicked(){
        return saveClicked;
    }
}
