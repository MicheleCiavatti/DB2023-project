package model;

import java.util.Objects;

public record Appearance(String nameCampaign, int progressiveCode, String nameNPC, String typeRelation) {
    public Appearance {
        Objects.requireNonNull(nameCampaign);
        Objects.requireNonNull(nameNPC);
        Objects.requireNonNull(typeRelation);
    }
}
