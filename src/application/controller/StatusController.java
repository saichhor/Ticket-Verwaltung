package application.controller;

import application.model.Status;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.io.Reader;

public class StatusController {
    public TextField statusTextField;
    public ListView<Status> statusListView;


    public void initialize(){
        StatusReader r = new StatusReader();

        statusListView.setItems((r.readFile("stati.csv")));
    }

    public void ListViewclicked(MouseEvent mouseEvent) {
        //statusTextField.setText(statusListView.getSelectionModel().getSelectedItems().);
//asd

    }
}
