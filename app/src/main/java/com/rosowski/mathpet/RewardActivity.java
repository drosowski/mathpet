package com.rosowski.mathpet;

import android.content.res.AssetManager;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import java.io.IOException;
import java.util.Random;

public class RewardActivity extends AppCompatActivity {

    private final ToastRenderer toast;

    public RewardActivity() {
        this.toast = new ToastRenderer();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reward);

        ImageView imageView = new ImageView(this);
        imageView.setLayoutParams(
                new ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT));
        AssetManager assets = this.getAssets();
        try {
            String[] files = assets.list("animals");
            Random random = new Random();
            int index = random.nextInt(files.length);
            Drawable drawable = Drawable.createFromStream(assets.open("animals/" + files[index]), null);
            imageView.setImageDrawable(drawable);
        } catch (IOException ex) {
            toast.show(getApplicationContext(), "Fehler beim Laden der Bilder!");
            this.finish();
        }

        RelativeLayout layout = (RelativeLayout) findViewById(R.id.pictureFrame);
        layout.addView(imageView);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        this.finish();
        return true;
    }
}
