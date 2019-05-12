package machucapps.com.duckhunt.models;

/**
 * User Model Class
 */
public class User
{

	/**
	 * Nick
	 */
	private String nick;

	/**
	 * Ducks
	 */
	private int ducks;

	/**
	 * Empty Constructor
	 */
	public User()
	{

	}

	/**
	 * Constructor
	 * 
	 * @param nick - nick of user
	 * @param ducks - number of ducks
	 */
	public User( String nick, int ducks )
	{
		this.nick = nick;
		this.ducks = ducks;
	}

	/**
	 * Get Nick
	 * 
	 * @return - nick
	 */
	public String getNick()
	{
		return nick;
	}

	/**
	 * Set Nick
	 * 
	 * @param nick - nick
	 */
	public void setNick( String nick )
	{
		this.nick = nick;
	}

	/**
	 * get Ducks
	 * 
	 * @return - number of ducks
	 */
	public int getDucks()
	{
		return ducks;
	}

	/**
	 * Set ducks
	 * 
	 * @param ducks - number of ducks
	 */
	public void setDucks( int ducks )
	{
		this.ducks = ducks;
	}
}
