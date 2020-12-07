package application.controller;

import application.model.Status;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class StatusController {
    public TextField statusNummerTextField;
    public ListView<Status> statusListView;
    public Button priorityButton;
    public Button statusButton;
    public Button cancelButton;
    public TextField statusBeschreibungTextField1;

    private Status selectedItem = null;

    public void initialize() throws Exception{
        statusListView.setItems(Status.readFile("stati.csv"));
    }

    public void ListViewclicked(MouseEvent mouseEvent) {
        Status s = statusListView.getSelectionModel().getSelectedItem();

        if (s != null) {
            selectedItem = s;
        }

        statusNummerTextField.setText(statusListView.getSelectionModel().getSelectedItem().StatusNummer);
        statusBeschreibungTextField1.setText(statusListView.getSelectionModel().getSelectedItem().Status1);
    }

    public void cancelButtonClicked(ActionEvent actionEvent) {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    public void statusButtonClicked(ActionEvent actionEvent) {
    }

    public void editStatiClicked(ActionEvent actionEvent) {
    }

}
