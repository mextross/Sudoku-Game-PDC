
/*Name: Michelle Extross
Student ID: 1393444*/
package PatternFilesToTables;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

//A class that loads Hard Patterns from files into tables and queries to get corresponding patterns.
public class DBHardPatternOperations {

    String url = "jdbc:derby://localhost:1527/PatternDB;create=true";
    String usernameDerby = "pdc";
    String passwordDerby = "pdc";
    Connection conn;
    Statement st;

    private File hardPatternFolder;
    private File[] hardPatternFiles;
    private ArrayList<ArrayList<Integer>> hardPatternLists;
    private ArrayList<Integer> hardPatternList1;
    private ArrayList<Integer> hardPatternList2;
    private ArrayList<Integer> hardPatternList3;
    private ArrayList<Integer> hardPatternList4;

    private ArrayList<String> hardPatternListValues1;
    private ArrayList<String> hardPatternListValues2;
    private ArrayList<String> hardPatternListValues3;
    private ArrayList<String> hardPatternListValues4;
    private ArrayList<ArrayList<String>> hardPatternListValues;

    public DBHardPatternOperations() {
        st = null;
        hardPatternFolder = new File("src\\Sudoku Patterns\\Hard Level");
        hardPatternFiles = hardPatternFolder.listFiles();
        hardPatternLists = new ArrayList<>();
        hardPatternList1 = new ArrayList<>();
        hardPatternList2 = new ArrayList<>();
        hardPatternList3 = new ArrayList<>();
        hardPatternList4 = new ArrayList<>();
        hardPatternLists.add(hardPatternList1);
        hardPatternLists.add(hardPatternList1);
        hardPatternLists.add(hardPatternList1);
        hardPatternLists.add(hardPatternList1);

        hardPatternListValues = new ArrayList<>();
        hardPatternListValues1 = new ArrayList<>();
        hardPatternListValues2 = new ArrayList<>();
        hardPatternListValues3 = new ArrayList<>();
        hardPatternListValues4 = new ArrayList<>();

        hardPatternListValues.add(hardPatternListValues1);
        hardPatternListValues.add(hardPatternListValues2);
        hardPatternListValues.add(hardPatternListValues3);
        hardPatternListValues.add(hardPatternListValues4);

    }

