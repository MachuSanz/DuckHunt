package machucapps.com.duckhunt.adapters;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import butterknife.BindView;
import butterknife.ButterKnife;
import machucapps.com.duckhunt.R;
import machucapps.com.duckhunt.models.User;

/**
 * User Rabking Adapter
 */
public class UserRankingAdapter extends RecyclerView.Adapter<UserRankingAdapter.ViewHolder>
{
	/**
	 * List of users
	 */
	private final List<User> mValues;

	/**
	 * Constructor
	 * 
	 * @param items - list of users
	 */
	public UserRankingAdapter( List<User> items )
	{
		mValues = items;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @param parent
	 * @param viewType
	 * @return
	 */
	@Override
	public ViewHolder onCreateViewHolder( ViewGroup parent, int viewType )
	{
		View view = LayoutInflater.from( parent.getContext() ).inflate( R.layout.fragment_user_ranking, parent, false );
		return new ViewHolder( view );
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @param holder
	 * @param position
	 */
	@Override
	public void onBindViewHolder( final ViewHolder holder, int position )
	{
		holder.setData( mValues.get( position ), position + 1 );
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @return
	 */
	@Override
	public int getItemCount()
	{
		return mValues.size();
	}

	/**
	 * View Holder
	 */
	public class ViewHolder extends RecyclerView.ViewHolder
	{

		/**
		 * Bindview's
		 */
		@BindView ( R.id.tv_position )
		TextView mTvPosition;

		@BindView ( R.id.tv_ducks )
		TextView mTvDucks;

		@BindView ( R.id.tv_nick )
		TextView mTvNick;

		private Context mContext;

		/**
		 * Constructor
		 * 
		 * @param view
		 */
		public ViewHolder( View view )
		{
			super( view );
			ButterKnife.bind( this, view );
			mContext = view.getContext();

		}

		/**
		 * Set Data
		 * 
		 * @param user
		 */
		private void setData( User user, int position )
		{
			mTvDucks.setText( String.valueOf( user.getDucks() ) );
			mTvNick.setText( user.getNick() );
			mTvPosition.setText( mContext.getString( R.string.ranking_position, position ) );
		}
	}
}
