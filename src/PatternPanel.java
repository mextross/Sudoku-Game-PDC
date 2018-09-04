
/*Name: Michelle Extross
Student ID: 1393444*/
import java.sql.SQLException;
import java.text.ParseException;
import javax.swing.JButton;
import javax.swing.JPanel;

/*An abstract parent class panel that when overriden by its subclasses
queries and draws the corresponding pattern chosen by the user*/
public abstract class PatternPanel extends JPanel {

    protected abstract JButton getBackButton();//allows the user to go back and choose a differnt pattern

    public abstract void setPatternNum(int patternNum);//pattern number is set based on user's choice

    public abstract int getPatternNum();//used as an accessor method to query for the corresponding pattern

    public abstract void queryTableForPattern(int patternNum) throws SQLException;//queries table for corresponding pattern

    public abstract void queryTableForSolvedPattern(int patternNum) throws SQLException;//queries table for corresponding solved pattern

    public abstract void drawPatternPanel() throws SQLException, ParseException;//draws the queried pattern

    public abstract void saveInputValues();//temporariy saves the input values given by the user

    public abstract void compareValues();//checks if the inputted values are correct

    public abstract boolean checkWin();//checks if all the values are correct and if the user won the game

    public abstract JButton getSubmitButton();//allows the user to submit inputted values

}
