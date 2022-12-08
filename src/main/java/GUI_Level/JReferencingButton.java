package GUI_Level;

import javax.swing.*;

public class JReferencingButton extends JButton {
    private int amount;

    public JReferencingButton(String text, int amount) {
        super(text);
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
