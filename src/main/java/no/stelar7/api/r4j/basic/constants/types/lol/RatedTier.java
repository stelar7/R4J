package no.stelar7.api.r4j.basic.constants.types.lol;

public enum RatedTier {
  ORANGE("ORANGE"),
  PURPLE("PURPLE"),
  BLUE("BLUE"), 
  GREEN("GREEN"),
  GRAY("GRAY");

  private String apiName;

  private RatedTier(String apiName) {
    this.apiName = apiName;
  }

  public static RatedTier getRatedTierWithApiName(String ratedTier) {
    for(RatedTier tierToCheck : RatedTier.values()) {
      if(ratedTier.equals(tierToCheck.getApiName()))
        return tierToCheck;
    }
    return null;
  }

  public String getApiName() {
    return apiName;
  }
}
