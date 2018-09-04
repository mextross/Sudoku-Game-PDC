
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

//A class that loads Solved Easy Patterns from files into tables and queries to get corresponding patterns.
public class DBSolvedEasyPatternOperations {

    String url = "jdbc:derby://localhost:1527/PatternDB;create=true";
    String usernameDerby = "pdc";
    String passwordDerby = "pdc";
    Connection conn;

    private File solvedEasyPatternFolder;
    private File[] solvedEasyPatternFiles;
    private ArrayList<ArrayList<Integer>> solvedEasyPatternLists;
    private ArrayList<Integer> solvedEasyPatternList1;
    private ArrayList<Integer> solvedEasyPatternList2;
    private ArrayList<Integer> easyPatternList3;
    private ArrayList<Integer> easyPatternList4;

    private ArrayList<String> solvedEasyPatternListValues1;
    private ArrayList<String> solvedEasyPatternListValues2;
    private ArrayList<String> solvedEasyPatternListValues3;
    private ArrayList<String> solvedEasyPatternListValues4;
    private ArrayList<ArrayList<String>> solvedEasyPatternListValues;

    public DBSolvedEasyPatternOperations() {
        solvedEasyPatternFolder = new File("src\\Sudoku Patterns\\Solved Easy Level");
        solvedEasyPatternFiles = solvedEasyPatternFolder.listFiles();
        solvedEasyPatternLists = new ArrayList<>();
        solvedEasyPatternList1 = new ArrayList<>();
        solvedEasyPatternList2 = new ArrayList<>();
        easyPatternList3 = new ArrayList<>();
        easyPatternList4 = new ArrayList<>();
        solvedEasyPatternLists.add(solvedEasyPatternList1);
        solvedEasyPatternLists.add(solvedEasyPatternList1);
        solvedEasyPatternLists.add(solvedEasyPatternList1);
        solvedEasyPatternLists.add(solvedEasyPatternList1);

        solvedEasyPatternListValues = new ArrayList<>();
        solvedEasyPatternListValues1 = new ArrayList<>();
        solvedEasyPatternListValues2 = new ArrayList<>();
        solvedEasyPatternListValues3 = new ArrayList<>();
        solvedEasyPatternListValues4 = new ArrayList<>();

        solvedEasyPatternListValues.add(solvedEasyPatternListValues1);
        solvedEasyPatternListValues.add(solvedEasyPatternListValues2);
        solvedEasyPatternListValues.add(solvedEasyPatternListValues3);
        solvedEasyPatternListValues.add(solvedEasyPatternListValues4);

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

    //loads all solved easy patterns from text files into ArrayLists, then use the Lists to create tables
    public void loadFilesToTables() throws FileNotFoundException {
        int x = 0;
        int y = 0;
        int element = 0;
        int index = 0;
        for (int i = 0; i < solvedEasyPatternFiles.length; i++) {
            Scanner sc = new Scanner(solvedEasyPatternFiles[i]);
            while (sc.hasNextLine()) {
                String currentLine = sc.nextLine();
                StringTokenizer tk1 = new StringTokenizer(currentLine, ",");
                while (tk1.hasMoreTokens()) {
                    element = Integer.parseInt(tk1.nextToken());
                    if (i == 0) {
                        solvedEasyPatternList1.add(element);
                    }
                    if (i == 1) {
                        solvedEasyPatternList2.add(element);
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

        for (int k = 0; k < solvedEasyPatternList1.size(); k++) {
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

            solvedEasyPatternListValues1.add(String.format("%d, %d, %d, %d", index, solvedEasyPatternList1.get(k), x, y));
            solvedEasyPatternListValues2.add(String.format("%d, %d, %d, %d", index, solvedEasyPatternList2.get(k), x, y));
            solvedEasyPatternListValues3.add(String.format("%d, %d, %d, %d", index, easyPatternList3.get(k), x, y));
            solvedEasyPatternListValues4.add(String.format("%d, %d, %d, %d", index, easyPatternList4.get(k), x, y));
            index++;
        }

        createTable();

    }

    //uses the ArrayLists to create DB tables
    public void createTable() throws FileNotFoundException {

        try {
            Statement statement = conn.createStatement();
            for (int i = 0; i < solvedEasyPatternListValues.size(); i++) {
                String newTableName = "EasyPattern";

                //creates SolvedEasyPattern1 Table
                if (i == 0) {
                    newTableName = "SolvedEasyPattern" + (1);
                    String sqlCreate = "create table " + newTableName + " (RowID int,"
                            + "Element int, XCoordinate int,"
                            + "YCoordinate int)";
                    statement.executeUpdate(sqlCreate);
                    for (int j = 0; j < solvedEasyPatternListValues1.size(); j++) {
                        System.out.println(solvedEasyPatternListValues1.get(j));
                        String sqlInsert = "insert into " + newTableName + " values(" + solvedEasyPatternListValues1.get(j) + ")";

                        System.out.println(sqlInsert);

                        statement.executeUpdate(sqlInsert);
                    }
                    System.out.println(newTableName + "created");
                }

                //creates SolvedEasyPattern2 Table
                if (i == 1) {
                    newTableName = "SolvedEasyPattern" + (2);
                    String sqlCreate = "create table " + newTableName + " (RowID int,"
                            + "Element int, XCoordinate int,"
                            + "YCoordinate int)";
                    statement.executeUpdate(sqlCreate);
                    for (int j = 0; j < solvedEasyPatternListValues2.size(); j++) {
                        String sqlInsert = "insert into " + newTableName + " values(" + solvedEasyPatternListValues2.get(j) + ")";
                        // sqlInsert = String.format("insert into "+newTableName+" values(%d , %d, %d", arr[i]);

                        System.out.println(sqlInsert);

                        statement.executeUpdate(sqlInsert);
                    }
                    System.out.println(newTableName + "created");
                }

                //creates SolvedEasyPatter3 Table
                if (i == 2) {
                    newTableName = "SolvedEasyPattern" + (3);
                    String sqlCreate = "create table " + newTableName + " (RowID int,"
                            + "Element int, XCoordinate int,"
                            + "YCoordinate int)";
                    statement.executeUpdate(sqlCreate);
                    for (int j = 0; j < solvedEasyPatternListValues3.size(); j++) {
                        String sqlInsert = "insert into " + newTableName + " values(" + solvedEasyPatternListValues3.get(j) + ")";

                        System.out.println(sqlInsert);

                        statement.executeUpdate(sqlInsert);
                    }
                    System.out.println(newTableName + "created");
                }

                //creates SolvedEasyPattern4 Table
                if (i == 3) {
                    newTableName = "SolvedEasyPattern" + (4);
                    String sqlCreate = "create table " + newTableName + " (RowID int,"
                            + "Element int, XCoordinate int,"
                            + "YCoordinate int)";
                    statement.executeUpdate(sqlCreate);
                    for (int j = 0; j < solvedEasyPatternListValues4.size(); j++) {
                        String sqlInsert = "insert into " + newTableName + " values(" + solvedEasyPatternListValues4.get(j) + ")";
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
