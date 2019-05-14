package machucapps.com.duckhunt.activities;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import butterknife.ButterKnife;
import machucapps.com.duckhunt.R;
import machucapps.com.duckhunt.fragments.UserRankingFragment;

/**
 * Ranking Activity
 */
public class RankingActivity extends AppCompatActivity
{
	/**
	 * {@inheritDoc}
	 * 
	 * @param savedInstanceState
	 */
	@Override
	protected void onCreate( Bundle savedInstanceState )
	{
		super.onCreate( savedInstanceState );
		ButterKnife.bind( this );
		setContentView( R.layout.activity_ranking );
		initToolbar();
		initFragment();

	}

	/**
	 * Init toolbar
	 */
	private void initToolbar()
	{
		Toolbar toolbar = findViewById( R.id.toolbar );
		setSupportActionBar( toolbar );
		getSupportActionBar().setDisplayHomeAsUpEnabled( true );
	}

	/**
	 * Init Fragment
	 */
	private void initFragment()
	{
		getSupportFragmentManager().beginTransaction().add( R.id.fl_container, new UserRankingFragment() ).commit();
	}

	/**
	 * {@inheritDoc}
	 *
	 * @param item
	 * @return
	 */
	@Override
	public boolean onOptionsItemSelected( MenuItem item )
	{
		switch ( item.getItemId() )
		{
		case android.R.id.home:
			finish();
			break;
		}
		return true;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void onBackPressed()
	{
		super.onBackPressed();
		finish();
	}
}
