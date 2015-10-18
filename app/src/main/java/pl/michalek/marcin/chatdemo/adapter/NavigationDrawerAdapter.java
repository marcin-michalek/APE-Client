package pl.michalek.marcin.chatdemo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.ButterKnife;
import butterknife.InjectView;
import com.nostra13.universalimageloader.core.ImageLoader;
import pl.michalek.marcin.apeclient.network.data.User;
import pl.michalek.marcin.chatdemo.R;
import pl.michalek.marcin.chatdemo.config.Constants;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO Add class description...
 *
 * @author Marcin Michałek
 */
public class NavigationDrawerAdapter extends BaseAdapter {
  private LayoutInflater layoutInflater;
  private List<User> data;
  private ImageLoader imageLoader;

  class ViewHolder {
    @InjectView(R.id.nameTextView)
    TextView name;

    @InjectView(R.id.avatar)
    ImageView avatar;

    public ViewHolder(View view) {
      ButterKnife.inject(this, view);
    }
  }

  public NavigationDrawerAdapter(Context context) {
    this.layoutInflater = LayoutInflater.from(context);
    this.imageLoader = ImageLoader.getInstance();
    this.data = new ArrayList<User>();
  }

  @Override
  public View getView(int position, View view, ViewGroup parent) {
    ViewHolder viewHolder;
    if (view != null) {
      viewHolder = (ViewHolder) view.getTag();
    } else {
      view = layoutInflater.inflate(R.layout.item_navigation_drawer_list, parent, false);
      viewHolder = new ViewHolder(view);
      view.setTag(viewHolder);
    }

    imageLoader.displayImage(Constants.URI_DRAWABLE + R.drawable.avatar_default, viewHolder.avatar, Constants.IMAGE_CIRCLE);
    viewHolder.name.setText(getItem(position).properties.name);
    return view;
  }

  public void setData(List<User> data) {
    this.data = new ArrayList<User>(data);
    notifyDataSetChanged();
  }

  public void addDataValue(User dataValue){
    this.data.add(dataValue);
    notifyDataSetChanged();
  }

  @Override
  public int getCount() {
    return data.size();
  }

  @Override
  public User getItem(int position) {
    return data.get(position);
  }

  @Override
  public long getItemId(int position) {
    return position;
  }

  public List<User> getData() {
    return data;
  }
}
