package no.stelar7.api.r4j.pojo.lol.liveclient;

import java.util.Objects;

public class ActiveGamePlayerSummonerSpells
{
    private ActiveGamePlayerSummonerSpell summonerSpellOne;
    private ActiveGamePlayerSummonerSpell summonerSpellTwo;
    
    public ActiveGamePlayerSummonerSpell getSummonerSpellOne()
    {
        return summonerSpellOne;
    }
    
    public ActiveGamePlayerSummonerSpell getSummonerSpellTwo()
    {
        return summonerSpellTwo;
    }
    
    @Override
    public boolean equals(Object o)
    {
        if (this == o)
        {
            return true;
        }
        if (o == null || getClass() != o.getClass())
        {
            return false;
        }
        ActiveGamePlayerSummonerSpells that = (ActiveGamePlayerSummonerSpells) o;
        return Objects.equals(summonerSpellOne, that.summonerSpellOne) &&
               Objects.equals(summonerSpellTwo, that.summonerSpellTwo);
    }
    
    @Override
    public int hashCode()
    {
        return Objects.hash(summonerSpellOne, summonerSpellTwo);
    }
    
    @Override
    public String toString()
    {
        return "ActiveGamePlayerSummonerSpells{" +
               "summonerSpellOne=" + summonerSpellOne +
               ", summonerSpellTwo=" + summonerSpellTwo +
               '}';
    }
}
