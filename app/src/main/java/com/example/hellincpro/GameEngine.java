package com.example.hellincpro;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameEngine {
    private Map<String, StoryNode> storyNodes;
    private String currentNodeId;

    public GameEngine(Context context) {
        this.storyNodes = new HashMap<>();
        initializeStory();
        this.currentNodeId = "nodeIntro1";
    }

    private void initializeStory() {
        // --- Введение --- (Изображения: 1)
        storyNodes.put("nodeIntro1", new StoryNode("nodeIntro1", "intro_year", R.drawable.image_intro, true, "nodeIntro2"));
        storyNodes.put("nodeIntro2", new StoryNode("nodeIntro2", "intro_projectGates", R.drawable.image_intro, true, "nodeIntro3"));
        storyNodes.put("nodeIntro3", new StoryNode("nodeIntro3", "intro_hellPassage", R.drawable.image_intro, true, "nodeIntro4"));
        storyNodes.put("nodeIntro4", new StoryNode("nodeIntro4", "intro_businessPotential", R.drawable.image_intro, true, "nodeIntro5"));
        storyNodes.put("nodeIntro5", new StoryNode("nodeIntro5", "intro_skyscrapers", R.drawable.image_intro, true, "nodeIntro6"));
        storyNodes.put("nodeIntro6", new StoryNode("nodeIntro6", "intro_hellInc", R.drawable.image_intro, true, "nodeIntro7"));
        storyNodes.put("nodeIntro7", new StoryNode("nodeIntro7", "intro_demonsFree", R.drawable.image_intro, true, "nodeIntro8"));
        storyNodes.put("nodeIntro8", new StoryNode("nodeIntro8", "intro_yourGoal", R.drawable.image_intro, true, "nodeOfficeEnterBuilding")); // Переход к входу в офис

        // --- Вход в офис --- (Изображения: 2, 3)
        storyNodes.put("nodeOfficeEnterBuilding", new StoryNode("nodeOfficeEnterBuilding", "officeEnterBuilding", R.drawable.image_office_building_exterior, true, "nodeOfficeEnterElevator"));
        storyNodes.put("nodeOfficeEnterElevator", new StoryNode("nodeOfficeEnterElevator", "officeEnterElevator", R.drawable.image_office_building_exterior, true, "nodeOfficeGoUpstairs"));
        storyNodes.put("nodeOfficeGoUpstairs", new StoryNode("nodeOfficeGoUpstairs", "officeGoUpstairs", R.drawable.image_office_building_exterior, true, "nodeOfficeArriveFloor"));
        storyNodes.put("nodeOfficeArriveFloor", new StoryNode("nodeOfficeArriveFloor", "officeArriveFloor", R.drawable.image_office_building_exterior, true, "nodeOfficeNeedMap"));
        storyNodes.put("nodeOfficeNeedMap", new StoryNode("nodeOfficeNeedMap", "officeNeedMap", R.drawable.image_office_building_exterior, true, "nodeOfficeMeetNewbieHelper"));
        storyNodes.put("nodeOfficeMeetNewbieHelper", new StoryNode("nodeOfficeMeetNewbieHelper", "officeMeetNewbieHelper", R.drawable.image_office_building_exterior, true, "nodeOfficeChoice"));

        // --- Узел выбора после входа в офис (Принять/Отказаться от помощи) --- (Изображение: 4)
        List<Choise> officeChoices = new ArrayList<>();
        officeChoices.add(new Choise("choiceAcceptHelp", "nodeAcceptHelpDecision"));
        officeChoices.add(new Choise("choiceDeclineHelp", "nodeDeclineHelpDecision"));
        storyNodes.put("nodeOfficeChoice", new StoryNode("nodeOfficeChoice", "officeNewbieHelperDialogue", R.drawable.image_office_building_exterior, officeChoices));

        // --- Ветка: Принять помощь (acceptHelp) --- (Изображения: 5, 6)
        storyNodes.put("nodeAcceptHelpDecision", new StoryNode("nodeAcceptHelpDecision", "acceptHelpDecision", R.drawable.image_accept_help, true, "nodeAcceptHelpReply"));
        storyNodes.put("nodeAcceptHelpReply", new StoryNode("nodeAcceptHelpReply", "acceptHelpReply", R.drawable.image_accept_help, true, "nodeAcceptHelpResponse"));
        storyNodes.put("nodeAcceptHelpResponse", new StoryNode("nodeAcceptHelpResponse", "acceptHelpResponse", R.drawable.image_accept_help, true, "nodeAcceptHelpMovingOffices"));
        storyNodes.put("nodeAcceptHelpMovingOffices", new StoryNode("nodeAcceptHelpMovingOffices", "acceptHelpMovingOffices", R.drawable.image_accept_help, true, "nodeAcceptHelpCharlesIntro"));
        storyNodes.put("nodeAcceptHelpCharlesIntro", new StoryNode("nodeAcceptHelpCharlesIntro", "acceptHelpCharlesIntro", R.drawable.image_accept_help, true, "nodeAcceptHelpShakeHands"));
        storyNodes.put("nodeAcceptHelpShakeHands", new StoryNode("nodeAcceptHelpShakeHands", "acceptHelpShakeHands", R.drawable.image_accept_help, true, "nodeAcceptHelpWorkplace"));
        storyNodes.put("nodeAcceptHelpWorkplace", new StoryNode("nodeAcceptHelpWorkplace", "acceptHelpWorkplace", R.drawable.image_accept_help, true, "nodeAcceptHelpStartWork"));
        storyNodes.put("nodeAcceptHelpStartWork", new StoryNode("nodeAcceptHelpStartWork", "acceptHelpStartWork", R.drawable.image_accept_help, true, "nodeWorkMonthAssignment")); // Переход к рабочему месяцу

        // --- Ветка: Отказаться от помощи (declineHelp - Концовка "Блинчик") --- (Изображения: 7)
        storyNodes.put("nodeDeclineHelpDecision", new StoryNode("nodeDeclineHelpDecision", "declineHelpDecision", R.drawable.image_pancake_ending, true, "nodeDeclineHelpYourself"));
        storyNodes.put("nodeDeclineHelpYourself", new StoryNode("nodeDeclineHelpYourself", "declineHelpYourself", R.drawable.image_pancake_ending, true, "nodeDeclineHelpManagerReply"));
        storyNodes.put("nodeDeclineHelpManagerReply", new StoryNode("nodeDeclineHelpManagerReply", "declineHelpManagerReply", R.drawable.image_pancake_ending, true, "nodeDeclineHelpLost"));
        storyNodes.put("nodeDeclineHelpLost", new StoryNode("nodeDeclineHelpLost", "declineHelpLost", R.drawable.image_pancake_ending, true, "nodeDeclineHelpNoResponse"));
        storyNodes.put("nodeDeclineHelpNoResponse", new StoryNode("nodeDeclineHelpNoResponse", "declineHelpNoResponse", R.drawable.image_pancake_ending, true, "nodeDeclineHelpPanic"));
        storyNodes.put("nodeDeclineHelpPanic", new StoryNode("nodeDeclineHelpPanic", "declineHelpPanic", R.drawable.image_pancake_ending, true, "nodeDeclineHelpCrushed"));
        storyNodes.put("nodeDeclineHelpCrushed", new StoryNode("nodeDeclineHelpCrushed", "declineHelpCrushed", R.drawable.image_pancake_ending, true, "nodeDeclineHelpLastThoughts"));
        storyNodes.put("nodeDeclineHelpLastThoughts", new StoryNode("nodeDeclineHelpLastThoughts", "declineHelpLastThoughts", R.drawable.image_pancake_ending, true, "nodeDeclineHelpPancakeEnding"));
        storyNodes.put("nodeDeclineHelpPancakeEnding", new StoryNode("nodeDeclineHelpPancakeEnding", "declineHelpPancakeEnding", R.drawable.image_pancake_ending, true, null)); // Конец игры

        // --- Месяц работы и Беломорье --- (Изображения: 8, 9)
        storyNodes.put("nodeWorkMonthAssignment", new StoryNode("nodeWorkMonthAssignment", "workMonthAssignment", R.drawable.image_work_month, true, "nodeWorkMonthCITSCall"));
        storyNodes.put("nodeWorkMonthCITSCall", new StoryNode("nodeWorkMonthCITSCall", "workMonthCITSCall", R.drawable.image_work_month, true, "nodeWorkMonthBelomoryeContract"));
        storyNodes.put("nodeWorkMonthBelomoryeContract", new StoryNode("nodeWorkMonthBelomoryeContract", "workMonthBelomoryeContract", R.drawable.image_work_month, true, "nodeWorkMonthQuestion"));
        storyNodes.put("nodeWorkMonthQuestion", new StoryNode("nodeWorkMonthQuestion", "workMonthQuestion", R.drawable.image_work_month, true, "nodeWorkMonthSearchOnline"));
        storyNodes.put("nodeWorkMonthSearchOnline", new StoryNode("nodeWorkMonthSearchOnline", "workMonthSearchOnline", R.drawable.image_work_month, true, "nodeWorkMonthArticleDreamnest"));
        storyNodes.put("nodeWorkMonthArticleDreamnest", new StoryNode("nodeWorkMonthArticleDreamnest", "workMonthArticleDreamnest", R.drawable.image_work_month, true, "nodeWorkMonthArticleBelomorye"));
        storyNodes.put("nodeWorkMonthArticleBelomorye", new StoryNode("nodeWorkMonthArticleBelomorye", "workMonthArticleBelomorye", R.drawable.image_work_month, true, "nodeWorkMonthPersonalShock"));
        storyNodes.put("nodeWorkMonthPersonalShock", new StoryNode("nodeWorkMonthPersonalShock", "workMonthPersonalShock", R.drawable.image_work_month, true, "nodeWorkMonthPersonalCatastrophe"));
        storyNodes.put("nodeWorkMonthPersonalCatastrophe", new StoryNode("nodeWorkMonthPersonalCatastrophe", "workMonthPersonalCatastrophe", R.drawable.image_work_month, true, "nodeWorkMonthChoice"));

        // --- Узел выбора: Узнать о Беломорье или забыть --- (Изображение: 10)
        List<Choise> belomoreChoices = new ArrayList<>();
        belomoreChoices.add(new Choise("choiceGoWithCharles", "nodeGoWithCharlesDecision")); // Забыть прошлое (Не копать)
        belomoreChoices.add(new Choise("choiceDigDeeper", "nodeDigDeeperInvestigation")); // Капнуть глубже (Задержаться)
        storyNodes.put("nodeWorkMonthChoice", new StoryNode("nodeWorkMonthChoice", "workMonthChoiceText", R.drawable.image_work_month, belomoreChoices));

        // --- Ветка: Пойти с Чарльзом (Не копать) --- (Изображения: 11, 12, 13)
        storyNodes.put("nodeGoWithCharlesDecision", new StoryNode("nodeGoWithCharlesDecision", "goWithCharlesDecision", R.drawable.with_charl, true, "nodeGoWithCharlesCharlesGreeting"));
        storyNodes.put("nodeGoWithCharlesCharlesGreeting", new StoryNode("nodeGoWithCharlesCharlesGreeting", "goWithCharlesCharlesGreeting", R.drawable.with_charl, true, "nodeGoWithCharlesCharlesRescue"));
        storyNodes.put("nodeGoWithCharlesCharlesRescue", new StoryNode("nodeGoWithCharlesCharlesRescue", "goWithCharlesCharlesRescue", R.drawable.with_charl, true, "nodeGoWithCharlesLiftWarning"));
        storyNodes.put("nodeGoWithCharlesLiftWarning", new StoryNode("nodeGoWithCharlesLiftWarning", "goWithCharlesLiftWarning", R.drawable.with_charl, true, "nodeGoWithCharlesSaved"));
        storyNodes.put("nodeGoWithCharlesSaved", new StoryNode("nodeGoWithCharlesSaved", "goWithCharlesSaved", R.drawable.with_charl, true, "nodeGoWithCharlesExit"));
        storyNodes.put("nodeGoWithCharlesExit", new StoryNode("nodeGoWithCharlesExit", "goWithCharlesExit", R.drawable.with_charl, true, "nodeGoWithCharlesNextDay"));
        storyNodes.put("nodeGoWithCharlesNextDay", new StoryNode("nodeGoWithCharlesNextDay", "goWithCharlesNextDay", R.drawable.with_charl, true, "nodeGoWithCharlesStorageEvent"));
        storyNodes.put("nodeGoWithCharlesStorageEvent", new StoryNode("nodeGoWithCharlesStorageEvent", "goWithCharlesStorageEvent", R.drawable.with_charl, true, "nodeGoWithCharlesStorageDeath"));
        storyNodes.put("nodeGoWithCharlesStorageDeath", new StoryNode("nodeGoWithCharlesStorageDeath", "goWithCharlesStorageDeath", R.drawable.with_charl, true, "nodeGoWithCharlesDeathScene"));storyNodes.put("nodeGoWithCharlesDeathScene", new StoryNode("nodeGoWithCharlesDeathScene", "goWithCharlesDeathScene", R.drawable.with_charl, true, "nodeRebirthIntro1")); // Ведет к перерождению

        // --- Ветка: Капнуть глубже (Задержаться) --- (Изображение: 14)
        storyNodes.put("nodeDigDeeperInvestigation", new StoryNode("nodeDigDeeperInvestigation", "digDeeperInvestigation", R.drawable.img_kopat, true, "nodeDigDeeperFileFound"));
        storyNodes.put("nodeDigDeeperFileFound", new StoryNode("nodeDigDeeperFileFound", "digDeeperFileFound", R.drawable.img_kopat, true, "nodeDigDeeperCharlesInterruption"));
        storyNodes.put("nodeDigDeeperCharlesInterruption", new StoryNode("nodeDigDeeperCharlesInterruption", "digDeeperCharlesInterruption", R.drawable.img_kopat, true, "nodeDigDeeperWorkdayEnd"));
        storyNodes.put("nodeDigDeeperWorkdayEnd", new StoryNode("nodeDigDeeperWorkdayEnd", "digDeeperWorkdayEnd", R.drawable.img_kopat, true, "nodeDigDeeperSatanMeeting1")); // Переход к встрече с Сатаной
        storyNodes.put("nodeDigDeeperSatanMeeting1", new StoryNode("nodeDigDeeperSatanMeeting1", "digDeeperSatanMeeting1", R.drawable.image_sammet, true, "nodeDigDeeperSatanMeeting2"));
        storyNodes.put("nodeDigDeeperSatanMeeting2", new StoryNode("nodeDigDeeperSatanMeeting2", "digDeeperSatanMeeting2", R.drawable.image_sammet, true, "nodeDigDeeperSatanMeeting3"));
        storyNodes.put("nodeDigDeeperSatanMeeting3", new StoryNode("nodeDigDeeperSatanMeeting3", "digDeeperSatanMeeting3", R.drawable.image_sammet, true, "nodeDigDeeperSatanMeeting4"));
        storyNodes.put("nodeDigDeeperSatanMeeting4", new StoryNode("nodeDigDeeperSatanMeeting4", "digDeeperSatanMeeting4", R.drawable.image_sammet, true, "nodeDigDeeperSatanMeeting5"));
        storyNodes.put("nodeDigDeeperSatanMeeting5", new StoryNode("nodeDigDeeperSatanMeeting5", "digDeeperSatanMeeting5", R.drawable.image_sammet, true, "nodeDigDeeperSatanMeeting6"));
        storyNodes.put("nodeDigDeeperSatanMeeting6", new StoryNode("nodeDigDeeperSatanMeeting6", "digDeeperSatanMeeting6", R.drawable.image_sammet, true, "nodeDigDeeperSatanMeeting7"));
        storyNodes.put("nodeDigDeeperSatanMeeting7", new StoryNode("nodeDigDeeperSatanMeeting7", "digDeeperSatanMeeting7", R.drawable.image_sammet, true, "nodeDigDeeperSatanMeeting8"));
        storyNodes.put("nodeDigDeeperSatanMeeting8", new StoryNode("nodeDigDeeperSatanMeeting8", "digDeeperSatanMeeting8", R.drawable.image_sammet, true, "nodeDigDeeperSatanMeeting9"));
        storyNodes.put("nodeDigDeeperSatanMeeting9", new StoryNode("nodeDigDeeperSatanMeeting9", "digDeeperSatanMeeting9", R.drawable.image_sammet, true, "nodeDigDeeperSatanMeeting10"));
        storyNodes.put("nodeDigDeeperSatanMeeting10", new StoryNode("nodeDigDeeperSatanMeeting10", "digDeeperSatanMeeting10", R.drawable.image_sammet, true, "nodeDigDeeperSatanMeeting11"));
        storyNodes.put("nodeDigDeeperSatanMeeting11", new StoryNode("nodeDigDeeperSatanMeeting11", "digDeeperSatanMeeting11", R.drawable.image_sammet, true, "nodeDigDeeperSatanMeeting12"));
        storyNodes.put("nodeDigDeeperSatanMeeting12", new StoryNode("nodeDigDeeperSatanMeeting12", "digDeeperSatanMeeting12", R.drawable.image_sammet, true, "nodeDigDeeperSatanMeeting13"));
        storyNodes.put("nodeDigDeeperSatanMeeting13", new StoryNode("nodeDigDeeperSatanMeeting13", "digDeeperSatanMeeting13", R.drawable.image_sammet, true, "nodeRebirthIntro1")); // Ведет к перерождению

        // --- Возрождение в Ад (после различных смертей) --- (Изображения: 15, 16)
        storyNodes.put("nodeRebirthIntro1", new StoryNode("nodeRebirthIntro1", "rebirthIntro1", R.drawable.image_to_hell, true, "nodeRebirthIntro2"));
        storyNodes.put("nodeRebirthIntro2", new StoryNode("nodeRebirthIntro2", "rebirthIntro2", R.drawable.image_to_hell, true, "nodeRebirthIntro3"));
        storyNodes.put("nodeRebirthIntro3", new StoryNode("nodeRebirthIntro3", "rebirthIntro3", R.drawable.image_to_hell, true, "nodeRebirthIntro4"));
        storyNodes.put("nodeRebirthIntro4", new StoryNode("nodeRebirthIntro4", "rebirthIntro4", R.drawable.image_to_hell, true, "nodeRebirthIntro5"));
        storyNodes.put("nodeRebirthIntro5", new StoryNode("nodeRebirthIntro5", "rebirthIntro5", R.drawable.image_to_hell, true, "nodeRebirthChoice"));

        // --- Узел выбора комнаты в Аду (Одиночная/Двойная) --- (Изображение: 17)
        List<Choise> rebirthCellChoices = new ArrayList<>();
        rebirthCellChoices.add(new Choise("choiceSingleCell", "nodeSingleCellChosen")); // Одиночная камера
        rebirthCellChoices.add(new Choise("choiceDoubleCell", "nodePhilosopherCellIntro1")); // Двойная камера (ведет к философу)
        storyNodes.put("nodeRebirthChoice", new StoryNode("nodeRebirthChoice", "rebirthChoiceText", R.drawable.mage_door, rebirthCellChoices));

        // --- Ветка: Одиночная камера --- (Изображения: 18, 19)
        storyNodes.put("nodeSingleCellChosen", new StoryNode("nodeSingleCellChosen", "singleCellChosen", R.drawable.image_odinoch, true, "nodeSingleCellRoom"));
        storyNodes.put("nodeSingleCellRoom", new StoryNode("nodeSingleCellRoom", "singleCellRoom", R.drawable.image_odinoch, true, "nodeSingleCellPoster"));
        storyNodes.put("nodeSingleCellPoster", new StoryNode("nodeSingleCellPoster", "singleCellPoster", R.drawable.image_odinoch, true, "nodeSingleCellSleep"));
        storyNodes.put("nodeSingleCellSleep", new StoryNode("nodeSingleCellSleep", "singleCellSleep", R.drawable.image_odinoch, true, "nodeSingleCellAwaken"));
        storyNodes.put("nodeSingleCellAwaken", new StoryNode("nodeSingleCellAwaken", "singleCellAwaken", R.drawable.image_odinoch, true, "nodeSingleCellMotivationCall"));
        storyNodes.put("nodeSingleCellMotivationCall", new StoryNode("nodeSingleCellMotivationCall", "singleCellMotivationCall", R.drawable.image_odinoch, true, "nodeSingleCellGoToSquare"));
        storyNodes.put("nodeSingleCellGoToSquare", new StoryNode("nodeSingleCellGoToSquare", "singleCellGoToSquare", R.drawable.image_odinoch, true, "nodeSingleCellDemonPresentation"));
        storyNodes.put("nodeSingleCellDemonPresentation", new StoryNode("nodeSingleCellDemonPresentation", "singleCellDemonPresentation", R.drawable.image_odinoch, true, "nodeSingleCellIllusion"));
        storyNodes.put("nodeSingleCellIllusion", new StoryNode("nodeSingleCellIllusion", "singleCellIllusion", R.drawable.image_odinoch, true, "nodeSingleCellStudyBeam"));
        storyNodes.put("nodeSingleCellStudyBeam", new StoryNode("nodeSingleCellStudyBeam", "singleCellStudyBeam", R.drawable.image_odinoch, true, null)); // Куда ведет эта ветка дальше? Возможно, к общей точке с архивом или новой ветке. Установил null как заглушку.

        // --- Ветка: Двойная камера (Философ) --- (Изображения: 20)
        // Использование ранее определенных строковых ресурсов для Философа
        storyNodes.put("nodePhilosopherCellIntro1", new StoryNode("nodePhilosopherCellIntro1", "storyPhilosopherCellIntro1", R.drawable.image_meet_filos, true, "nodePhilosopherCellIntro2"));
        storyNodes.put("nodePhilosopherCellIntro2", new StoryNode("nodePhilosopherCellIntro2", "storyPhilosopherCellIntro2", R.drawable.image_meet_filos, true, "nodePhilosopherCellIntro3"));
        storyNodes.put("nodePhilosopherCellIntro3", new StoryNode("nodePhilosopherCellIntro3", "storyPhilosopherCellIntro3", R.drawable.image_meet_filos, true, "nodePhilosopherCellIntro4"));
        storyNodes.put("nodePhilosopherCellIntro4", new StoryNode("nodePhilosopherCellIntro4", "storyPhilosopherCellIntro4", R.drawable.image_meet_filos, true, "nodePhilosopherCellIntro5"));
        storyNodes.put("nodePhilosopherCellIntro5", new StoryNode("nodePhilosopherCellIntro5", "storyPhilosopherCellIntro5", R.drawable.image_meet_filos, true, "nodePhilosopherRevelation1a"));
        storyNodes.put("nodePhilosopherRevelation1a", new StoryNode("nodePhilosopherRevelation1a", "storyPhilosopherRevelation1a", R.drawable.image_meet_filos, true, "nodePhilosopherRevelation1b"));
        storyNodes.put("nodePhilosopherRevelation1b", new StoryNode("nodePhilosopherRevelation1b", "storyPhilosopherRevelation1b", R.drawable.image_meet_filos, true, "nodePhilosopherRevelation1c"));
        storyNodes.put("nodePhilosopherRevelation1c", new StoryNode("nodePhilosopherRevelation1c", "storyPhilosopherRevelation1c", R.drawable.image_meet_filos, true, "nodePhilosopherRevelation1d"));
        storyNodes.put("nodePhilosopherRevelation1d", new StoryNode("nodePhilosopherRevelation1d", "storyPhilosopherRevelation1d", R.drawable.image_meet_filos, true, "nodePhilosopherRevelation2a"));
        storyNodes.put("nodePhilosopherRevelation2a", new StoryNode("nodePhilosopherRevelation2a", "storyPhilosopherRevelation2a", R.drawable.image_meet_filos, true, "nodePhilosopherRevelation2b"));
        storyNodes.put("nodePhilosopherRevelation2b", new StoryNode("nodePhilosopherRevelation2b", "storyPhilosopherRevelation2b", R.drawable.image_meet_filos, true, "nodePhilosopherRevelation2c"));
        storyNodes.put("nodePhilosopherRevelation2c", new StoryNode("nodePhilosopherRevelation2c", "storyPhilosopherRevelation2c", R.drawable.image_meet_filos, true, "nodePhilosopherRevelation3"));
        storyNodes.put("nodePhilosopherRevelation3", new StoryNode("nodePhilosopherRevelation3", "storyPhilosopherRevelation3", R.drawable.image_meet_filos, true, "nodePhilosopherRevelation4"));
        storyNodes.put("nodePhilosopherRevelation4", new StoryNode("nodePhilosopherRevelation4", "storyPhilosopherRevelation4", R.drawable.image_meet_filos, true, "nodePhilosopherPlan1"));
        storyNodes.put("nodePhilosopherPlan1", new StoryNode("nodePhilosopherPlan1", "storyPhilosopherPlan1", R.drawable.image_meet_filos, true, "nodePhilosopherPlan2"));
        storyNodes.put("nodePhilosopherPlan2", new StoryNode("nodePhilosopherPlan2", "storyPhilosopherPlan2", R.drawable.image_meet_filos, true, "nodePhilosopherChoice"));

        // --- Узел выбора после Философа (Атаковать / Искать Молот) --- (Изображение: 21)
        List<Choise> philosopherChoices = new ArrayList<>();
        philosopherChoices.add(new Choise("choiceAttackDirectly", "nodeAttackDirectlyGatherArmy1"));
        philosopherChoices.add(new Choise("choiceSeekHammer", "nodeSeekHammerJourney1"));
        storyNodes.put("nodePhilosopherChoice", new StoryNode("nodePhilosopherChoice", "storyPhilosopherChoiceText", R.drawable.image_meet_filos, philosopherChoices));

        // --- Ветка: Организовать прямой штурм --- (Изображения: 22, 23)
        storyNodes.put("nodeAttackDirectlyGatherArmy1", new StoryNode("nodeAttackDirectlyGatherArmy1", "attackDirectlyGatherArmy1", R.drawable.image_sturm, true, "nodeAttackDirectlyGatherArmy2"));
        storyNodes.put("nodeAttackDirectlyGatherArmy2", new StoryNode("nodeAttackDirectlyGatherArmy2", "attackDirectlyGatherArmy2", R.drawable.image_meet_filos, true, "nodeAttackDirectlyTrain"));
        storyNodes.put("nodeAttackDirectlyTrain", new StoryNode("nodeAttackDirectlyTrain", "attackDirectlyTrain", R.drawable.image_meet_filos, true, "nodeAttackDirectlyBattle1"));
        storyNodes.put("nodeAttackDirectlyBattle1", new StoryNode("nodeAttackDirectlyBattle1", "attackDirectlyBattle1", R.drawable.image_meet_filos, true, "nodeAttackDirectlyBattle2"));
        storyNodes.put("nodeAttackDirectlyBattle2", new StoryNode("nodeAttackDirectlyBattle2", "attackDirectlyBattle2", R.drawable.image_meet_filos, true, "nodeAttackDirectlyArchdemons"));
        storyNodes.put("nodeAttackDirectlyArchdemons", new StoryNode("nodeAttackDirectlyArchdemons", "attackDirectlyArchdemons", R.drawable.image_meet_filos, true, "nodeAttackDirectlyGates"));
        storyNodes.put("nodeAttackDirectlyGates", new StoryNode("nodeAttackDirectlyGates", "attackDirectlyGates", R.drawable.image_meet_filos, true, "nodeAttackDirectlyPhilosopherSacrifice"));
        storyNodes.put("nodeAttackDirectlyPhilosopherSacrifice", new StoryNode("nodeAttackDirectlyPhilosopherSacrifice", "attackDirectlyPhilosopherSacrifice", R.drawable.image_meet_filos, true, "nodeAttackDirectlyChoice"));

        // --- Узел выбора после прямого штурма ---
        List<Choise> attackDirectlyEndChoices = new ArrayList<>();
        attackDirectlyEndChoices.add(new Choise("choiceBreakThroughGates", "nodeBreakThroughGatesEnding1"));
        storyNodes.put("nodeAttackDirectlyChoice", new StoryNode("nodeAttackDirectlyChoice", "attackDirectlyChoiceText", R.drawable.image_meet_filos, attackDirectlyEndChoices));

        // --- Ветка: Прорваться к Вратам Энергии (Концовка) ---
        storyNodes.put("nodeBreakThroughGatesEnding1", new StoryNode("nodeBreakThroughGatesEnding1", "breakThroughGatesEnding1", R.drawable.image_gate, true, "nodeBreakThroughGatesEnding2"));
        storyNodes.put("nodeBreakThroughGatesEnding2", new StoryNode("nodeBreakThroughGatesEnding2", "breakThroughGatesEnding2", R.drawable.image_gate, true, "nodeBreakThroughGatesEnding3"));
        storyNodes.put("nodeBreakThroughGatesEnding3", new StoryNode("nodeBreakThroughGatesEnding3", "breakThroughGatesEnding3", R.drawable.image_gate, true, null)); // Конец игры

        // --- Ветка: Искать Молот Проклятия --- (Изображения: 24, 25, 26, 27)
        storyNodes.put("nodeSeekHammerJourney1", new StoryNode("nodeSeekHammerJourney1", "seekHammerJourney1", R.drawable.image_work_month, true, "nodeSeekHammerJourney2"));
        storyNodes.put("nodeSeekHammerJourney2", new StoryNode("nodeSeekHammerJourney2", "seekHammerJourney2", R.drawable.image_work_month, true, "nodeSeekHammerJourney3"));
        storyNodes.put("nodeSeekHammerJourney3", new StoryNode("nodeSeekHammerJourney3", "seekHammerJourney3", R.drawable.image_work_month, true, "nodeSeekHammerNightmare"));
        storyNodes.put("nodeSeekHammerNightmare", new StoryNode("nodeSeekHammerNightmare", "seekHammerNightmare", R.drawable.image_work_month, true, "nodeSeekHammerChallenge"));
        storyNodes.put("nodeSeekHammerChallenge", new StoryNode("nodeSeekHammerChallenge", "seekHammerChallenge", R.drawable.image_work_month, true, "nodeSeekHammerAncientDemons"));
        storyNodes.put("nodeSeekHammerAncientDemons", new StoryNode("nodeSeekHammerAncientDemons", "seekHammerAncientDemons", R.drawable.image_work_month, true, "nodeSeekHammerSurvivalSkills"));
        storyNodes.put("nodeSeekHammerSurvivalSkills", new StoryNode("nodeSeekHammerSurvivalSkills", "seekHammerSurvivalSkills", R.drawable.image_work_month, true, "nodeSeekHammerMentalLink"));
        storyNodes.put("nodeSeekHammerMentalLink", new StoryNode("nodeSeekHammerMentalLink", "seekHammerMentalLink", R.drawable.image_work_month, true, "nodeSeekHammerHammerNature"));
        storyNodes.put("nodeSeekHammerHammerNature", new StoryNode("nodeSeekHammerHammerNature", "seekHammerHammerNature", R.drawable.image_work_month, true, "nodeSeekHammerFindHammer"));
        storyNodes.put("nodeSeekHammerFindHammer", new StoryNode("nodeSeekHammerFindHammer", "seekHammerFindHammer", R.drawable.image_work_month, true, "nodeSeekHammerArchdemonAttack"));
        storyNodes.put("nodeSeekHammerArchdemonAttack", new StoryNode("nodeSeekHammerArchdemonAttack", "seekHammerArchdemonAttack", R.drawable.image_work_month, true, "nodeSeekHammerFinalBattle"));
        storyNodes.put("nodeSeekHammerFinalBattle", new StoryNode("nodeSeekHammerFinalBattle", "seekHammerFinalBattle", R.drawable.image_work_month, true, "nodeSeekHammerChoice"));

        // --- Узел выбора после нахождения Молота ---
        List<Choise> hammerChoices = new ArrayList<>();
        hammerChoices.add(new Choise("choiceUseHammerToDestroy", "nodeUseHammerDestroy1"));
        hammerChoices.add(new Choise("choiceUseHammerToOpenPortal", "nodeUseHammerOpenPortal1"));
        storyNodes.put("nodeSeekHammerChoice", new StoryNode("nodeSeekHammerChoice", "seekHammerChoiceText", R.drawable.image_work_month, hammerChoices));

        // --- Ветка: Использовать Молот для уничтожения Врат (Концовка) ---
        storyNodes.put("nodeUseHammerDestroy1", new StoryNode("nodeUseHammerDestroy1", "useHammerDestroy1", R.drawable.image_molot2, true, "nodeUseHammerDestroy2"));
        storyNodes.put("nodeUseHammerDestroy2", new StoryNode("nodeUseHammerDestroy2", "useHammerDestroy2", R.drawable.image_molot2, true, "nodeUseHammerDestroy3"));
        storyNodes.put("nodeUseHammerDestroy3", new StoryNode("nodeUseHammerDestroy3", "useHammerDestroy3", R.drawable.image_molot2, true, null)); // Конец игры

        // --- Ветка: Использовать Молот для открытия портала (Концовка с Сатаной в будущем) ---
        storyNodes.put("nodeUseHammerOpenPortal1", new StoryNode("nodeUseHammerOpenPortal1", "useHammerOpenPortal1", R.drawable.image_gate, true, "nodeUseHammerOpenPortal2"));
        storyNodes.put("nodeUseHammerOpenPortal2", new StoryNode("nodeUseHammerOpenPortal2", "useHammerOpenPortal2", R.drawable.image_gate, true, "nodeUseHammerOpenPortal3"));
        storyNodes.put("nodeUseHammerOpenPortal3", new StoryNode("nodeUseHammerOpenPortal3", "useHammerOpenPortal3", R.drawable.image_deemon_end , true, "nodeUseHammerOpenPortal4"));
        storyNodes.put("nodeUseHammerOpenPortal4", new StoryNode("nodeUseHammerOpenPortal4", "useHammerOpenPortal4", R.drawable.image_deemon_end ,true, "nodeCatastrophicFuture1")); // Ведет к будущему

        // --- Катастрофическое будущее (Новая концовка) ---
        storyNodes.put("nodeCatastrophicFuture1", new StoryNode("nodeCatastrophicFuture1", "catastrophicFuture1", R.drawable.image_catastrofe, true, "nodeCatastrophicFuture2"));
        storyNodes.put("nodeCatastrophicFuture2", new StoryNode("nodeCatastrophicFuture2", "catastrophicFuture2", R.drawable.image_catastrofe, true, "nodeCatastrophicFuture3"));
        storyNodes.put("nodeCatastrophicFuture3", new StoryNode("nodeCatastrophicFuture3", "catastrophicFuture3", R.drawable.image_catastrofe, true, "nodeCatastrophicFuture4"));
        storyNodes.put("nodeCatastrophicFuture4", new StoryNode("nodeCatastrophicFuture4", "catastrophicFuture4", R.drawable.image_catastrofe, true, "nodeCatastrophicFuture5"));
        storyNodes.put("nodeCatastrophicFuture5", new StoryNode("nodeCatastrophicFuture5", "catastrophicFuture5", R.drawable.image_catastrofe, true, "nodeCatastrophicFuture6"));
        storyNodes.put("nodeCatastrophicFuture6", new StoryNode("nodeCatastrophicFuture6", "catastrophicFuture6", R.drawable.image_catastrofe, true, "nodeCatastrophicFuture7"));
        storyNodes.put("nodeCatastrophicFuture7", new StoryNode("nodeCatastrophicFuture7", "catastrophicFuture7", R.drawable.image_catastrofe, true, null)); // Конец игры


        //Жизнь в Адском архиве
        storyNodes.put("nodeHellArchiveIntroA", new StoryNode("nodeHellArchiveIntroA", "hellArchiveIntroA", R.drawable.image_hell_arhive, true, "nodeHellArchiveIntroB"));
        storyNodes.put("nodeHellArchiveIntroB", new StoryNode("nodeHellArchiveIntroB", "hellArchiveIntroB", R.drawable.image_hell_arhive, true, "nodeHellArchiveIntroC"));
        storyNodes.put("nodeHellArchiveIntroC", new StoryNode("nodeHellArchiveIntroC", "hellArchiveIntroC", R.drawable.image_hell_arhive, true, "nodeHellArchiveIntroD"));
        storyNodes.put("nodeHellArchiveIntroD", new StoryNode("nodeHellArchiveIntroD", "hellArchiveIntroD", R.drawable.image_hell_arhive, true, "nodeHellArchiveAnomalyA"));
        storyNodes.put("nodeHellArchiveAnomalyA", new StoryNode("nodeHellArchiveAnomalyA", "hellArchiveAnomalyA", R.drawable.image_hell_arhive, true, "nodeHellArchiveAnomalyB"));
        storyNodes.put("nodeHellArchiveAnomalyB", new StoryNode("nodeHellArchiveAnomalyB", "hellArchiveAnomalyB", R.drawable.image_hell_arhive, true, "nodeHellArchiveAnomalyD"));
        storyNodes.put("nodeHellArchiveAnomalyD", new StoryNode("nodeHellArchiveAnomalyD", "hellArchiveAnomalyD", R.drawable.image_hell_arhive, true, "nodeHellArchiveAnomalyE"));
        storyNodes.put("nodeHellArchiveAnomalyE", new StoryNode("nodeHellArchiveAnomalyE", "hellArchiveAnomalyE", R.drawable.image_hell_arhive, true, "nodeHellArchiveChoice"));

        //Узел выбора в Адском архиве
        List<Choise> hellArchiveChoices = new ArrayList<>();
        hellArchiveChoices.add(new Choise("choiceSubmitSystem", "nodeSubmitSystemEnding1")); // Смириться
        hellArchiveChoices.add(new Choise("choiceSeekGlitch", "nodeSeekGlitchInvestigation1")); // Искать сбой
        storyNodes.put("nodeHellArchiveChoice", new StoryNode("nodeHellArchiveChoice", "hellArchiveChoiceText", R.drawable.image_hell_arhive, hellArchiveChoices));

        //Ветка: Смириться и стать частью системы (Концовка)
        storyNodes.put("nodeSubmitSystemEnding1", new StoryNode("nodeSubmitSystemEnding1", "submitSystemEnding1", R.drawable.image_work_month, true, "nodeSubmitSystemEnding2"));
        storyNodes.put("nodeSubmitSystemEnding2", new StoryNode("nodeSubmitSystemEnding2", "submitSystemEnding2", R.drawable.image_work_month, true, "nodeSubmitSystemEnding3"));
        storyNodes.put("nodeSubmitSystemEnding3", new StoryNode("nodeSubmitSystemEnding3", "submitSystemEnding3", R.drawable.image_work_month, true, "nodeSubmitSystemEnding4"));
        storyNodes.put("nodeSubmitSystemEnding4", new StoryNode("nodeSubmitSystemEnding4", "submitSystemEnding4", R.drawable.image_work_month, true, null)); // Конец игры

        //Ветка: Искать "сбой" в системе Ада
        storyNodes.put("nodeSeekGlitchInvestigation1", new StoryNode("nodeSeekGlitchInvestigation1", "seekGlitchInvestigation1", R.drawable.image_find_sboi, true, "nodeSeekGlitchInvestigation2"));
        storyNodes.put("nodeSeekGlitchInvestigation2", new StoryNode("nodeSeekGlitchInvestigation2", "seekGlitchInvestigation2", R.drawable.image_find_sboi, true, "nodeSeekGlitchSoulsArtist1"));
        storyNodes.put("nodeSeekGlitchSoulsArtist1", new StoryNode("nodeSeekGlitchSoulsArtist1", "seekGlitchSoulsArtist1", R.drawable.image_find_sboi, true, "nodeSeekGlitchSoulsArtist2"));
        storyNodes.put("nodeSeekGlitchSoulsArtist2", new StoryNode("nodeSeekGlitchSoulsArtist2", "seekGlitchSoulsArtist2", R.drawable.image_find_sboi, true, "nodeSeekGlitchSoulsArtist3"));
        storyNodes.put("nodeSeekGlitchSoulsArtist3", new StoryNode("nodeSeekGlitchSoulsArtist3", "seekGlitchSoulsArtist3", R.drawable.image_find_sboi, true, "nodeSeekGlitchProtocol57_1"));
        storyNodes.put("nodeSeekGlitchProtocol57_1", new StoryNode("nodeSeekGlitchProtocol57_1", "seekGlitchProtocol57_1", R.drawable.image_find_sboi, true, "nodeSeekGlitchProtocol57_2"));
        storyNodes.put("nodeSeekGlitchProtocol57_2", new StoryNode("nodeSeekGlitchProtocol57_2", "seekGlitchProtocol57_2", R.drawable.image_find_sboi, true, "nodeSeekGlitchProtocol57_3"));
        storyNodes.put("nodeSeekGlitchProtocol57_3", new StoryNode("nodeSeekGlitchProtocol57_3", "seekGlitchProtocol57_3", R.drawable.image_find_sboi, true, "nodeSeekGlitchProtocol57_4"));
        storyNodes.put("nodeSeekGlitchProtocol57_4", new StoryNode("nodeSeekGlitchProtocol57_4", "seekGlitchProtocol57_4", R.drawable.image_find_sboi, true, "nodeSeekGlitchProtocol57_5"));
        storyNodes.put("nodeSeekGlitchProtocol57_5", new StoryNode("nodeSeekGlitchProtocol57_5", "seekGlitchProtocol57_5", R.drawable.image_find_sboi, true, "nodeSeekGlitchProtocol57_6"));
        storyNodes.put("nodeSeekGlitchProtocol57_6", new StoryNode("nodeSeekGlitchProtocol57_6", "seekGlitchProtocol57_6", R.drawable.image_find_sboi, true, "nodeSeekGlitchChoice"));

        //Узел выбора после Протокола 57
        List<Choise> protocol57Choices = new ArrayList<>();
        protocol57Choices.add(new Choise("choiceRiskActivateProtocol", "nodeRiskActivateProtocol1")); // Рискнуть и активировать
        protocol57Choices.add(new Choise("choiceExposeHellSystem", "nodeExposeHellSystem1")); // Разоблачить
        storyNodes.put("nodeSeekGlitchChoice", new StoryNode("nodeSeekGlitchChoice", "seekGlitchChoiceText", R.drawable.image_find_sboi, protocol57Choices));

        //Рискнуть и активировать протокол
        storyNodes.put("nodeRiskActivateProtocol1", new StoryNode("nodeRiskActivateProtocol1", "riskActivateProtocol1", R.drawable.image_work_month, true, "nodeRiskActivateProtocol2"));
        storyNodes.put("nodeRiskActivateProtocol2", new StoryNode("nodeRiskActivateProtocol2", "riskActivateProtocol2", R.drawable.image_work_month, true, "nodeRiskActivateProtocol3"));
        storyNodes.put("nodeRiskActivateProtocol3", new StoryNode("nodeRiskActivateProtocol3", "riskActivateProtocol3", R.drawable.image_work_month, true, "nodeRiskActivateProtocol4"));
        storyNodes.put("nodeRiskActivateProtocol4", new StoryNode("nodeRiskActivateProtocol4", "riskActivateProtocol4", R.drawable.image_work_month, true, "nodeRiskActivateProtocol5"));
        storyNodes.put("nodeRiskActivateProtocol5", new StoryNode("nodeRiskActivateProtocol5", "riskActivateProtocol5", R.drawable.image_work_month, true, "nodeRiskActivateProtocol6"));
        storyNodes.put("nodeRiskActivateProtocol6", new StoryNode("nodeRiskActivateProtocol6", "riskActivateProtocol6", R.drawable.image_work_month, true, "nodeRiskActivateProtocol7")); // Изображение с Чарльзом и пистолетом
        storyNodes.put("nodeRiskActivateProtocol7", new StoryNode("nodeRiskActivateProtocol7", "riskActivateProtocol7", R.drawable.image_work_month, true, "nodeAfterGunshotChoice")); // Ведет к выбору после выстрела

        // --- Ветка: Разоблачить систему Ада
        storyNodes.put("nodeExposeHellSystem1", new StoryNode("nodeExposeHellSystem1", "exposeHellSystem1", R.drawable.image_find_sboi, true, "nodeExposeHellSystem2"));
        storyNodes.put("nodeExposeHellSystem2", new StoryNode("nodeExposeHellSystem2", "exposeHellSystem2", R.drawable.image_find_sboi, true, "nodeExposeHellSystem3"));
        storyNodes.put("nodeExposeHellSystem3", new StoryNode("nodeExposeHellSystem3", "exposeHellSystem3", R.drawable.image_find_sboi, true, "nodeExposeHellSystem4"));
        storyNodes.put("nodeExposeHellSystem4", new StoryNode("nodeExposeHellSystem4", "exposeHellSystem4", R.drawable.image_find_sboi, true, "nodeExposeHellSystem5"));
        storyNodes.put("nodeExposeHellSystem5", new StoryNode("nodeExposeHellSystem5", "exposeHellSystem5", R.drawable.image_find_sboi, true, "nodeExposeHellSystem6"));
        storyNodes.put("nodeExposeHellSystem6", new StoryNode("nodeExposeHellSystem6", "exposeHellSystem6", R.drawable.image_find_sboi, true, "nodeExposeHellSystem7"));
        storyNodes.put("nodeExposeHellSystem7", new StoryNode("nodeExposeHellSystem7", "exposeHellSystem7", R.drawable.image_find_sboi, true, null)); // Конец игры

        //После выстрела
        storyNodes.put("nodeAfterGunshotLucky", new StoryNode("nodeAfterGunshotLucky", "afterGunshotLucky", R.drawable.image_gun, true, "nodeAfterGunshotPassed"));
        storyNodes.put("nodeAfterGunshotPassed", new StoryNode("nodeAfterGunshotPassed", "afterGunshotPassed", R.drawable.image_gun, true, "nodeAfterGunshotPuzzle"));
        storyNodes.put("nodeAfterGunshotPuzzle", new StoryNode("nodeAfterGunshotPuzzle", "afterGunshotPuzzle", R.drawable.image_gun, true, "nodeAfterGunshotParadisePurpose"));
        storyNodes.put("nodeAfterGunshotParadisePurpose", new StoryNode("nodeAfterGunshotParadisePurpose", "afterGunshotParadisePurpose", R.drawable.image_gun, true, "nodeAfterGunshotChoice"));

        //Узел выбора после выстрела
        List<Choise> afterGunshotChoices = new ArrayList<>();
        afterGunshotChoices.add(new Choise("choiceAnger", "nodeAngerEndingAction")); // Ярость
        afterGunshotChoices.add(new Choise("choiceRegret", "nodeRegretEndingThought")); // Сожаление
        afterGunshotChoices.add(new Choise("choiceBeResident", "nodeBeResidentDialogue1")); // Стать простым жителем
        afterGunshotChoices.add(new Choise("choiceBeCreator", "nodeBeCreatorDialogue1")); // Стать творцом
        storyNodes.put("nodeAfterGunshotChoice", new StoryNode("nodeAfterGunshotChoice", "afterGunshotChoiceText", R.drawable.image_gun, afterGunshotChoices));

        //Ярость (Концовка)
        storyNodes.put("nodeAngerEndingAction", new StoryNode("nodeAngerEndingAction", "angerEndingAction", R.drawable.image_gun, true, "nodeAngerEndingConsequence"));
        storyNodes.put("nodeAngerEndingConsequence", new StoryNode("nodeAngerEndingConsequence", "angerEndingConsequence", R.drawable.image_gun, true, null)); // Конец игры

        //Сожаление (Концовка)
        storyNodes.put("nodeRegretEndingThought", new StoryNode("nodeRegretEndingThought", "regretEndingThought", R.drawable.image_gun, true, "nodeRegretEndingShot"));
        storyNodes.put("nodeRegretEndingShot", new StoryNode("nodeRegretEndingShot", "regretEndingShot", R.drawable.image_gun, true, "nodeRegretEndingPain"));
        storyNodes.put("nodeRegretEndingPain", new StoryNode("nodeRegretEndingPain", "regretEndingPain", R.drawable.image_gun, true, "nodeRegretEndingDissolve"));
        storyNodes.put("nodeRegretEndingDissolve", new StoryNode("nodeRegretEndingDissolve", "regretEndingDissolve", R.drawable.image_gun, true, "nodeRegretEndingDialogue1"));
        storyNodes.put("nodeRegretEndingDialogue1", new StoryNode("nodeRegretEndingDialogue1", "regretEndingDialogue1", R.drawable.image_gun, true, "nodeRegretEndingDialogue2"));
        storyNodes.put("nodeRegretEndingDialogue2", new StoryNode("nodeRegretEndingDialogue2", "regretEndingDialogue2", R.drawable.image_gun, true, "nodeRegretEndingDialogue3"));
        storyNodes.put("nodeRegretEndingDialogue3", new StoryNode("nodeRegretEndingDialogue3", "regretEndingDialogue3", R.drawable.image_gun, true, "nodeRegretEndingDialogue4"));
        storyNodes.put("nodeRegretEndingDialogue4", new StoryNode("nodeRegretEndingDialogue4", "regretEndingDialogue4", R.drawable.image_gun, true, null)); // Конец игры

        //Стать простым жителем (Концовка)
        storyNodes.put("nodeBeResidentDialogue1", new StoryNode("nodeBeResidentDialogue1", "beResidentDialogue1", R.drawable.image_liver, true, "nodeBeResidentDialogue2"));
        storyNodes.put("nodeBeResidentDialogue2", new StoryNode("nodeBeResidentDialogue2", "beResidentDialogue2", R.drawable.image_liver, true, "nodeBeResidentLife"));
        storyNodes.put("nodeBeResidentLife", new StoryNode("nodeBeResidentLife", "beResidentLife", R.drawable.image_liver, true, "nodeBeResidentHomeNews"));
        storyNodes.put("nodeBeResidentHomeNews", new StoryNode("nodeBeResidentHomeNews", "beResidentHomeNews", R.drawable.image_liver, true, "nodeBeResidentNewsReport"));
        storyNodes.put("nodeBeResidentNewsReport", new StoryNode("nodeBeResidentNewsReport", "beResidentNewsReport", R.drawable.image_liver, true, "nodeBeResidentNuclear"));
        storyNodes.put("nodeBeResidentNuclear", new StoryNode("nodeBeResidentNuclear", "beResidentNuclear", R.drawable.image_liver, true, "nodeBeResidentBelomorye"));
        storyNodes.put("nodeBeResidentBelomorye", new StoryNode("nodeBeResidentBelomorye", "beResidentBelomorye", R.drawable.image_liver, true, "nodeBeResidentSatanReveal1"));
        storyNodes.put("nodeBeResidentSatanReveal1", new StoryNode("nodeBeResidentSatanReveal1", "beResidentSatanReveal1", R.drawable.image_liver, true, "nodeBeResidentSatanReveal2"));
        storyNodes.put("nodeBeResidentSatanReveal2", new StoryNode("nodeBeResidentSatanReveal2", "beResidentSatanReveal2", R.drawable.image_liver, true, "nodeBeResidentSatanReveal3"));
        storyNodes.put("nodeBeResidentSatanReveal3", new StoryNode("nodeBeResidentSatanReveal3", "beResidentSatanReveal3", R.drawable.image_liver, true, null)); // Конец игры

        //Стать творцом (Концовка)
        storyNodes.put("nodeBeCreatorDialogue1", new StoryNode("nodeBeCreatorDialogue1", "beCreatorDialogue1", R.drawable.image_philosofer, true, "nodeBeCreatorWorkplace"));
        storyNodes.put("nodeBeCreatorWorkplace", new StoryNode("nodeBeCreatorWorkplace", "beCreatorWorkplace", R.drawable.image_philosofer, true, "nodeBeCreatorNewJob"));
        storyNodes.put("nodeBeCreatorNewJob", new StoryNode("nodeBeCreatorNewJob", "beCreatorNewJob", R.drawable.image_philosofer, true, "nodeBeCreatorElite"));
        storyNodes.put("nodeBeCreatorElite", new StoryNode("nodeBeCreatorElite", "beCreatorElite", R.drawable.image_philosofer, true, null)); // Конец игры
    }

    public StoryNode getCurrentNode() {
        return storyNodes.get(currentNodeId);
    }

    public void choose(int choiceNum) {
        StoryNode currentNode = getCurrentNode();
        if (currentNode == null) {
            return;
        }

        if (currentNode.isLinear()) {
            if (currentNode.getFixedNextNodeId1() != null) {
                currentNodeId = currentNode.getFixedNextNodeId1();
            } else {
                currentNodeId = null;
            }
        } else if (currentNode.hasDynamicChoices()) {
            List<Choise> choices = currentNode.getDynamicChoices();
            if (choiceNum > 0 && choiceNum <= choices.size()) {
                currentNodeId = choices.get(choiceNum - 1).getNextNodeId();
            } else {
                Log.e("GameEngine", "Invalid dynamic choice: " + choiceNum + " for node: " + currentNode.getNodeId());
            }
        } else if (currentNode.hasFixedChoices()) {
            if (choiceNum == 1) {
                currentNodeId = currentNode.getFixedNextNodeId1();
            } else if (choiceNum == 2) {
                currentNodeId = currentNode.getFixedNextNodeId2();
            } else {
                Log.e("GameEngine", "Invalid fixed choice: " + choiceNum + " for node: " + currentNode.getNodeId());
            }
        } else {
            Log.e("GameEngine", "Node '" + currentNode.getNodeId() + "' has no valid choices.");
            currentNodeId = null;
        }
    }

    public String getCurrentNodeId() {
        return currentNodeId;
    }

    public void setCurrentNodeId(String currentNodeId) {
        this.currentNodeId = currentNodeId;
    }
}