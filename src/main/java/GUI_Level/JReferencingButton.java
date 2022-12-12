package GUI_Level;

import Rucksack.Level;
import Rucksack.Rucksack;

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

    public int getPosition() {
        return position;
    }

    /**
     * setzt die Anzahl an Items in Rucksack und in zur Verfügung stehenden Mwnge wenn ein Item in den Rucksack gepackt wird
     */
    public void setItemAmount(){
        int itemAmount = level.getItemAmountList().get(position);
        if(itemAmount > 0) {
           level.getItemAmountList().set(position ,itemAmount - 1 );
           level.getRucksack().getAmountList().set(position, (level.getRucksack().getAmountList().get(position) + 1));
            System.out.println("Rucksack Itemanzahlen:" + level.getRucksack().getAmountList());
            System.out.println("zur Verügung stehende Itemanzahlen:" + level.getItemAmountList());
       }

        System.out.println(level.getItemAmountList());

    }

    /**
     * setzt die Anzahl an Items in Rucksack und in zur Verfügung stehenden Menge wenn ein Item aus den Rucksack gepackt wird
     */
    public void setRucksackItemAmount(){
        int itemAmountRucksack = level.getRucksack().getAmountList().get(position);
        if(itemAmountRucksack > 0) {
            level.getRucksack().getAmountList().set(position ,itemAmountRucksack - 1 );
            level.getItemAmountList().set(position, (level.getItemAmountList().get(position) + 1));
            System.out.println("Rucksack Itemanzahlen:" + level.getRucksack().getAmountList());
            System.out.println("zur Verügung stehende Itemanzahlen:" + level.getItemAmountList());
        }

    }

    public int getAmountLevelItem(){
        return level.getItemAmountList().get(position);
    }

    public int getAmountRucksackItem() {
        return level.getRucksack().getAmountList().get(position);
    }
}
