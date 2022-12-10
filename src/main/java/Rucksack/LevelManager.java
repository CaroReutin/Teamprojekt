package Rucksack;

import Solving.Solver;

import java.util.ArrayList;

public class LevelManager {
    private Solver solver = new Solver();
    private ArrayList<Level> levels = new ArrayList<>();



    public Level getLevel(int levelNumber) {
        return levels.get(levelNumber);
    }
}
