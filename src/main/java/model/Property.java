package model;

import java.util.Objects;

public record Property(String nameItem, String nameProtagonist, int quantity) {
    public Property {
        Objects.requireNonNull(nameItem);
        Objects.requireNonNull(nameProtagonist);
    }
}
