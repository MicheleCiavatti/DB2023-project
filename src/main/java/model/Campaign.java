package model;

import java.util.Objects;

public record Campaign(int numPlayers, String nameCampaign) {
    public Campaign {
        Objects.requireNonNull(nameCampaign);
    }
}
