package model;

import java.util.Objects;

public record PartMonster(int numTurn, String nameCampaign, int progressiveCode, String nameMonster, int damageMonster) {
    public PartMonster {
        Objects.requireNonNull(nameCampaign);
        Objects.requireNonNull(nameMonster);
    }
}
