package machucapps.com.duckhunt.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import machucapps.com.duckhunt.R;
import machucapps.com.duckhunt.Utils.Constants;

/**
 * Login Activity
 */
public class LoginActivity extends AppCompatActivity
{
	/**
	 * BindView's
	 */
	@BindView ( R.id.ed_nickname )
	EditText mEtUserNickName;

	/**
	 * {@inheritDoc}
	 * 
	 * @param savedInstanceState
	 */
	@Override
	protected void onCreate( Bundle savedInstanceState )
	{
		super.onCreate( savedInstanceState );
		setContentView( R.layout.activity_login );
		ButterKnife.bind( this );
	}

	/**
	 * Login event click
	 */
	@OnClick ( R.id.btn_login )
	public void onButtonLoginClick()
	{
		if ( mEtUserNickName != null && mEtUserNickName.getText() != null && mEtUserNickName.getText().toString().isEmpty() )
		{
			mEtUserNickName.setError( getString( R.string.login_et_name_error ) );
		}
		else if ( mEtUserNickName != null && mEtUserNickName.length() < Constants.NICK_NAME_LENGHT )
		{
			mEtUserNickName.setError( getString( R.string.login_et_name_error_length ) );
		}
		else if ( mEtUserNickName != null && mEtUserNickName.getText() != null && !mEtUserNickName.getText().toString().isEmpty() )
		{
			initGameActivity( mEtUserNickName.getText().toString() );
		}
	}

	/**
	 * Launch Game Activity
	 */
	private void initGameActivity( String nickName )
	{
		Intent initGameActivityIntent = new Intent( LoginActivity.this , GameActivity.class );
		initGameActivityIntent.putExtra( Constants.NICK_NAME_EXTRA, nickName );
		startActivity( initGameActivityIntent );
		finish();
	}
}
