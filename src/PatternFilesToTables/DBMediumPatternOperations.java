
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

//A class that loads Medium Patterns from files into tables and queries to get corresponding patterns.
public class DBMediumPatternOperations {

    String url = "jdbc:derby://localhost:1527/PatternDB;create=true";
    String usernameDerby = "pdc";
    String passwordDerby = "pdc";
    Connection conn;
    Statement st;

    private File mediumPatternFolder;
    private File[] mediumPatternFiles;
    private ArrayList<ArrayList<Integer>> mediumPatternLists;
    private ArrayList<Integer> mediumPatternList1;
    private ArrayList<Integer> mediumPatternList2;
    private ArrayList<Integer> mediumPatternList3;
    private ArrayList<Integer> mediumPatternList4;

    private ArrayList<String> mediumPatternListValues1;
    private ArrayList<String> mediumPatternListValues2;
    private ArrayList<String> mediumPatternListValues3;
    private ArrayList<String> mediumPatternListValues4;
    private ArrayList<ArrayList<String>> mediumPatternListValues;

    public DBMediumPatternOperations() {
        st = null;
        mediumPatternFolder = new File("src\\Sudoku Patterns\\Medium Level");
        mediumPatternFiles = mediumPatternFolder.listFiles();
        mediumPatternLists = new ArrayList<>();
        mediumPatternList1 = new ArrayList<>();
        mediumPatternList2 = new ArrayList<>();
        mediumPatternList3 = new ArrayList<>();
        mediumPatternList4 = new ArrayList<>();
        mediumPatternLists.add(mediumPatternList1);
        mediumPatternLists.add(mediumPatternList1);
        mediumPatternLists.add(mediumPatternList1);
        mediumPatternLists.add(mediumPatternList1);

        mediumPatternListValues = new ArrayList<>();
        mediumPatternListValues1 = new ArrayList<>();
        mediumPatternListValues2 = new ArrayList<>();
        mediumPatternListValues3 = new ArrayList<>();
        mediumPatternListValues4 = new ArrayList<>();

        mediumPatternListValues.add(mediumPatternListValues1);
        mediumPatternListValues.add(mediumPatternListValues2);
        mediumPatternListValues.add(mediumPatternListValues3);
        mediumPatternListValues.add(mediumPatternListValues4);

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

    //accessor method to get the statement
    public Statement getStatement() {
        return st;
    }

    public Connection getConnection()
    {
    return conn;
    }
    
    //loads all medium patterns from text files into ArrayLists, then use the Lists to create tables
    public void loadFilesToTables() throws FileNotFoundException {
        int x = 0;
        int y = 0;
        int element = 0;
        int index = 0;
        for (int i = 0; i < mediumPatternFiles.length; i++) {
            Scanner sc = new Scanner(mediumPatternFiles[i]);
            while (sc.hasNextLine()) {
                String currentLine = sc.nextLine();
                StringTokenizer tk1 = new StringTokenizer(currentLine, ",");
                while (tk1.hasMoreTokens()) {
                    element = Integer.parseInt(tk1.nextToken());
                    if (i == 0) {
                        mediumPatternList1.add(element);
                    }
                    if (i == 1) {
                        mediumPatternList2.add(element);
                    }
                    if (i == 2) {
                        mediumPatternList3.add(element);
                    }
                    if (i == 3) {
                        mediumPatternList4.add(element);
                    }

                }
            }
        }

        for (int k = 0; k < mediumPatternList1.size(); k++) {
            if (k > -1 && k < 5) {
                x = 0;
            }
            if (k > 4 && k < 10) {
                x = 1;
            }
            if (k > 9 && k < 15) {
                x = 2;
            }
            if (k > 14 && k < 20) {
                x = 3;
            }
            if (k > 19 && k < 25) {
                x = 4;
            }

            y = k % 5;

            mediumPatternListValues1.add(String.format("%d, %d, %d, %d", index, mediumPatternList1.get(k), x, y));
            mediumPatternListValues2.add(String.format("%d, %d, %d, %d", index, mediumPatternList2.get(k), x, y));
            mediumPatternListValues3.add(String.format("%d, %d, %d, %d", index, mediumPatternList3.get(k), x, y));
            mediumPatternListValues4.add(String.format("%d, %d, %d, %d", index, mediumPatternList4.get(k), x, y));
            index++;
        }

        createTable();
    }

    //uses the ArrayLists to create DB tables
    public void createTable() throws FileNotFoundException {
        try {
            Statement statement = conn.createStatement();
            for (int i = 0; i < mediumPatternListValues.size(); i++) {
                String newTableName = "MediumPattern";

                //creates MediumPattern1 Table
                if (i == 0) {
                    newTableName = "MediumPattern" + (1);
                    String sqlCreate = "create table " + newTableName + " (RowID int,"
                            + "Element int, XCoordinate int,"
                            + "YCoordinate int)";
                    statement.executeUpdate(sqlCreate);
                    for (int j = 0; j < mediumPatternListValues1.size(); j++) {
                        System.out.println(mediumPatternListValues1.get(j));
                        String sqlInsert = "insert into " + newTableName + " values(" + mediumPatternListValues1.get(j) + ")";
                        System.out.println(sqlInsert);

                        statement.executeUpdate(sqlInsert);
                    }
                    System.out.println(newTableName + "created");
                }

                //creates MediumPattern2 Table
                if (i == 1) {
                    newTableName = "MediumPattern" + (2);
                    String sqlCreate = "create table " + newTableName + " (RowID int,"
                            + "Element int, XCoordinate int,"
                            + "YCoordinate int)";
                    statement.executeUpdate(sqlCreate);
                    for (int j = 0; j < mediumPatternListValues2.size(); j++) {
                        String sqlInsert = "insert into " + newTableName + " values(" + mediumPatternListValues2.get(j) + ")";

                        System.out.println(sqlInsert);

                        statement.executeUpdate(sqlInsert);
                    }
                    System.out.println(newTableName + "created");
                }

                //creates MediumPattern3 Table
                if (i == 2) {
                    newTableName = "MediumPattern" + (3);
                    String sqlCreate = "create table " + newTableName + " (RowID int,"
                            + "Element int, XCoordinate int,"
                            + "YCoordinate int)";
                    statement.executeUpdate(sqlCreate);
                    for (int j = 0; j < mediumPatternListValues3.size(); j++) {
                        String sqlInsert = "insert into " + newTableName + " values(" + mediumPatternListValues3.get(j) + ")";

                        System.out.println(sqlInsert);

                        statement.executeUpdate(sqlInsert);
                    }
                    System.out.println(newTableName + "created");
                }

                //creates MediumPattern4 Table
                if (i == 3) {
                    newTableName = "MediumPattern" + (4);
                    String sqlCreate = "create table " + newTableName + " (RowID int,"
                            + "Element int, XCoordinate int,"
                            + "YCoordinate int)";
                    statement.executeUpdate(sqlCreate);
                    for (int j = 0; j < mediumPatternListValues4.size(); j++) {
                        String sqlInsert = "insert into " + newTableName + " values(" + mediumPatternListValues4.get(j) + ")";
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

    //Query to the get the ResultSet of an MediumPattern Table depending on the patternNum
    public ResultSet getMediumPattern(int patternNum) {
        ResultSet rs = null;

        try {
            System.out.println(" getting query....");
            Statement statement = conn.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            st = statement;

            if (patternNum == 1) {
                rs = statement.executeQuery("SELECT * FROM  MEDIUMPATTERN1");
            }
            if (patternNum == 2) {
                rs = statement.executeQuery("SELECT * FROM  MEDIUMPATTERN2");
            }
            if (patternNum == 3) {
                rs = statement.executeQuery("SELECT * FROM  MEDIUMPATTERN3");
            }
            if (patternNum == 4) {
                rs = statement.executeQuery("SELECT * FROM  MEDIUMPATTERN4");
            }

        } catch (SQLException ex) {
            Logger.getLogger(DBEasyPatternOperations.class.getName()).log(Level.SEVERE, null, ex);
        }

        return (rs);
    }

    //Query to the get the ResultSet of an SolvedMediumPattern Table depending on the patternNum
    public ResultSet getSolvedMediumPattern(int patternNum) {
        ResultSet rs = null;

        try {
            System.out.println(" getting query....");
            Statement statement = conn.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);

            if (patternNum == 1) {
                rs = statement.executeQuery("SELECT * FROM  SOLVEDMEDIUMPATTERN1");
            }
            if (patternNum == 2) {
                rs = statement.executeQuery("SELECT * FROM  SOLVEDMEDIUMPATTERN2");
            }
            if (patternNum == 3) {
                rs = statement.executeQuery("SELECT * FROM  SOLVEDMEDIUMPATTERN3");
            }
            if (patternNum == 4) {
                rs = statement.executeQuery("SELECT * FROM  SOLVEDMEDIUMPATTERN4");
            }

        } catch (SQLException ex) {
            Logger.getLogger(DBEasyPatternOperations.class.getName()).log(Level.SEVERE, null, ex);
        }

        return (rs);
    }

}
