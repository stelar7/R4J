package no.stelar7.api.l4j8.basic;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Locale;
import java.util.logging.*;

public final class Utils
{
    private static final Logger LOGGER = Logger.getGlobal();
    
    private Utils()
    {
    }
    
    public static String normalizeSummonerName(final String data)
    {
        try
        {
            final String stripped  = data.replaceAll("\\s+", "");
            final String lowercase = stripped.toLowerCase(Locale.ENGLISH);
            return URLEncoder.encode(lowercase, "UTF-8");
        } catch (final UnsupportedEncodingException e)
        {
            Utils.LOGGER.log(Level.WARNING, e.getMessage(), e);
        }
        return null;
    }
    
}
