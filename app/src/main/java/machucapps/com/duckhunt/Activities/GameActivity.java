package machucapps.com.duckhunt.Activities;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import butterknife.BindView;
import machucapps.com.duckhunt.R;

/**
 * Game Activity Class
 */
public class GameActivity extends AppCompatActivity
{

	/**
	 * BindView's
	 */
	@BindView ( R.id.tv_counter )
	TextView tvDuckHuntedCounter;

	@BindView ( R.id.tv_nick )
	TextView tvUserNickName;

	@BindView ( R.id.tv_timer )
	TextView tvTimer;

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
	}
}
