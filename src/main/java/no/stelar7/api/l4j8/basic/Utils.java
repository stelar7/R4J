package no.stelar7.api.l4j8.basic;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Utils
{
    private static final Logger LOGGER = Logger.getGlobal();

    public static String prepareForURL(final String data)
    {
        try
        {
            final String stripped  = data.replace(" ", "");
            final String lowercase = stripped.toLowerCase(Locale.ENGLISH);
            return URLEncoder.encode(lowercase, "UTF-8");
        } catch (final UnsupportedEncodingException e)
        {
            Utils.LOGGER.log(Level.WARNING, e.getMessage(), e);
        }
        return null;
    }

    private Utils()
    {
    }

}
