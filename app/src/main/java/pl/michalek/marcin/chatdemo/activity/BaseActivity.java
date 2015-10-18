package pl.michalek.marcin.chatdemo.activity;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.widget.Toast;

/**
 * Base activity
 *
 * @author Marcin Michałek
 */
public class BaseActivity extends Activity {

  protected void displayToast(String message) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
  }

  protected void setActionBarTitle(String title) {
    ActionBar actionBar = getActionBar();
    if (actionBar != null) {
      actionBar.setDisplayShowTitleEnabled(true);
      actionBar.setTitle(title);
    }
  }

  protected void displayInformationDialog(String title, String message, String positiveButton, DialogInterface.OnClickListener positiveButtonOnClick) {
    new AlertDialog.Builder(this)
        .setTitle(title)
        .setMessage(message)
        .setPositiveButton(positiveButton, positiveButtonOnClick)
        .create().show();
  }
}
