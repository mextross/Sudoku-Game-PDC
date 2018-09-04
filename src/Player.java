
/*Name: Michelle Extross
Student ID: 1393444*/
import PatternFilesToTables.DBEasyPatternOperations;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

//A class for a player that plays a Sudoku game
public class Player {

    private static String name;//player name
    private static int score;//player score
    private ArrayList<String> playerNamesList;//list of player names
    private PlayerOperations playerQuery;

    public Player(String name, int score) {
        this.name = name;//the field name is set to equal to the parameter(inputted by user)
        this.score = score;//the field name is set to equal to the parameter(inputted by user)
        playerNamesList = new ArrayList<>();//list of player names
        playerQuery = new PlayerOperations();
    }

    //Acessor method to get the player's name
    public String getName() {
        return name;
    }

    //Acessor method to get the player's score
    public int getScore() {
        return score;
    }

    //Mutator method to set the player's score when the player wins a game
    public void setScore(int score) {
        this.score = score;
    }

    //queries the table to find all the players that have played before
    public void queryPlayerNames() throws SQLException {
        playerQuery.establishConnection();
        ResultSet rs = playerQuery.getCurrentPlayersList();
        while (rs.next()) {
            String playerName = rs.getString(1);
            playerNamesList.add(playerName);
        }
    }

    //checks if the current player has played before
    public boolean playerExists(String playerName) {
        try {
            queryPlayerNames();

        } catch (SQLException ex) {
            Logger.getLogger(DBEasyPatternOperations.class.getName()).log(Level.SEVERE, null, ex);
        }
        for (String name : playerNamesList) {
            if (name.equalsIgnoreCase(playerName)) {
                return true;
            }
        }
        return false;

    }

}
