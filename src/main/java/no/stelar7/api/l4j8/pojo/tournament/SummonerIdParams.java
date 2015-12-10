package no.stelar7.api.l4j8.pojo.tournament;

import java.util.HashSet;
import java.util.Set;

public class SummonerIdParams
{
    Set<Number> participants;

    public SummonerIdParams(Set<Long> set)
    {
        this.participants = new HashSet<Number>();
        for (Number id : set)
        {
            participants.add(id);
        }
    }

    public SummonerIdParams()
    {
        this.participants = new HashSet<>();
    }

    public Set<Number> getParticipants()
    {
        return participants;
    }

    public void addParticipant(Number id)
    {
        participants.add(id);
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((participants == null) ? 0 : participants.hashCode());
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
        SummonerIdParams other = (SummonerIdParams) obj;
        if (participants == null)
        {
            if (other.participants != null)
                return false;
        } else if (!participants.equals(other.participants))
            return false;
        return true;
    }
}
