/*Name: Michelle Extross
Student ID: 1393444*/

import PatternFilesToTables.DBEasyPatternOperations;
import PatternFilesToTables.DBHardPatternOperations;
import PatternFilesToTables.DBMediumPatternOperations;
import org.junit.Test;
import static org.junit.Assert.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import org.junit.Assert;

//Tests for important methods for the GUI logic to function as intended
public class SudokuTests {

    @Test //tests if the QueryTableForPattern takes a negative argument
    public void testQueryTableForPatternNotNegative() throws RuntimeException, SQLException {

        try {
            EasyPatternPanel epp = new EasyPatternPanel();
            epp.queryTableForPattern(-1);
            MediumPatternPanel mpp = new MediumPatternPanel();
            mpp.queryTableForPattern(-1);
            HardPatternPanel hpp = new HardPatternPanel();
            hpp.queryTableForPattern(-1);
            fail("QueryTableForPattern should reject negative arguments");
        } catch (IllegalArgumentException | SQLException e) {
        }
    }

    @Test //tests if the QueryTableForPattern takes a parameter greater than 4(as there are only 4 patterns)
    public void testQueryTableForPatternValidParameter() throws RuntimeException, SQLException {

        try {
            EasyPatternPanel epp = new EasyPatternPanel();
            epp.queryTableForPattern(5);
            MediumPatternPanel mpp = new MediumPatternPanel();
            mpp.queryTableForPattern(6);
            HardPatternPanel hpp = new HardPatternPanel();
            hpp.queryTableForPattern(7);
            fail("QueryTableForPattern should reject arguments greater than 4");
        } catch (IllegalArgumentException | SQLException e) {
        }
    }

    @Test //tests if getEasyPattern returns the expected ResultSet. 
    public void testGetEasyPattern() throws SQLException {
        DBEasyPatternOperations easyPatterns = new DBEasyPatternOperations();
        easyPatterns.establishConnection();
        ArrayList<Integer> actual = new ArrayList<>();
        ResultSet a = easyPatterns.getEasyPattern(1);
        while (a.next()) {
            int element = a.getInt(2);
            actual.add(element);
        }
        Statement statement = easyPatterns.getStatement();
        ArrayList<Integer> expected = new ArrayList<>();
        ResultSet e = statement.executeQuery("SELECT * FROM  EASYPATTERN1");
        while (e.next()) {
            int element = e.getInt(2);
            expected.add(element);
        }
        Assert.assertEquals(expected, actual);
    }

    @Test//tests if getMediumPattern returns the expected ResultSet. 
    public void testGetMediumPattern() throws SQLException {
        DBMediumPatternOperations mediumPatterns = new DBMediumPatternOperations();
        mediumPatterns.establishConnection();
        ArrayList<Integer> actual = new ArrayList<>();
        ResultSet a = mediumPatterns.getMediumPattern(2);
        while (a.next()) {
            int element = a.getInt(2);
            actual.add(element);
        }
        Statement statement = mediumPatterns.getStatement();
        ArrayList<Integer> expected = new ArrayList<>();
        ResultSet e = statement.executeQuery("SELECT * FROM  MEDIUMPATTERN2");
        while (e.next()) {
            int element = e.getInt(2);
            expected.add(element);
        }
        Assert.assertEquals(expected, actual);
    }

    @Test//tests if getHardPattern returns the expected ResultSet.
    public void testGetHardPattern() throws SQLException {
        DBHardPatternOperations hardPatterns = new DBHardPatternOperations();
        hardPatterns.establishConnection();
        ArrayList<Integer> actual = new ArrayList<>();
        ResultSet a = hardPatterns.getHardPattern(3);
        while (a.next()) {
            int element = a.getInt(2);
            actual.add(element);
        }
        Statement statement = hardPatterns.getStatement();
        ArrayList<Integer> expected = new ArrayList<>();
        ResultSet e = statement.executeQuery("SELECT * FROM  HARDPATTERN3");
        while (e.next()) {
            int element = e.getInt(2);
            expected.add(element);
        }
        Assert.assertEquals(expected, actual);
    }

    @Test//tests if the SolvedQueryTableForPattern takes a negative argument
    public void testSolvedQueryTableForPatternNotNegative() throws RuntimeException, SQLException {

        try {
            EasyPatternPanel epp = new EasyPatternPanel();
            epp.queryTableForSolvedPattern(-1);
            MediumPatternPanel mpp = new MediumPatternPanel();
            mpp.queryTableForSolvedPattern(-1);
            HardPatternPanel hpp = new HardPatternPanel();
            hpp.queryTableForSolvedPattern(-1);
            fail("QueryTableForPattern should reject negative arguments");
        } catch (IllegalArgumentException | SQLException e) {
        }
    }

    @Test //tests if the SolvedQueryTableForPattern takes a parameter greater than 4(as there are only 4 patterns)
    public void testSolvedQueryTableForPatternValidParameter() throws RuntimeException, SQLException {

        try {
            EasyPatternPanel epp = new EasyPatternPanel();
            epp.queryTableForSolvedPattern(5);
            MediumPatternPanel mpp = new MediumPatternPanel();
            mpp.queryTableForSolvedPattern(6);
            HardPatternPanel hpp = new HardPatternPanel();
            hpp.queryTableForSolvedPattern(7);
            fail("QueryTableForPattern should reject arguments greater than 4");
        } catch (IllegalArgumentException | SQLException e) {
        }
    }
}
