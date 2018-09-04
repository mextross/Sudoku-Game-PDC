
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

//A class that loads Easy Patterns from files into tables and queries to get corresponding patterns. 
public class DBEasyPatternOperations {

    String url = "jdbc:derby://localhost:1527/PatternDB;create=true";
    String usernameDerby = "pdc";
    String passwordDerby = "pdc";
    Connection conn;
    Statement st;

    private File easyPatternFolder;
    private File[] easyPatternFiles;
    private ArrayList<ArrayList<Integer>> easyPatternLists;
    private ArrayList<Integer> easyPatternList1;
    private ArrayList<Integer> easyPatternList2;
    private ArrayList<Integer> easyPatternList3;
    private ArrayList<Integer> easyPatternList4;

    private ArrayList<String> easyPatternListValues1;
    private ArrayList<String> easyPatternListValues2;
    private ArrayList<String> easyPatternListValues3;
    private ArrayList<String> easyPatternListValues4;
    private ArrayList<ArrayList<String>> easyPatternListValues;

    public DBEasyPatternOperations() {
        st = null;
        easyPatternFolder = new File("src\\Sudoku Patterns\\Easy Level");
        easyPatternFiles = easyPatternFolder.listFiles();
        easyPatternLists = new ArrayList<>();
        easyPatternList1 = new ArrayList<>();
        easyPatternList2 = new ArrayList<>();
        easyPatternList3 = new ArrayList<>();
        easyPatternList4 = new ArrayList<>();
        easyPatternLists.add(easyPatternList1);
        easyPatternLists.add(easyPatternList1);
        easyPatternLists.add(easyPatternList1);
        easyPatternLists.add(easyPatternList1);

        easyPatternListValues = new ArrayList<>();
        easyPatternListValues1 = new ArrayList<>();
        easyPatternListValues2 = new ArrayList<>();
        easyPatternListValues3 = new ArrayList<>();
        easyPatternListValues4 = new ArrayList<>();

        easyPatternListValues.add(easyPatternListValues1);
        easyPatternListValues.add(easyPatternListValues2);
        easyPatternListValues.add(easyPatternListValues3);
        easyPatternListValues.add(easyPatternListValues4);

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

    //loads all easy patterns from text files into ArrayLists, then use the Lists to create tables
    public void loadFilesToTables() throws FileNotFoundException {
        int x = 0;
        int y = 0;
        int element = 0;
        int index = 0;
        for (int i = 0; i < easyPatternFiles.length; i++) {
            Scanner sc = new Scanner(easyPatternFiles[i]);
            while (sc.hasNextLine()) {
                String currentLine = sc.nextLine();
                StringTokenizer tk1 = new StringTokenizer(currentLine, ",");
                while (tk1.hasMoreTokens()) {
                    element = Integer.parseInt(tk1.nextToken());
                    if (i == 0) {
                        easyPatternList1.add(element);
                    }
                    if (i == 1) {
                        easyPatternList2.add(element);
                    }
                    if (i == 2) {
                        easyPatternList3.add(element);
                    }
                    if (i == 3) {
                        easyPatternList4.add(element);
                    }

                }
            }
        }

        for (int k = 0; k < easyPatternList1.size(); k++) {
            if (k > -1 && k < 3) {
                x = 0;
            }
            if (k > 2 && k < 6) {
                x = 1;
            }
            if (k > 5 && k < 9) {
                x = 2;
            }
            y = k % 3;

            easyPatternListValues1.add(String.format("%d, %d, %d, %d", index, easyPatternList1.get(k), x, y));
            easyPatternListValues2.add(String.format("%d, %d, %d, %d", index, easyPatternList2.get(k), x, y));
            easyPatternListValues3.add(String.format("%d, %d, %d, %d", index, easyPatternList3.get(k), x, y));
            easyPatternListValues4.add(String.format("%d, %d, %d, %d", index, easyPatternList4.get(k), x, y));
            index++;
        }

        createTable();

    }

    //uses the ArrayLists to create DB Tables
    public void createTable() throws FileNotFoundException {
        try {
            Statement statement = conn.createStatement();
            for (int i = 0; i < easyPatternListValues.size(); i++) {
                String newTableName = "EasyPattern";

                //creates EasyPattern1 Table
                if (i == 0) {
                    newTableName = "EasyPattern" + (1);
                    String sqlCreate = "create table " + newTableName + " (RowID int,"
                            + "Element int, XCoordinate int,"
                            + "YCoordinate int)";
                    statement.executeUpdate(sqlCreate);
                    for (int j = 0; j < easyPatternListValues1.size(); j++) {
                        System.out.println(easyPatternListValues1.get(j));
                        String sqlInsert = "insert into " + newTableName + " values(" + easyPatternListValues1.get(j) + ")";
                        System.out.println(sqlInsert);
                        statement.executeUpdate(sqlInsert);
                    }
                    System.out.println(newTableName + "created");
                }

                //creates EasyPattern2 Table
                if (i == 1) {
                    newTableName = "EasyPattern" + (2);
                    String sqlCreate = "create table " + newTableName + " (RowID int,"
                            + "Element int, XCoordinate int,"
                            + "YCoordinate int)";
                    statement.executeUpdate(sqlCreate);
                    for (int j = 0; j < easyPatternListValues2.size(); j++) {
                        String sqlInsert = "insert into " + newTableName + " values(" + easyPatternListValues2.get(j) + ")";

                        System.out.println(sqlInsert);

                        statement.executeUpdate(sqlInsert);
                    }
                    System.out.println(newTableName + "created");
                }

                //creates EasyPattern3 Table
                if (i == 2) {
                    newTableName = "EasyPattern" + (3);
                    String sqlCreate = "create table " + newTableName + " (RowID int,"
                            + "Element int, XCoordinate int,"
                            + "YCoordinate int)";
                    statement.executeUpdate(sqlCreate);
                    for (int j = 0; j < easyPatternListValues3.size(); j++) {
                        String sqlInsert = "insert into " + newTableName + " values(" + easyPatternListValues3.get(j) + ")";

                        System.out.println(sqlInsert);

                        statement.executeUpdate(sqlInsert);
                    }
                    System.out.println(newTableName + "created");
                }

                //creates EasyPattern4 Table
                if (i == 3) {
                    newTableName = "EasyPattern" + (4);
                    String sqlCreate = "create table " + newTableName + " (RowID int,"
                            + "Element int, XCoordinate int,"
                            + "YCoordinate int)";
                    statement.executeUpdate(sqlCreate);
                    for (int j = 0; j < easyPatternListValues4.size(); j++) {
                        String sqlInsert = "insert into " + newTableName + " values(" + easyPatternListValues4.get(j) + ")";
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

    //Query to the get the ResultSet of an EasyPattern Table depending on the patternNum
    public ResultSet getEasyPattern(int patternNum) {
        ResultSet rs = null;

        try {
            System.out.println(" getting query....");
            Statement statement = conn.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            st = statement;

            if (patternNum == 1) {
                rs = statement.executeQuery("SELECT * FROM  EASYPATTERN1");
            }
            if (patternNum == 2) {
                rs = statement.executeQuery("SELECT * FROM  EASYPATTERN2");
            }
            if (patternNum == 3) {
                rs = statement.executeQuery("SELECT * FROM  EASYPATTERN3");
            }
            if (patternNum == 4) {
                rs = statement.executeQuery("SELECT * FROM  EASYPATTERN4");
            }

        } catch (SQLException ex) {
            Logger.getLogger(DBEasyPatternOperations.class.getName()).log(Level.SEVERE, null, ex);
        }

        return (rs);
    }

    //Query to the get the ResultSet of an SolvedEasyPattern Table depending on the patternNum
    public ResultSet getSolvedEasyPattern(int patternNum) {
        ResultSet rs = null;

        try {
            System.out.println(" getting query....");
            Statement statement = conn.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);

            if (patternNum == 1) {
                rs = statement.executeQuery("SELECT * FROM  SOLVEDEASYPATTERN1");
            }
            if (patternNum == 2) {
                rs = statement.executeQuery("SELECT * FROM  SOLVEDEASYPATTERN2");
            }
            if (patternNum == 3) {
                rs = statement.executeQuery("SELECT * FROM  SOLVEDEASYPATTERN3");
            }
            if (patternNum == 4) {
                rs = statement.executeQuery("SELECT * FROM  SOLVEDEASYPATTERN4");
            }

        } catch (SQLException ex) {
            Logger.getLogger(DBEasyPatternOperations.class.getName()).log(Level.SEVERE, null, ex);
        }

        return (rs);
    }

}
