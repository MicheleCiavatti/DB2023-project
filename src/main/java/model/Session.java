package model;

public record Session(String nameCampaign, int progressiveCode, String codParty) {
    public Session {
        Checker.checkName(nameCampaign);
        Checker.checkCode(codParty);
    }
}
