
/*Name: Michelle Extross
Student ID: 1393444*/
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

//A card layout panel container that controls all the other panels
public class PanelContainer extends JPanel implements ActionListener {

    //layout for the PanelContainer
    private CardLayout cl;
    private Player player;
    private static String playerName;
    private PlayerOperations playerOp;
    private static int playerScore;

    //Greeting Panel and its components
    private GreetingPanel greetingPanel;
    private JButton greetingNext;

    //ChooseLevelPanel and its components
    private ChooseLevelPanel chooseLevelPanel;
    private JButton chooseLevelBackButton;
    private JButton easyLevelButton;
    private JButton mediumLevelButton;
    private JButton hardLevelButton;

    //LevelPanel and its components
    //easy level  panel
    private ChoosePatternPanel easyLevelPanel;
    private JButton easyLevelBackButton;
    private JButton easyLevelPattern1Button;
    private JButton easyLevelPattern2Button;
    private JButton easyLevelPattern3Button;
    private JButton easyLevelPattern4Button;

    //medium level panel
    private ChoosePatternPanel mediumLevelPanel;
    private JButton mediumLevelBackButton;
    private JButton mediumLevelPattern1Button;
    private JButton mediumLevelPattern2Button;
    private JButton mediumLevelPattern3Button;
    private JButton mediumLevelPattern4Button;

    //hard level panel
    private ChoosePatternPanel hardLevelPanel;
    private JButton hardLevelBackButton;
    private JButton hardLevelPattern1Button;
    private JButton hardLevelPattern2Button;
    private JButton hardLevelPattern3Button;
    private JButton hardLevelPattern4Button;

    //PatternPanels and their components
    //Easy Pattern Panels
    private EasyPatternPanel easyPatternPanel;
    private EasyPatternPanel easySubmittedPanel;
    private JButton easyPatternBackButton;
    private JButton easyPatternSubmitButton;
    private JButton easySubmitBackButton;
    private JButton easySubmit2Button;

    //Medium Pattern Panels
    private MediumPatternPanel mediumPatternPanel;
    private MediumPatternPanel mediumSubmittedPanel;
    private JButton mediumPatternBackButton;
    private JButton mediumPatternSubmitButton;
    private JButton mediumSubmitBackButton;
    private JButton mediumSubmit2Button;

    //Hard Pattern Panels
    private HardPatternPanel hardPatternPanel;
    private HardPatternPanel hardSubmittedPanel;
    private JButton hardPatternBackButton;
    private JButton hardPatternSubmitButton;
    private JButton hardSubmitBackButton;
    private JButton hardSubmit2Button;

    //Congratulations Panel and its components
    private CongratulationsPanel congratsPanel;
    private JButton congratsBackButton;
    private JLabel congratsMessage;
    private JLabel scoreMessage;

