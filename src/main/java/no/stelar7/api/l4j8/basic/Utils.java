package no.stelar7.api.l4j8.basic;

import com.google.gson.*;

import java.io.*;
import java.net.*;
import java.util.*;
import java.util.logging.*;

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
	
	public static String toJson(Object o)
	{
		return new Gson().toJson(o);
	}
	
}
