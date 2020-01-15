package no.stelar7.api.r4j.impl.lol.lcu;

import javax.net.ssl.*;
import java.security.*;
import java.security.cert.X509Certificate;

public class SimpleSSLContext
{
    private SimpleSSLContext()
    {
    }
    
    public static SSLContext getInstance(String protocol) throws NoSuchAlgorithmException
    {
        return init(SSLContext.getInstance(protocol));
    }
    
    private static SSLContext init(SSLContext context)
    {
        try
        {
            context.init(null, new TrustManager[]{new NaiveTrustManager()}, null);
        } catch (KeyManagementException e)
        {
            throw new RuntimeException("Failed to initialize an SSLContext.", e);
        }
        
        return context;
    }
    
    private static class NaiveTrustManager implements X509TrustManager
    {
        @Override
        public X509Certificate[] getAcceptedIssuers()
        {
            return null;
        }
        
        
        public void checkClientTrusted(X509Certificate[] certs, String authType)
        {
            // trust everyone
        }
        
        
        public void checkServerTrusted(X509Certificate[] certs, String authType)
        {
            // trust everyone
        }
    }
}
