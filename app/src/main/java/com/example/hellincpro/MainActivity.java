package com.example.hellincpro;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView; // <-- Импорт TextView
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;
import java.util.Random; // <-- Импорт Random

public class MainActivity extends AppCompatActivity {

    private SharedPreferences sharedPreferences;
    private FrameLayout settingsOverlay;
    private SeekBar musicVolumeSlider;
    private RadioGroup languageRadioGroup;
    private RadioButton radioRu, radioEn;
    private Button continueButton;
    private MediaPlayer mediaPlayer; // Для фоновой музыки меню

    // Новые поля для смены заголовка-пасхалки
    private TextView mainMenuTitleTextView;
    private String originalTitleText; // Для хранения исходного текста заголовка
    private String[] easterEggTitles; // Массив ключей строковых ресурсов для пасхалок
    private Handler handler;
    private Runnable easterEggRunnable; // Задача для смены пасхалок

    // Время для смены пасхалок
    private final long EASTER_EGG_INTERVAL_MS = 10 * 60 * 1000; // 10 минут
    private final long EASTER_EGG_DURATION_MS = 500;            // 0.5 секунды

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Убираем заголовок и устанавливаем полноэкранный режим
        requestWindowFeature(Window.FEATURE_NO_TITLE);getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_main);

        sharedPreferences = getSharedPreferences("GamePrefs", MODE_PRIVATE);

        // Инициализация MediaPlayer для музыки главного меню
        mediaPlayer = MediaPlayer.create(this, R.raw.menu_music);
        if (mediaPlayer != null) {
            mediaPlayer.setLooping(true); // Зацикливание
            float volume = sharedPreferences.getFloat("musicVolume", 0.5f);
            mediaPlayer.setVolume(volume, volume);
        }

        // Находим элементы UI
        Button startButton = findViewById(R.id.startButton);
        continueButton = findViewById(R.id.continueButton);
        Button settingsButton = findViewById(R.id.settingsButton);
        Button exitButton = findViewById(R.id.exitButton);
        mainMenuTitleTextView = findViewById(R.id.mainMenuTitleTextView); // <-- Находим TextView для заголовка

        settingsOverlay = findViewById(R.id.settingsOverlay);
        musicVolumeSlider = findViewById(R.id.musicVolumeSlider);
        languageRadioGroup = findViewById(R.id.languageRadioGroup);
        radioRu = findViewById(R.id.radioRu);
        radioEn = findViewById(R.id.radioEn);
        Button closeSettingsButton = findViewById(R.id.closeSettingsButton);

        // Сохраняем исходный текст заголовка
        originalTitleText = getString(R.string.app_name);

        // Инициализируем массив ключей пасхалок
        easterEggTitles = new String[]{
                "easter_egg_title_1",
                "easter_egg_title_2",
                "easter_egg_title_3",
                "easter_egg_title_4"
        };

        // Инициализация Handler для планирования задач
        handler = new Handler(Looper.getMainLooper());

        // Определение задачи для смены пасхалок
        easterEggRunnable = new Runnable() {
            private int lastEasterEggIndex = -1; // Чтобы не повторять пасхалку сразу

            @Override
            public void run() {
                // Выбираем случайную пасхалку, отличную от предыдущей
                Random random = new Random();
                int newEasterEggIndex;
                do {
                    newEasterEggIndex = random.nextInt(easterEggTitles.length);
                } while (newEasterEggIndex == lastEasterEggIndex);
                lastEasterEggIndex = newEasterEggIndex;

                String selectedEasterEggKey = easterEggTitles[newEasterEggIndex];
                int easterEggResId = getResources().getIdentifier(selectedEasterEggKey, "string", getPackageName());
                if (easterEggResId != 0) {
                    mainMenuTitleTextView.setText(easterEggResId); // Устанавливаем текст пасхалки

                    // Планируем возврат к дефолтному заголовку через EASTER_EGG_DURATION_MS
                    handler.postDelayed(() -> {
                        mainMenuTitleTextView.setText(originalTitleText);
                    }, EASTER_EGG_DURATION_MS);
                }

                // Планируем следующий вызов этой же задачи через EASTER_EGG_INTERVAL_MS
                handler.postDelayed(this, EASTER_EGG_INTERVAL_MS);
            }
        };

        // Устанавливаем слушатели
        startButton.setOnClickListener(v -> startGame(false));
        continueButton.setOnClickListener(v -> startGame(true));
        settingsButton.setOnClickListener(v -> showSettingsOverlay());
        exitButton.setOnClickListener(v -> finish());

        closeSettingsButton.setOnClickListener(v -> hideSettingsOverlay());

        // Настройки музыки
        musicVolumeSlider.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                float volume = progress / 100f;
                if (mediaPlayer != null) {
                    mediaPlayer.setVolume(volume, volume);
                }
                if (fromUser) {
                    sharedPreferences.edit().putFloat("musicVolume", volume).apply();
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });

        // Настройки языка
        languageRadioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            String langCode;
            if (checkedId == R.id.radioRu) {
                langCode = "ru";
            } else {
                langCode = "en";
            }
            setLocale(langCode);
        });

        loadSettings();
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateContinueButtonState();
        if (mediaPlayer != null && !mediaPlayer.isPlaying()) {
            mediaPlayer.start();
        }
        // Запускаем смену пасхалок при возобновлении активности
        // Первый запуск через EASTER_EGG_INTERVAL_MS
        handler.postDelayed(easterEggRunnable, EASTER_EGG_INTERVAL_MS);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
        }
        // Обязательно останавливаем смену пасхалок при уходе с экрана, чтобы избежать утечек памяти
        handler.removeCallbacks(easterEggRunnable);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
        // Также останавливаем на onDestroy на всякий случай
        handler.removeCallbacks(easterEggRunnable);
    }

    private void startGame(boolean loadSavedGame) {
        Intent intent = new Intent(MainActivity.this, GameActivity.class);
        intent.putExtra("loadSavedGame", loadSavedGame);
        startActivity(intent);
    }

    private void showSettingsOverlay() {
        settingsOverlay.setVisibility(View.VISIBLE);
    }

    private void hideSettingsOverlay() {
        settingsOverlay.setVisibility(View.GONE);
    }

    private void loadSettings() {
        float volume = sharedPreferences.getFloat("musicVolume", 0.5f);
        musicVolumeSlider.setProgress((int) (volume * 100));

        String langCode = sharedPreferences.getString("language", "ru");
        if (langCode.equals("ru")) {
            radioRu.setChecked(true);
        } else {
            radioEn.setChecked(true);
        }
        setLocale(langCode); // Это вызовет recreate() только если язык действительно изменился

        updateContinueButtonState();
    }

    private void updateContinueButtonState() {
        String savedNodeId = sharedPreferences.getString("savedNodeId", null);
        continueButton.setEnabled(savedNodeId != null);
    }

    private void setLocale(String langCode) {
        Resources resources = getResources();
        Configuration config = resources.getConfiguration();
        DisplayMetrics dm = resources.getDisplayMetrics();

        Locale currentLocale;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            currentLocale = config.getLocales().get(0);
        } else {
            currentLocale = config.locale;
        }

        if (!currentLocale.getLanguage().equals(langCode)) {
            Locale newLocale = new Locale(langCode);
            config.setLocale(newLocale);
            resources.updateConfiguration(config, dm);
            sharedPreferences.edit().putString("language", langCode).apply();
            recreate();
        }
    }
}