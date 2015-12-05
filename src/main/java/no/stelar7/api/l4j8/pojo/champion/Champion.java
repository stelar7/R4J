package no.stelar7.api.l4j8.pojo.champion;

public class Champion
{

    Boolean active;
    Boolean botEnabled;
    Boolean botMmEnabled;
    Boolean freeToPlay;
    Boolean rankedPlayEnabled;
    Integer id;

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
        final Champion other = (Champion) obj;
        if (this.active == null)
        {
            if (other.active != null)
            {
                return false;
            }
        } else if (!this.active.equals(other.active))
        {
            return false;
        }
        if (this.botEnabled == null)
        {
            if (other.botEnabled != null)
            {
                return false;
            }
        } else if (!this.botEnabled.equals(other.botEnabled))
        {
            return false;
        }
        if (this.botMmEnabled == null)
        {
            if (other.botMmEnabled != null)
            {
                return false;
            }
        } else if (!this.botMmEnabled.equals(other.botMmEnabled))
        {
            return false;
        }
        if (this.freeToPlay == null)
        {
            if (other.freeToPlay != null)
            {
                return false;
            }
        } else if (!this.freeToPlay.equals(other.freeToPlay))
        {
            return false;
        }
        if (this.id == null)
        {
            if (other.id != null)
            {
                return false;
            }
        } else if (!this.id.equals(other.id))
        {
            return false;
        }
        if (this.rankedPlayEnabled == null)
        {
            if (other.rankedPlayEnabled != null)
            {
                return false;
            }
        } else if (!this.rankedPlayEnabled.equals(other.rankedPlayEnabled))
        {
            return false;
        }
        return true;
    }

    public Integer getId()
    {
        return this.id;
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = (prime * result) + ((this.active == null) ? 0 : this.active.hashCode());
        result = (prime * result) + ((this.botEnabled == null) ? 0 : this.botEnabled.hashCode());
        result = (prime * result) + ((this.botMmEnabled == null) ? 0 : this.botMmEnabled.hashCode());
        result = (prime * result) + ((this.freeToPlay == null) ? 0 : this.freeToPlay.hashCode());
        result = (prime * result) + ((this.id == null) ? 0 : this.id.hashCode());
        result = (prime * result) + ((this.rankedPlayEnabled == null) ? 0 : this.rankedPlayEnabled.hashCode());
        return result;
    }

    public Boolean isActive()
    {
        return this.active;
    }

    public Boolean isBotEnabled()
    {
        return this.botEnabled;
    }

    public Boolean isBotMmEnabled()
    {
        return this.botMmEnabled;
    }

    public Boolean isFreeToPlay()
    {
        return this.freeToPlay;
    }

    public Boolean isRankedPlayEnabled()
    {
        return this.rankedPlayEnabled;
    }

    @Override
    public String toString()
    {
        return "Champion [active=" + this.active + ", botEnabled=" + this.botEnabled + ", botMmEnabled=" + this.botMmEnabled + ", freeToPlay=" + this.freeToPlay + ", id=" + this.id + ", rankedPlayEnabled=" + this.rankedPlayEnabled + "]";
    }
}
