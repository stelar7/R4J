package no.stelar7.api.r4j.basic.utils;

public class OAuthTokenData
{
    private String accessToken;
    private String refreshToken;
    private Long expiresIn;
    
    public String getAccessToken()
    {
        return accessToken;
    }
    
    public String getRefreshToken()
    {
        return refreshToken;
    }
    
    public Long getExpiresIn()
    {
        return expiresIn;
    }
}
