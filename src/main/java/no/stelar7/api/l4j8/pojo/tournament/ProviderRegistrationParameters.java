package no.stelar7.api.l4j8.pojo.tournament;

import no.stelar7.api.l4j8.basic.Server;

public class ProviderRegistrationParameters
{
    Server region;
    String url;

    public Server getRegion()
    {
        return region;
    }

    public String getCallbackUrl()
    {
        return url;
    }

    public ProviderRegistrationParameters(Server region, String callbackUrl)
    {
        super();
        this.region = region;
        this.url = callbackUrl;
    }

    public String getUrl()
    {
        return url;
    }

    public void setUrl(String url)
    {
        this.url = url;
    }

    public void setRegion(Server region)
    {
        this.region = region;
    }

    @Override
    public String toString()
    {
        return "ProviderRegistrationParameters [region=" + region + ", url=" + url + "]";
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((region == null) ? 0 : region.hashCode());
        result = prime * result + ((url == null) ? 0 : url.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ProviderRegistrationParameters other = (ProviderRegistrationParameters) obj;
        if (region != other.region)
            return false;
        if (url == null)
        {
            if (other.url != null)
                return false;
        } else if (!url.equals(other.url))
            return false;
        return true;
    }

}
