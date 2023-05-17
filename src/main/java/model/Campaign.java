package model;

import java.util.Objects;

public record Campaign(int numPlayers, String nameCampaign) {

    public Campaign {
        Objects.requireNonNull(nameCampaign);
    }

    @Override
    public String toString() {
        return "numGiocatori = " + this.numPlayers + "; nomeCampagna = " + nameCampaign;
    }
}
