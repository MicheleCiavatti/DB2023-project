package model;

import java.util.Objects;

public record Composition(String nameProtagonist, String codParty) {
    public Composition {
        Objects.requireNonNull(nameProtagonist);
        Objects.requireNonNull(codParty);
    }
}
