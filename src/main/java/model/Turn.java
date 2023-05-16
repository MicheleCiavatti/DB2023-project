package model;

import java.util.Objects;

public record Turn(int numTurn, String nameCampaign, int progressiveCode) {
    public Turn {
        Objects.requireNonNull(nameCampaign);
    }
}
