
/*Name: Michelle Extross
Student ID: 1393444*/
import PatternFilesToTables.DBEasyPatternOperations;
import PatternFilesToTables.DBHardPatternOperations;
import PatternFilesToTables.DBMediumPatternOperations;
import PatternFilesToTables.DBSolvedEasyPatternOperations;
import PatternFilesToTables.DBSolvedHardPatternOperations;
import PatternFilesToTables.DBSolvedMediumPatternOperations;
import java.awt.BorderLayout;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JFrame;

//Allows a user to play a sudoku game
public class PlayGame {

    //checks if a table exists
    public static boolean tableExist(Connection conn, String tableName) throws SQLException {
        try (ResultSet rs = conn.getMetaData().getTables(null, null, tableName, null)) {
            while (rs.next()) {
                String tName = rs.getString("TABLE_NAME");
                if (tName != null && tName.equals(tableName)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) throws FileNotFoundException, SQLException {
        //Commands to convert patterns in files to DB Tables (Tables have already been created)
        //DatabaseMetaData dbm = 

        Connection con = null;
        DBEasyPatternOperations easyPatterns = new DBEasyPatternOperations();
        easyPatterns.establishConnection();
        con = easyPatterns.getConnection();

        boolean easyPattern1Exists = PlayGame.tableExist(con, "EASYPATTERN1");
        boolean easyPattern2Exists = PlayGame.tableExist(con, "EASYPATTERN2");
        boolean easyPattern3Exists = PlayGame.tableExist(con, "EASYPATTERN3");
        boolean easyPattern4Exists = PlayGame.tableExist(con, "EASYPATTERN4");
        
        //only creates a table if it does not exist
        if (!easyPattern1Exists && !easyPattern2Exists && !easyPattern3Exists && !easyPattern4Exists) {
            easyPatterns.loadFilesToTables();
        }
        

        DBSolvedEasyPatternOperations solvedEasyPatterns = new DBSolvedEasyPatternOperations();
        solvedEasyPatterns.establishConnection();
        con = solvedEasyPatterns.getConnection();

        boolean solvedEasyPattern1Exists = PlayGame.tableExist(con, "SOLVEDEASYPATTERN1");
        boolean solvedEasyPattern2Exists = PlayGame.tableExist(con, "SOLVEDEASYPATTERN2");
        boolean solvedEasyPattern3Exists = PlayGame.tableExist(con, "SOLVEDEASYPATTERN3");
        boolean solvedEasyPattern4Exists = PlayGame.tableExist(con, "SOLVEDEASYPATTERN4");
        
        //only creates a table if it does not exist
        if (!solvedEasyPattern1Exists && !solvedEasyPattern2Exists && !solvedEasyPattern3Exists && !solvedEasyPattern4Exists) {
            solvedEasyPatterns.loadFilesToTables();
        }
     

        DBMediumPatternOperations mediumPatterns = new DBMediumPatternOperations();
        mediumPatterns.establishConnection();
        con = mediumPatterns.getConnection();

        boolean mediumPattern1Exists = PlayGame.tableExist(con, "MEDIUMPATTERN1");
        boolean mediumPattern2Exists = PlayGame.tableExist(con, "MEDIUMPATTERN2");
        boolean mediumPattern3Exists = PlayGame.tableExist(con, "MEDIUMPATTERN3");
        boolean mediumPattern4Exists = PlayGame.tableExist(con, "MEDIUMPATTERN4");
        
        //only creates a table if it does not exist
        if (!mediumPattern1Exists && !mediumPattern2Exists && !mediumPattern3Exists && !mediumPattern4Exists) {
            mediumPatterns.loadFilesToTables();
        }
        

        DBSolvedMediumPatternOperations solvedMediumPatterns = new DBSolvedMediumPatternOperations();
        solvedMediumPatterns.establishConnection();
        con = solvedMediumPatterns.getConnection();

        boolean solvedMediumPattern1Exists = PlayGame.tableExist(con, "SOLVEDMEDIUMPATTERN1");
        boolean solvedMediumPattern2Exists = PlayGame.tableExist(con, "SOLVEDMEDIUMPATTERN2");
        boolean solvedMediumPattern3Exists = PlayGame.tableExist(con, "SOLVEDMEDIUMPATTERN3");
        boolean solvedMediumPattern4Exists = PlayGame.tableExist(con, "SOLVEDMEDIUMPATTERN4");

        if (!solvedMediumPattern1Exists && !solvedMediumPattern2Exists && !solvedMediumPattern3Exists && !solvedMediumPattern4Exists) {
            solvedMediumPatterns.loadFilesToTables();
        }
        
        
        DBHardPatternOperations hardPatterns = new DBHardPatternOperations();
        hardPatterns.establishConnection();
        con = hardPatterns.getConnection();

        boolean hardPattern1Exists = PlayGame.tableExist(con, "HARDPATTERN1");
        boolean hardPattern2Exists = PlayGame.tableExist(con, "HARDPATTERN2");
        boolean hardPattern3Exists = PlayGame.tableExist(con, "HARDPATTERN3");
        boolean hardPattern4Exists = PlayGame.tableExist(con, "HARDPATTERN4");

        if (!hardPattern1Exists && !hardPattern2Exists && !hardPattern3Exists && !hardPattern4Exists) {
            hardPatterns.loadFilesToTables();
        }
        
        //only creates a table if it does not exist
        DBSolvedHardPatternOperations solvedHardPatterns = new DBSolvedHardPatternOperations();
        solvedHardPatterns.establishConnection();
        con = solvedHardPatterns.getConnection();

        boolean solvedHardPattern1Exists = PlayGame.tableExist(con, "SOLVEDHARDPATTERN1");
        boolean solvedHardPattern2Exists = PlayGame.tableExist(con, "SOLVEDHARDPATTERN2");
        boolean solvedHardPattern3Exists = PlayGame.tableExist(con, "SOLVEDHARDPATTERN3");
        boolean solvedHardPattern4Exists = PlayGame.tableExist(con, "SOLVEDHARDPATTERN4");
        
        if (!solvedHardPattern1Exists && !solvedHardPattern2Exists && !solvedHardPattern3Exists && !solvedHardPattern4Exists) {
            solvedHardPatterns.loadFilesToTables();
        }
        
        //Create Player's List
        PlayerOperations playerList = new PlayerOperations();
        playerList.establishConnection();
         boolean currentPlayerList = PlayGame.tableExist(con, "CURRENTPLAYERLIST");
         if (!currentPlayerList) {
            playerList.createPlayerList();
        }

        //Loads the Sudoku GUI
        PanelContainer panelContainer = new PanelContainer();
        JFrame f = new JFrame("Sudoku");
        f.setTitle("Sudoku Challenge");
        f.setLocation(320, 180);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(600, 400);
        f.setLayout(new BorderLayout());
        f.getContentPane().add(panelContainer);
        f.setVisible(true);
    }

}
