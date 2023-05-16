package model;

import java.util.Objects;

public record Class(String nameClass, String descClass) {
    public Class {
        Objects.requireNonNull(nameClass);
        Objects.requireNonNull(descClass);
    }
}
