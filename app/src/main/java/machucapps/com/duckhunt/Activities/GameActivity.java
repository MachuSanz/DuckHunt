package machucapps.com.duckhunt.Activities;

import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
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
		ButterKnife.bind( this );
		getIntentExtras();
		setCustomTypeface();
	}

	/**
	 * Set Custom Typeface
	 */
	private void setCustomTypeface()
	{
		Typeface typeface = Typeface.createFromAsset( getAssets(), "pixel.ttf" );
		mTvDuckHuntedCounter.setTypeface( typeface );
		mTvUserNickName.setTypeface( typeface );
		mTvTimer.setTypeface( typeface );
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
		mTvUserNickName.setText( nickName );
	}
}
