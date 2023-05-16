package model;

import java.util.Objects;

public record PartProtagonist(int numTurn, String nameCampaign, int progressiveCode, int nameProtagonist, int damageProtagonist) {
    public PartProtagonist {
        Objects.requireNonNull(nameCampaign);
        Objects.requireNonNull(nameProtagonist);
    }
}