    //method used to establish a connection with DB tables
    public void establishConnection() {
        try {
            conn = DriverManager.getConnection(url, usernameDerby, passwordDerby);
            System.out.println(url + "   connected....");
        } catch (SQLException ex) {
            Logger.getLogger(DBEasyPatternOperations.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public Connection getConnection()
    {
    return conn;
    }

    //accessor method to get the statement
    public Statement getStatement() {
        return st;
    }

    //loads all hard patterns from text files into ArrayLists, then use the Lists to create tables
    public void loadFilesToTables() throws FileNotFoundException {
        int x = 0;
        int y = 0;
        int element = 0;
        int index = 0;
        for (int i = 0; i < hardPatternFiles.length; i++) {
            Scanner sc = new Scanner(hardPatternFiles[i]);
            while (sc.hasNextLine()) {
                String currentLine = sc.nextLine();
                StringTokenizer tk1 = new StringTokenizer(currentLine, ",");
                while (tk1.hasMoreTokens()) {
                    element = Integer.parseInt(tk1.nextToken());
                    if (i == 0) {
                        hardPatternList1.add(element);
                    }
                    if (i == 1) {
                        hardPatternList2.add(element);
                    }
                    if (i == 2) {
                        hardPatternList3.add(element);
                    }
                    if (i == 3) {
                        hardPatternList4.add(element);
                    }

                }
            }
        }

        for (int k = 0; k < hardPatternList1.size(); k++) {
            if (k > -1 && k < 9) {
                x = 0;
            }
            if (k > 8 && k < 18) {
                x = 1;
            }
            if (k > 17 && k < 27) {
                x = 2;
            }
            if (k > 26 && k < 36) {
                x = 3;
            }
            if (k > 35 && k < 45) {
                x = 4;
            }
            if (k > 44 && k < 54) {
                x = 5;
            }
            if (k > 53 && k < 63) {
                x = 6;
            }
            if (k > 62 && k < 72) {
                x = 7;
            }
            if (k > 71 && k < 81) {
                x = 8;
            }

            y = k % 9;

            hardPatternListValues1.add(String.format("%d, %d, %d, %d", index, hardPatternList1.get(k), x, y));
            hardPatternListValues2.add(String.format("%d, %d, %d, %d", index, hardPatternList2.get(k), x, y));
            hardPatternListValues3.add(String.format("%d, %d, %d, %d", index, hardPatternList3.get(k), x, y));
            hardPatternListValues4.add(String.format("%d, %d, %d, %d", index, hardPatternList4.get(k), x, y));
            index++;
        }

        createTable();

    }

    //uses the ArrayLists to create DB tables
    public void createTable() throws FileNotFoundException {

        try {
            Statement statement = conn.createStatement();
            for (int i = 0; i < hardPatternListValues.size(); i++) {
                String newTableName = "HardPattern";

                //creates HardPattern1 Table
                if (i == 0) {
                    newTableName = "HardPattern" + (1);
                    String sqlCreate = "create table " + newTableName + " (RowID int,"
                            + "Element int, XCoordinate int,"
                            + "YCoordinate int)";
                    statement.executeUpdate(sqlCreate);
                    for (int j = 0; j < hardPatternListValues1.size(); j++) {
                        System.out.println(hardPatternListValues1.get(j));
                        String sqlInsert = "insert into " + newTableName + " values(" + hardPatternListValues1.get(j) + ")";
                        System.out.println(sqlInsert);

                        statement.executeUpdate(sqlInsert);
                    }
                    System.out.println(newTableName + "created");
                }

                //creates HardPattern2 Table
                if (i == 1) {
                    newTableName = "HardPattern" + (2);
                    String sqlCreate = "create table " + newTableName + " (RowID int,"
                            + "Element int, XCoordinate int,"
                            + "YCoordinate int)";
                    statement.executeUpdate(sqlCreate);
                    for (int j = 0; j < hardPatternListValues2.size(); j++) {
                        String sqlInsert = "insert into " + newTableName + " values(" + hardPatternListValues2.get(j) + ")";

                        System.out.println(sqlInsert);

                        statement.executeUpdate(sqlInsert);
                    }
                    System.out.println(newTableName + "created");
                }

                //creates HardPattern3 Table
                if (i == 2) {
                    newTableName = "HardPattern" + (3);
                    String sqlCreate = "create table " + newTableName + " (RowID int,"
                            + "Element int, XCoordinate int,"
                            + "YCoordinate int)";
                    statement.executeUpdate(sqlCreate);
                    for (int j = 0; j < hardPatternListValues3.size(); j++) {
                        String sqlInsert = "insert into " + newTableName + " values(" + hardPatternListValues3.get(j) + ")";

                        System.out.println(sqlInsert);

                        statement.executeUpdate(sqlInsert);
                    }
                    System.out.println(newTableName + "created");
                }

                //creates HardPattern4 Table
                if (i == 3) {
                    newTableName = "HardPattern" + (4);
                    String sqlCreate = "create table " + newTableName + " (RowID int,"
                            + "Element int, XCoordinate int,"
                            + "YCoordinate int)";
                    statement.executeUpdate(sqlCreate);
                    for (int j = 0; j < hardPatternListValues4.size(); j++) {
                        String sqlInsert = "insert into " + newTableName + " values(" + hardPatternListValues4.get(j) + ")";
                        System.out.println(sqlInsert);
                        statement.executeUpdate(sqlInsert);
                    }
                    System.out.println(newTableName + "created");
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBEasyPatternOperations.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //Query to the get the ResultSet of an HardPattern Table depending on the patternNum
    public ResultSet getHardPattern(int patternNum) {
        ResultSet rs = null;

        try {
            System.out.println(" getting query....");
            Statement statement = conn.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            st = statement;

            if (patternNum == 1) {
                rs = statement.executeQuery("SELECT * FROM  HARDPATTERN1");
            }
            if (patternNum == 2) {
                rs = statement.executeQuery("SELECT * FROM  HARDPATTERN2");
            }
            if (patternNum == 3) {
                rs = statement.executeQuery("SELECT * FROM  HARDPATTERN3");
            }
            if (patternNum == 4) {
                rs = statement.executeQuery("SELECT * FROM  HARDPATTERN4");
            }

        } catch (SQLException ex) {
            Logger.getLogger(DBEasyPatternOperations.class.getName()).log(Level.SEVERE, null, ex);
        }

        return (rs);
    }

    //Query to the get the ResultSet of an SolvedHardPattern Table depending on the patternNum
    public ResultSet getSolvedHardPattern(int patternNum) {
        ResultSet rs = null;

        try {
            System.out.println(" getting query....");
            Statement statement = conn.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);

            if (patternNum == 1) {
                rs = statement.executeQuery("SELECT * FROM  SOLVEDHARDPATTERN1");
            }
            if (patternNum == 2) {
                rs = statement.executeQuery("SELECT * FROM  SOLVEDHARDPATTERN2");
            }
            if (patternNum == 3) {
                rs = statement.executeQuery("SELECT * FROM  SOLVEDHARDPATTERN3");
            }
            if (patternNum == 4) {
                rs = statement.executeQuery("SELECT * FROM  SOLVEDHARDPATTERN4");
            }

        } catch (SQLException ex) {
            Logger.getLogger(DBEasyPatternOperations.class.getName()).log(Level.SEVERE, null, ex);
        }

        return (rs);
    }

}
