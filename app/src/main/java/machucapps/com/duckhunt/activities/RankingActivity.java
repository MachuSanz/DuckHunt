package machucapps.com.duckhunt.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import butterknife.ButterKnife;
import machucapps.com.duckhunt.R;
import machucapps.com.duckhunt.fragments.UserRankingFragment;

public class RankingActivity extends AppCompatActivity
{

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

}
