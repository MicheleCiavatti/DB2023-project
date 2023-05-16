package model;

public record Session(String nameCampaign, int progressiveCode, String codParty) {
    public Session {
        if (nameCampaign == null || nameCampaign.length() > 50
            || codParty == null || codParty.length() > 20) {
                throw new IllegalArgumentException();
            }
    }
}
