package application.controller;

import application.model.Status;
import javafx.event.ActionEvent;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class StatusController {
    public TextField statusTextField;
    public ListView<Status> statusListView;

    private Status selectedItem = null;

    public void initialize() throws Exception{
        statusListView.setItems(Status.readFile("stati.csv"));
    }

    public void ListViewclicked(MouseEvent mouseEvent) {
        Status s = statusListView.getSelectionModel().getSelectedItem();

        if (s != null) {
            selectedItem = s;
        }
    }

    public void cancelButtonClicked(ActionEvent actionEvent) {
    }

    public void priorityButtonClicked(ActionEvent actionEvent) {
    }

    public void statusButtonClicked(ActionEvent actionEvent) {
    }

    public void editStatiClicked(ActionEvent actionEvent) {
    }

    public void editPriorityClicked(ActionEvent actionEvent) {

    }
}
