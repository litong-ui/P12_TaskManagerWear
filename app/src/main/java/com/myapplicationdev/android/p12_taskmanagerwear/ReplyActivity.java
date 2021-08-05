package com.myapplicationdev.android.p12_taskmanagerwear;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.RemoteInput;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class ReplyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reply);

        CharSequence reply = null;
        Intent intent = getIntent();

        long id = intent.getLongExtra("id", 0);
        Log.d("id:", id+"");

        Bundle remoteInput = RemoteInput.getResultsFromIntent(intent);
        if (remoteInput != null){
            reply = remoteInput.getCharSequence("status");
        }

        if(reply != null && reply.toString().equalsIgnoreCase("Completed")){
            Toast.makeText(ReplyActivity.this, "You have indicated: " + reply,
                    Toast.LENGTH_SHORT).show();

            DBHelper dbh = new DBHelper(ReplyActivity.this);
            dbh.deleteTask((int) id);
            dbh.close();
            finish();
        }

    }
}