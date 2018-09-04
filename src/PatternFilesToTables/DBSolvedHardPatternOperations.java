
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
public class DBSolvedHardPatternOperations {

    String url = "jdbc:derby://localhost:1527/PatternDB;create=true";

    String usernameDerby = "pdc";
    String passwordDerby = "pdc";
    Connection conn;

    private File solvedHardPatternFolder;
    private File[] solvedHardPatternFiles;
    private ArrayList<ArrayList<Integer>> solvedHardPatternLists;
    private ArrayList<Integer> solvedHardPatternList1;
    private ArrayList<Integer> solvedHardPatternList2;
    private ArrayList<Integer> solvedHardPatternList3;
    private ArrayList<Integer> solvedHardPatternList4;

    private ArrayList<String> solvedHardPatternListValues1;
    private ArrayList<String> solvedHardPatternListValues2;
    private ArrayList<String> solvedHardPatternListValues3;
    private ArrayList<String> solvedHardPatternListValues4;
    private ArrayList<ArrayList<String>> solvedHardPatternListValues;

    public DBSolvedHardPatternOperations() {
        solvedHardPatternFolder = new File("src\\Sudoku Patterns\\Solved Hard Level");
        solvedHardPatternFiles = solvedHardPatternFolder.listFiles();
        solvedHardPatternLists = new ArrayList<>();
        solvedHardPatternList1 = new ArrayList<>();
        solvedHardPatternList2 = new ArrayList<>();
        solvedHardPatternList3 = new ArrayList<>();
        solvedHardPatternList4 = new ArrayList<>();
        solvedHardPatternLists.add(solvedHardPatternList1);
        solvedHardPatternLists.add(solvedHardPatternList1);
        solvedHardPatternLists.add(solvedHardPatternList1);
        solvedHardPatternLists.add(solvedHardPatternList1);

        solvedHardPatternListValues = new ArrayList<>();
        solvedHardPatternListValues1 = new ArrayList<>();
        solvedHardPatternListValues2 = new ArrayList<>();
        solvedHardPatternListValues3 = new ArrayList<>();
        solvedHardPatternListValues4 = new ArrayList<>();

        solvedHardPatternListValues.add(solvedHardPatternListValues1);
        solvedHardPatternListValues.add(solvedHardPatternListValues2);
        solvedHardPatternListValues.add(solvedHardPatternListValues3);
        solvedHardPatternListValues.add(solvedHardPatternListValues4);

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

    //loads all solved hard patterns from text files into ArrayLists, then use the Lists to create tables
    public void loadFilesToTables() throws FileNotFoundException {
        int x = 0;
        int y = 0;
        int element = 0;
        int index = 0;
        for (int i = 0; i < solvedHardPatternFiles.length; i++) {
            Scanner sc = new Scanner(solvedHardPatternFiles[i]);
            while (sc.hasNextLine()) {
                String currentLine = sc.nextLine();
                StringTokenizer tk1 = new StringTokenizer(currentLine, ",");
                while (tk1.hasMoreTokens()) {
                    element = Integer.parseInt(tk1.nextToken());
                    if (i == 0) {
                        solvedHardPatternList1.add(element);
                    }
                    if (i == 1) {
                        solvedHardPatternList2.add(element);
                    }
                    if (i == 2) {
                        solvedHardPatternList3.add(element);
                    }
                    if (i == 3) {
                        solvedHardPatternList4.add(element);
                    }

                }
            }
        }

        for (int k = 0; k < solvedHardPatternList1.size(); k++) {
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

            solvedHardPatternListValues1.add(String.format("%d, %d, %d, %d", index, solvedHardPatternList1.get(k), x, y));
            solvedHardPatternListValues2.add(String.format("%d, %d, %d, %d", index, solvedHardPatternList2.get(k), x, y));
            solvedHardPatternListValues3.add(String.format("%d, %d, %d, %d", index, solvedHardPatternList3.get(k), x, y));
            solvedHardPatternListValues4.add(String.format("%d, %d, %d, %d", index, solvedHardPatternList4.get(k), x, y));
            index++;
        }

        createTable();

    }

    //uses the ArrayLists to create DB tables
    public void createTable() throws FileNotFoundException {

        try {
            Statement statement = conn.createStatement();
            for (int i = 0; i < solvedHardPatternListValues.size(); i++) {
                String newTableName = "SolvedHardPattern";

                //creates SolvedHardPattern1 Table
                if (i == 0) {
                    newTableName = "SolvedHardPattern" + (1);
                    String sqlCreate = "create table " + newTableName + " (RowID int,"
                            + "Element int, XCoordinate int,"
                            + "YCoordinate int)";
                    statement.executeUpdate(sqlCreate);
                    for (int j = 0; j < solvedHardPatternListValues1.size(); j++) {
                        System.out.println(solvedHardPatternListValues1.get(j));
                        String sqlInsert = "insert into " + newTableName + " values(" + solvedHardPatternListValues1.get(j) + ")";
                        System.out.println(sqlInsert);
                        statement.executeUpdate(sqlInsert);
                    }
                    System.out.println(newTableName + "created");
                }

                //creates SolvedHardPattern2 Table
                if (i == 1) {
                    newTableName = "SolvedHardPattern" + (2);
                    String sqlCreate = "create table " + newTableName + " (RowID int,"
                            + "Element int, XCoordinate int,"
                            + "YCoordinate int)";
                    statement.executeUpdate(sqlCreate);
                    for (int j = 0; j < solvedHardPatternListValues2.size(); j++) {
                        String sqlInsert = "insert into " + newTableName + " values(" + solvedHardPatternListValues2.get(j) + ")";

                        System.out.println(sqlInsert);

                        statement.executeUpdate(sqlInsert);
                    }
                    System.out.println(newTableName + "created");
                }

                //creates SolvedHardPattern3 Table
                if (i == 2) {
                    newTableName = "SolvedHardPattern" + (3);
                    String sqlCreate = "create table " + newTableName + " (RowID int,"
                            + "Element int, XCoordinate int,"
                            + "YCoordinate int)";
                    statement.executeUpdate(sqlCreate);
                    for (int j = 0; j < solvedHardPatternListValues3.size(); j++) {
                        String sqlInsert = "insert into " + newTableName + " values(" + solvedHardPatternListValues3.get(j) + ")";

                        System.out.println(sqlInsert);

                        statement.executeUpdate(sqlInsert);
                    }
                    System.out.println(newTableName + "created");
                }

                //creates SolvedHardPattern4 Table
                if (i == 3) {
                    newTableName = "SolvedHardPattern" + (4);
                    String sqlCreate = "create table " + newTableName + " (RowID int,"
                            + "Element int, XCoordinate int,"
                            + "YCoordinate int)";
                    statement.executeUpdate(sqlCreate);
                    for (int j = 0; j < solvedHardPatternListValues4.size(); j++) {
                        String sqlInsert = "insert into " + newTableName + " values(" + solvedHardPatternListValues4.get(j) + ")";
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
