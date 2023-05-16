package model;

import java.sql.Blob;
import java.util.Objects;
import java.util.Optional;

public record Protagonist(Optional<Blob> imageProtagonist, String nameProtagonist, int gold, int level, String nameClass, int numSubclass, String nameRace) {
    public Protagonist {
        Objects.requireNonNull(imageProtagonist);
        Objects.requireNonNull(nameProtagonist);
        Objects.requireNonNull(nameClass);
        Objects.requireNonNull(nameRace);
    }
}
