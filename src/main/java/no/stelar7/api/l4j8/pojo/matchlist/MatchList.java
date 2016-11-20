package no.stelar7.api.l4j8.pojo.matchlist;

import java.util.List;

public class MatchList
{
	private Integer              endIndex;
	private Integer              startIndex;
	private Integer              totalGames;
	private List<MatchReference> matches;

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
		final MatchList other = (MatchList) obj;
		if (this.endIndex == null)
		{
			if (other.endIndex != null)
			{
				return false;
			}
		} else if (!this.endIndex.equals(other.endIndex))
		{
			return false;
		}
		if (this.matches == null)
		{
			if (other.matches != null)
			{
				return false;
			}
		} else if (!this.matches.equals(other.matches))
		{
			return false;
		}
		if (this.startIndex == null)
		{
			if (other.startIndex != null)
			{
				return false;
			}
		} else if (!this.startIndex.equals(other.startIndex))
		{
			return false;
		}
		if (this.totalGames == null)
		{
			if (other.totalGames != null)
			{
				return false;
			}
		} else if (!this.totalGames.equals(other.totalGames))
		{
			return false;
		}
		return true;
	}

	/**
	 * Gets the end index.
	 *
	 * @return the end index
	 */
	public Integer getEndIndex()
	{
		return this.endIndex;
	}

	/**
	 * Gets the matches.
	 *
	 * @return the matches
	 */
	public List<MatchReference> getMatches()
	{
		return this.matches;
	}

	/**
	 * Gets the start index.
	 *
	 * @return the start index
	 */
	public Integer getStartIndex()
	{
		return this.startIndex;
	}

	/**
	 * Gets the total games.
	 *
	 * @return the total games
	 */
	public Integer getTotalGames()
	{
		return this.totalGames;
	}

	@Override
	public int hashCode()
	{
		final int prime  = 31;
		int       result = 1;
		result = (prime * result) + ((this.endIndex == null) ? 0 : this.endIndex.hashCode());
		result = (prime * result) + ((this.matches == null) ? 0 : this.matches.hashCode());
		result = (prime * result) + ((this.startIndex == null) ? 0 : this.startIndex.hashCode());
		result = (prime * result) + ((this.totalGames == null) ? 0 : this.totalGames.hashCode());
		return result;
	}

	@Override
	public String toString()
	{
		return "MatchList [endIndex=" + this.endIndex + ", startIndex=" + this.startIndex + ", totalGames=" + this.totalGames + ", matches=" + this.matches + "]";
	}

}
