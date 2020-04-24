package no.stelar7.api.r4j.tests.lor;

import no.stelar7.api.r4j.impl.lor.LoRDeckCode;
import no.stelar7.api.r4j.pojo.lor.offline.card.*;
import org.junit.*;

import java.io.InputStream;
import java.util.*;

public class TestLorDeck
{
    
    @Test
    public void testEncodingRecommendedDecks()
    {
        InputStream file = TestLorDeck.class.getClassLoader().getResourceAsStream("testdata.txt");
        if (file == null)
        {
            throw new RuntimeException("Unable to load test file");
        }
        
        List<String>  codes = new ArrayList<>();
        List<LoRDeck> decks = new ArrayList<>();
        
        try (Scanner scanner = new Scanner(file))
        {
            while (scanner.hasNextLine())
            {
                String line = scanner.nextLine();
                codes.add(line);
                
                LoRDeck deck = new LoRDeck();
                while (scanner.hasNextLine() && !(line = scanner.nextLine()).equalsIgnoreCase(""))
                {
                    String[] parts = line.split(":");
                    deck.addCard(LoRCard.create(parts[1]), Integer.parseInt(parts[0]));
                }
                decks.add(deck);
            }
        }
        
        for (int i = 0; i < decks.size(); i++)
        {
            String encoded = LoRDeckCode.encode(decks.get(i));
            Assert.assertEquals("Decks are not equal", codes.get(i), encoded);
            
            LoRDeck decoded = LoRDeckCode.decode(encoded);
            Assert.assertTrue("Did not produce same deck when re-coded", checkSameDeck(decks.get(i), decoded));
        }
    }
    
    @Test
    public void testDecodeEncode()
    {
        String DECK_CODE = "CEBAIAIFB4WDANQIAEAQGDAUDAQSIJZUAIAQCAIEAEAQKBIA";
        
        LoRDeck deck   = LoRDeckCode.decode(DECK_CODE);
        String  result = LoRDeckCode.encode(deck);
        
        Assert.assertEquals("Did not transform code to match!", DECK_CODE, result);
    }
    
    
    @Test
    public void testSmallDeck()
    {
        LoRDeck deck = new LoRDeck();
        deck.addCard(LoRCard.create("01DE002"), 3);
        
        String  code    = LoRDeckCode.encode(deck);
        LoRDeck decoded = LoRDeckCode.decode(code);
        
        Assert.assertTrue("Did not produce same deck when re-coded", checkSameDeck(deck, decoded));
    }
    
    @Test
    public void testLargeDeck()
    {
        LoRDeck deck = new LoRDeck();
        deck.addCard(LoRCard.create("01DE002"), 3);
        deck.addCard(LoRCard.create("01DE003"), 3);
        deck.addCard(LoRCard.create("01DE004"), 3);
        deck.addCard(LoRCard.create("01DE005"), 3);
        deck.addCard(LoRCard.create("01DE006"), 3);
        deck.addCard(LoRCard.create("01DE007"), 3);
        deck.addCard(LoRCard.create("01DE008"), 3);
        deck.addCard(LoRCard.create("01DE009"), 3);
        deck.addCard(LoRCard.create("01DE010"), 3);
        deck.addCard(LoRCard.create("01DE011"), 3);
        deck.addCard(LoRCard.create("01DE012"), 3);
        deck.addCard(LoRCard.create("01DE013"), 3);
        deck.addCard(LoRCard.create("01DE014"), 3);
        deck.addCard(LoRCard.create("01DE015"), 3);
        deck.addCard(LoRCard.create("01DE016"), 3);
        deck.addCard(LoRCard.create("01DE017"), 3);
        deck.addCard(LoRCard.create("01DE018"), 3);
        deck.addCard(LoRCard.create("01DE019"), 3);
        deck.addCard(LoRCard.create("01DE020"), 3);
        deck.addCard(LoRCard.create("01DE021"), 3);
        
        String  code    = LoRDeckCode.encode(deck);
        LoRDeck decoded = LoRDeckCode.decode(code);
        
        Assert.assertTrue("Did not produce same deck when re-coded", checkSameDeck(deck, decoded));
    }
    
    
    @Test
    public void testMoreThan3Small()
    {
        LoRDeck deck = new LoRDeck();
        deck.addCard(LoRCard.create("01DE002"), 4);
        deck.addCard(LoRCard.create("02BW003"), 2);
        deck.addCard(LoRCard.create("02BW010"), 3);
        deck.addCard(LoRCard.create("01DE004"), 5);
        
        String  code    = LoRDeckCode.encode(deck);
        LoRDeck decoded = LoRDeckCode.decode(code);
        
        Assert.assertTrue("Did not produce same deck when re-coded", checkSameDeck(deck, decoded));
    }
    
