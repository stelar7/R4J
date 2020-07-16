package no.stelar7.api.r4j.tests.val;

import no.stelar7.api.r4j.basic.constants.api.regions.ValorantShard;
import no.stelar7.api.r4j.impl.R4J;
import no.stelar7.api.r4j.impl.val.VALContentAPI;
import no.stelar7.api.r4j.pojo.val.content.Content;
import no.stelar7.api.r4j.tests.SecretFile;
import org.junit.jupiter.api.Test;

import java.util.Optional;

public class TestVALContent
{
    @Test
    public void testGetContent()
    {
        R4J api = new R4J(SecretFile.CREDS);
        VALContentAPI content = api.getVALAPI().getContentAPI();
    
        Content data = content.getContent(ValorantShard.EU, Optional.empty());
        System.out.println(data);
    }
}
