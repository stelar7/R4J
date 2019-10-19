package no.stelar7.api.l4j8.impl.lor;

import no.stelar7.lor.types.LoRCard;

import java.util.Objects;

public class LoRGameRectangle
{
    private String CardID;
    private String CardCode;
    private int    TopLeftX;
    private int    TopLeftY;
    private int    Width;
    private int    Height;
    
    private LoRCard card;
    
    public LoRCard getCard()
    {
        if (card == null)
        {
            card = LoRCard.create(CardCode);
        }
        
        return card;
    }
    
    public String getCardID()
    {
        return CardID;
    }
    
    public String getCardCode()
    {
        return CardCode;
    }
    
    public int getTopLeftX()
    {
        return TopLeftX;
    }
    
    public int getTopLeftY()
    {
        return TopLeftY;
    }
    
    public int getWidth()
    {
        return Width;
    }
    
    public int getHeight()
    {
        return Height;
    }
    
    @Override
    public boolean equals(Object o)
    {
        if (this == o)
        {
            return true;
        }
        if (o == null || getClass() != o.getClass())
        {
            return false;
        }
        LoRGameRectangle that = (LoRGameRectangle) o;
        return TopLeftX == that.TopLeftX &&
               TopLeftY == that.TopLeftY &&
               Width == that.Width &&
               Height == that.Height &&
               Objects.equals(CardID, that.CardID) &&
               Objects.equals(CardCode, that.CardCode);
    }
    
    @Override
    public int hashCode()
    {
        return Objects.hash(CardID, CardCode, TopLeftX, TopLeftY, Width, Height);
    }
    
    @Override
    public String toString()
    {
        return "LoRGameRectangle{" +
               "CardID='" + CardID + '\'' +
               ", Card='" + (getCard() == null ? CardCode : card) + '\'' +
               ", TopLeftX=" + TopLeftX +
               ", TopLeftY=" + TopLeftY +
               ", Width=" + Width +
               ", Height=" + Height +
               '}';
    }
}
