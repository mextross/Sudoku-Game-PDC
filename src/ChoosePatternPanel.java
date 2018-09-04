
/*Name: Michelle Extross
Student ID: 1393444*/
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

//Allows the user to choose one of four available patterns (of any level - easy, medium or hard)
public class ChoosePatternPanel extends JPanel implements ActionListener {

    protected JPanel levelTitlePanel;
    protected JLabel levelTitle;

    protected JPanel levelPatternChoicePanel;
    protected JButton pattern1;
    protected JButton pattern2;
    protected JButton pattern3;
    protected JButton pattern4;

    protected JPanel levelBottomPanel;
    protected JButton levelBackButton;
    protected JButton exitButton;

    public ChoosePatternPanel() {

        //LevelTitle Panel and its components
        this.setLayout(new BorderLayout());
        levelTitlePanel = new JPanel();
        levelTitlePanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        levelTitlePanel.setBackground(Color.black);

        levelTitle = new JLabel();
        levelTitle.setFont(new Font("Times", Font.ITALIC, 20));
        levelTitle.setForeground(Color.white);
        levelTitlePanel.add(levelTitle);
        this.add(levelTitlePanel, BorderLayout.NORTH);

        //levelPatternPanel and its components
        levelPatternChoicePanel = new JPanel();
        levelPatternChoicePanel.setLayout(new GridLayout(2, 2));
        pattern1 = new JButton("PATTERN I");
        pattern1.setFont(new Font("Calibri", Font.PLAIN, 24));
        pattern1.setBackground(Color.black);
        pattern1.setForeground(Color.yellow);
        levelPatternChoicePanel.add(pattern1);

        pattern2 = new JButton("PATTERN II");
        pattern2.setFont(new Font("Calibri", Font.PLAIN, 24));
        pattern2.setBackground(Color.black);
        pattern2.setForeground(Color.blue);
        levelPatternChoicePanel.add(pattern2);

        pattern3 = new JButton("PATTERN III");
        pattern3.setFont(new Font("Calibri", Font.PLAIN, 24));
        pattern3.setBackground(Color.black);
        pattern3.setForeground(Color.green);
        levelPatternChoicePanel.add(pattern3);

        pattern4 = new JButton("PATTERN IV");
        pattern4.setFont(new Font("Calibri", Font.PLAIN, 24));
        pattern4.setBackground(Color.black);
        pattern4.setForeground(Color.MAGENTA);
        levelPatternChoicePanel.add(pattern4);

        this.add(levelPatternChoicePanel, BorderLayout.CENTER);

        //levelBottomPanel and its components
        levelBottomPanel = new JPanel(new BorderLayout());
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

        levelBottomPanel.add(levelBackButton, BorderLayout.WEST);
        levelBottomPanel.add(exitButton, BorderLayout.EAST);
        this.add(levelBottomPanel, BorderLayout.SOUTH);
    }

    //accessor method for the panel container(allows the user to go back and choose another difficulty)
    protected JButton getLevelBackButton() {
        return levelBackButton;
    }

    //accessor method for the panel container(allows the user to choose the 1st pattern and switch panels)
    protected JButton getPattern1Button() {
        return pattern1;
    }
    //accessor method for the panel container(allows the user to choose the 2nd pattern and switch panels)

    protected JButton getPattern2Button() {
        return pattern2;
    }

    //accessor method for the panel container(allows the user to choose the 3rd pattern and switch panels)
    protected JButton getPattern3Button() {
        return pattern3;
    }

    //accessor method for the panel container(allows the user to choose the 4th pattern and switch panels)
    protected JButton getPattern4Button() {
        return pattern4;
    }

    //allows the user to exit the program
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == exitButton) {
            System.exit(0);
        }
    }

}
