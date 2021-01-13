package application.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class AccessDb {
    static {
        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }

    }
    private static Connection connection = null;

    /**
     * Vereinfachtes Singleton -Pattern
     * @return
     */
    public static Connection getConnection(){
        //Wenn connection noch nicht befüllt wurde ( connection == null)
        //da es sich um eine statische Variable handelt, ist diese
        //in allen Objektinstanzen gleich!
        if(connection == null){
            try {
                //erzeuge eine neue Verbindung zu Datenbank
                connection= DriverManager.getConnection("jdbc:ucanaccess://C:/Users/andre/HTL3Klasse/ITP/Ticket-Verwaltung/db/itp16.11.accdb");
            }catch(SQLException throwables) {

            }
        }
        return connection;
    }
}
