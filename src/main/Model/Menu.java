package main.Model;

public class Menu {

    int optionWidth, optionHeight;
    String[] options = new String[]{
            "new game",
            "load game",
            "exit"
    };

    int selectedOption = 0;

    public String getSelectedOption() {
        return options[selectedOption];
    }

    public void setSelectedOption(int increase) {
//        System.out.print(selectedOption + " + " + increase + " = " );
        int increased = (selectedOption + increase) % options.length;
        if (increased < 0) {
            this.selectedOption = (options.length) + increased;
        } else {
            this.selectedOption = increased;
        }
//        System.out.println(selectedOption);
    }

    public String[] getOptions() {
        return options;
    }
}
