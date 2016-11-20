package no.stelar7.api.l4j8.pojo.match;

import java.util.List;

public class Timeline
{
	private Long        frameInterval;
	private List<Frame> frames;

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
		final Timeline other = (Timeline) obj;
		if (this.frameInterval == null)
		{
			if (other.frameInterval != null)
			{
				return false;
			}
		} else if (!this.frameInterval.equals(other.frameInterval))
		{
			return false;
		}
		if (this.frames == null)
		{
			if (other.frames != null)
			{
				return false;
			}
		} else if (!this.frames.equals(other.frames))
		{
			return false;
		}
		return true;
	}

	/**
	 * Gets the frame interval.
	 *
	 * @return the frame interval
	 */
	public Long getFrameInterval()
	{
		return this.frameInterval;
	}

	/**
	 * Gets the frames.
	 *
	 * @return the frames
	 */
	public List<Frame> getFrames()
	{
		return this.frames;
	}

	@Override
	public int hashCode()
	{
		final int prime  = 31;
		int       result = 1;
		result = (prime * result) + ((this.frameInterval == null) ? 0 : this.frameInterval.hashCode());
		result = (prime * result) + ((this.frames == null) ? 0 : this.frames.hashCode());
		return result;
	}

	@Override
	public String toString()
	{
		return "Timeline [frameInterval=" + this.frameInterval + ", frames=" + this.frames + "]";
	}

}
