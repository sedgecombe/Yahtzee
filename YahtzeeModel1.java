import java.util.Random;

public class YahtzeeModel1 implements YahtzeeModel {

    // constants
    private final int numberOfRolls = 3;
    private final int sidesOnDie = 6;

    //class variables
    private int[] scores;
    private int[] dice;
    private boolean[] dieHeld;
    private int rollNumber;
    private boolean[] scorePlayable;
    private Random generator = new Random();
    private int[] distribution = new int[this.sidesOnDie];

    //public final int numberOfDice = 5;
    //public final int numberOfScores = 20;

    public YahtzeeModel1() {

        // set length of arrays
        this.scores = new int[numberOfScores];
        this.dice = new int[numberOfDice];
        this.dieHeld = new boolean[numberOfDice];
        this.scorePlayable = new boolean[numberOfScores];

        // initialize the variables
        this.initialize();
    }

    //inializes all variables, sets dice to 0 before first roll
    private void initialize() {

        for (int i = 0; i < this.dice.length; i++) {
            this.dice[i] = 0;
            this.dieHeld[i] = false;
        }

        for (int i = 0; i < this.scores.length; i++) {
            this.scores[i] = 0;
            this.scorePlayable[i] = true;
        }

        this.rollNumber = 0;

        // need to manually set special score values where totals are
        // calculated to not playable manually, these places have indeces of
        // 6,7,8,16,17,18,19.
        this.scorePlayable[6] = false;
        this.scorePlayable[7] = false;
        this.scorePlayable[8] = false;
        this.scorePlayable[16] = false;
        this.scorePlayable[17] = false;
        this.scorePlayable[18] = false;
        this.scorePlayable[19] = false;
    }

    @Override
    public String getDie(int i) {

        return String.valueOf(this.dice[i]);
    }

    @Override
    public boolean getDiceRollable(int i) {

        return !this.dieHeld[i];
    }

    @Override
    public boolean getScorePlayable(int i) {

        return this.scorePlayable[i];
    }

    @Override
    public String getScore(int i) {

        return String.valueOf(this.scores[i]);
    }

    @Override
    public boolean stillRolling() {

        return (this.rollNumber < this.numberOfRolls);
    }

    @Override
    public void roll() {
        for (int i = 0; i < this.dice.length; i++) {
            if (!this.dieHeld[i]) {
                this.dice[i] = this.generator.nextInt(this.sidesOnDie) + 1;
            }
        }

        this.rollNumber++;
    }

    @Override
    public void holdDice(int i) {
        this.dieHeld[i] = !this.dieHeld[i];

    }

