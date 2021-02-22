package application.controller;

import application.model.Priority;
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
    public Button deleteButton;

    private Status selectedItem = null;

    public void initialize() {
        statusListView.toString();

        statusListView.setItems(Status.loadList());
    }

    public void ListViewclicked(MouseEvent mouseEvent) {
        Status s = statusListView.getSelectionModel().getSelectedItem();

        if (s != null) {
            selectedItem = s;
        }

        statusNummerTextField.setText(String.valueOf(statusListView.getSelectionModel().getSelectedItem().StatusID));
        statusBeschreibungTextField1.setText(statusListView.getSelectionModel().getSelectedItem().StatusName);
    }

    public void cancelButtonClicked(ActionEvent actionEvent) {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    public void saveButtonClicked(ActionEvent actionEvent) {
        if (selectedItem != null) {
            selectedItem.StatusName = statusBeschreibungTextField1.getText();

            statusListView.refresh();

            selectedItem.update(); // Aktualisiere DB
        }else{

            Status s = new Status(0, statusBeschreibungTextField1.getText());
            s.insert();

            statusListView.getItems().add(s);
        }
    }

    public void deleteButtonClicked(ActionEvent actionEvent) {
        statusBeschreibungTextField1.clear();
        statusListView.getItems().remove(selectedItem);

        selectedItem.delete();
    }
}