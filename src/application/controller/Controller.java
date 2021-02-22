package application.controller;

import application.MyFXMLLoader;
import application.model.Priority;
import application.model.Status;
import application.model.Ticket;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.util.ArrayList;

public class Controller {

    private TicketController active = null;

    public ListView<Ticket> ticketListView;
    public AnchorPane contentPane;
    //neue Felder
    /**
     * Filter müssen UND- Verknüpft werden!
     */
    public TextField filterNameTextField;   //filtern nach name des Todos
    public ComboBox<Status> filterStatusCombobox;   //filtern nach status
    public ComboBox<Priority> filterPrioritätCombobox;//filtern nach priorität
    private ArrayList<Ticket> allTicket;
    Ticket selectedItem = null;





    public void initialize() {

        /**
         ticketListView.setItems(Ticket.readFile("tickets.csv"));

         ObservableList<Status> statusList = Status.readFile("stati.csv");
         statusList.add(0, new Status(-1, "Filter wählen"));
         **/
        filterStatusCombobox.setItems(Status.loadList());
        filterPrioritätCombobox.setItems(Priority.loadList());
        ticketListView.setItems(Ticket.loadList());
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
        AnchorPane.setBottomAnchor(root, 0.0);
        AnchorPane.setTopAnchor(root, 0.0);
        AnchorPane.setLeftAnchor(root, 0.0);
        AnchorPane.setRightAnchor(root, 0.0);

        contentPane.getChildren().add(root);

        TicketController controller = (TicketController) loader.getController();
        controller.setTicket(ticketListView.getSelectionModel().getSelectedItem());

    selectedItem = ticketListView.getSelectionModel().getSelectedItem();
    }

    public void newClicked(ActionEvent actionEvent) {

        MyFXMLLoader loader = new MyFXMLLoader();
        Parent root = loader.loadFXML("view/ticket.fxml");
        AnchorPane.setBottomAnchor(root, 0.0);
        AnchorPane.setTopAnchor(root, 0.0);
        AnchorPane.setLeftAnchor(root, 0.0);
        AnchorPane.setRightAnchor(root, 0.0);

        contentPane.getChildren().add(root);

        active = (TicketController) loader.getController();
        active.setTicket(null);

        System.out.println("Neues Ticket");
    }

    public void deleteClicked(ActionEvent actionEvent) {
        //laden des Tickets
        // Entfernen aus ListView
        //Datei aktualisieren
        MyFXMLLoader loader = new MyFXMLLoader();
        Parent root = loader.loadFXML("view/ticket.fxml");
        AnchorPane.setBottomAnchor(root, 0.0);
        AnchorPane.setTopAnchor(root, 0.0);
        AnchorPane.setLeftAnchor(root, 0.0);
        AnchorPane.setRightAnchor(root, 0.0);

        contentPane.getChildren().add(root);

        active = (TicketController) loader.getController();


        active.getTicket();

        Ticket selected = ticketListView.getSelectionModel().getSelectedItem();

    }


    public void saveClicked(ActionEvent actionEvent) {
        //Wenn Ticket neu -> laden des Ticktes und hinzufügen zur Liste
        //Datei aktualisieren


        if(selectedItem != null){
            //selectedItem.name
        }
    }


    public void searchlistKey(KeyEvent keyEvent) {

    }
}