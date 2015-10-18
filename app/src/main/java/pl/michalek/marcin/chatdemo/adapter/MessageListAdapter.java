package pl.michalek.marcin.chatdemo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.ButterKnife;
import butterknife.InjectView;
import com.nostra13.universalimageloader.core.ImageLoader;
import pl.michalek.marcin.chatdemo.R;
import pl.michalek.marcin.chatdemo.adapter.data.MessageData;
import pl.michalek.marcin.chatdemo.adapter.data.MessageType;
import pl.michalek.marcin.chatdemo.config.Constants;

import java.util.ArrayList;
import java.util.List;

/**
 * Adapter for chat message list.
 *
 * @author Marcin Michałek
 */
public class MessageListAdapter extends RecyclerView.Adapter<MessageListAdapter.MessageHolder> {
  private List<MessageData> data;
  private LayoutInflater layoutInflater;
  private ImageLoader imageLoader;

  public class MessageHolder extends RecyclerView.ViewHolder {
    @InjectView(R.id.message)
    public TextView message;

    @InjectView(R.id.avatar)
    public ImageView avatar;

    public MessageHolder(View view) {
      super(view);
      ButterKnife.inject(this, view);
    }
  }

  public MessageListAdapter(Context context) {
    layoutInflater = LayoutInflater.from(context);
    imageLoader = ImageLoader.getInstance();
    data = new ArrayList<>();
  }

  public void setData(List<MessageData> data) {
    this.data = data;
    notifyDataSetChanged();
  }

  public void addDataItem(MessageData messageData) {
    data.add(messageData);
    notifyItemInserted(data.size());
  }

  @Override
  public MessageHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View view;
    if (viewType == Constants.MESSAGE_VIEW_TYPE_MY) {
      view = layoutInflater.inflate(R.layout.item_message_list_my, parent, false);
      ((LinearLayout) view).setGravity(Gravity.END);
    } else if (viewType == Constants.MESSAGE_VIEW_TYPE_OUTSIDE) {
      view = layoutInflater.inflate(R.layout.item_message_list_outside, parent, false);
      ((LinearLayout) view).setGravity(Gravity.START);

    } else {
      view = null;
      Log.e(Constants.LOGTAG, "Message view type not supported.");
    }

    return new MessageHolder(view);
  }

  @Override
  public void onBindViewHolder(MessageHolder holder, int position) {
    holder.message.setText(getItem(position).getMessage());
    if (getItem(position).getMessageType() == MessageType.OUTSIDE) {
      imageLoader.displayImage(Constants.URI_DRAWABLE + R.drawable.avatar_default, holder.avatar);
    } else if (getItem(position).getMessageType() == MessageType.MY) {
      imageLoader.displayImage(Constants.URI_DRAWABLE + R.drawable.avatar_my, holder.avatar);
    }
  }

  @Override
  public int getItemViewType(int position) {
    if (data.get(position).getMessageType() == MessageType.MY) {
      return Constants.MESSAGE_VIEW_TYPE_MY;
    } else if (data.get(position).getMessageType() == MessageType.OUTSIDE) {
      return Constants.MESSAGE_VIEW_TYPE_OUTSIDE;
    } else {
      return Constants.MESSAGE_VIEW_TYPE_UNSUPPORTED;
    }
  }

  @Override
  public int getItemCount() {
    return data.size();
  }

  public MessageData getItem(int position) {
    return data.get(position);
  }

}
