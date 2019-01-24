package no.stelar7.api.l4j8.basic.constants.api;

import no.stelar7.api.l4j8.basic.calling.DataCall;

import java.time.Instant;

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
            System.err.printf(DataCall.getLogFormat(), Instant.now(), other.toString(), text);
        }
    }
}
