package application.controller;

import application.model.Priority;
import javafx.event.ActionEvent;

import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;


public class PriorityController {

    public Button cancelButton;
    public ListView<Priority> statusListView;
    public TextField priorityField;

    private Priority selectedItem = null;

    public void saveButtonClicked(ActionEvent actionEvent) {

    }

    public void initialize() throws Exception{
        statusListView.toString();

        statusListView.setItems(Priority.loadList());
    }

    public void cancelButtonClicked(ActionEvent actionEvent) {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    public void listViewCLicked(MouseEvent mouseEvent) {
        Priority priority = statusListView.getSelectionModel().getSelectedItem();

        if (priority != null) {
            selectedItem = priority;
        }

        priorityField.setText((statusListView.getSelectionModel().getSelectedItem()).priorityId);
    }
}
