package com.rosowski.mathpet;

import org.junit.Test;
import org.mockito.internal.util.reflection.Whitebox;

import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by danielrosowski on 14.07.16.
 */
public class LevelsTest {

    @Test
    public void should_get_next_level() {
        Levels levels = new Levels();
        int levelCount = getLevelCount(levels);
        for (int i = 0; i < levelCount; i++) {
            assertNotNull(levels.getNextLevel());
            if (i + 1 < levelCount) {
                assertTrue(levels.hasNextLevel());
            }
        }
        assertFalse(levels.hasNextLevel());
    }

    @Test(expected = IllegalStateException.class)
    public void should_throw_ex_when_requesting_more_than_max_level() {
        Levels levels = new Levels();
        int levelCount = getLevelCount(levels);
        for (int i = 0; i < levelCount; i++) {
            levels.getNextLevel();
        }
        levels.getNextLevel();
    }

    private int getLevelCount(Levels levels) {
        return ((List<Levels.Level>) Whitebox.getInternalState(levels, "levels")).size();
    }
}