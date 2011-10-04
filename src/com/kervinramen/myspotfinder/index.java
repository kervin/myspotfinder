package com.kervinramen.myspotfinder;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class index extends Activity {

    EditText txtUsername;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.index);

        txtUsername = (EditText) findViewById(R.id.userName);

        Button enterButton = (Button) findViewById(R.id.enter);

        enterButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                showListActivity();

            }
        });
    }

    private void showListActivity() {
        String usernameText = txtUsername.getText().toString();
        Intent i = new Intent(this, placeslist.class);

        i.putExtra("username", usernameText);

        startActivity(i);
    }

}
