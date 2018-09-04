
/*Name: Michelle Extross
Student ID: 1393444*/
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

//Congratulates the user on winning a game and displays total score
public class CongratulationsPanel extends JPanel implements ActionListener {

    //Top Panel
    protected JPanel titlePanel;

    //Middle Panel
    protected JPanel middlePanel;
    private JLabel congrats;
    private JLabel message;
    private JLabel scoreMessage;

    //Bottom Panel
    protected JPanel bottomPanel;
    protected JButton backButton;
    protected JButton exitButton;

    public CongratulationsPanel() {

        //Title Panel and its components
        this.setLayout(new BorderLayout());
        titlePanel = new JPanel();
        titlePanel.setBackground(Color.red);

        this.add(titlePanel, BorderLayout.NORTH);

        //Middle Panel
        middlePanel = new JPanel();
        middlePanel.setBackground(Color.black);

        congrats = new JLabel();
        congrats.setFont(new Font("Times", Font.BOLD, 36));
        congrats.setForeground(Color.red);
        congrats.setBackground(Color.black);
        congrats.setOpaque(true);
        congrats.setHorizontalAlignment(JLabel.CENTER);
        congrats.setVerticalAlignment(JLabel.CENTER);

        message = new JLabel();
        message.setText("You won this game!");
        message.setFont(new Font("Times", Font.BOLD, 36));
        message.setForeground(Color.red);
        message.setBackground(Color.black);
        message.setOpaque(true);
        message.setHorizontalAlignment(JLabel.CENTER);

        scoreMessage = new JLabel();
        scoreMessage.setText("score");
        scoreMessage.setFont(new Font("Times", Font.BOLD, 36));
        scoreMessage.setForeground(Color.red);
        scoreMessage.setBackground(Color.black);
        scoreMessage.setOpaque(true);
        scoreMessage.setHorizontalAlignment(JLabel.CENTER);

        middlePanel.add(congrats);
        middlePanel.add(message);
        middlePanel.add(scoreMessage);
        this.add(middlePanel, BorderLayout.CENTER);

        //bottom panel
        bottomPanel = new JPanel();
        bottomPanel.setBackground(Color.black);
        backButton = new JButton("BACK");
        backButton.addActionListener(this);
        backButton.setBackground(Color.black);
        backButton.setForeground(Color.red);
        backButton.setFont(new Font("Calibri", Font.PLAIN, 16));

        exitButton = new JButton("EXIT");
        exitButton.addActionListener(this);
        exitButton.setBackground(Color.black);
        exitButton.setForeground(Color.red);
        exitButton.setFont(new Font("Calibri", Font.PLAIN, 16));
        bottomPanel.add(backButton);
        bottomPanel.add(exitButton);
        this.add(bottomPanel, BorderLayout.SOUTH);
    }

    //accessor method for the panel container(message contains user inputted name)
    public JLabel getCongratsMessage() {

        return congrats;
    }

    //accessor method for the panel container(total score depends on  querying the PlayerListTable)
    public JLabel getScoreMessage() {

        return scoreMessage;
    }

    //accessor method for the panel container(back button to back to the ChooseLevelPanel)
    public JButton getBackButton() {
        return backButton;
    }

    //allows the user to exit the program
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == exitButton) {
            System.exit(0);
        }
    }

}
