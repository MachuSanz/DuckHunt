package machucapps.com.duckhunt.fragments;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import machucapps.com.duckhunt.R;
import machucapps.com.duckhunt.adapters.UserRankingAdapter;
import machucapps.com.duckhunt.models.User;

/**
 * User Rabking Fragment
 */
public class UserRankingFragment extends Fragment
{

	private static final String ARG_COLUMN_COUNT = "column-count";
	private int mColumnCount = 1;
	List<User> mUserList;
	UserRankingAdapter mAdapter;

	/**
	 * Constructor
	 */
	public UserRankingFragment()
	{
	}

	/**
	 * 
	 * @param columnCount
	 * @return
	 */
	@SuppressWarnings ( "unused" )
	public static UserRankingFragment newInstance(int columnCount )
	{
		UserRankingFragment fragment = new UserRankingFragment();
		Bundle args = new Bundle();
		args.putInt( ARG_COLUMN_COUNT, columnCount );
		fragment.setArguments( args );
		return fragment;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @param savedInstanceState
	 */
	@Override
	public void onCreate( Bundle savedInstanceState )
	{
		super.onCreate( savedInstanceState );

		if ( getArguments() != null )
		{
			mColumnCount = getArguments().getInt( ARG_COLUMN_COUNT );
		}
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @param inflater
	 * @param container
	 * @param savedInstanceState
	 * @return
	 */
	@Override
	public View onCreateView( LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState )
	{
		View view = inflater.inflate( R.layout.fragment_user_ranking_list, container, false );

		// Set the adapter
		if ( view instanceof RecyclerView )
		{
			Context context = view.getContext();
			RecyclerView recyclerView = ( RecyclerView ) view;
			if ( mColumnCount <= 1 )
			{
				recyclerView.setLayoutManager( new LinearLayoutManager( context ) );
			}
			else
			{
				recyclerView.setLayoutManager( new GridLayoutManager( context , mColumnCount ) );
			}

			mUserList = new ArrayList<>();
			mAdapter = new UserRankingAdapter( mUserList );
			recyclerView.setAdapter( mAdapter );
		}
		return view;
	}

}
