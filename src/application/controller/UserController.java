package application.controller;

import application.model.Status;
import application.model.User;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class UserController {
    public Button cancelButton;
    public TextField userTitelTextField;
    public TextField userStrasseTextField;
    public TextField userPLZTextField;
    public TextField userStadtTextField;
    public TextField userAbteilungTextField;
    public ListView<User> userListView;
    public TextField userNummerTextField;
    public TextField userNameTextField;

    private User selectedItem = null;



    public void initialize() throws Exception{
        userListView.setItems(User.readFile("users.csv"));
    }

    public void ListViewclicked(MouseEvent mouseEvent) {
        User s = userListView.getSelectionModel().getSelectedItem();

        if (s != null) {
            selectedItem = s;
        }

        userNummerTextField.setText(userListView.getSelectionModel().getSelectedItem().userNummer);
        userTitelTextField.setText(userListView.getSelectionModel().getSelectedItem().userTitel);
        userNameTextField.setText(userListView.getSelectionModel().getSelectedItem().userName);
        userStrasseTextField.setText(userListView.getSelectionModel().getSelectedItem().userStrasse);
        userPLZTextField.setText(userListView.getSelectionModel().getSelectedItem().userPLZ);
        userStadtTextField.setText(userListView.getSelectionModel().getSelectedItem().userStadt);
        userAbteilungTextField.setText(userListView.getSelectionModel().getSelectedItem().userAbteilung);

    }

    public void cancelButtonClicked(ActionEvent actionEvent) {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    public void saveButtonClicked(ActionEvent actionEvent) {
    }

    public void createButtonClicked(ActionEvent actionEvent) {
    }

    public void deleteButtonClicked(ActionEvent actionEvent) {
    }
}
