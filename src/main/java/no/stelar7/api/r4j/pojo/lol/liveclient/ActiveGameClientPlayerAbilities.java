package no.stelar7.api.r4j.pojo.lol.liveclient;

import java.util.Objects;

public class ActiveGameClientPlayerAbilities
{
    private ActiveGameClientPlayerAbility Passive;
    private ActiveGameClientPlayerAbility Q;
    private ActiveGameClientPlayerAbility W;
    private ActiveGameClientPlayerAbility E;
    private ActiveGameClientPlayerAbility R;
    
    public ActiveGameClientPlayerAbility getPassive()
    {
        return Passive;
    }
    
    public ActiveGameClientPlayerAbility getQ()
    {
        return Q;
    }
    
    public ActiveGameClientPlayerAbility getW()
    {
        return W;
    }
    
    public ActiveGameClientPlayerAbility getE()
    {
        return E;
    }
    
    public ActiveGameClientPlayerAbility getR()
    {
        return R;
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
        ActiveGameClientPlayerAbilities that = (ActiveGameClientPlayerAbilities) o;
        return Objects.equals(Passive, that.Passive) &&
               Objects.equals(Q, that.Q) &&
               Objects.equals(W, that.W) &&
               Objects.equals(E, that.E) &&
               Objects.equals(R, that.R);
    }
    
    @Override
    public int hashCode()
    {
        return Objects.hash(Passive, Q, W, E, R);
    }
    
    @Override
    public String toString()
    {
        return "ActiveGameClientPlayerAbilities{" +
               "Passive=" + Passive +
               ", Q=" + Q +
               ", W=" + W +
               ", E=" + E +
               ", R=" + R +
               '}';
    }
}
