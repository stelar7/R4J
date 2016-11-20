package no.stelar7.api.l4j8.pojo.staticdata.shared;

public class Image
{
	private String  full;
	private String  group;
	private Integer h;
	private String  sprite;
	private Integer w;
	private Integer x;
	private Integer y;

	@Override
	public boolean equals(final Object obj)
	{
		if (this == obj)
		{
			return true;
		}
		if (obj == null)
		{
			return false;
		}
		if (this.getClass() != obj.getClass())
		{
			return false;
		}
		final Image other = (Image) obj;
		if (this.full == null)
		{
			if (other.full != null)
			{
				return false;
			}
		} else if (!this.full.equals(other.full))
		{
			return false;
		}
		if (this.group == null)
		{
			if (other.group != null)
			{
				return false;
			}
		} else if (!this.group.equals(other.group))
		{
			return false;
		}
		if (this.h == null)
		{
			if (other.h != null)
			{
				return false;
			}
		} else if (!this.h.equals(other.h))
		{
			return false;
		}
		if (this.sprite == null)
		{
			if (other.sprite != null)
			{
				return false;
			}
		} else if (!this.sprite.equals(other.sprite))
		{
			return false;
		}
		if (this.w == null)
		{
			if (other.w != null)
			{
				return false;
			}
		} else if (!this.w.equals(other.w))
		{
			return false;
		}
		if (this.x == null)
		{
			if (other.x != null)
			{
				return false;
			}
		} else if (!this.x.equals(other.x))
		{
			return false;
		}
		if (this.y == null)
		{
			if (other.y != null)
			{
				return false;
			}
		} else if (!this.y.equals(other.y))
		{
			return false;
		}
		return true;
	}

	/**
	 * Gets the full.
	 *
	 * @return the full
	 */
	public String getFull()
	{
		return this.full;
	}

	/**
	 * Gets the group.
	 *
	 * @return the group
	 */
	public String getGroup()
	{
		return this.group;
	}

	/**
	 * Gets the h.
	 *
	 * @return the h
	 */
	public Integer getH()
	{
		return this.h;
	}

	/**
	 * Gets the sprite.
	 *
	 * @return the sprite
	 */
	public String getSprite()
	{
		return this.sprite;
	}

	/**
	 * Gets the w.
	 *
	 * @return the w
	 */
	public Integer getW()
	{
		return this.w;
	}

	/**
	 * Gets the x.
	 *
	 * @return the x
	 */
	public Integer getX()
	{
		return this.x;
	}

	/**
	 * Gets the y.
	 *
	 * @return the y
	 */
	public Integer getY()
	{
		return this.y;
	}

	@Override
	public int hashCode()
	{
		final int prime  = 31;
		int       result = 1;
		result = (prime * result) + ((this.full == null) ? 0 : this.full.hashCode());
		result = (prime * result) + ((this.group == null) ? 0 : this.group.hashCode());
		result = (prime * result) + ((this.h == null) ? 0 : this.h.hashCode());
		result = (prime * result) + ((this.sprite == null) ? 0 : this.sprite.hashCode());
		result = (prime * result) + ((this.w == null) ? 0 : this.w.hashCode());
		result = (prime * result) + ((this.x == null) ? 0 : this.x.hashCode());
		result = (prime * result) + ((this.y == null) ? 0 : this.y.hashCode());
		return result;
	}
}
