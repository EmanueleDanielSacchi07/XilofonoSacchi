package com.example.xilofonosacchi;

import android.media.AudioAttributes;
import android.media.SoundPool;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private final int NR_OF_SIMULTANEOUS_SOUNDS = 7;
    private final float LEFT_VOLUME = 1.0f;
    private final float RIGHT_VOLUME = 1.0f;
    private final int NO_LOOP = 0;
    private final int PRIORITY = 0;
    private final float NORMAL_PLAY_RATE = 1.0f;

    private SoundPool mSoundPool;
    private int mCSoundId;
    private int mDSoundId;
    private int mESoundId;
    private int mFSoundId;
    private int mGSoundId;
    private int mASoundId;
    private int mBSoundId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // 1. Creo il SoundPool
        AudioAttributes attributes = new AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_GAME)
                .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                .build();

        mSoundPool = new SoundPool.Builder()
                .setMaxStreams(NR_OF_SIMULTANEOUS_SOUNDS)
                .setAudioAttributes(attributes)
                .build();

        // 2. Carico i suoni da res/raw (senza estensione)
        mCSoundId = mSoundPool.load(this, R.raw.note1_c, 1);
        mDSoundId = mSoundPool.load(this, R.raw.note2_d, 1);
        mESoundId = mSoundPool.load(this, R.raw.note3_e, 1);
        mFSoundId = mSoundPool.load(this, R.raw.note4_f, 1);
        mGSoundId = mSoundPool.load(this, R.raw.note5_g, 1);
        mASoundId = mSoundPool.load(this, R.raw.note6_a, 1);
        mBSoundId = mSoundPool.load(this, R.raw.note7_b, 1);

        // 3. Collego i bottoni
        findViewById(R.id.buttonDo).setOnClickListener(v -> playSound(mCSoundId));
        findViewById(R.id.buttonRe).setOnClickListener(v -> playSound(mDSoundId));
        findViewById(R.id.buttonMi).setOnClickListener(v -> playSound(mESoundId));
        findViewById(R.id.buttonFa).setOnClickListener(v -> playSound(mFSoundId));
        findViewById(R.id.buttonSol).setOnClickListener(v -> playSound(mGSoundId));
        findViewById(R.id.buttonLa).setOnClickListener(v -> playSound(mASoundId));
        findViewById(R.id.buttonSi).setOnClickListener(v -> playSound(mBSoundId));
    }

    private void playSound(int soundId) {
        mSoundPool.play(soundId, LEFT_VOLUME, RIGHT_VOLUME, PRIORITY, NO_LOOP, NORMAL_PLAY_RATE);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mSoundPool != null) {
            mSoundPool.release();
            mSoundPool = null;
        }
    }
}