package com.hassan.datareceiver;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //http://code.tutsplus.com/tutorials/android-sdk-receiving-data-from-the-send-intent--mobile-14878

        //get the image view
        ImageView picView = (ImageView)findViewById(R.id.picture);
        //get the text view
        TextView txtView = (TextView)findViewById(R.id.txt);
        //get the received intent
        Intent receivedIntent = getIntent();
        //get the action
        String receivedAction = receivedIntent.getAction();

        //find out what we are dealing with
        String receivedType = receivedIntent.getType();

        //make sure it's an action and type we can handle
        if(receivedAction.equals(Intent.ACTION_SEND)){
            //content is being shared
            if(receivedType.startsWith("text/")){
                //handle sent text
                picView.setVisibility(View.GONE);
                //hide the other ui item
                picView.setVisibility(View.GONE);
                //get the received text
                String receivedText = receivedIntent.getStringExtra(Intent.EXTRA_TEXT);
                //check we have a string
                if (receivedText != null) {
                    //set the text
                    txtView.setText(receivedText);
                }
            }
            else if(receivedType.startsWith("image/")){
                //handle sent image
                //hide the other ui item
                txtView.setVisibility(View.GONE);
                //get the uri of the received image
                Uri receivedUri = receivedIntent.getParcelableExtra(Intent.EXTRA_STREAM);
                //check we have a uri
                if (receivedUri != null) {
                    //set the picture
                    //RESAMPLE YOUR IMAGE DATA BEFORE DISPLAYING
                    picView.setImageURI(receivedUri);//just for demonstration
                }
            }
        }
        else if(receivedAction.equals(Intent.ACTION_MAIN)){
            //app has been launched directly, not from share list
            txtView.setText("Nothing has been shared!");
        }
    }
}
