package no.stelar7.api.l4j8.basic.utils;

public final class Pair<A, B>
{
    private A key;
    private B value;
    
    public Pair(A key, B value)
    {
        this.key = key;
        this.value = value;
    }
    
    public B getValue()
    {
        
        return value;
    }
    
    public void setValue(B value)
    {
        this.value = value;
    }
    
    public A getKey()
    {
        
        return key;
    }
    
    public void setKey(A key)
    {
        this.key = key;
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
        
        Pair<?, ?> pair = (Pair<?, ?>) o;
        
        if ((key != null) ? !key.equals(pair.key) : (pair.key != null))
        {
            return false;
        }
        return (value != null) ? value.equals(pair.value) : (pair.value == null);
    }
    
    @Override
    public int hashCode()
    {
        int result = key != null ? key.hashCode() : 0;
        result = 31 * result + (value != null ? value.hashCode() : 0);
        return result;
    }
    
    @Override
    public String toString()
    {
        return "Pair{" +
               "key=" + key +
               ", value=" + value +
               '}';
    }
}
