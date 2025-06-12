package com.example.hellincpro;

import java.util.ArrayList;
import java.util.List;

public class StoryNode {
    private String nodeId;
    private String textResId;
    private int imageResId; // Это поле было добавлено, так как оно использовалось в некоторых конструкторах, но не было объявлено

    // Поля для фиксированных 2-х выборов (или для линейного прохождения с "zagl")
    private String fixedChoice1TextResId;
    private String fixedChoice2TextResId;
    private String fixedNextNodeId1;
    private String fixedNextNodeId2;

    // Поле для динамического списка выборов
    private List<Choise> dynamicChoices;

    // --- КОНСТРУКТОР 1 (для фиксированных 2-х выборов или линейных без изображения) ---
    // Сигнатура: StoryNode(String nodeId, String textResId, String fixedChoice1TextResId, String fixedChoice2TextResId, String fixedNextNodeId1, String fixedNextNodeId2)
    public StoryNode(String nodeId, String textResId, String fixedChoice1TextResId, String fixedChoice2TextResId, String fixedNextNodeId1, String fixedNextNodeId2) {
        this.nodeId = nodeId;
        this.textResId = textResId;
        this.imageResId = 0; // Изображение не предоставлено, по умолчанию 0
        this.fixedChoice1TextResId = fixedChoice1TextResId;
        this.fixedChoice2TextResId = fixedChoice2TextResId;
        this.fixedNextNodeId1 = fixedNextNodeId1;
        this.fixedNextNodeId2 = fixedNextNodeId2;
        this.dynamicChoices = null; // Для этого конструктора нет динамических выборов
    }

    // --- КОНСТРУКТОР 2 (для динамических выборов без изображения) ---
    // Сигнатура: StoryNode(String nodeId, String textResId, List<Choise> dynamicChoices)
    public StoryNode(String nodeId, String textResId, List<Choise> dynamicChoices) {
        this.nodeId = nodeId;
        this.textResId = textResId;
        this.imageResId = 0; // Изображение не предоставлено, по умолчанию 0
        this.dynamicChoices = (dynamicChoices != null) ? dynamicChoices : new ArrayList<>();

        // Обнуляем фиксированные поля, чтобы избежать путаницы
        this.fixedChoice1TextResId = null;
        this.fixedChoice2TextResId = null;
        this.fixedNextNodeId1 = null;
        this.fixedNextNodeId2 = null;
    }

    // --- КОНСТРУКТОР 3 (для линейных узлов с изображением) ---
    // Сигнатура: StoryNode(String nodeId, String textResId, int imageResId, boolean isLinear, String nextNodeId1)
    // Этот конструктор ВАЖЕН, так как он используется для большинства ваших линейных узлов.
    public StoryNode(String nodeId, String textResId, int imageResId, boolean isLinear, String nextNodeId1) {
        this.nodeId = nodeId;
        this.textResId = textResId;
        this.imageResId = imageResId;


        if (isLinear) {
            this.fixedChoice1TextResId = "zagl"; // Маркер для линейного узла
            this.fixedNextNodeId1 = nextNodeId1;
            this.fixedChoice2TextResId = null; // Нет второго выбора
            this.fixedNextNodeId2 = null;
            this.dynamicChoices = null; // Нет динамических выборов
        } else {
            // Если isLinear = false, этот конструктор не должен использоваться для создания узлов
            // с выборами (они должны использовать конструктор с List<Choise>).
            // Инициализируем заглушками, чтобы не было NPE.
            this.fixedChoice1TextResId = null;
            this.fixedNextNodeId1 = null;
            this.fixedChoice2TextResId = null;
            this.fixedNextNodeId2 = null;
            this.dynamicChoices = new ArrayList<>(); // Пустой список динамических выборов
        }
    }

    // --- КОНСТРУКТОР 4 (для динамических узлов с изображением) ---
    // Сигнатура: StoryNode(String nodeId, String textResId, int imageResId, List<Choise> dynamicChoices)
    // Этот конструктор ВАЖЕН, так как он используется для большинства ваших узлов с выборами.
    public StoryNode(String nodeId, String textResId, int imageResId, List<Choise> dynamicChoices) {
        this.nodeId = nodeId;
        this.textResId = textResId;
        this.imageResId = imageResId;
        this.dynamicChoices = (dynamicChoices != null) ? dynamicChoices : new ArrayList<>();

        // Обнуляем фиксированные поля, чтобы избежать путаницы
        this.fixedChoice1TextResId = null;
        this.fixedChoice2TextResId = null;
        this.fixedNextNodeId1 = null;
        this.fixedNextNodeId2 = null;
    }

    // Геттеры для всех полей
    public String getNodeId() { return nodeId; }
    public String getTextResId() { return textResId; }
    public int getImageResId() { return imageResId; }

    public String getFixedChoice1TextResId() { return fixedChoice1TextResId; }
    public String getFixedChoice2TextResId() { return fixedChoice2TextResId; }
    public String getFixedNextNodeId1() { return fixedNextNodeId1; }
    public String getFixedNextNodeId2() { return fixedNextNodeId2; }

    public List<Choise> getDynamicChoices() { return dynamicChoices; }

    public boolean hasDynamicChoices() {
        return dynamicChoices != null && !dynamicChoices.isEmpty();
    }

    public boolean hasFixedChoices() {
        // Узел имеет фиксированные выборы, если fixedChoice1TextResId не null, не "zagl" И fixedChoice2TextResId не null
        return fixedChoice1TextResId != null && !fixedChoice1TextResId.equals("zagl") && fixedChoice2TextResId != null;
    }

    public boolean isLinear() {
        // Узел является линейным, если fixedChoice1TextResId - "zagl" И fixedNextNodeId1 не null
        return fixedChoice1TextResId != null && fixedChoice1TextResId.equals("zagl") && fixedNextNodeId1 != null;
    }
}