package com.careful.hyperfvm.compose.ui.animation.bgeffect;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.RequiresApi;

import com.careful.hyperfvm.compose.R;

@SuppressLint("ViewConstructor")
public class BgEffectView extends LinearLayout {

    private View mBgEffectView;
    private BgEffectPainter mBgEffectPainter;
    private final float startTime = (float) System.nanoTime();
    private final Handler mHandler = new Handler(Looper.getMainLooper());

    private boolean darkMode;

    Runnable runnableBgEffect = new Runnable() {
        @RequiresApi(api = Build.VERSION_CODES.TIRAMISU)
        @Override
        public void run() {
            float animTime = (((float) System.nanoTime()) - startTime) / 1.0E9f;
            mBgEffectPainter.setAnimTime((animTime * 2) % 62.831852f); // 调整光影流动的速度
            mBgEffectPainter.setResolution(new float[]{mBgEffectView.getWidth(), mBgEffectView.getHeight()});
            mBgEffectPainter.updateMaterials();
            mBgEffectView.setRenderEffect(mBgEffectPainter.getRenderEffect());
            mHandler.postDelayed(runnableBgEffect, 16L);
        }
    };

    @RequiresApi(api = Build.VERSION_CODES.TIRAMISU)
    public BgEffectView(Context context, boolean darkMode) {
        super(context);
        this.darkMode = darkMode;
        BgEffect(context);
    }

    @RequiresApi(api = Build.VERSION_CODES.TIRAMISU)
    public void BgEffect(Context context) {

        mBgEffectView = LayoutInflater.from(context).inflate(R.layout.layout_effect_bg, this, true);


        mBgEffectView.post(() -> {
            if (context != null) {

                Context appContext = context.getApplicationContext();
                mBgEffectPainter = new BgEffectPainter(appContext);
                mBgEffectPainter.showRuntimeShader(darkMode);

                mHandler.post(runnableBgEffect);
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.TIRAMISU)
    public void  updateMode(boolean darkMode){
        if (darkMode != this.darkMode){
            this.darkMode = darkMode;
            mBgEffectPainter.updateMode(darkMode);
        }
    }

}

