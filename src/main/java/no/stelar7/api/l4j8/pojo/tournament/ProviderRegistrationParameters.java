package no.stelar7.api.l4j8.pojo.tournament;

import no.stelar7.api.l4j8.basic.constants.api.*;

public class ProviderRegistrationParameters
{
    private Server region;
    private String url;
    
    public ProviderRegistrationParameters(final Server region, final String callbackUrl)
    {
        super();
        this.region = region;
        this.url = callbackUrl;
    }
    
    @Override
    public boolean equals(final Object obj)
    {
        if (this == obj)
        {
            return true;
        }
        if (obj == null)
        {
            return false;
        }
        if (this.getClass() != obj.getClass())
        {
            return false;
        }
        final ProviderRegistrationParameters other = (ProviderRegistrationParameters) obj;
        if (this.region != other.region)
        {
            return false;
        }
        if (this.url == null)
        {
            if (other.url != null)
            {
                return false;
            }
        } else if (!this.url.equals(other.url))
        {
            return false;
        }
        return true;
    }
    
    public String getCallbackUrl()
    {
        return this.url;
    }
    
    public Server getRegion()
    {
        return this.region;
    }
    
    public void setRegion(final Server region)
    {
        this.region = region;
    }
    
    public String getUrl()
    {
        return this.url;
    }
    
    public void setUrl(final String url)
    {
        this.url = url;
    }
    
    @Override
    public int hashCode()
    {
        final int prime  = 31;
        int       result = 1;
        result = (prime * result) + ((this.region == null) ? 0 : this.region.hashCode());
        result = (prime * result) + ((this.url == null) ? 0 : this.url.hashCode());
        return result;
    }
    
    @Override
    public String toString()
    {
        return "ProviderRegistrationParameters [region=" + this.region + ", url=" + this.url + "]";
    }
    
}
