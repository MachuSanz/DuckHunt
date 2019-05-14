package machucapps.com.duckhunt.activities;

import java.util.Random;

import com.google.firebase.firestore.FirebaseFirestore;

import android.content.Intent;
import android.graphics.Point;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.Display;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import machucapps.com.duckhunt.R;
import machucapps.com.duckhunt.utils.Constants;

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

	@BindView ( R.id.iv_duck )
	ImageView mIvDuck;

	/**
	 * Number of ducks hunted
	 */
	private int mCounter = 0;

	/**
	 * Screen's height and width
	 */
	private int mScreenHeight;
	private int mScreenWidth;

	/**
	 * Object random
	 */
	private Random mRandom;

	/**
	 * Check Game Over
	 */
	private boolean mGameOver = false;

	/**
	 * User's Id
	 */
	private String mUserId;

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
		setContentView( R.layout.activity_game );
		ButterKnife.bind( this );
		initDatabase();
		getIntentExtras();
		setCustomTypeface();
		initScreen();
		moveDuck();
		initCountDown();
	}

	/**
	 * Init Firestone connection
	 */
	private void initDatabase()
	{
		db = FirebaseFirestore.getInstance();
	}

	/**
	 * Get Intent Extras
	 */
	private void getIntentExtras()
	{
		Bundle extras = getIntent().getExtras();
		if ( extras != null && extras.getString( Constants.NICK_NAME_EXTRA ) != null && extras.getString( Constants.ID_EXTRA ) != null )
		{
			String nick = extras.getString( Constants.NICK_NAME_EXTRA );
			mUserId = extras.getString( Constants.ID_EXTRA );
			setNickName( nick );
		}
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
	 * Init Duck position
	 */
	private void initScreen()
	{
		Display display = getWindowManager().getDefaultDisplay();
		Point size = new Point();
		display.getSize( size );

		mScreenWidth = size.x;
		mScreenHeight = size.y;
		mRandom = new Random();
	}

	/**
	 * Init Count Down
	 */
	private void initCountDown()
	{
		new CountDownTimer( 10000 , 1000 )
		{

			public void onTick( long millisUntilFinished )
			{
				mTvTimer.setText( String.valueOf( millisUntilFinished / 1000 ).concat( "s" ) );
			}

			public void onFinish()
			{
				mGameOver = true;
				mTvTimer.setText( "0s" );
				showGameOverDialog();
				saveResultFirestore( mCounter );
			}
		}.start();

	}

	/**
	 * Update and save result on database
	 * 
	 * @param numDucks - final ducks hunted
	 */
	private void saveResultFirestore( int numDucks )
	{
		db.collection( Constants.DB_USERS_COLLECTION ).document( mUserId ).update( Constants.DB_FIELD_DUCKS, numDucks );
	}

	/**
	 * Show GameOver's dialog
	 */
	private void showGameOverDialog()
	{
		AlertDialog.Builder builder = new AlertDialog.Builder( this );
		builder.setMessage( getString( R.string.game_over_dialog_message, mCounter ) ).setTitle( getString( R.string.game_over_dialog_title ) );

		builder.setPositiveButton( getString( R.string.game_over_dialog_positive_message_text ), ( dialogInterface, i ) -> {
			mCounter = 0;
			mTvDuckHuntedCounter.setText( String.valueOf( mCounter ) );
			mGameOver = false;
			initCountDown();
			moveDuck();
		} );

		builder.setNegativeButton( getString( R.string.game_over_dialog_negative_message_text ), ( dialogInterface, i ) -> {
			dialogInterface.dismiss();
			finish();
		} );

		builder.setNeutralButton( getString( R.string.game_over_dialog_neutral_message_text ), ( ( dialogInterface, i ) -> {
			initRankingActivity();
		} ) );

		AlertDialog dialog = builder.create();
		dialog.setCancelable( false );
		dialog.show();

	}

	/**
	 * Init Ranking Activity
	 */
	private void initRankingActivity()
	{
		startActivity( new Intent( GameActivity.this , RankingActivity.class ) );
	}

	/**
	 * Set User Nick Name
	 */
	private void setNickName( String nickName )
	{
		mTvUserNickName.setText( nickName );
	}

	/**
	 * Duck's click event
	 */
	@OnClick ( R.id.iv_duck )
	public void onDuckClick()
	{
		if ( !mGameOver )
		{
			mTvDuckHuntedCounter.setText( String.valueOf( ++mCounter ) );
			mIvDuck.setImageResource( R.drawable.duck_clicked );

			new Handler().postDelayed( () -> {
				mIvDuck.setImageResource( R.drawable.duck );
				moveDuck();
			}, 500 );
		}

	}

	/**
	 * Change duck's position when it's hunted
	 */
	private void moveDuck()
	{
		int xMin = 0;
		int xMax = mScreenWidth - mIvDuck.getWidth();
		int yMax = mScreenHeight - mIvDuck.getHeight();

		int randomX = mRandom.nextInt( ( xMax - xMin ) + 1 );
		int randomY = mRandom.nextInt( ( yMax - xMin ) + 1 );

		mIvDuck.setX( randomX );
		mIvDuck.setY( randomY );
	}
}
