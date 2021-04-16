package no.stelar7.api.r4j.basic.utils;

import no.stelar7.api.r4j.basic.constants.api.regions.RuneterraShard;

public class OAuthAuthorizeData
{
    private String grantType;
    private String refreshToken;
    private String clientId;
    private String clientSecret;
    private String scope;
    private RuneterraShard platform;
    
    public String getGrantType()
    {
        return grantType;
    }
    
    public String getRefreshToken()
    {
        return refreshToken;
    }
    
    public String getClientId()
    {
        return clientId;
    }
    
    public String getClientSecret()
    {
        return clientSecret;
    }
    
    public String getScope()
    {
        return scope;
    }
    
    public RuneterraShard getPlatform()
    {
        return platform;
    }
}