    @Test
    public void testBilgewater()
    {
        LoRDeck deck = new LoRDeck();
        deck.addCard(LoRCard.create("01DE002"), 4);
        
        String  code    = LoRDeckCode.encode(deck);
        LoRDeck decoded = LoRDeckCode.decode(code);
        
        Assert.assertTrue("Did not produce same deck when re-coded", checkSameDeck(deck, decoded));
    }
    
    @Test
    public void testMoreThan3Large()
    {
        LoRDeck deck = new LoRDeck();
        deck.addCard(LoRCard.create("01DE002"), 3);
        deck.addCard(LoRCard.create("01DE003"), 3);
        deck.addCard(LoRCard.create("01DE004"), 3);
        deck.addCard(LoRCard.create("01DE005"), 3);
        deck.addCard(LoRCard.create("01DE006"), 4);
        deck.addCard(LoRCard.create("01DE007"), 5);
        deck.addCard(LoRCard.create("01DE008"), 6);
        deck.addCard(LoRCard.create("01DE009"), 7);
        deck.addCard(LoRCard.create("01DE010"), 8);
        deck.addCard(LoRCard.create("01DE011"), 9);
        deck.addCard(LoRCard.create("01DE012"), 3);
        deck.addCard(LoRCard.create("01DE013"), 3);
        deck.addCard(LoRCard.create("01DE014"), 3);
        deck.addCard(LoRCard.create("01DE015"), 3);
        deck.addCard(LoRCard.create("01DE016"), 3);
        deck.addCard(LoRCard.create("01DE017"), 3);
        deck.addCard(LoRCard.create("01DE018"), 3);
        deck.addCard(LoRCard.create("01DE019"), 3);
        deck.addCard(LoRCard.create("01DE020"), 3);
        deck.addCard(LoRCard.create("01DE021"), 3);
        
        String  code    = LoRDeckCode.encode(deck);
        LoRDeck decoded = LoRDeckCode.decode(code);
        
        Assert.assertTrue("Did not produce same deck when re-coded", checkSameDeck(deck, decoded));
    }
    
    @Test
    public void testSingleCard40()
    {
        LoRDeck deck = new LoRDeck();
        deck.addCard(LoRCard.create("01DE002"), 40);
        
        String  code    = LoRDeckCode.encode(deck);
        LoRDeck decoded = LoRDeckCode.decode(code);
        
        Assert.assertTrue("Did not produce same deck when re-coded", checkSameDeck(deck, decoded));
    }
    
    @Test
    public void testWorstCaseLength()
    {
        LoRDeck deck = new LoRDeck();
        deck.addCard(LoRCard.create("01DE002"), 4);
        deck.addCard(LoRCard.create("01DE003"), 4);
        deck.addCard(LoRCard.create("01DE004"), 4);
        deck.addCard(LoRCard.create("01DE005"), 4);
        deck.addCard(LoRCard.create("01DE006"), 4);
        deck.addCard(LoRCard.create("01DE007"), 5);
        deck.addCard(LoRCard.create("01DE008"), 6);
        deck.addCard(LoRCard.create("01DE009"), 7);
        deck.addCard(LoRCard.create("01DE010"), 8);
        deck.addCard(LoRCard.create("01DE011"), 9);
        deck.addCard(LoRCard.create("01DE012"), 4);
        deck.addCard(LoRCard.create("01DE013"), 4);
        deck.addCard(LoRCard.create("01DE014"), 4);
        deck.addCard(LoRCard.create("01DE015"), 4);
        deck.addCard(LoRCard.create("01DE016"), 4);
        deck.addCard(LoRCard.create("01DE017"), 4);
        deck.addCard(LoRCard.create("01DE018"), 4);
        deck.addCard(LoRCard.create("01DE019"), 4);
        deck.addCard(LoRCard.create("01DE020"), 4);
        deck.addCard(LoRCard.create("01DE021"), 4);
        
        String  code    = LoRDeckCode.encode(deck);
        LoRDeck decoded = LoRDeckCode.decode(code);
        
        Assert.assertTrue("Did not produce same deck when re-coded", checkSameDeck(deck, decoded));
    }
    
    @Test
    public void testOrderDoesNotMatter()
    {
        LoRDeck deck = new LoRDeck();
        deck.addCard(LoRCard.create("01DE002"), 1);
        deck.addCard(LoRCard.create("01DE003"), 2);
        deck.addCard(LoRCard.create("02DE003"), 3);
        
        LoRDeck deck2 = new LoRDeck();
        deck2.addCard(LoRCard.create("01DE003"), 2);
        deck2.addCard(LoRCard.create("02DE003"), 3);
        deck2.addCard(LoRCard.create("01DE002"), 1);
        
        String code  = LoRDeckCode.encode(deck);
        String code2 = LoRDeckCode.encode(deck2);
        
        Assert.assertEquals("Order matters?", code, code2);
    }
    
