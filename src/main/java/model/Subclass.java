package model;

import java.util.Objects;

public record Subclass(String nameClass, int numSubclass, String descSubclass) {
    public Subclass {
        Objects.requireNonNull(nameClass);
        Objects.requireNonNull(descSubclass);
    }
}
