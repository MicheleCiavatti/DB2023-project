package model;

import java.util.Objects;

public record Party(String codParty) {
    public Party {
        Objects.requireNonNull(codParty);
    }

    @Override
    public String toString() {
        return "codParty = " + this.codParty;
    }
}
