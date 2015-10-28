package no.stelar7.api.l4j8.basic;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Locale;

public class Utils
{
    public static String prepareForURL(final String data)
    {
        try
        {
            final String stripped = data.replace(" ", "");
            final String lowercase = stripped.toLowerCase(Locale.ENGLISH);
            final String encoded = URLEncoder.encode(lowercase, "UTF-8");
            return encoded;
        } catch (final UnsupportedEncodingException e)
        {
            e.printStackTrace();
            return null;
        }
    }
}
