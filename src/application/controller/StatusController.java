package application.controller;

import application.model.Status;
import javafx.event.ActionEvent;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.io.FileReader;
import java.io.Reader;

public class StatusController {
    public TextField statusTextField;
    public ListView<Status> statusListView;


    public void initialize() throws Exception{
        StatusReader r = new StatusReader();

        FileReader reader = new FileReader("stati.csv");
    }

    public void ListViewclicked(MouseEvent mouseEvent) {
        //statusTextField.setText(statusListView.getSelectionModel().getSelectedItems().);
        //asd

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
