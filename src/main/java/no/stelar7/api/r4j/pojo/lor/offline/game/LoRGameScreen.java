package no.stelar7.api.r4j.pojo.lor.offline.game;

import no.stelar7.api.r4j.pojo.lor.offline.card.LoRCard;

import java.io.Serializable;
import java.util.Objects;

public class LoRGameScreen implements Serializable
{
    private static final long serialVersionUID = 8604441099984405595L;
    private int ScreenWidth;
    private int ScreenHeight;
    
    public int getWidth()
    {
        return ScreenWidth;
    }
    
    public int getHeight()
    {
        return ScreenHeight;
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
        LoRGameScreen that = (LoRGameScreen) o;
        return ScreenWidth == that.ScreenWidth &&
               ScreenHeight == that.ScreenHeight;
    }
    
    @Override
    public int hashCode()
    {
        return Objects.hash(ScreenWidth, ScreenHeight);
    }
    
    @Override
    public String toString()
    {
        return "LoRGameScreen{" +
               "ScreenWidth=" + ScreenWidth +
               ", ScreenHeight=" + ScreenHeight +
               '}';
    }
    
    public static class LoRCardRectangle
    {
        private String  CardID;
        private String  CardCode;
        private boolean localPlayer;
        private int     TopLeftX;
        private int     TopLeftY;
        private int     Width;
        private int     Height;
        
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
        
        public boolean isLocalPlayer()
        {
            return localPlayer;
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
            LoRCardRectangle that = (LoRCardRectangle) o;
            return localPlayer == that.localPlayer &&
                   TopLeftX == that.TopLeftX &&
                   TopLeftY == that.TopLeftY &&
                   Width == that.Width &&
                   Height == that.Height &&
                   Objects.equals(CardID, that.CardID) &&
                   Objects.equals(CardCode, that.CardCode) &&
                   Objects.equals(card, that.card);
        }
        
        @Override
        public int hashCode()
        {
            return Objects.hash(CardID, CardCode, localPlayer, TopLeftX, TopLeftY, Width, Height, card);
        }
        
        @Override
        public String toString()
        {
            return "LoRCardRectangle{" +
                   "CardID='" + CardID + '\'' +
                   ", CardCode='" + CardCode + '\'' +
                   ", localPlayer=" + localPlayer +
                   ", TopLeftX=" + TopLeftX +
                   ", TopLeftY=" + TopLeftY +
                   ", Width=" + Width +
                   ", Height=" + Height +
                   ", card=" + card +
                   '}';
        }
    }
}
