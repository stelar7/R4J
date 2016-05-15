package no.stelar7.api.l4j8.tests;

import no.stelar7.api.l4j8.basic.*;

public class SecretFile
{
    public static final String API_KEY            = "FAKE KEY";
    public static final String TOURNAMENT_API_KEY = "FAKE KEY";
    public static final APICredentials CREDS      = new APICredentials(SecretFile.API_KEY, SecretFile.TOURNAMENT_KEY);
}
