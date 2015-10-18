package pl.michalek.marcin.chatdemo.activity;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.ViewTreeObserver;
import android.widget.EditText;
import android.widget.RelativeLayout;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import pl.michalek.marcin.chatdemo.R;
import pl.michalek.marcin.chatdemo.config.Constants;

/**
 * Activity with login form.
 *
 * @author Marcin Michałek
 */
public class LoginActivity extends BaseActivity {
  @InjectView(R.id.nickEditText)
  EditText nickEditText;

  @InjectView(R.id.rootView)
  RelativeLayout rootView;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_login);
    ButterKnife.inject(this);
    setActionBarTitle(getString(R.string.joinAChat));
    addOnSoftInputShowListener();
  }

  private void addOnSoftInputShowListener() {
    rootView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
      @Override
      public void onGlobalLayout() {
        int heightDiff = rootView.getRootView().getHeight() - rootView.getHeight();
        if (heightDiff > 110) {
          //soft input shows up
          moveLoginEditTextAndButtonUp();
        }
      }

      @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
      private void moveLoginEditTextAndButtonUp() {
        RelativeLayout.LayoutParams layoutParams =
            (RelativeLayout.LayoutParams) findViewById(R.id.nickEditText).getLayoutParams();
        layoutParams.setMargins(40, 50, 40, 8);
        layoutParams.removeRule(RelativeLayout.CENTER_IN_PARENT);
        layoutParams.addRule(RelativeLayout.ALIGN_PARENT_TOP);
      }
    });
  }

  @OnClick(R.id.joinButton)
  void startChatActivity() {
    Intent startChatActivity = new Intent(this, ChatActivity.class);
    startChatActivity.putExtra(Constants.EXTRA_NICK, nickEditText.getText().toString());
    startActivity(startChatActivity);
    finish();
  }
}
