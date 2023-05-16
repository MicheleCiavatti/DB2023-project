package model;

import java.util.Objects;

public record Item(String typeItem, int numUsage, String nameItem, String descItem) {
    public Item {
        Objects.requireNonNull(typeItem);
        Objects.requireNonNull(nameItem);
        Objects.requireNonNull(descItem);
    }
}