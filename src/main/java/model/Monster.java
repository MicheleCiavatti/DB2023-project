package model;

import java.util.Objects;
import java.sql.Blob;
import java.util.Optional;

public record Monster(String nameMonster, Optional<Blob> imageMonster) {
    public Monster {
        Objects.requireNonNull(nameMonster);
    }

    public String toString() {
        return "nomeMostro = " + this.nameMonster;
    }
}
