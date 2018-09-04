
/*Name: Michelle Extross
Student ID: 1393444*/
package PatternFilesToTables;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

//A class that loads Solved Medium Patterns from files into tables and queries to get corresponding patterns.
public class DBSolvedMediumPatternOperations {

    String url = "jdbc:derby://localhost:1527/PatternDB;create=true";
    String usernameDerby = "pdc";
    String passwordDerby = "pdc";
    Connection conn;

    private File solvedMediumPatternFolder;
    private File[] solvedMediumPatternFiles;
    private ArrayList<ArrayList<Integer>> solvedMediumPatternLists;
    private ArrayList<Integer> solvedMediumPatternList1;
    private ArrayList<Integer> solvedMediumPatternList2;
    private ArrayList<Integer> solvedMediumPatternList3;
    private ArrayList<Integer> solvedMediumPatternList4;

    private ArrayList<String> solvedMediumPatternListValues1;
    private ArrayList<String> solvedMediumPatternListValues2;
    private ArrayList<String> solvedMediumPatternListValues3;
    private ArrayList<String> solvedMediumPatternListValues4;
    private ArrayList<ArrayList<String>> solvedMediumPatternListValues;

    public DBSolvedMediumPatternOperations() {
        solvedMediumPatternFolder = new File("src\\Sudoku Patterns\\Solved Medium Level");
        solvedMediumPatternFiles = solvedMediumPatternFolder.listFiles();
        solvedMediumPatternLists = new ArrayList<>();
        solvedMediumPatternList1 = new ArrayList<>();
        solvedMediumPatternList2 = new ArrayList<>();
        solvedMediumPatternList3 = new ArrayList<>();
        solvedMediumPatternList4 = new ArrayList<>();
        solvedMediumPatternLists.add(solvedMediumPatternList1);
        solvedMediumPatternLists.add(solvedMediumPatternList1);
        solvedMediumPatternLists.add(solvedMediumPatternList1);
        solvedMediumPatternLists.add(solvedMediumPatternList1);

        solvedMediumPatternListValues = new ArrayList<>();
        solvedMediumPatternListValues1 = new ArrayList<>();
        solvedMediumPatternListValues2 = new ArrayList<>();
        solvedMediumPatternListValues3 = new ArrayList<>();
        solvedMediumPatternListValues4 = new ArrayList<>();

        solvedMediumPatternListValues.add(solvedMediumPatternListValues1);
        solvedMediumPatternListValues.add(solvedMediumPatternListValues2);
        solvedMediumPatternListValues.add(solvedMediumPatternListValues3);
        solvedMediumPatternListValues.add(solvedMediumPatternListValues4);

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

    //loads all solved medium patterns from text files into ArrayLists, then use the Lists to create tables
    public void loadFilesToTables() throws FileNotFoundException {
        int x = 0;
        int y = 0;
        int element = 0;
        int index = 0;
        for (int i = 0; i < solvedMediumPatternFiles.length; i++) {
            Scanner sc = new Scanner(solvedMediumPatternFiles[i]);
            while (sc.hasNextLine()) {
                String currentLine = sc.nextLine();
                StringTokenizer tk1 = new StringTokenizer(currentLine, ",");
                while (tk1.hasMoreTokens()) {
                    element = Integer.parseInt(tk1.nextToken());
                    if (i == 0) {
                        solvedMediumPatternList1.add(element);
                    }
                    if (i == 1) {
                        solvedMediumPatternList2.add(element);
                    }
                    if (i == 2) {
                        solvedMediumPatternList3.add(element);
                    }
                    if (i == 3) {
                        solvedMediumPatternList4.add(element);
                    }

                }
            }
        }

        for (int k = 0; k < solvedMediumPatternList1.size(); k++) {
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

            solvedMediumPatternListValues1.add(String.format("%d, %d, %d, %d", index, solvedMediumPatternList1.get(k), x, y));
            solvedMediumPatternListValues2.add(String.format("%d, %d, %d, %d", index, solvedMediumPatternList2.get(k), x, y));
            solvedMediumPatternListValues3.add(String.format("%d, %d, %d, %d", index, solvedMediumPatternList3.get(k), x, y));
            solvedMediumPatternListValues4.add(String.format("%d, %d, %d, %d", index, solvedMediumPatternList4.get(k), x, y));
            index++;
        }

        createTable();

    }

    //uses the ArrayLists to create DB tables
    public void createTable() throws FileNotFoundException {

        try {
            Statement statement = conn.createStatement();
            for (int i = 0; i < solvedMediumPatternListValues.size(); i++) {
                String newTableName = "SolvedMediumPattern";

                //creates SolvedMediumPattern1 Table
                if (i == 0) {
                    newTableName = "SolvedMediumPattern" + (1);
                    String sqlCreate = "create table " + newTableName + " (RowID int,"
                            + "Element int, XCoordinate int,"
                            + "YCoordinate int)";
                    statement.executeUpdate(sqlCreate);
                    for (int j = 0; j < solvedMediumPatternListValues1.size(); j++) {
                        System.out.println(solvedMediumPatternListValues1.get(j));
                        String sqlInsert = "insert into " + newTableName + " values(" + solvedMediumPatternListValues1.get(j) + ")";
                        System.out.println(sqlInsert);
                        statement.executeUpdate(sqlInsert);
                    }
                    System.out.println(newTableName + "created");
                }

                //creates SolvedMediumPattern2 Table
                if (i == 1) {
                    newTableName = "SolvedMediumPattern" + (2);
                    String sqlCreate = "create table " + newTableName + " (RowID int,"
                            + "Element int, XCoordinate int,"
                            + "YCoordinate int)";
                    statement.executeUpdate(sqlCreate);
                    for (int j = 0; j < solvedMediumPatternListValues2.size(); j++) {
                        String sqlInsert = "insert into " + newTableName + " values(" + solvedMediumPatternListValues2.get(j) + ")";

                        System.out.println(sqlInsert);

                        statement.executeUpdate(sqlInsert);
                    }
                    System.out.println(newTableName + "created");
                }

                //creates SolvedMediumPattern3 Table
                if (i == 2) {
                    newTableName = "SolvedMediumPattern" + (3);
                    String sqlCreate = "create table " + newTableName + " (RowID int,"
                            + "Element int, XCoordinate int,"
                            + "YCoordinate int)";
                    statement.executeUpdate(sqlCreate);
                    for (int j = 0; j < solvedMediumPatternListValues3.size(); j++) {
                        String sqlInsert = "insert into " + newTableName + " values(" + solvedMediumPatternListValues3.get(j) + ")";

                        System.out.println(sqlInsert);

                        statement.executeUpdate(sqlInsert);
                    }
                    System.out.println(newTableName + "created");
                }

                //creates SolvedMediumPattern4 Table
                if (i == 3) {
                    newTableName = "SolvedMediumPattern" + (4);
                    String sqlCreate = "create table " + newTableName + " (RowID int,"
                            + "Element int, XCoordinate int,"
                            + "YCoordinate int)";
                    statement.executeUpdate(sqlCreate);
                    for (int j = 0; j < solvedMediumPatternListValues4.size(); j++) {
                        String sqlInsert = "insert into " + newTableName + " values(" + solvedMediumPatternListValues4.get(j) + ")";
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

}