    @Override
    public void playScore(int index) {
        this.updateDistribution();
        int roundScore = 0;

        if (index < this.sidesOnDie) {
            for (int j = 0; j < numberOfDice; j++) {
                if (this.dice[j] == index + 1) {
                    roundScore += this.dice[j];
                }
            }

            if (this.yahtzee() && this.scores[14] != 0) {
                this.scores[16] += 100;
            }

        } else if (index == 9) {
            if (this.threeOfAKind()) {
                for (int j = 0; j < this.dice.length; j++) {
                    roundScore += this.dice[j];
                }

                if (this.yahtzee() && this.scores[14] != 0
                        && !this.scorePlayable[this.dice[0] - 1]) {
                    this.scores[16] += 100;
                }
            }
        } else if (index == 10) {
            if (this.fourOfAKind()) {
                for (int j = 0; j < this.dice.length; j++) {
                    roundScore += this.dice[j];
                }

                if (this.yahtzee() && this.scores[14] != 0
                        && !this.scorePlayable[this.dice[0] - 1]) {
                    this.scores[16] += 100;
                }
            }
        } else if (index == 11) {
            if (this.fullHouse()) {
                roundScore += 25;
            }

            if (this.yahtzee() && this.scores[14] != 0
                    && !this.scorePlayable[this.dice[0] - 1]) {
                this.scores[16] += 100;
                roundScore += 25;
            }
        } else if (index == 12) {
            if (this.smallStraight()) {
                roundScore += 30;
            }

            if (this.yahtzee() && this.scores[14] != 0
                    && !this.scorePlayable[this.dice[0] - 1]) {
                this.scores[16] += 100;
                roundScore += 30;
            }
        } else if (index == 13) {
            if (this.largeStraight()) {
                roundScore += 40;
            }

            if (this.yahtzee() && this.scores[14] != 0
                    && !this.scorePlayable[this.dice[0] - 1]) {
                this.scores[16] += 100;
                roundScore += 40;
            }
        } else if (index == 14) {
            if (this.yahtzee()) {
                roundScore += 50;
            }
        } else if (index == 15) {
            for (int j = 0; j < this.dice.length; j++) {
                roundScore += this.dice[j];
            }

            if (this.yahtzee() && this.scores[14] != 0
                    && !this.scorePlayable[this.dice[0] - 1]) {
                this.scores[16] += 100;
            }
        } else {
            System.exit(1);
        }

        // update score
        this.scores[index] = roundScore;
        this.scorePlayable[index] = false;

        // calculate upper score
        int tempScore = 0;

        for (int j = 0; j < this.sidesOnDie; j++) {
            tempScore += this.scores[j];
        }

        this.scores[6] = tempScore;

        // check for and add bonus
        if (this.scores[6] >= 63) {
            this.scores[7] = 35;
        }

        this.scores[8] = this.scores[6] + this.scores[7];

        // calculate lower score
        tempScore = 0;

        for (int j = 9; j <= 16; j++) {
            tempScore += this.scores[j];
        }

        this.scores[17] = tempScore;

        this.scores[18] = this.scores[8];
        this.scores[19] = this.scores[17] + this.scores[18];

        // reset roll
        this.rollNumber = 0;
        for (int j = 0; j < this.dice.length; j++) {
            this.dieHeld[j] = false;

        }

    }

    private void updateDistribution() {
        // initialize distribution
        for (int i = 0; i < this.sidesOnDie; i++) {
            this.distribution[i] = 0;
        }

        for (int i = 0; i < numberOfDice; i++) {
            this.distribution[this.dice[i] - 1]++;
        }

    }

    private boolean yahtzee() {
        boolean returnValue = false;

        for (int j = 1; j < this.sidesOnDie; j++) {
            if (this.distribution[j] == 5) {
                returnValue = true;
            }
        }

        return returnValue;
    }

    private boolean largeStraight() {
        boolean returnValue = true;

        for (int i = 1; i < this.sidesOnDie - 1; i++) {
            if (this.distribution[i] != 1) {
                returnValue = false;
            }
        }

        return returnValue;
    }

    private boolean smallStraight() {
        boolean returnValue = true;

        if (this.distribution[2] < 1) {
            returnValue = false;
        } else if (this.distribution[3] < 1) {
            returnValue = false;
        } else if (this.distribution[1] < 1) {
            if (this.distribution[4] < 1 || this.distribution[5] < 1) {
                returnValue = false;
            }
        } else if (this.distribution[0] < 1) {
            if (this.distribution[4] < 1) {
                returnValue = false;
            }
        }

        return returnValue;
    }

    private boolean fullHouse() {
        boolean has3 = false;
        boolean has2 = false;

        for (int i = 0; i < this.distribution.length; i++) {
            if (this.distribution[i] == 3) {
                has3 = true;
            }
            if (this.distribution[i] == 2) {
                has2 = true;
            }
        }

        return has2 && has3;
    }

    private boolean fourOfAKind() {
        boolean returnValue = false;

        for (int i = 0; i < this.distribution.length; i++) {
            if (this.distribution[i] >= 4) {
                returnValue = true;
            }
        }

        return returnValue;
    }

    private boolean threeOfAKind() {
        boolean returnValue = false;

        for (int i = 0; i < this.distribution.length; i++) {
            if (this.distribution[i] >= 3) {
                returnValue = true;
            }
        }

        return returnValue;
    }

    @Override
    public void newGame() {
        this.initialize();

    }
}
