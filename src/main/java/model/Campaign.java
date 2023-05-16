package model;

public record Campaign(int numPlayers, String nameCampaign) {
    public Campaign {
        Checker.checkName(nameCampaign);
    }
}
