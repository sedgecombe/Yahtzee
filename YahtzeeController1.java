import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class YahtzeeController1 implements YahtzeeController {

    // model and view needed
    private YahtzeeModel model;
    private YahtzeeView view;

    public YahtzeeController1(YahtzeeModel model, YahtzeeView view) {

        this.model = model;
        this.view = view;

        // create action listeners arrays for rolling and score keeping
        DiceListener[] diceListener = new DiceListener[YahtzeeModel1.numberOfDice];
        ScoreListener[] scoreListener = new ScoreListener[YahtzeeModel1.numberOfScores];

        for (int i = 0; i < diceListener.length; i++) {
            diceListener[i] = new DiceListener(i);
        }

        for (int i = 0; i < scoreListener.length; i++) {
            scoreListener[i] = new ScoreListener(i);
        }

        // add action listeners
        view.addRollListener(new RollListener());
        view.addNewGameListener(new NewGameListener());
        view.addScoreListener(scoreListener);
        view.addDiceListener(diceListener);

    }

    class NewGameListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            YahtzeeController1.this.model.newGame();

            YahtzeeController1.this.view
            .updateModel(YahtzeeController1.this.model);
            YahtzeeController1.this.view.updateView();
        }
    }

    class DiceListener implements ActionListener {

        private int index;

        public DiceListener(int i) {
            this.index = i;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            YahtzeeController1.this.model.holdDice(this.index);

            YahtzeeController1.this.view
                    .updateModel(YahtzeeController1.this.model);
            YahtzeeController1.this.view.holdDice(this.index);

        }

    }

    class ScoreListener implements ActionListener {

        private int index;

        public ScoreListener(int i) {
            this.index = i;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            YahtzeeController1.this.model.playScore(this.index);
            YahtzeeController1.this.view
                    .updateModel(YahtzeeController1.this.model);
            YahtzeeController1.this.view.updateView();
            YahtzeeController1.this.view.justPlayed();

        }

    }

    class RollListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            YahtzeeController1.this.model.roll();

            YahtzeeController1.this.view
                    .updateModel(YahtzeeController1.this.model);
            YahtzeeController1.this.view.updateView();

        }

    }
}
