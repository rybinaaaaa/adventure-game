package rybina.Model;

/**
 * The Menu class represents the menu in the game.
 * It holds the available options and the currently selected option.
 */
public class Menu {
    String[] options = new String[]{
            "new game",
            "load game",
            "exit"
    };

    int selectedOption = 0;

    /**
     * Returns the currently selected option.
     *
     * @return The currently selected option.
     */
    public String getSelectedOption() {
        return options[selectedOption];
    }

    /**
     * Sets the selected option by increasing or decreasing the current selection.
     * If the increase value is negative, it will decrease the selection.
     * If the increase value is positive, it will increase the selection.
     *
     * @param increase The value by which the selected option should be increased or decreased.
     */
    public void setSelectedOption(int increase) {
        int increased = (selectedOption + increase) % options.length;
        if (increased < 0) {
            this.selectedOption = (options.length) + increased;
        } else {
            this.selectedOption = increased;
        }
    }

    /**
     * Returns the array of available options in the menu.
     *
     * @return The array of available options.
     */
    public String[] getOptions() {
        return options;
    }
}
