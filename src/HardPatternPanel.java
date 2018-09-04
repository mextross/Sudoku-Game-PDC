
/*Name: Michelle Extross
Student ID: 1393444*/
import PatternFilesToTables.DBHardPatternOperations;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Objects;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.text.MaskFormatter;

//The panel that queries and draws the corresponding hard pattern chosen by the user
public class HardPatternPanel extends PatternPanel implements ActionListener {

    int patternNum;
    boolean win;

    //Top Panel
    protected JPanel levelTitlePanel;
    protected JLabel levelTitle;
    private JLabel hintLabel;

    //Middle Panel
    protected JPanel levelPatternChoicePanel;
    private DBHardPatternOperations query;
    private ArrayList<int[]> hardPattern;
    private ArrayList<int[]> solvedHardPattern;
    private ArrayList<int[]> solvedArrayValues;
    private int[] hardPatternAttributes;
    private int[] solvedHardPatternAttributes;

    private ArrayList<JComponent> componentList;
    private ArrayList<Integer> inputValuesList;
    private ArrayList<Integer> puzzleIndexes;
    private ArrayList<Integer> solvedInputValuesList;

    //Bottom Panel
    protected JPanel levelBottomPanel;
    protected JButton levelBackButton;
    protected JButton exitButton;
    protected JButton submit;

    public HardPatternPanel() {

        patternNum = 0;
        win = false;
        //LevelTitle Panel and its components
        this.setLayout(new BorderLayout());
        levelTitlePanel = new JPanel();
        levelTitlePanel.setLayout(new GridLayout(2, 1));
        levelTitlePanel.setBackground(Color.black);
        levelTitle = new JLabel();
        hintLabel = new JLabel();
        levelTitle.setFont(new Font("Times", Font.ITALIC, 20));
        levelTitle.setForeground(Color.white);
        levelTitle.setHorizontalAlignment(JLabel.CENTER);
        hintLabel.setFont(new Font("Times", Font.ITALIC, 14));
        hintLabel.setForeground(Color.green);
        hintLabel.setHorizontalAlignment(JLabel.CENTER);

        levelTitle.setText("Let's play! Please fill in the blanks with numbers 1 to 9!");
        hintLabel.setText("Hint: The correct entries will be highlighted as green when you submit!");
        levelTitlePanel.add(levelTitle);
        levelTitlePanel.add(hintLabel);
        this.add(levelTitlePanel, BorderLayout.NORTH);

        //Middle Panel
        query = new DBHardPatternOperations();
        levelPatternChoicePanel = new JPanel();
        levelPatternChoicePanel.setLayout(new GridLayout(9, 9));
        hardPatternAttributes = new int[4];
        solvedHardPatternAttributes = new int[4];
        hardPattern = new ArrayList<>();
        solvedHardPattern = new ArrayList<>();
        solvedArrayValues = new ArrayList<>();
        componentList = new ArrayList<>();
        inputValuesList = new ArrayList<>();
        puzzleIndexes = new ArrayList<>();
        solvedInputValuesList = new ArrayList<>();
        this.add(levelPatternChoicePanel, BorderLayout.CENTER);

        //bottom panel
        levelBottomPanel = new JPanel();
        levelBottomPanel.setBackground(Color.black);
        levelBackButton = new JButton("BACK");
        levelBackButton.addActionListener(this);
        levelBackButton.setBackground(Color.black);
        levelBackButton.setForeground(Color.red);
        levelBackButton.setFont(new Font("Calibri", Font.PLAIN, 16));

        exitButton = new JButton("EXIT");
        exitButton.addActionListener(this);
        exitButton.setBackground(Color.black);
        exitButton.setForeground(Color.red);
        exitButton.setFont(new Font("Calibri", Font.PLAIN, 16));

        submit = new JButton("SUBMIT");
        submit.addActionListener(this);
        submit.setBackground(Color.black);
        submit.setForeground(Color.red);
        submit.setFont(new Font("Calibri", Font.PLAIN, 16));

        levelBottomPanel.add(levelBackButton);
        levelBottomPanel.add(submit);
        levelBottomPanel.add(exitButton);

        this.add(levelBottomPanel, BorderLayout.SOUTH);
    }

    //accessor method for the panel container(allows the user to go back and choose a differnt pattern)
    @Override
    protected JButton getBackButton() {
        return levelBackButton;
    }

    //pattern number is set based on user's choice
    @Override
    public void setPatternNum(int patternNum) {
        this.patternNum = patternNum;
    }

    //used as an accessor method to query for the corresponding pattern
    @Override
    public int getPatternNum() {
        return patternNum;
    }

