package com.example.admin.reportcardapplication;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

/**
 * Created by Admin on 2016/10/26.
 */
public class Registration extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration);
    }

    @Override
    public void onBackPressed() {
        final AlertDialog.Builder progressDialog = new AlertDialog.Builder(Registration.this);
        progressDialog.setMessage("Are you sure you want to exit?").setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert = progressDialog.create();
        alert.setTitle("You are about to exit!");
        alert.show();

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater showMenu = getMenuInflater();
        showMenu.inflate(R.menu.action_bar_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent;
        switch (item.getItemId())
        {
            case R.id.action_settings:

                break;
            case R.id.action_Exit:
                onBackPressed();
                break;
            case R.id.action_preference:
                intent = new Intent(getApplicationContext(),Preferences.class);
                startActivity(intent);
                break;
        }
        return false;
    }
}
