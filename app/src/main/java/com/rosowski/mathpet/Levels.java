package com.rosowski.mathpet;

import com.google.common.base.Preconditions;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by danielrosowski on 14.07.16.
 */
public class Levels {

    private List<Level> levels = new ArrayList<>(5);
    private int currentLevel = -1;

    public Levels() {
        levels.add(new Level(10));
        levels.add(new Level(20));
        levels.add(new Level(100));
        levels.add(new Level(1000));
    }

    public Level getNextLevel() {
        Preconditions.checkState(hasNextLevel(), "There are no more levels!");
        currentLevel++;
        return levels.get(currentLevel);
    }

    public boolean hasNextLevel() {
        return currentLevel + 1 < levels.size();
    }

    public static class Level {
        public final int bound;

        private Level(int bound) {
            this.bound = bound;
        }
    }
}
