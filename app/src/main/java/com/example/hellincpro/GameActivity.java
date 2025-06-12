package com.example.hellincpro;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout; // Добавлено для FrameLayout
import android.widget.ImageButton; // Для кнопки настроек
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class GameActivity extends AppCompatActivity {

    private GameEngine gameEngine;
    private TextView storyTextView;
    private ImageView gameImageView; // Соответствует ID в XML
    private LinearLayout choiceButtonsContainer;
    private MediaPlayer gameMediaPlayer;

    private ImageButton inGameSettingsButton;
    private Button saveGameButton;
    private Button continueGameButton;
    private Button exitToMenuButton;
    private FrameLayout inGameSettingsOverlay; // Соответствует ID и типу в XML


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        // Инициализация компонентов UI с корректными ID из XML
        storyTextView = findViewById(R.id.storyTextView);
        gameImageView = findViewById(R.id.gameImageView); // Исправлено ID
        choiceButtonsContainer = findViewById(R.id.choiceButtonsContainer);

        inGameSettingsButton = findViewById(R.id.inGameSettingsButton);
        saveGameButton = findViewById(R.id.saveGameButton);
        continueGameButton = findViewById(R.id.continueGameButton);
        exitToMenuButton = findViewById(R.id.exitToMenuButton);
        inGameSettingsOverlay = findViewById(R.id.inGameSettingsOverlay); // Исправлено ID и тип

        // Скрываем панель настроек по умолчанию
        inGameSettingsOverlay.setVisibility(View.GONE);

        // Инициализация игрового движка
        gameEngine = new GameEngine(this);

        // Инициализация фоновой музыки
        try {
            gameMediaPlayer = MediaPlayer.create(this, R.raw.menu_music);
            if (gameMediaPlayer != null) {
                gameMediaPlayer.setLooping(true);
                gameMediaPlayer.start();
            }
        } catch (Exception e) {
            Log.e("GameActivity", "MediaPlayer error: " + e.getMessage());
            Toast.makeText(this, "Music load error", Toast.LENGTH_SHORT).show();
        }

        // Обработчик клика по storyTextView (только для линейного прохождения)
        storyTextView.setOnClickListener(v -> {
           StoryNode currentNode = gameEngine.getCurrentNode();
            if (currentNode != null && currentNode.isLinear()) {
                gameEngine.choose(1); // Переходим к следующему узлу по первому (и единственному) пути
                updateUI(); // Обновляем UI после перехода
            }
            // Если узел имеет реальные выборы, клик по тексту ничего не делает, ожидается нажатие кнопки.
        });

        // Слушатели для кнопок настроек
        inGameSettingsButton.setOnClickListener(v -> showInGameSettings());
        saveGameButton.setOnClickListener(v -> saveGame());
        continueGameButton.setOnClickListener(v -> hideInGameSettings());
        exitToMenuButton.setOnClickListener(v -> exitToMainMenu());

        // Первое обновление UI при старте активности, чтобы отобразить начальный узел
        updateUI();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (gameMediaPlayer != null && !gameMediaPlayer.isPlaying()) {
            gameMediaPlayer.start(); // Возобновляем музыку, если она была на паузе
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (gameMediaPlayer != null && gameMediaPlayer.isPlaying()) {
            gameMediaPlayer.pause(); // Приостанавливаем музыку при сворачивании приложения
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (gameMediaPlayer != null) {
            gameMediaPlayer.release(); // Освобождаем ресурсы MediaPlayer при уничтожении активности
            gameMediaPlayer = null;
        }
    }

    // --- Реализация updateUI() ---
    private void updateUI() {
        StoryNode currentNode = gameEngine.getCurrentNode();
        Log.d("GameActivity", "updateUI() called. Current Node ID: " + (currentNode != null ? currentNode.getNodeId() : "NULL"));

        // Обработка состояния, когда текущего узла нет (конец игры или ошибка)
        if (currentNode == null) {
            storyTextView.setText(R.string.theGameOver);
            gameImageView.setImageDrawable(null);
            choiceButtonsContainer.removeAllViews();
            choiceButtonsContainer.setVisibility(View.GONE);
            return;
        }

        // Устанавливаем текст истории из строкового ресурса
        String textResName = currentNode.getTextResId();
        int textResId = getResources().getIdentifier(textResName, "string", getPackageName());
        if (textResId != 0) {
            storyTextView.setText(textResId);
        } else {
            storyTextView.setText("Error: Text resource '" + textResName + "' not found.");
            Log.e("GameActivity", "Text resource not found: " + textResName + " for node " + currentNode.getNodeId());
        }

        // Устанавливаем изображение истории
        int imageResId = currentNode.getImageResId();
        if (imageResId != 0) {
            gameImageView.setImageResource(imageResId);
        } else {
            gameImageView.setImageDrawable(null);
            Log.e("GameActivity", "Image resource not found or ID is 0 for node: " + currentNode.getNodeId());
        }

        choiceButtonsContainer.removeAllViews();
        choiceButtonsContainer.setVisibility(View.GONE);

        // Динамическое создание кнопок выбора
        if (currentNode.hasDynamicChoices()) {
            choiceButtonsContainer.setVisibility(View.VISIBLE);
            List<Choise> choices = currentNode.getDynamicChoices();
            for (int i = 0; i < choices.size(); i++) {
                final int choiceNum = i + 1;
                Choise choice = choices.get(i);

                Button button = new Button(this);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                );
                params.setMargins(0, (int) getResources().getDimension(R.dimen.choice_button_margin_top), 0, 0);
                button.setLayoutParams(params);

                String choiceTextResName = choice.getTextResId();
                int choiceTextResId = getResources().getIdentifier(choiceTextResName, "string", getPackageName());
                if (choiceTextResId != 0) {
                    button.setText(choiceTextResId);
                } else {
                    button.setText("Error: Choice text '" + choiceTextResName + "' not found.");
                    button.setEnabled(false);
                }

                button.setOnClickListener(v -> {
                    gameEngine.choose(choiceNum);
                    updateUI();
                });
                choiceButtonsContainer.addView(button);
            }
        } else if (currentNode.hasFixedChoices()) {
            choiceButtonsContainer.setVisibility(View.VISIBLE);

            // Fixed Choice 1 Button
            Button button1 = new Button(this);
            LinearLayout.LayoutParams params1 = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            params1.setMargins(0, (int) getResources().getDimension(R.dimen.choice_button_margin_top), 0, 0);
            button1.setLayoutParams(params1);
            String choice1TextResName = currentNode.getFixedChoice1TextResId();
            int choice1TextResId = getResources().getIdentifier(choice1TextResName, "string", getPackageName());
            if (choice1TextResId != 0) {
                button1.setText(choice1TextResId);
            } else {
                button1.setText("Error: Fixed Choice 1 text '" + choice1TextResName + "' not found.");
                button1.setEnabled(false);
            }
            button1.setOnClickListener(v -> {
                gameEngine.choose(1);
                updateUI();
            });
            choiceButtonsContainer.addView(button1);

            // Fixed Choice 2 Button (if exists)
            if (currentNode.getFixedChoice2TextResId() != null) {
                Button button2 = new Button(this);
                LinearLayout.LayoutParams params2 = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                params2.setMargins(0, (int) getResources().getDimension(R.dimen.choice_button_margin_top), 0, 0);
                button2.setLayoutParams(params2);
                String choice2TextResName = currentNode.getFixedChoice2TextResId();
                int choice2TextResId = getResources().getIdentifier(choice2TextResName, "string", getPackageName());
                if (choice2TextResId != 0) {
                    button2.setText(choice2TextResId);
                } else {
                    button2.setText("Error: Fixed Choice 2 text '" + choice2TextResName + "' not found.");
                    button2.setEnabled(false);
                }
                button2.setOnClickListener(v -> {
                    gameEngine.choose(2);
                    updateUI();
                });
                choiceButtonsContainer.addView(button2);
            }
        }
        // Если узел не имеет никаких выборов, кнопки не отображаются.
    }

    // --- Методы для работы с настройками ---
    private void showInGameSettings() {
        inGameSettingsOverlay.setVisibility(View.VISIBLE);
        if (gameMediaPlayer != null && gameMediaPlayer.isPlaying()) {
            gameMediaPlayer.pause();
        }
    }

    private void hideInGameSettings() {
        inGameSettingsOverlay.setVisibility(View.GONE);
        if (gameMediaPlayer != null && !gameMediaPlayer.isPlaying()) {
            gameMediaPlayer.start();
        }
    }

    private void saveGame() {
        getSharedPreferences("GameSave", MODE_PRIVATE).edit()
                .putString("lastNodeId", gameEngine.getCurrentNodeId())
                .apply();
        Toast.makeText(this, R.string.save, Toast.LENGTH_SHORT).show(); // Используем строковый ресурс
    }

    private void exitToMainMenu() {
        if (gameMediaPlayer != null) {
            gameMediaPlayer.stop();
            gameMediaPlayer.release();
            gameMediaPlayer = null;
        }
        Intent intent = new Intent(GameActivity.this, MainActivity.class); // Убедитесь, что MainActivity.class - ваш класс главного меню
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }
}