package model;

import java.util.Objects;
import java.util.Optional;

public record Item(String typeItem, Optional<Integer> numUsage, String nameItem, String descItem) {
    public Item {
        Objects.requireNonNull(typeItem);
        Objects.requireNonNull(nameItem);
        Objects.requireNonNull(descItem);
    }

    public String toString() {
        return "tipoOggetto = " + this.typeItem + (this.numUsage.isPresent() ? "; numUtilizzi = " + this.numUsage.get() : "") + "; nomeOggetto = " + this.nameItem + "; descOggetto = " + this.descItem;
    }
}