package model;

import java.util.Objects;

public record Session(String nameCampaign, int progressiveCode, String codParty) {
    public Session {
        Objects.requireNonNull(nameCampaign);
        Objects.requireNonNull(codParty);
    }

    @Override
    public String toString() {
        return "nomeCampagna = " + this.nameCampaign + "; codProgressivo = " + this.progressiveCode + "; codParty = " + this.codParty;
    }
}
