package application.controller;

import application.model.Priority;
import application.model.Status;
import application.model.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.*;

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

    ObservableList<User> list = FXCollections.observableArrayList();

    private User selectedItem = null;

    public void initialize() {
        userListView.toString();

        userListView.setItems(User.loadList());
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
        userAbteilungTextField.setText(String.valueOf(userListView.getSelectionModel().getSelectedItem().userAbteilung));

    }

    public void cancelButtonClicked(ActionEvent actionEvent) {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    public void saveButtonClicked(ActionEvent actionEvent) {
        if (this.selectedItem != null) {
            // Aktualisiere die Artikeldaten
            // (übernimm die aktuellen Daten von den Textfeldern)
            // und speichere alles in die Datei
            selectedItem.userNummer = userNummerTextField.getText();
            selectedItem.userTitel = userTitelTextField.getText();
            selectedItem.userName = userNameTextField.getText();
            selectedItem.userStrasse = userStrasseTextField.getText();
            selectedItem.userPLZ = userPLZTextField.getText();
            selectedItem.userStadt = userStadtTextField.getText();
            selectedItem.userAbteilung = userAbteilungTextField.getText();

            userListView.refresh();

            selectedItem.update(); // Aktualisiere DB
        }
    }

    public void createButtonClicked(ActionEvent actionEvent) {
        userNummerTextField.clear();
        userTitelTextField.clear();
        userNameTextField.clear();
        userStrasseTextField.clear();
        userPLZTextField.clear();
        userStadtTextField.clear();
        userAbteilungTextField.clear();

        // lösche die Variable, die den gewählten Artikel
        // beinhaltet
        this.selectedItem = null;
    }

    public void deleteButtonClicked(ActionEvent actionEvent) {
        userTitelTextField.clear();
        userListView.getItems().remove(selectedItem);

        selectedItem.delete();
    }

    public void overwrite(){

        try {
            try (BufferedWriter wr = new BufferedWriter(new FileWriter("users.csv"))) {

                for (User a : list) {
                    wr.write("\"" + a.userNummer + "\";\"" + a.userTitel + "\";\"" + a.userName + "\";\"" + a.userStrasse + "\";\"" + a.userPLZ + "\";\"" + a.userStadt + "\";\"" + a.userAbteilung + "\";\n");
                }
            }
        } catch (IOException io) {
            io.printStackTrace();
        }
    }
}
