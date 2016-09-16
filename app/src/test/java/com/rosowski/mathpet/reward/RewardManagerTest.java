package com.rosowski.mathpet.reward;

import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.graphics.drawable.Drawable;

import com.rosowski.mathpet.reward.RewardManager;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class RewardManagerTest {

    public static final String SEPARATOR = ",";
    public static final String A1_JPG = "a1.jpg";
    public static final String A2_JPG = "a2.jpg";
    @Mock
    private SharedPreferences sharedPref;

    @Mock
    private SharedPreferences.Editor editor;

    @Mock
    private AssetManager assetManager;

    @Captor
    private ArgumentCaptor<String> editorArgs;

    private RewardManager rewardManager;

    @Before
    public void setup() throws Exception {
        when(assetManager.list("animals")).thenReturn(new String[]{A1_JPG, A2_JPG});
        when(sharedPref.edit()).thenReturn(editor);
        this.rewardManager = new RewardManager(sharedPref);
    }

    @Test
    public void should_add_without_separator_for_first_unlocked_item() throws Exception {
        when(sharedPref.getString(RewardManager.UNLOCKED_IMAGES, "")).thenReturn("");

        rewardManager.unlockReward(assetManager);

        verify(editor).putString(eq(RewardManager.UNLOCKED_IMAGES), editorArgs.capture());
        assertThat(editorArgs.getValue(), not(containsString(SEPARATOR)));
    }

    @Test
    public void should_add_with_separator_for_first_unlocked_item() throws Exception {
        when(sharedPref.getString(RewardManager.UNLOCKED_IMAGES, "")).thenReturn("1");

        rewardManager.unlockReward(assetManager);

        verify(editor).putString(eq(RewardManager.UNLOCKED_IMAGES), editorArgs.capture());
        assertThat(editorArgs.getValue(), containsString(SEPARATOR));
    }

    @Test
    public void should_load_all_unlocked_rewards() throws Exception {
        when(sharedPref.getString(RewardManager.UNLOCKED_IMAGES, "")).thenReturn("0");

        List<Drawable> rewards = rewardManager.loadUnlockedRewards(assetManager);

        assertEquals(1, rewards.size());
    }
}