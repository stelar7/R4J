package no.stelar7.api.l4j8.basic.constants.api;

public enum LogLevel
{
    NONE,
    INFO,
    EXTENDED_INFO,
    DEBUG;
    
    public void printIf(LogLevel other, String text)
    {
        if (other.ordinal() <= this.ordinal())
        {
            System.err.printf("%s: %s%n", other.toString(), text);
        }
    }
}
