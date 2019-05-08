package machucapps.com.duckhunt.Activities;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import butterknife.BindView;
import machucapps.com.duckhunt.R;
import machucapps.com.duckhunt.Utils.Constants;

/**
 * Game Activity Class
 */
public class GameActivity extends AppCompatActivity
{

	/**
	 * BindView's
	 */
	@BindView ( R.id.tv_counter )
	TextView mTvDuckHuntedCounter;

	@BindView ( R.id.tv_nick )
	TextView mTvUserNickName;

	@BindView ( R.id.tv_timer )
	TextView mTvTimer;

	/**
	 * {@inheritDoc}
	 * 
	 * @param savedInstanceState
	 */
	@Override
	protected void onCreate( Bundle savedInstanceState )
	{
		super.onCreate( savedInstanceState );
		setContentView( R.layout.activity_game );
		getIntentExtras();
	}

	/**
	 * Get Intent Extras
	 */
	private void getIntentExtras()
	{
		Bundle extras = getIntent().getExtras();
		String nick = extras.getString( Constants.NICK_NAME_EXTRA );
		setNickName( nick );
	}

	/**
	 * Set User Nick Name
	 */
	private void setNickName( String nickName )
	{
		mTvDuckHuntedCounter.setText( nickName );
	}
}
