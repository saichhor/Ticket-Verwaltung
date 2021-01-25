package application.controller;

import application.model.Department;
import application.model.Priority;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import jdk.jshell.execution.JdiExecutionControlProvider;

import javax.swing.*;

public class DepartmentController {
    public Button cancelButton;
    public TextField AbteilungTextField;
    public TextField AbteilungsNummerTextField;
    public ListView<Department> DepartmentListView;

    private Department selectedItem = null;

    public void saveButtonClicked(ActionEvent actionEvent) {
        if (selectedItem != null) {
            selectedItem.Department1 = AbteilungTextField.getText();

            DepartmentListView.refresh();

            selectedItem.update(); // Aktualisiere DB

        }
    }

    public void initialize() throws Exception{
        DepartmentListView.toString();

        DepartmentListView.setItems(Department.loadList());
    }

    public void cancelButtonClicked(ActionEvent actionEvent) {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    public void ListViewClicked(MouseEvent mouseEvent) {
        Department department = DepartmentListView.getSelectionModel().getSelectedItem();

        if (department != null) {
            selectedItem = department;
        }

        AbteilungsNummerTextField.setText((DepartmentListView.getSelectionModel().getSelectedItem()).DepartmentNumber);
        AbteilungTextField.setText((DepartmentListView.getSelectionModel().getSelectedItem()).Department1);
    }

    public void deleteButtonClicked(ActionEvent actionEvent) {
        AbteilungTextField.clear();
        DepartmentListView.getItems().remove(selectedItem);

        selectedItem.delete();
    }
}
