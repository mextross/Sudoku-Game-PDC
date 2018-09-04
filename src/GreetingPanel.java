
/*Name: Michelle Extross
Student ID: 1393444*/
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

//A greeting panel that welcomes the user (panel container shows this panel first)
public class GreetingPanel extends JPanel implements ActionListener {

    private JPanel labelPanel;
    private JLabel greetingLabel;
    private JPanel enterNamePanel;
    private JTextField playerNameTextField;
    private JLabel playerName;
    private Image image;
    private JButton next;
    private JButton exitButton;

    public GreetingPanel() {
        this.setLayout(new BorderLayout());

        //Top Panel
        playerName = new JLabel();
        labelPanel = new JPanel();
        labelPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        labelPanel.setBackground(Color.black);
        greetingLabel = new JLabel("Welcome to the Sudoku Challenge! Please enter you name below!");
        greetingLabel.setFont(new Font("Times", Font.ITALIC, 18));
        greetingLabel.setForeground(Color.white);
        labelPanel.add(greetingLabel);

        //Bottom Panel
        enterNamePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        exitButton = new JButton("EXIT");
        exitButton.setFont(new Font("Calibri", Font.PLAIN, 26));
        exitButton.setBackground(Color.black);
        exitButton.setForeground(Color.red);
        exitButton.addActionListener(this);
        enterNamePanel.add(exitButton);

        enterNamePanel.setBackground(Color.black);
        playerNameTextField = new JTextField("", 15);
        playerNameTextField.setHorizontalAlignment(JTextField.CENTER);
        playerNameTextField.setFont(new Font("Calibri", Font.PLAIN, 26));

        playerNameTextField.addActionListener(this);
        enterNamePanel.add(playerNameTextField);

        next = new JButton("NEXT");
        next.setFont(new Font("Calibri", Font.PLAIN, 26));
        next.setBackground(Color.black);
        next.setForeground(Color.red);
        next.addActionListener(this);
        enterNamePanel.add(next);

        this.add(labelPanel, BorderLayout.NORTH);
        this.add(enterNamePanel, BorderLayout.SOUTH);
        image = new ImageIcon("src\\Sudoku bg.jpg").getImage();

    }

    //accessor method for the panel container to get the user name inputted
    public String getPlayerNameText() {
        return playerNameTextField.getText();
    }

    //accessor method for the panel container to get and listen to the panel
    public JPanel getEnterNamePanel() {
        return enterNamePanel;
    }

    //accessor method for the panel container to allow the user to change panels and choose a level
    public JButton nextButton() {
        return next;
    }

    //sets the background of the middle panel with a public domain image
    @Override
    public void paintComponent(Graphics g) {
        g.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), null);
    }

    //allows the user to exit the program
    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == exitButton) {
            System.exit(0);
        }
    }

}
