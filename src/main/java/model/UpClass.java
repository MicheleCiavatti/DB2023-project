package model;

import java.util.Objects;

public record UpClass(String nameClass, String descClass) {
    public UpClass {
        Objects.requireNonNull(nameClass);
        Objects.requireNonNull(descClass);
    }

    public String toString() {
        return "nomeClasse = " + this.nameClass + "; descClasse = " + this.descClass;
    }
}
