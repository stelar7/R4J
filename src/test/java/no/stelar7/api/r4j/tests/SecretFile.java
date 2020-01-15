package no.stelar7.api.r4j.tests;

import no.stelar7.api.r4j.basic.APICredentials;

public class SecretFile
{
    public static final String         API_KEY            = "";
    public static final String         DEV_API_KEY        = "";
    public static final String         TOURNAMENT_API_KEY = "NO_KEY_:(";
    public static final APICredentials CREDS              = new APICredentials(SecretFile.API_KEY, SecretFile.API_KEY, SecretFile.TOURNAMENT_API_KEY);
}