    //queries table for corresponding pattern
    @Override
    public void queryTableForPattern(int patternNum) throws SQLException {
        checkWithinBounds(patternNum, "patternNum");
        hardPattern.clear();
        query.establishConnection();
        ResultSet rs = query.getHardPattern(patternNum);
        while (rs.next()) {
            int rowID = rs.getInt(1);
            int element = rs.getInt(2);
            int x = rs.getInt(3);
            int y = rs.getInt(4);
            hardPatternAttributes = new int[4];
            hardPatternAttributes[0] = rowID;
            hardPatternAttributes[1] = element;
            hardPatternAttributes[2] = x;
            hardPatternAttributes[3] = y;
            hardPattern.add(hardPatternAttributes);
        }
    }

    // queries table for corresponding solved pattern
    @Override
    public void queryTableForSolvedPattern(int patternNum) throws SQLException {
        checkWithinBounds(patternNum, "patternNum");
        solvedHardPattern.clear();
        query.establishConnection();
        ResultSet rs = query.getSolvedHardPattern(patternNum);
        while (rs.next()) {
            int rowID = rs.getInt(1);
            int element = rs.getInt(2);
            int x = rs.getInt(3);
            int y = rs.getInt(4);
            solvedHardPatternAttributes = new int[4];
            solvedHardPatternAttributes[0] = rowID;
            solvedHardPatternAttributes[1] = element;
            solvedHardPatternAttributes[2] = x;
            solvedHardPatternAttributes[3] = y;
            solvedHardPattern.add(solvedHardPatternAttributes);
        }
    }

    //draws the queried pattern
    @Override
    public void drawPatternPanel() throws SQLException, ParseException {
        levelPatternChoicePanel.removeAll();
        componentList.clear();
        puzzleIndexes.clear();
        for (int[] e : hardPattern) {
            if (e[1] == 0) {
                puzzleIndexes.add(hardPattern.indexOf(e));
                MaskFormatter formatter = new MaskFormatter("#");
                JFormattedTextField jtf = new JFormattedTextField(formatter);
                componentList.add(jtf);
                jtf.setHorizontalAlignment(JTextField.CENTER);
                jtf.setFont(new Font("Calibri", Font.PLAIN, 26));
                jtf.addActionListener(this);
                levelPatternChoicePanel.add(jtf);
            } else {
                JLabel b = new JLabel();
                componentList.add(b);
                Border border = BorderFactory.createLineBorder(Color.WHITE, 2);
                b.setBorder(border);
                b.setText(Integer.toString(e[1]));
                b.setHorizontalAlignment(JLabel.CENTER);
                b.setFont(new Font("Calibri", Font.PLAIN, 26));
                b.setBackground(Color.black);
                b.setOpaque(true);
                b.setForeground(Color.red);
                levelPatternChoicePanel.add(b);
            }

        }
    }

    //temporariy saves the input values given by the user
    @Override
    public void saveInputValues() {
        inputValuesList.clear();
        for (JComponent comp : componentList) {
            if (comp instanceof JTextField) {
                int input = 0;
                String text = ((JTextField) comp).getText();
                text = text.replace(" ", "");

                if (!text.equals("")) {
                    input = Integer.parseInt(text);
                }
                inputValuesList.add(input);
            }

        }
        compareValues();
    }

    //checks if the inputted values are correct
    @Override
    public void compareValues() {

        solvedInputValuesList.clear();
        for (int i : puzzleIndexes) {
            int[] solvedArray = solvedHardPattern.get(i);
            solvedArrayValues.add(solvedArray);
            solvedInputValuesList.add(solvedArray[1]);
        }

        for (int i = 0; i < inputValuesList.size(); i++) {
            JFormattedTextField tf = null;
            if (Objects.equals(inputValuesList.get(i), solvedInputValuesList.get(i))) {
                tf = (JFormattedTextField) componentList.get(puzzleIndexes.get(i));
                tf.setBackground(Color.green);
            }
            if (!Objects.equals(inputValuesList.get(i), solvedInputValuesList.get(i))) {
                tf = (JFormattedTextField) componentList.get(puzzleIndexes.get(i));
                tf.setBackground(Color.red);
            }

        }

        win = inputValuesList.equals(solvedInputValuesList);

    }

    //checks if all the values are correct and if the user won the game
    public boolean checkWin() {

        return win;

    }

    //allows the user to submit inputted values
    public JButton getSubmitButton() {
        return submit;
    }

    //a method that calls all other relevant methods to make an HardPatternPanel
    public void makeHardPatternPanel(int patternNum) throws SQLException, ParseException {
        setPatternNum(patternNum);
        queryTableForPattern(patternNum);
        queryTableForSolvedPattern(patternNum);
        drawPatternPanel();
    }

    //allows the user to exit and to clear and create a new pattern if the user chooses an alternate pattern
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == exitButton) {
            System.exit(0);
        }

        if (e.getSource() == levelBackButton) {

            componentList.clear();
        }

    }

    //checks if the patternNum inputted is within bounds (between 1 and 4)
    private static void checkWithinBounds(int x, String name) {
        if (x < 0) {
            throw new IllegalArgumentException(name + " must not be negative");
        }
        if (x > 4) {
            throw new IllegalArgumentException(name + "cannot be greater than 4");
        }
    }

}
