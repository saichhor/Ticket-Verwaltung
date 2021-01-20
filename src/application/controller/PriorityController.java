package application.controller;

import application.model.Priority;
import javafx.event.ActionEvent;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;


public class PriorityController {

    public Button cancelButton;
    public ListView<Priority> priorityListView;
    public TextField priorityField;
    public Button createButton;
    public Label priorityLabel;
    public Button saveButton;
    public Button deleteButton;

    private Priority selectedItem = null;

    public void saveButtonClicked(ActionEvent actionEvent) {

    }

    public void initialize() throws Exception{
        priorityListView.toString();

        priorityListView.setItems(Priority.loadList());
    }

    public void cancelButtonClicked(ActionEvent actionEvent) {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    public void listViewCLicked(MouseEvent mouseEvent) {
        Priority priority = priorityListView.getSelectionModel().getSelectedItem();

        if (priority != null) {
            selectedItem = priority;
        }

        priorityField.setText((priorityListView.getSelectionModel().getSelectedItem()).desc);
    }

    public void createButtonClicked(ActionEvent actionEvent) {

    }

    public void deleteButtonClicked(ActionEvent actionEvent) {
        priorityField.clear();
        priorityListView.getItems().remove(selectedItem);

        selectedItem.delete();
    }
}
