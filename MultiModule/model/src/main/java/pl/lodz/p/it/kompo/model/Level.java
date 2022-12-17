package pl.lodz.p.it.kompo.model;

public enum Level {

    Easy(2),
    Medium(4),
    Hard(6);

    private int numberEmpty;

    Level(int numberEmpty) {
        this.numberEmpty = numberEmpty;
    }

    public int getNumberEmpty() {
        return numberEmpty;
    }

    public void setNumberEmpty(int numberEmpty) {
        this.numberEmpty = numberEmpty;
    }
}
