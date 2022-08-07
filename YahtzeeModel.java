
public interface YahtzeeModel {

    public final static int numberOfScores = 20;
    public final static int numberOfDice = 5;

    // returns a string that is the number on the ith die
    public String getDie(int i);

    // returns whether or not the ith die is still playable
    public boolean getDiceRollable(int i);

    // returns whether or not the ith score is playable, always false for
    // score totals
    public boolean getScorePlayable(int i);

    //returns ith score on the scorecard
    public String getScore(int i);

    // returns if another roll is allowed
    public boolean stillRolling();

    // generate random ints from 1 to 6 for all non held dice
    public void roll();

    public void holdDice(int i);

    public void playScore(int index);

    // restart Game
    public void newGame();
}
