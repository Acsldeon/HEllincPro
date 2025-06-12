package com.example.hellincpro;


public class Choise { // Changed Choise to Choice here
    private String textResId; // Ключ к строковому ресурсу для текста выбора
    private String nextNodeId; // ID узла, к которому ведет этот выбор

    public Choise(String textResId, String nextNodeId) { // Changed Choise to Choice here
        this.textResId = textResId;
        this.nextNodeId = nextNodeId;
    }

    public String getTextResId() {
        return textResId;
    }

    public String getNextNodeId() {
        return nextNodeId;
    }
}