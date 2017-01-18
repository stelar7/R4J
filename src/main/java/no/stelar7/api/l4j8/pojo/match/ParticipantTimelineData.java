package no.stelar7.api.l4j8.pojo.match;

public class ParticipantTimelineData
{
	
	private Double tenToTwenty;
	private Double thirtyToEnd;
	private Double twentyToThirty;
	private Double zeroToTen;
	
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
		final ParticipantTimelineData other = (ParticipantTimelineData) obj;
		if (this.tenToTwenty == null)
		{
			if (other.tenToTwenty != null)
			{
				return false;
			}
		} else if (!this.tenToTwenty.equals(other.tenToTwenty))
		{
			return false;
		}
		if (this.thirtyToEnd == null)
		{
			if (other.thirtyToEnd != null)
			{
				return false;
			}
		} else if (!this.thirtyToEnd.equals(other.thirtyToEnd))
		{
			return false;
		}
		if (this.twentyToThirty == null)
		{
			if (other.twentyToThirty != null)
			{
				return false;
			}
		} else if (!this.twentyToThirty.equals(other.twentyToThirty))
		{
			return false;
		}
		if (this.zeroToTen == null)
		{
			if (other.zeroToTen != null)
			{
				return false;
			}
		} else if (!this.zeroToTen.equals(other.zeroToTen))
		{
			return false;
		}
		return true;
	}
	
	/**
	 * Gets the ten to twenty.
	 *
	 * @return the ten to twenty
	 */
	public Double getTenToTwenty()
	{
		return this.tenToTwenty;
	}
	
	/**
	 * Gets the thirty to end.
	 *
	 * @return the thirty to end
	 */
	public Double getThirtyToEnd()
	{
		return this.thirtyToEnd;
	}
	
	/**
	 * Gets the twenty to thirty.
	 *
	 * @return the twenty to thirty
	 */
	public Double getTwentyToThirty()
	{
		return this.twentyToThirty;
	}
	
	/**
	 * Gets the zero to ten.
	 *
	 * @return the zero to ten
	 */
	public Double getZeroToTen()
	{
		return this.zeroToTen;
	}
	
	@Override
	public int hashCode()
	{
		final int prime  = 31;
		int       result = 1;
		result = (prime * result) + ((this.tenToTwenty == null) ? 0 : this.tenToTwenty.hashCode());
		result = (prime * result) + ((this.thirtyToEnd == null) ? 0 : this.thirtyToEnd.hashCode());
		result = (prime * result) + ((this.twentyToThirty == null) ? 0 : this.twentyToThirty.hashCode());
		result = (prime * result) + ((this.zeroToTen == null) ? 0 : this.zeroToTen.hashCode());
		return result;
	}
	
	@Override
	public String toString()
	{
		return "ParticipantTimelineData [tenToTwenty=" + this.tenToTwenty + ", thirtyToEnd=" + this.thirtyToEnd + ", twentyToThirty=" + this.twentyToThirty + ", zeroToTen=" + this.zeroToTen + "]";
	}
}
