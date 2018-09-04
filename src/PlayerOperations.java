
/*Name: Michelle Extross
Student ID: 1393444*/
import PatternFilesToTables.DBEasyPatternOperations;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

//All operations used to query and modify the Current Player List DB table
public class PlayerOperations {

    String url = "jdbc:derby://localhost:1527/PatternDB;create=true";
    String usernameDerby = "pdc";
    String passwordDerby = "pdc";
    Connection conn;
    String tableName;
    Statement statement;

    public PlayerOperations() {
        tableName = "CurrentPlayerList";

    }

    //method used to establish a connection with DB tables
    public void establishConnection() {
        try {
            conn = DriverManager.getConnection(url, usernameDerby, passwordDerby);
            System.out.println(url + "   connected....");

        } catch (SQLException ex) {
            Logger.getLogger(PlayerOperations.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    //method to create the table CurrentPlayerList
    public void createPlayerList() {
        try {
            statement = conn.createStatement();
            tableName = "CurrentPlayerList";

            String sqlCreate = "create table " + tableName + " (PlayerName varchar(20),"
                    + "Score int)";
            statement.executeUpdate(sqlCreate);

            System.out.println(tableName + "created");

        } catch (SQLException ex) {
            Logger.getLogger(PlayerOperations.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //method used query the DB and find all players that have played previously
    public ResultSet getCurrentPlayersList() {
        establishConnection();
        ResultSet rs = null;

        try {
            System.out.println(" getting query....");
            Statement statement = conn.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);

            rs = statement.executeQuery("SELECT * FROM  CURRENTPLAYERLIST");

        } catch (SQLException ex) {
            Logger.getLogger(DBEasyPatternOperations.class.getName()).log(Level.SEVERE, null, ex);
        }

        return (rs);
    }

    //method used to create a new  player in the CurrentPlayerList table
    public void CreateNewPlayerTableEntry(String name, int score) throws SQLException {
        establishConnection();
        statement = conn.createStatement();

        String sqlInsert = "insert into " + tableName + " values(" + "'" + name + "', " + score + ")";
        // System.out.println(sqlInsert);
        statement.executeUpdate(sqlInsert);
    }

    //method used to modify the player's score if the player wins a new game
    public void ModifyPlayerTableScore(String name, int score) throws SQLException {
        establishConnection();
        statement = conn.createStatement();
        String sqlUpdateTable = "update " + tableName + " set score=" + score
                + "where PlayerName='" + name + "'";
        statement.executeUpdate(sqlUpdateTable);

    }

    //method used to query the current player's score
    public int queryPlayerScore(String playerName) {
        establishConnection();
        ResultSet rs = null;
        int score = 0;
        try {
            System.out.println(" getting query....");
            Statement statement = conn.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);

            rs = statement.executeQuery("SELECT SCORE FROM CURRENTPLAYERLIST WHERE PlayerName='" + playerName + "'");
            while (rs.next()) {
                score = rs.getInt(1);
            }

        } catch (SQLException ex) {
            Logger.getLogger(DBEasyPatternOperations.class.getName()).log(Level.SEVERE, null, ex);
        }

        return score;

    }

}