    @Test
    public void testOrderDoesNotMatterMoreThan3()
    {
        LoRDeck deck = new LoRDeck();
        deck.addCard(LoRCard.create("01DE002"), 4);
        deck.addCard(LoRCard.create("01DE003"), 2);
        deck.addCard(LoRCard.create("02DE003"), 3);
        deck.addCard(LoRCard.create("01DE004"), 5);
        
        LoRDeck deck2 = new LoRDeck();
        deck2.addCard(LoRCard.create("01DE004"), 5);
        deck2.addCard(LoRCard.create("01DE003"), 2);
        deck2.addCard(LoRCard.create("02DE003"), 3);
        deck2.addCard(LoRCard.create("01DE002"), 4);
        
        String code  = LoRDeckCode.encode(deck);
        String code2 = LoRDeckCode.encode(deck2);
        
        Assert.assertEquals("Order matters?", code, code2);
    }
    
    @Test
    public void testInvalidDecks()
    {
        LoRDeck deck = new LoRDeck();
        
        
        deck.addCard(LoRCard.create("01DE02"), 1);
        String code = LoRDeckCode.encode(deck);
        Assert.assertNull("Code was 6 chars, but didnt fail", code);
        
        deck.clear();
        deck.addCard(LoRCard.create("01XX202"), 1);
        code = LoRDeckCode.encode(deck);
        Assert.assertNull("Faction was invalid, but didnt fail", code);
        
        try
        {
            deck.clear();
            deck.addCard(LoRCard.create("01DE002"), 0);
            LoRDeckCode.encode(deck);
            
            Assert.fail("Count is 0, so it shouldnt return a valid card");
        } catch (IllegalArgumentException e)
        {
            // ok
        }
        
        try
        {
            deck.clear();
            deck.addCard(LoRCard.create("01DE002"), -1);
            LoRDeckCode.encode(deck);
            
            Assert.fail("Count is less than 1, but didnt fail");
        } catch (IllegalArgumentException e)
        {
            // ok
        }
    }
    
    @Test
    public void testInvalidCodes()
    {
        String badNot32 = "This is not a card code. Dont @me";
        String bad32    = "ABCDEFG";
        String empty    = "";
        
        checkCode(badNot32);
        checkCode(bad32);
        checkCode(empty);
    }
    
    private void checkCode(String code)
    {
        try
        {
            LoRDeck deck = LoRDeckCode.decode(code);
            Assert.fail("Invalid code did not produce an error");
        } catch (IllegalArgumentException e)
        {
            // ok
        } catch (Exception e)
        {
            Assert.fail("Invalid code produced the wrong exception");
        }
    }
    
    @Test
    public void testLargeDeckPrettyPrint()
    {
        LoRDeck deck = new LoRDeck();
        deck.addCard(LoRCard.create("01DE002"), 3);
        deck.addCard(LoRCard.create("01DE003"), 3);
        deck.addCard(LoRCard.create("01DE004"), 3);
        deck.addCard(LoRCard.create("01DE005"), 3);
        deck.addCard(LoRCard.create("01DE006"), 4);
        deck.addCard(LoRCard.create("01DE007"), 5);
        deck.addCard(LoRCard.create("01DE008"), 6);
        deck.addCard(LoRCard.create("01DE009"), 7);
        deck.addCard(LoRCard.create("01DE010"), 8);
        deck.addCard(LoRCard.create("01DE011"), 9);
        deck.addCard(LoRCard.create("01DE012"), 3);
        deck.addCard(LoRCard.create("01DE013"), 3);
        deck.addCard(LoRCard.create("01DE014"), 3);
        deck.addCard(LoRCard.create("01DE015"), 3);
        deck.addCard(LoRCard.create("01DE016"), 3);
        deck.addCard(LoRCard.create("01DE017"), 3);
        deck.addCard(LoRCard.create("01DE018"), 3);
        deck.addCard(LoRCard.create("01DE019"), 3);
        deck.addCard(LoRCard.create("01DE020"), 3);
        deck.addCard(LoRCard.create("01DE021"), 3);
        
        System.out.println(deck.toPrettyString());
    }
    
    
    private boolean checkSameDeck(LoRDeck a, LoRDeck b)
    {
        if (a.getDeck().size() != b.getDeck().size())
        {
            return false;
        }
        
        for (LoRCard cardA : a.getDeck().keySet())
        {
            if (!b.getDeck().containsKey(cardA))
            {
                return false;
            } else
            {
                Integer cardACount = a.getDeck().get(cardA);
                Integer cardBCount = b.getDeck().get(cardA);
                
                if (cardACount == null)
                {
                    return false;
                }
                
                if (cardACount.intValue() != cardBCount.intValue())
                {
                    return false;
                }
            }
        }
        
        return true;
    }
}
