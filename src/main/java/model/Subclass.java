package model;

import java.util.Objects;

public record Subclass(String nameClass, int numSubclass, String descSubclass) {
    public Subclass {
        Objects.requireNonNull(nameClass);
        Objects.requireNonNull(descSubclass);
    }

    public String toString() {
        return "nomeClasse = " + this.nameClass + "; numSottoclasse = " + this.numSubclass + "; descSottoclasse = " + this.descSubclass;
    }
}
