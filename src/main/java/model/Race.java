package model;

import java.util.Objects;

public record Race(String nameRace, String descRace) {
    public Race {
        Objects.requireNonNull(nameRace);
        Objects.requireNonNull(descRace);
    }

    public String toString() {
        return "nomeRazza = " + this.nameRace + "; descRazza = " + this.descRace;
    }
}
