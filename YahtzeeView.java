import java.awt.event.ActionListener;

public interface YahtzeeView {

    void setVisible(boolean b);

    void addRollListener(ActionListener rollListener);

    void addNewGameListener(ActionListener newGameListener);

    void addScoreListener(ActionListener[] scoreListener);

    void addDiceListener(ActionListener[] diceListener);

    void updateModel(YahtzeeModel model);

    void updateView();

    void holdDice(int i);

    void justPlayed();

}
