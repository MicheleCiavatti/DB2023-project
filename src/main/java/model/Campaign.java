package model;

public record Campaign(int numPlayers, String nameCampaign) {
    public Campaign {
        if (nameCampaign == null || nameCampaign.length() > 50) {
            throw new IllegalArgumentException();
        }
    }
}
