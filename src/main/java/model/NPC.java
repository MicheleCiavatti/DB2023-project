package model;

import java.util.Objects;
import java.util.Optional;
import java.sql.Blob;

public record NPC(String typeInteraction, String nameNPC, Optional<Blob> imageNPC, String descPersonality, String nameCampaign) {
    public NPC {
        Objects.requireNonNull(typeInteraction);
        Objects.requireNonNull(nameNPC);
        Objects.requireNonNull(descPersonality);
        Objects.requireNonNull(nameCampaign);
    }

    public String toString() {
        return "tipoInterazione = " + this.typeInteraction + "; nomeNPC = " + this.nameNPC + 
        "; descrizionePersonalità = " + this.descPersonality + "; nomeCampagna = " + this.nameCampaign;
    }
}
