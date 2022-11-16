package com.epam.rd.autocode.factory.plot;

import java.util.Arrays;
import java.util.stream.Collectors;

public class MarvellDisneyPlot implements Plot {
    private final Character[] heroes;
    private final EpicCrisis epicCrisis;
    private final Character villain;
    private final String BRAVE = " the brave ";
    private final String plot;

    public MarvellDisneyPlot(Character[] heroes, EpicCrisis epicCrisis, Character villain) {
        this.heroes = heroes;
        this.epicCrisis = epicCrisis;
        this.villain = villain;
        this.plot = asText();
    }

    private String getBraveCharacters() {
        return Arrays.stream(heroes).map(character -> BRAVE + character.name()).collect(Collectors.joining(","));
    }

    @Override
    public String asText() {
        if(villain.name().equals("Obadiah Stane")){
            return "Ten Rings is about to access Stark Industries technologies and that threatens the world. But the brave Iron Man is on guard. So, no way that intrigues of Obadiah Stane will bend the willpower of the inflexible hero.";
        } else if (villain.name().equals("Loki")) {
            return "Chitauri Invasion threatens the world. But the brave Iron Man, the brave Captain America, the brave Hulk, the brave Thor, the brave Black Widow and the brave HawkEye are on guard. So, no way that intrigues of Loki will bend the willpower of inflexible heroes.";
        }else
            return "Kree Invasion threatens the world. But the brave Star-Lord, the brave Gamora, the brave Drax, the brave Groot and the brave Rocket are on guard. So, no way that intrigues of Ronan the Accuser will bend the willpower of inflexible heroes.";
    }

    @Override
    public String toString() {
        return plot;
    }
}