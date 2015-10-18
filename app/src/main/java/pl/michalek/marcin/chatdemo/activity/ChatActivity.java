package pl.michalek.marcin.chatdemo.activity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ListView;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnEditorAction;
import butterknife.OnItemClick;
import de.greenrobot.event.EventBus;
import org.joda.time.DateTime;
import pl.michalek.marcin.apeclient.APEClient;
import pl.michalek.marcin.apeclient.ape.error.APEErrorType;
import pl.michalek.marcin.apeclient.event.*;
import pl.michalek.marcin.apeclient.interfaces.APEChatCommunicationInterface;
import pl.michalek.marcin.apeclient.network.data.User;
import pl.michalek.marcin.chatdemo.R;
import pl.michalek.marcin.chatdemo.adapter.MessageListAdapter;
import pl.michalek.marcin.chatdemo.adapter.NavigationDrawerAdapter;
import pl.michalek.marcin.chatdemo.adapter.data.MessageData;
import pl.michalek.marcin.chatdemo.adapter.data.MessageRepository;
import pl.michalek.marcin.chatdemo.adapter.data.MessageType;
import pl.michalek.marcin.chatdemo.config.ChatClientConfig;
import pl.michalek.marcin.chatdemo.config.Constants;

import java.util.Iterator;
import java.util.List;

/**
 * Main activity containing chat
 *
 * @author Marcin Michałek
 */
public class ChatActivity extends BaseActivity implements APEChatCommunicationInterface {
  @InjectView(R.id.navigationDrawerList)
  ListView navigationDrawerList;

  @InjectView(R.id.chatMessagesList)
  RecyclerView chatMessagesList;

  private APEClient apeClient;

  private EventBus eventBus;

  private MessageListAdapter messageListAdapter;
  private NavigationDrawerAdapter navigationDrawerAdapter;

  private String nickName;

  private MessageRepository messageRepository;

  private String currentPipe;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_chat);
    ButterKnife.inject(this);
    nickName = getIntent().getStringExtra(Constants.EXTRA_NICK);

    initializeRecyclerView();

    navigationDrawerAdapter = new NavigationDrawerAdapter(this);
    navigationDrawerList.setAdapter(navigationDrawerAdapter);

    eventBus = new EventBus();
    eventBus.register(this);

    apeClient = new APEClient(eventBus, new ChatClientConfig());
    apeClient.connect(nickName);

    messageRepository = new MessageRepository();
  }

  private void initializeRecyclerView() {
    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
    linearLayoutManager.setStackFromEnd(true);
    chatMessagesList.setLayoutManager(linearLayoutManager);
    messageListAdapter = new MessageListAdapter(this);
    chatMessagesList.setAdapter(messageListAdapter);
    chatMessagesList.setItemAnimator(new DefaultItemAnimator());
  }

  @OnEditorAction(R.id.sendEditText)
  public boolean onEditorAction(EditText editText, int actionId, KeyEvent event) {
    if ((event != null && (event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) || (actionId == EditorInfo.IME_ACTION_DONE)) {
      sendMessage(editText.getText().toString());
      editText.setText("");
    }
    return false;
  }

  @OnItemClick(R.id.navigationDrawerList)
  public void loadMessagesFromUser(int position) {
    User clickedUser = navigationDrawerAdapter.getItem(position);
    messageListAdapter.setData(messageRepository.getMessagesFromUser(clickedUser));
    apeClient.setCurrentPipe(clickedUser.publicId);
    currentPipe = clickedUser.publicId;
  }

  private void sendMessage(String message) {
    displayMyMessage(message);
    apeClient.sendMessage(message, currentPipe);
  }

  private void displayMyMessage(String message) {
    messageListAdapter.addDataItem(new MessageData(new DateTime().getMillis(),
        MessageType.MY, nickName, message));
  }

  @Override
  public void onEvent(LoginEvent loginEvent) {
    Log.d(Constants.LOGTAG, "Login event");
    apeClient.joinChannel("chatdemo");
  }

  @Override
  public void onEvent(IdentificationEvent identificationEvent) {
    Log.d(Constants.LOGTAG, "Identification event");
  }

  @Override
  public void onEvent(CloseEvent closeEvent) {
    Log.d(Constants.LOGTAG, "Close event");
  }

  @Override
  public void onEvent(NewMessageEvent newMessageEvent) {
    Log.d(Constants.LOGTAG, "New message event");
    Log.d(Constants.LOGTAG, newMessageEvent.getFrom().properties.name + ": " + newMessageEvent.getMessage());

    messageRepository.addMessageFromUser(newMessageEvent.getFrom(), new MessageData(newMessageEvent.getTime(), MessageType.OUTSIDE,
        newMessageEvent.getFrom().properties.name, newMessageEvent.getMessage()));

    if (currentPipe.equals(newMessageEvent.getPipe().publicId)) {
      messageListAdapter.addDataItem(new MessageData(newMessageEvent.getTime(), MessageType.OUTSIDE,
          newMessageEvent.getFrom().properties.name, newMessageEvent.getMessage()));
    } else {
      displayToast(newMessageEvent.getFrom().properties.name + Constants.CHARACTER_SPACE
          + getString(R.string.wroteToYou) + Constants.CHARACTER_SPACE + getString(R.string.swipeRight));
    }
  }

  @Override
  public void onEvent(ErrorEvent errorEvent) {
    Log.d(Constants.LOGTAG, "Error event: " + errorEvent.getCode() + errorEvent.getMessage());
    if (errorEvent.getApeErrorType() == APEErrorType.NICK_USED) {
      displayInformationDialog(getString(R.string.information), getString(R.string.nickAlreadyTaken), getString(R.string.inputDifferentNick), new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
          dialog.dismiss();
          finish();
        }
      });
    }
  }

  @Override
  public void onEvent(ChannelEvent channelEvent) {
    Log.d(Constants.LOGTAG, "Channel event");
    displayUsersListInNavigationDrawer(channelEvent.getUsersList());
    currentPipe = channelEvent.getPipe().publicId;
  }

  @Override
  public void onEvent(JoinEvent joinEvent) {
    Log.d(Constants.LOGTAG, "Join event");
    navigationDrawerAdapter.addDataValue(joinEvent.getUser());
  }

  @Override
  public void onEvent(ConnectionErrorEvent connectionErrorEvent) {
    Log.e(Constants.LOGTAG, "Connection error: " + connectionErrorEvent.getStatusCode() + " - " + connectionErrorEvent.getReason());
  }

  @Override
  public void onEvent(LeftEvent leftEvent) {
    displayToast(getString(R.string.userLeft) + Constants.CHARACTER_SPACE + leftEvent.getUser().properties.name);
    messageRepository.deleteMessagesFromUser(leftEvent.getUser());
    deleteUserFromNavigationDrawer(leftEvent.getUser());
  }

  public void deleteUserFromNavigationDrawer(User user) {
    Iterator<User> iterator = navigationDrawerAdapter.getData().iterator();
    while (iterator.hasNext()) {
      if (iterator.next().equals(user)) {
        iterator.remove();
      }
    }
    navigationDrawerAdapter.notifyDataSetChanged();
  }

  private void displayUsersListInNavigationDrawer(List<User> usersList) {
    navigationDrawerAdapter.setData(usersList);
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.menu_main, menu);
    return super.onCreateOptionsMenu(menu);
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem menuItem) {
    switch (menuItem.getItemId()) {
      case R.id.leaveChat:
        apeClient.leave();
        finish();
        break;
      default:
        super.onOptionsItemSelected(menuItem);
        break;
    }
    return true;
  }
}
