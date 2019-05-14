package machucapps.com.duckhunt.fragments;

import java.util.ArrayList;
import java.util.List;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import machucapps.com.duckhunt.R;
import machucapps.com.duckhunt.adapters.UserRankingAdapter;
import machucapps.com.duckhunt.models.User;
import machucapps.com.duckhunt.utils.Constants;

/**
 * User Rabking Fragment
 */
public class UserRankingFragment extends Fragment
{

	List<User> mUserList;
	UserRankingAdapter mAdapter;

	FirebaseFirestore db;

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
	public static UserRankingFragment newInstance( int columnCount )
	{
		UserRankingFragment fragment = new UserRankingFragment();
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
		db = FirebaseFirestore.getInstance();
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
			RecyclerView recyclerView = ( RecyclerView ) view;
			db.collection( Constants.DB_USERS_COLLECTION ).orderBy( Constants.DB_FIELD_DUCKS, Query.Direction.DESCENDING ).limit( 10 ).get()
					.addOnCompleteListener( task -> {
						mUserList = new ArrayList<>();
						for ( DocumentSnapshot document : task.getResult() )
						{
							User user = document.toObject( User.class );
							mUserList.add( user );
						}
						mAdapter = new UserRankingAdapter( mUserList );
						recyclerView.setAdapter( mAdapter );
					} );
		}
		return view;
	}

}
