import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class YahtzeeView1 extends JFrame implements YahtzeeView {

    // Variables needed by view
    private YahtzeeModel model;
    private JButton rollBtn = new JButton("Roll");
    private JButton[] scoreSheetLbl;
    private JLabel[] scoreSheetResults;
    private JButton[] dice;
    private JPanel rollingArea = new JPanel();
    private JPanel scoreSheet = new JPanel();
    private JPanel diceScreen = new JPanel();
    private JButton newGame = new JButton("New Game");

    public YahtzeeView1(YahtzeeModel model) {
        this.model = model;

        //set length of arrays
        this.scoreSheetLbl = new JButton[YahtzeeModel1.numberOfScores];
        this.scoreSheetResults = new JLabel[YahtzeeModel1.numberOfScores];
        this.dice = new JButton[YahtzeeModel1.numberOfDice];

        // initialize components, only roll button should be enabled to start
        for (int i = 0; i < this.dice.length; i++) {
            this.dice[i] = new JButton();
            this.dice[i].setEnabled(false);
            this.dice[i].setOpaque(true);
            this.dice[i].setBackground(Color.green);
        }

        this.setUpScoreSheet();

        for (int i = 0; i < this.scoreSheetResults.length; i++) {
            this.scoreSheetResults[i] = new JLabel();
            this.scoreSheetLbl[i].setEnabled(false);
        }

        //add components to panels and frame
        this.rollingArea.setLayout(new BorderLayout());
        this.scoreSheet
                .setLayout(new GridLayout(this.scoreSheetResults.length, 2));
        this.diceScreen.setLayout(new GridLayout(1, this.dice.length));
        this.setLayout(new FlowLayout());

        for (int i = 0; i < this.dice.length; i++) {
            this.diceScreen.add(this.dice[i]);
        }

        this.rollingArea.add(this.rollBtn, BorderLayout.PAGE_START);
        this.rollingArea.add(this.diceScreen, BorderLayout.CENTER);
        this.rollingArea.add(this.newGame, BorderLayout.PAGE_END);

        for (int i = 0; i < this.scoreSheetResults.length; i++) {
            this.scoreSheet.add(this.scoreSheetLbl[i]);
            this.scoreSheet.add(this.scoreSheetResults[i]);
        }

        this.add(this.rollingArea);
        this.add(this.scoreSheet);

        DieGraphics die = new DieGraphics(0, 0, 5);
        this.add(die);

        this.pack();

    }

    // write out labels to score sheet
    private void setUpScoreSheet() {
        this.scoreSheetLbl[0] = new JButton("Aces");
        this.scoreSheetLbl[1] = new JButton("Twos");
        this.scoreSheetLbl[2] = new JButton("Threes");
        this.scoreSheetLbl[3] = new JButton("Fours");
        this.scoreSheetLbl[4] = new JButton("Fives");
        this.scoreSheetLbl[5] = new JButton("Sixes");
        this.scoreSheetLbl[6] = new JButton("Total Score");
        this.scoreSheetLbl[7] = new JButton("Bonus");
        this.scoreSheetLbl[8] = new JButton("Total");
        this.scoreSheetLbl[9] = new JButton("3 of a kind");
        this.scoreSheetLbl[10] = new JButton("4 of a kind");
        this.scoreSheetLbl[11] = new JButton("Full House");
        this.scoreSheetLbl[12] = new JButton("Small Straight");
        this.scoreSheetLbl[13] = new JButton("Large Straight");
        this.scoreSheetLbl[14] = new JButton("Yahtzee");
        this.scoreSheetLbl[15] = new JButton("Chance");
        this.scoreSheetLbl[16] = new JButton("Yahtzee Bonus");
        this.scoreSheetLbl[17] = new JButton("Total Lower Score");
        this.scoreSheetLbl[18] = new JButton("Total Upper Score");
        this.scoreSheetLbl[19] = new JButton("Grand Total");

    }

    @Override
    public void updateModel(YahtzeeModel model) {
        this.model = model;
    }

    @Override
    public void updateView() {

        for (int i = 0; i < this.dice.length; i++) {
            this.dice[i].setText(this.model.getDie(i));
            if (this.model.getDiceRollable(i)) {
                this.dice[i].setBackground(Color.green);
            } else {
                this.dice[i].setBackground(Color.red);
            }

            this.dice[i].setEnabled(this.model.stillRolling());

        }

        this.rollBtn.setEnabled(this.model.stillRolling());

        for (int i = 0; i < this.scoreSheetLbl.length; i++) {
            this.scoreSheetResults[i].setText(this.model.getScore(i));
            this.scoreSheetLbl[i].setEnabled(this.model.getScorePlayable(i));
        }

    }

    // add action listeners for the buttons
    @Override
    public void addDiceListener(ActionListener[] diceListener) {
        for (int i = 0; i < this.dice.length; i++) {
            this.dice[i].addActionListener(diceListener[i]);
        }

    }

    @Override
    public void addScoreListener(ActionListener[] scoreListener) {
        for (int i = 0; i < this.scoreSheetLbl.length; i++) {
            this.scoreSheetLbl[i].addActionListener(scoreListener[i]);
        }

    }

    @Override
    public void addRollListener(ActionListener rollListener) {
        this.rollBtn.addActionListener(rollListener);
    }

    @Override
    public void addNewGameListener(ActionListener newGameListener) {
        this.newGame.addActionListener(newGameListener);
    }

    @Override
    public void holdDice(int i) {
        if (this.model.getDiceRollable(i)) {
            this.dice[i].setBackground(Color.green);
        } else {
            this.dice[i].setBackground(Color.red);
        }
    }

    @Override
    public void justPlayed() {
        for (int i = 0; i < this.scoreSheetLbl.length; i++) {
            this.scoreSheetLbl[i].setEnabled(false);
        }

        for (int i = 0; i < this.dice.length; i++) {
            this.dice[i].setEnabled(false);
            this.dice[i].setBackground(Color.green);
        }
    }

}
