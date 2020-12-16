package application.controller;

import application.MyFXMLLoader;
import application.model.Priority;
import application.model.Status;
import application.model.Ticket;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class Controller {

    public ListView<Ticket> ticketListView;
    public AnchorPane contentPane;
    //neue Felder
    /**
     * Filter müssen UND- Verknüpft werden!
     */
    public TextField filterNameTextField;   //filern nach name des Todos
    public ComboBox<Status> filterStatusCombobox;   //filter nach status
    public ComboBox<Priority> filterPrioritätCombobox;//filter nach priorität


    public void initialize() {
        filterStatusCombobox.setItems(Status.readFile("stati.csv"));
        filterPrioritätCombobox.setItems(Priority.readFile("priorities.csv"));
        ticketListView.setItems(Ticket.readFile("tickets.csv"));
    }

    public void priorityButtonClicked(ActionEvent actionEvent) {
        MyFXMLLoader loader = new MyFXMLLoader();
        loader.loadFXML("view/priority.fxml", "Prioritäten bearbeiten");
    }

    public void statusButtonClicked(ActionEvent actionEvent) {
        MyFXMLLoader loader = new MyFXMLLoader();
        loader.loadFXML("view/status.fxml", "Stati bearbeiten");
    }

    public void userButtonClicked(ActionEvent actionEvent) {
        MyFXMLLoader loader = new MyFXMLLoader();
        loader.loadFXML("view/user.fxml", "User bearbeiten");
    }

    public void departmentButtonClicked(ActionEvent actionEvent) {
        MyFXMLLoader loader = new MyFXMLLoader();
        loader.loadFXML("view/department.fxml", "Department bearbeiten");
    }

    public void ticketListViewClicked(MouseEvent mouseEvent) {
        MyFXMLLoader loader = new MyFXMLLoader();
        Parent root = loader.loadFXML("view/ticket.fxml");
        contentPane.getChildren().add(root);

        TicketController controller = (TicketController) loader.getController();
        controller.setTicket(ticketListView.getSelectionModel().getSelectedItem());
    }

}
