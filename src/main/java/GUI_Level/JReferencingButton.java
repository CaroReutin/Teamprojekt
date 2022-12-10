package GUI_Level;

import Rucksack.Level;

import javax.swing.*;

public class JReferencingButton extends JButton {
    private Level level;
    /**
     * Stelle des Items in der Itemslist
     */
    private int position;

    public JReferencingButton(String text, Level level, int position) {
        super(text);
        this.level = level;
        this.position = position;
    }

    public Level getLevel() {
        return level;
    }

    public void setItemAmount(){
        int itemAmount = level.getItemAmountList().get(position);
        if(itemAmount > 0) {
           level.getItemAmountList().set(position ,itemAmount - 1 );
       }
        System.out.println(level.getItemAmountList());
    }

    public int getAmount(){
        return level.getItemAmountList().get(position);
    }
}
