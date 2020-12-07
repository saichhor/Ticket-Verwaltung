package application.controller;

import application.model.Department;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class DepartmentController {
    public Button cancelButton;
    public ListView<Department> statusListView;
    public TextField AbteilungTextField;
    public TextField AbteilungsNummerTextField;

    private Department selectedItem = null;

    public void saveButtonClicked(ActionEvent actionEvent) {

    }

    public void initialize() throws Exception{

        statusListView.setItems(Department.readFile("departments.csv"));
    }

    public void cancelButtonClicked(ActionEvent actionEvent) {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    public void ListViewClicked(MouseEvent mouseEvent) {
        Department department = statusListView.getSelectionModel().getSelectedItem();

        if (department != null) {
            selectedItem = department;
        }

        AbteilungsNummerTextField.setText((statusListView.getSelectionModel().getSelectedItem()).DepartmentNumber);
        AbteilungTextField.setText((statusListView.getSelectionModel().getSelectedItem()).Department1);
    }
}
