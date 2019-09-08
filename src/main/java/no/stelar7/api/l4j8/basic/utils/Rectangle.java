package no.stelar7.api.l4j8.basic.utils;

import java.util.Objects;

public class Rectangle
{
    private int x;
    private int y;
    private int w;
    private int h;
    
    public Rectangle(int x, int y, int w, int h)
    {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }
    
    public int getX()
    {
        return x;
    }
    
    public void setX(int x)
    {
        this.x = x;
    }
    
    public int getY()
    {
        return y;
    }
    
    public void setY(int y)
    {
        this.y = y;
    }
    
    public int getW()
    {
        return w;
    }
    
    public void setW(int w)
    {
        this.w = w;
    }
    
    public int getH()
    {
        return h;
    }
    
    public void setH(int h)
    {
        this.h = h;
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
        Rectangle rectangle = (Rectangle) o;
        return x == rectangle.x &&
               y == rectangle.y &&
               w == rectangle.w &&
               h == rectangle.h;
    }
    
    @Override
    public int hashCode()
    {
        return Objects.hash(x, y, w, h);
    }
    
    @Override
    public String toString()
    {
        return "Rectangle{" +
               "x=" + x +
               ", y=" + y +
               ", w=" + w +
               ", h=" + h +
               '}';
    }
}
