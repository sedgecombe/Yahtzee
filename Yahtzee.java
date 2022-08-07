
public class Yahtzee {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private Yahtzee() {
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments
     */
    public static void main(String[] args) {

        YahtzeeModel model = new YahtzeeModel1();
        YahtzeeView view = new YahtzeeView1(model);
        YahtzeeController controller = new YahtzeeController1(model, view);

        view.setVisible(true);

    }
}
