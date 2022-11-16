package com.epam.rd.autocode.factory.plot;

public class ClassicDisneyPlotFactory implements PlotFactory {
    private final Character hero;
    private final Character beloved;
    private final Character villain;

    public ClassicDisneyPlotFactory(Character hero, Character beloved, Character villain) {
        this.hero = hero;
        this.beloved = beloved;
        this.villain = villain;
    }

    @Override
    public Plot plot() {
        return new ClassicDisneyPlot(getHero(), getBeloved(), getVillain());
    }

    @Override
    public Character getHero() {
        return hero;
    }

    @Override
    public Character getBeloved() {
        return beloved;
    }

    @Override
    public Character getVillain() {
        return villain;
    }

    @Override
    public EpicCrisis getEpicCrisis() {
        return null;
    }

    @Override
    public Character getFunnyFriend() {
        return null;
    }

    @Override
    public Character[] getCharacters() {
        return new Character[0];
    }
}