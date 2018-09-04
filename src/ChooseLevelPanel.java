
/*Name: Michelle Extross
Student ID: 1393444*/
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

//The panel that allows the user to choose a level difficulty (Easy, Medium or Hard)
public class ChooseLevelPanel extends JPanel implements ActionListener {

    private JLabel chooseLevelTitle;
    private JPanel chooseLevelTitlePanel;
    private ButtonPanel chooseLevelButtonPanel;
    private JPanel chooseLevelBottomPanel;

    private JButton easyLevel;
    private JButton mediumLevel;
    private JButton hardLevel;

    private JButton chooseLevelBackButton;
    private JButton exitButton;
    private Image woodenFrame;

    public ChooseLevelPanel() {

        this.setLayout(new BorderLayout());

        //ChooseLevelTitle Panel and its components
        chooseLevelTitlePanel = new JPanel();
        chooseLevelTitlePanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        chooseLevelTitlePanel.setBackground(Color.black);

        chooseLevelTitle = new JLabel();
        chooseLevelTitle.setFont(new Font("Times", Font.ITALIC, 20));
        chooseLevelTitle.setForeground(Color.white);
        chooseLevelTitlePanel.add(chooseLevelTitle);
        this.add(chooseLevelTitlePanel, BorderLayout.NORTH);

        //ChooseLevelTitle ButtonPanel and its components
        chooseLevelButtonPanel = new ButtonPanel();
        chooseLevelButtonPanel.setLayout(new GridLayout(0, 1, 10, 30));
        chooseLevelButtonPanel.setBorder(new EmptyBorder(60, 120, 60, 120));
        chooseLevelButtonPanel.setBackground(Color.DARK_GRAY);
        woodenFrame = new ImageIcon("src\\woodenFrame.jpg").getImage();

        //Button initialisation and formatting. 
        easyLevel = new JButton("EASY LEVEL");
        easyLevel.setFont(new Font("Calibri", Font.PLAIN, 24));
        easyLevel.setBackground(Color.black);
        easyLevel.setForeground(Color.red);
        mediumLevel = new JButton("MEDIUM LEVEL");
        mediumLevel.setFont(new Font("Calibri", Font.PLAIN, 24));
        mediumLevel.setBackground(Color.black);
        mediumLevel.setForeground(Color.red);
        hardLevel = new JButton("HARD");
        hardLevel = new JButton("HARD LEVEL");
        hardLevel.setFont(new Font("Calibri", Font.PLAIN, 24));
        hardLevel.setBackground(Color.black);
        hardLevel.setForeground(Color.red);
        chooseLevelButtonPanel.add(easyLevel);
        chooseLevelButtonPanel.add(mediumLevel);
        chooseLevelButtonPanel.add(hardLevel);
        this.add(chooseLevelButtonPanel, BorderLayout.CENTER);

        //Bootom panel layout and buttons formatting
        chooseLevelBottomPanel = new JPanel(new BorderLayout());
        chooseLevelBottomPanel.setBackground(Color.black);
        chooseLevelBackButton = new JButton("BACK");
        chooseLevelBackButton.addActionListener(this);
        chooseLevelBackButton.setBackground(Color.black);
        chooseLevelBackButton.setForeground(Color.red);
        chooseLevelBackButton.setFont(new Font("Calibri", Font.PLAIN, 16));

        exitButton = new JButton("EXIT");
        exitButton.addActionListener(this);
        exitButton.setBackground(Color.black);
        exitButton.setForeground(Color.red);
        exitButton.setFont(new Font("Calibri", Font.PLAIN, 16));

        chooseLevelBottomPanel.add(chooseLevelBackButton, BorderLayout.WEST);
        chooseLevelBottomPanel.add(exitButton, BorderLayout.EAST);
        this.add(chooseLevelBottomPanel, BorderLayout.SOUTH);

    }

    //accessor method for the panel container(title contains user inputted name)
    public JLabel getChooseLevelTitle() {
        return chooseLevelTitle;
    }

    //accessor method for the panel container(back button to go to the greeting panel)
    public JButton getChooseLevelBackButton() {
        return chooseLevelBackButton;
    }

    //accessor method for the panel container(EasyLevelButton to go to the ChoosePatternPanel)
    public JButton getEasyLevelButton() {
        return easyLevel;
    }

    //accessor method for the panel container(MediumLevelButton to go to the ChoosePatternPanel)
    public JButton getMediumLevelButton() {
        return mediumLevel;
    }
    //accessor method for the panel container(HardLevelButton to go to the ChoosePatternPanel)

    public JButton getHardLevelButton() {
        return hardLevel;
    }

    //allows the user to exit the program
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == exitButton) {
            System.exit(0);
        }
    }

    //sets the background of the button panel with a public domain image
    private class ButtonPanel extends JPanel {

        @Override
        public void paintComponent(Graphics g) {
            g.drawImage(woodenFrame, 0, 0, this.getWidth(), this.getHeight(), null);
        }

    }

}
