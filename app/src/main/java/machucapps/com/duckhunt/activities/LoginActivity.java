package machucapps.com.duckhunt.activities;

import com.google.firebase.firestore.FirebaseFirestore;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import machucapps.com.duckhunt.R;
import machucapps.com.duckhunt.models.User;
import machucapps.com.duckhunt.utils.Constants;

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

	@BindView ( R.id.btn_login )
	Button mBtnLogin;

	/**
	 * Database
	 */
	private FirebaseFirestore db;

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
		initDatabase();
		setCustomTypeface();

	}

	/**
	 * Init Firestone connection
	 */
	private void initDatabase()
	{
		db = FirebaseFirestore.getInstance();
	}

	/**
	 * Set Custom Typeface
	 */
	private void setCustomTypeface()
	{
		Typeface typeface = Typeface.createFromAsset( getAssets(), "pixel.ttf" );
		mEtUserNickName.setTypeface( typeface );
		mBtnLogin.setTypeface( typeface );
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
			checkUserExists( mEtUserNickName.getText().toString().toLowerCase() );
		}
	}

	/**
	 * Check if user already exists
	 */
	private void checkUserExists( String nickname )
	{
		db.collection( Constants.DB_USERS_COLLECTION ).whereEqualTo( Constants.DB_FIELD_NICK, nickname ).get().addOnSuccessListener( queryDocumentSnapshots -> {
			if ( queryDocumentSnapshots.size() > 0 )
			{
				mEtUserNickName.setError( getString( R.string.login_et_name_error_not_available ) );
			}
			else
			{
				saveNickAndStart( nickname );
			}
		} );
	}

	/**
	 * Launch Game Activity
	 */
	private void saveNickAndStart( String nickName )
	{
		db.collection( Constants.DB_USERS_COLLECTION ).add( new User( nickName , 0 ) ).addOnSuccessListener( documentReference -> {
			Intent initGameActivityIntent = new Intent( LoginActivity.this , GameActivity.class );
			initGameActivityIntent.putExtra( Constants.NICK_NAME_EXTRA, nickName );
			initGameActivityIntent.putExtra( Constants.ID_EXTRA, documentReference.getId() );
			mEtUserNickName.setText( "" );
			startActivity( initGameActivityIntent );
		} );

	}
}