    public PanelContainer() throws SQLException {
        cl = new CardLayout();
        this.setLayout(cl);

        //greeting Panel and its components
        player = new Player("", 0);
        playerOp = new PlayerOperations();
        playerName = player.getName();
        playerScore = player.getScore();

        greetingPanel = new GreetingPanel();
        greetingNext = greetingPanel.nextButton();
        greetingNext.addActionListener(this);

        //chooseLevelPanel and its components
        chooseLevelPanel = new ChooseLevelPanel();
        chooseLevelBackButton = chooseLevelPanel.getChooseLevelBackButton();
        chooseLevelBackButton.addActionListener(this);
        easyLevelButton = chooseLevelPanel.getEasyLevelButton();
        easyLevelButton.addActionListener(this);
        mediumLevelButton = chooseLevelPanel.getMediumLevelButton();
        mediumLevelButton.addActionListener(this);
        hardLevelButton = chooseLevelPanel.getHardLevelButton();
        hardLevelButton.addActionListener(this);

        //levelPanel and its componenets
        //EasyLevelPanel
        easyLevelPanel = new ChoosePatternPanel();
        easyLevelPanel.levelTitle.setText("Please choose your Easy Level (3x3) Pattern!");
        easyLevelPanel.pattern1.setText("EASY PATTERN I");
        easyLevelPanel.pattern2.setText("EASY PATTERN II");
        easyLevelPanel.pattern3.setText("EASY PATTERN III");
        easyLevelPanel.pattern4.setText("EASY PATTERN IV");
        easyLevelBackButton = easyLevelPanel.getLevelBackButton();
        easyLevelBackButton.addActionListener(this);

        easyLevelPattern1Button = easyLevelPanel.getPattern1Button();
        easyLevelPattern1Button.addActionListener(this);

        easyLevelPattern2Button = easyLevelPanel.getPattern2Button();
        easyLevelPattern2Button.addActionListener(this);

        easyLevelPattern3Button = easyLevelPanel.getPattern3Button();
        easyLevelPattern3Button.addActionListener(this);

        easyLevelPattern4Button = easyLevelPanel.getPattern4Button();
        easyLevelPattern4Button.addActionListener(this);

        //MediumLevelPanel
        mediumLevelPanel = new ChoosePatternPanel();
        mediumLevelPanel.levelTitle.setText("Please choose your Medium Level (5x5) Pattern!");
        mediumLevelPanel.pattern1.setText("MEDIUM PATTERN I");
        mediumLevelPanel.pattern2.setText("MEDIUM PATTERN II");
        mediumLevelPanel.pattern3.setText("MEDIUM PATTERN III");
        mediumLevelPanel.pattern4.setText("MEDIUM PATTERN IV");
        mediumLevelBackButton = mediumLevelPanel.getLevelBackButton();
        mediumLevelBackButton.addActionListener(this);

        mediumLevelPattern1Button = mediumLevelPanel.getPattern1Button();
        mediumLevelPattern1Button.addActionListener(this);

        mediumLevelPattern2Button = mediumLevelPanel.getPattern2Button();
        mediumLevelPattern2Button.addActionListener(this);

        mediumLevelPattern3Button = mediumLevelPanel.getPattern3Button();
        mediumLevelPattern3Button.addActionListener(this);

        mediumLevelPattern4Button = mediumLevelPanel.getPattern4Button();
        mediumLevelPattern4Button.addActionListener(this);

        //HardLevelPanel
        hardLevelPanel = new ChoosePatternPanel();
        hardLevelPanel.levelTitle.setText("Please choose your Hard Level (9x9) Pattern!");
        hardLevelPanel.pattern1.setText("HARD PATTERN I");
        hardLevelPanel.pattern2.setText("HARD PATTERN II");
        hardLevelPanel.pattern3.setText("HARD PATTERN III");
        hardLevelPanel.pattern4.setText("HARD PATTERN IV");
        hardLevelBackButton = hardLevelPanel.getLevelBackButton();
        hardLevelBackButton.addActionListener(this);

        hardLevelPattern1Button = hardLevelPanel.getPattern1Button();
        hardLevelPattern1Button.addActionListener(this);

        hardLevelPattern2Button = hardLevelPanel.getPattern2Button();
        hardLevelPattern2Button.addActionListener(this);

        hardLevelPattern3Button = hardLevelPanel.getPattern3Button();
        hardLevelPattern3Button.addActionListener(this);

        hardLevelPattern4Button = hardLevelPanel.getPattern4Button();
        hardLevelPattern4Button.addActionListener(this);

        //PatternPanels and their components
        //EasyPatternPanel
        easyPatternPanel = new EasyPatternPanel();
        easySubmittedPanel = new EasyPatternPanel();
        easyPatternBackButton = easyPatternPanel.getBackButton();
        easyPatternBackButton.addActionListener(this);
        easyPatternSubmitButton = easyPatternPanel.getSubmitButton();
        easyPatternSubmitButton.addActionListener(this);
        easySubmitBackButton = easySubmittedPanel.getBackButton();
        easySubmitBackButton.addActionListener(this);
        easySubmit2Button = easySubmittedPanel.getSubmitButton();
        easySubmit2Button.addActionListener(this);

        //MediumPatternPanel
        mediumPatternPanel = new MediumPatternPanel();
        mediumSubmittedPanel = new MediumPatternPanel();
        mediumPatternBackButton = mediumPatternPanel.getBackButton();
        mediumPatternBackButton.addActionListener(this);
        mediumPatternSubmitButton = mediumPatternPanel.getSubmitButton();
        mediumPatternSubmitButton.addActionListener(this);
        mediumSubmitBackButton = mediumSubmittedPanel.getBackButton();
        mediumSubmitBackButton.addActionListener(this);
        mediumSubmit2Button = mediumSubmittedPanel.getSubmitButton();
        mediumSubmit2Button.addActionListener(this);

        //HardPatternPanel
        hardPatternPanel = new HardPatternPanel();
        hardSubmittedPanel = new HardPatternPanel();
        hardPatternBackButton = hardPatternPanel.getBackButton();
        hardPatternBackButton.addActionListener(this);
        hardPatternSubmitButton = hardPatternPanel.getSubmitButton();
        hardPatternSubmitButton.addActionListener(this);
        hardSubmitBackButton = hardSubmittedPanel.getBackButton();
        hardSubmitBackButton.addActionListener(this);
        hardSubmit2Button = hardSubmittedPanel.getSubmitButton();
        hardSubmit2Button.addActionListener(this);

        //Congratulations Panel and its components
        congratsPanel = new CongratulationsPanel();
        congratsBackButton = congratsPanel.getBackButton();
        congratsBackButton.addActionListener(this);
        congratsMessage = congratsPanel.getCongratsMessage();
        scoreMessage = congratsPanel.getScoreMessage();

        //panels added to the Panel Container
        this.add(greetingPanel, "greeting panel");
        this.add(chooseLevelPanel, "choose level panel");
        this.add(easyLevelPanel, "easy level panel");
        this.add(mediumLevelPanel, "medium level panel");
        this.add(hardLevelPanel, "hard level panel");
        this.add(easyPatternPanel, "easy pattern panel");
        this.add(easySubmittedPanel, "easy submitted panel");
        this.add(mediumPatternPanel, "medium pattern panel");
        this.add(mediumSubmittedPanel, "medium submitted panel");
        this.add(hardPatternPanel, "hard pattern panel");
        this.add(hardSubmittedPanel, "hard submitted panel");
        this.add(congratsPanel, "congrats panel");
        cl.show(this, "greeting panel");
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        //Sets the player's name, the player's score, and moves to the Choose Level Panel
        if (e.getSource() == greetingNext) {
            String name = greetingPanel.getPlayerNameText();
            if (!player.playerExists(name)) {
                player = new Player(name, 0);
                playerName = player.getName();
                playerScore = player.getScore();
                try {
                    playerOp.CreateNewPlayerTableEntry(playerName, playerScore);
                } catch (SQLException ex) {
                    Logger.getLogger(PanelContainer.class.getName()).log(Level.SEVERE, null, ex);
                }
                chooseLevelPanel.getChooseLevelTitle().setText("Hi " + player.getName() + " ! Please choose a level below!");
                cl.show(this, "choose level panel");
            } else {
                playerScore = playerOp.queryPlayerScore(name);
                player = new Player(name, playerScore);

                chooseLevelPanel.getChooseLevelTitle().setText("Welcome back " + player.getName() + " ! Please choose a level below!");
                cl.show(this, "choose level panel");
            }

        }

        //goes to the choosePatternPanel to choose easy patterns
        if (e.getSource() == easyLevelButton) {
            cl.show(this, "easy level panel");
        }

        //goes to the choosePatternPanel to choose medium patterns
        if (e.getSource() == mediumLevelButton) {
            cl.show(this, "medium level panel");
        }

        //goes to the choosePatternPanel to choose hard patterns
        if (e.getSource() == hardLevelButton) {
            cl.show(this, "hard level panel");
        }

        //easy pattern buttons
        //loads and displays the 1st easy pattern
        if (e.getSource() == easyLevelPattern1Button) {
            try {
                easyPatternPanel.makeEasyPatternPanel(1);
            } catch (SQLException ex) {
                Logger.getLogger(PanelContainer.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ParseException ex) {
                Logger.getLogger(PanelContainer.class.getName()).log(Level.SEVERE, null, ex);
            }
            cl.show(this, "easy pattern panel");
        }

        //loads and displays the 2nd easy pattern
        if (e.getSource() == easyLevelPattern2Button) {

            try {
                easyPatternPanel.makeEasyPatternPanel(2);
            } catch (SQLException ex) {
                Logger.getLogger(PanelContainer.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ParseException ex) {
                Logger.getLogger(PanelContainer.class.getName()).log(Level.SEVERE, null, ex);
            }
            cl.show(this, "easy pattern panel");
        }

        //loads and displays the 3rd easy pattern
        if (e.getSource() == easyLevelPattern3Button) {

            try {
                easyPatternPanel.makeEasyPatternPanel(3);
            } catch (SQLException ex) {
                Logger.getLogger(PanelContainer.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ParseException ex) {
                Logger.getLogger(PanelContainer.class.getName()).log(Level.SEVERE, null, ex);
            }
            cl.show(this, "easy pattern panel");
        }

        //loads and displays the 4th easy pattern
        if (e.getSource() == easyLevelPattern4Button) {

            try {
                easyPatternPanel.makeEasyPatternPanel(4);
            } catch (SQLException ex) {
                Logger.getLogger(PanelContainer.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ParseException ex) {
                Logger.getLogger(PanelContainer.class.getName()).log(Level.SEVERE, null, ex);
            }
            cl.show(this, "easy pattern panel");
        }

        //checks for correct values and if the player won the easy game
        if (e.getSource() == easyPatternSubmitButton) {
            easyPatternPanel.saveInputValues();
            boolean win = easyPatternPanel.checkWin();
            if (win) {
                playerScore += 10;
                player.setScore(playerScore);
                try {
                    playerOp.ModifyPlayerTableScore(player.getName(), player.getScore());
                } catch (SQLException ex) {
                    Logger.getLogger(PanelContainer.class.getName()).log(Level.SEVERE, null, ex);
                }
                congratsMessage.setText("Congratulations " + player.getName() + "!");
                scoreMessage.setText("Your Total Score: " + player.getScore());
                cl.show(this, "congrats panel");
            } else {
                JOptionPane.showMessageDialog(this,
                        "Please Try Again!");
                int patternnum = easyPatternPanel.getPatternNum();
                try {
                    easySubmittedPanel.makeEasyPatternPanel(patternnum);
                } catch (SQLException ex) {
                    Logger.getLogger(PanelContainer.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ParseException ex) {
                    Logger.getLogger(PanelContainer.class.getName()).log(Level.SEVERE, null, ex);
                }
                cl.show(this, "easy submitted panel");
            }
        }

        //another easyPatternPanel to switch to if player did not win
        if (e.getSource() == easySubmit2Button) {
            easySubmittedPanel.saveInputValues();
            boolean win = easySubmittedPanel.checkWin();
            if (win) {
                playerScore += 10;
                player.setScore(playerScore);
                try {
                    playerOp.ModifyPlayerTableScore(player.getName(), player.getScore());
                } catch (SQLException ex) {
                    Logger.getLogger(PanelContainer.class.getName()).log(Level.SEVERE, null, ex);
                }
                congratsMessage.setText("Congratulations " + player.getName() + "!");
                scoreMessage.setText("Your Total Score: " + player.getScore());
                cl.show(this, "congrats panel");
            } else {
                JOptionPane.showMessageDialog(this,
                        "Please Try Again!");
                int patternnum = easySubmittedPanel.getPatternNum();
                try {
                    easyPatternPanel.makeEasyPatternPanel(patternnum);
                } catch (SQLException ex) {
                    Logger.getLogger(PanelContainer.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ParseException ex) {
                    Logger.getLogger(PanelContainer.class.getName()).log(Level.SEVERE, null, ex);
                }
                cl.show(this, "easy pattern panel");
            }
        }

        //medium pattern buttons
        //loads and displays the 1st medium pattern
        if (e.getSource() == mediumLevelPattern1Button) {
            try {
                mediumPatternPanel.makeMediumPatternPanel(1);
            } catch (SQLException ex) {
                Logger.getLogger(PanelContainer.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ParseException ex) {
                Logger.getLogger(PanelContainer.class.getName()).log(Level.SEVERE, null, ex);
            }
            cl.show(this, "medium pattern panel");
        }
        //loads and displays the 2nd medium pattern
        if (e.getSource() == mediumLevelPattern2Button) {

            try {
                mediumPatternPanel.makeMediumPatternPanel(2);
            } catch (SQLException ex) {
                Logger.getLogger(PanelContainer.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ParseException ex) {
                Logger.getLogger(PanelContainer.class.getName()).log(Level.SEVERE, null, ex);
            }
            cl.show(this, "medium pattern panel");
        }

        //loads and displays the 3rd medium pattern
        if (e.getSource() == mediumLevelPattern3Button) {

            try {
                mediumPatternPanel.makeMediumPatternPanel(3);
            } catch (SQLException ex) {
                Logger.getLogger(PanelContainer.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ParseException ex) {
                Logger.getLogger(PanelContainer.class.getName()).log(Level.SEVERE, null, ex);
            }
            cl.show(this, "medium pattern panel");
        }

        //loads and displays the 4th medium pattern
        if (e.getSource() == mediumLevelPattern4Button) {
            try {
                mediumPatternPanel.makeMediumPatternPanel(4);
            } catch (SQLException ex) {
                Logger.getLogger(PanelContainer.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ParseException ex) {
                Logger.getLogger(PanelContainer.class.getName()).log(Level.SEVERE, null, ex);
            }
            cl.show(this, "medium pattern panel");
        }

        //checks for correct values and if the player won the medium game
        if (e.getSource() == mediumPatternSubmitButton) {
            mediumPatternPanel.saveInputValues();
            boolean win = mediumPatternPanel.checkWin();
            if (win) {
                playerScore += 20;
                congratsMessage.setText("Congratulations " + playerName + "!");
                scoreMessage.setText("Your Total Score: " + playerScore);
                cl.show(this, "congrats panel");
            } else {
                JOptionPane.showMessageDialog(this,
                        "Please Try Again!");
                int patternnum = mediumPatternPanel.getPatternNum();
                try {
                    mediumSubmittedPanel.makeMediumPatternPanel(patternnum);
                } catch (SQLException ex) {
                    Logger.getLogger(PanelContainer.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ParseException ex) {
                    Logger.getLogger(PanelContainer.class.getName()).log(Level.SEVERE, null, ex);
                }
                cl.show(this, "medium submitted panel");
            }
        }

        //another mediumPatternPanel to switch to if player did not win
        if (e.getSource() == mediumSubmit2Button) {
            mediumSubmittedPanel.saveInputValues();
            boolean win = mediumSubmittedPanel.checkWin();
            if (win) {
                playerScore += 20;
                congratsMessage.setText("Congratulations " + playerName + "!");
                scoreMessage.setText("Your Total Score: " + playerScore);
                cl.show(this, "congrats panel");
            } else {
                JOptionPane.showMessageDialog(this,
                        "Please Try Again!");
                int patternnum = mediumSubmittedPanel.getPatternNum();
                try {
                    mediumPatternPanel.makeMediumPatternPanel(patternnum);
                } catch (SQLException ex) {
                    Logger.getLogger(PanelContainer.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ParseException ex) {
                    Logger.getLogger(PanelContainer.class.getName()).log(Level.SEVERE, null, ex);
                }
                cl.show(this, "medium pattern panel");
            }
        }

        //hard pattern buttons
        //loads and displays the 1st hard pattern
        if (e.getSource() == hardLevelPattern1Button) {
            try {
                hardPatternPanel.makeHardPatternPanel(1);
            } catch (SQLException ex) {
                Logger.getLogger(PanelContainer.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ParseException ex) {
                Logger.getLogger(PanelContainer.class.getName()).log(Level.SEVERE, null, ex);
            }
            cl.show(this, "hard pattern panel");
        }
        //loads and displays the 2nd hard pattern
        if (e.getSource() == hardLevelPattern2Button) {

            try {
                hardPatternPanel.makeHardPatternPanel(2);
            } catch (SQLException ex) {
                Logger.getLogger(PanelContainer.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ParseException ex) {
                Logger.getLogger(PanelContainer.class.getName()).log(Level.SEVERE, null, ex);
            }
            cl.show(this, "hard pattern panel");
        }

        //loads and displays the 3rd hard pattern
        if (e.getSource() == hardLevelPattern3Button) {
            try {
                hardPatternPanel.makeHardPatternPanel(3);
            } catch (SQLException ex) {
                Logger.getLogger(PanelContainer.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ParseException ex) {
                Logger.getLogger(PanelContainer.class.getName()).log(Level.SEVERE, null, ex);
            }
            cl.show(this, "hard pattern panel");
        }

        //loads and displays the 4th hard pattern
        if (e.getSource() == hardLevelPattern4Button) {
            try {
                hardPatternPanel.makeHardPatternPanel(4);
            } catch (SQLException ex) {
                Logger.getLogger(PanelContainer.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ParseException ex) {
                Logger.getLogger(PanelContainer.class.getName()).log(Level.SEVERE, null, ex);
            }
            cl.show(this, "hard pattern panel");
        }

        //checks for correct values and if the player won the medium game
        if (e.getSource() == hardPatternSubmitButton) {
            hardPatternPanel.saveInputValues();
            boolean win = hardPatternPanel.checkWin();
            if (win) {
                playerScore += 50;
                congratsMessage.setText("Congratulations " + playerName + "!");
                scoreMessage.setText("Your Total Score: " + playerScore);
                cl.show(this, "congrats panel");
            } else {
                JOptionPane.showMessageDialog(this,
                        "Please Try Again!");
                int patternnum = hardPatternPanel.getPatternNum();
                try {
                    hardSubmittedPanel.makeHardPatternPanel(patternnum);
                } catch (SQLException ex) {
                    Logger.getLogger(PanelContainer.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ParseException ex) {
                    Logger.getLogger(PanelContainer.class.getName()).log(Level.SEVERE, null, ex);
                }
                cl.show(this, "hard submitted panel");
            }
        }

        //another hardPatternPanel to switch to if player did not win
        if (e.getSource() == hardSubmit2Button) {
            hardSubmittedPanel.saveInputValues();
            boolean win = hardSubmittedPanel.checkWin();
            if (win) {
                playerScore += 50;
                congratsMessage.setText("Congratulations " + playerName + "!");
                scoreMessage.setText("Your Total Score: " + playerScore);
                cl.show(this, "congrats panel");
            } else {
                JOptionPane.showMessageDialog(this,
                        "Please Try Again!");
                int patternnum = hardSubmittedPanel.getPatternNum();
                try {
                    hardPatternPanel.makeHardPatternPanel(patternnum);
                } catch (SQLException ex) {
                    Logger.getLogger(PanelContainer.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ParseException ex) {
                    Logger.getLogger(PanelContainer.class.getName()).log(Level.SEVERE, null, ex);
                }
                cl.show(this, "hard pattern panel");
            }
        }
        //all the back buttons
        if (e.getSource() == chooseLevelBackButton) {
            cl.show(this, "greeting panel");
        }
        if (e.getSource() == easyLevelBackButton) {
            cl.show(this, "choose level panel");
        }
        if (e.getSource() == mediumLevelBackButton) {
            cl.show(this, "choose level panel");
        }
        if (e.getSource() == hardLevelBackButton) {
            cl.show(this, "choose level panel");
        }
        if (e.getSource() == easyPatternBackButton) {
            cl.show(this, "easy level panel");
        }
        if (e.getSource() == easySubmitBackButton) {
            cl.show(this, "easy level panel");
        }
        if (e.getSource() == mediumPatternBackButton) {
            cl.show(this, "medium level panel");
        }
        if (e.getSource() == mediumSubmitBackButton) {
            cl.show(this, "medium level panel");
        }
        if (e.getSource() == hardPatternBackButton) {
            cl.show(this, "hard level panel");
        }
        if (e.getSource() == hardSubmitBackButton) {
            cl.show(this, "hard level panel");
        }
        if (e.getSource() == congratsBackButton) {
            cl.show(this, "choose level panel");
        }

    }

}
