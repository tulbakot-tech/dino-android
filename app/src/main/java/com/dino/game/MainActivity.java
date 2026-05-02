package com.dino.game;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.graphics.Color;

public class MainActivity extends Activity {
    // Представь, что это наш динозавр
    private View dino;
    private boolean isJumping = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        // Создаем контейнер на весь экран
        FrameLayout gameLayout = new FrameLayout(this);
        gameLayout.setBackgroundColor(Color.WHITE);

        // Рисуем нашего "Дино" (пока это просто серый квадрат, хардкор!)
        dino = new View(this);
        dino.setBackgroundColor(Color.DKGRAY);
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(100, 100);
        params.leftMargin = 100;
        params.topMargin = 800; // Позиция "на земле"
        dino.setLayoutParams(params);

        gameLayout.addView(dino);

        // Обработка тапа для прыжка
        gameLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isJumping) {
                    jump();
                }
            }
        });

        setContentView(gameLayout);
    }

    private void jump() {
        isJumping = true;
        // Простая анимация вверх
        dino.animate().translationYBy(-300).setDuration(300).withEndAction(new Runnable() {
            @Override
            public void run() {
                // Анимация вниз
                dino.animate().translationYBy(300).setDuration(300).withEndAction(new Runnable() {
                    @Override
                    public void run() {
                        isJumping = false;
                    }
                });
            }
        });
    }
}

