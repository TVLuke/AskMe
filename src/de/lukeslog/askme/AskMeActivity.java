/*
 * Copyright (C) Lukas Ruge
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package de.lukeslog.askme;

import de.lukeslog.questionnaire.main.QuestionnaireActivity;
import de.lukeslog.askme.R;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class AskMeActivity extends Activity 
{
	final static int RESULTCODE = 0;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        final Context c = this;
        Button button1 = (Button) findViewById(R.id.button1);
        final String url_String="http://www.lukeslog.de/stuff/test_questionnaire.xml"; //ADD YOUR URL HERE
        final String type_String = "QueXML";
        final String termsAndConditions_String = "http://www.randomurl.com"; //ADD A WORKING URL TO THE TERMS OF YOUR QUESTIONNAIRE HERE
        //notication("There is an new Questionnaire for you", true, true);
        //alert
        button1.setOnClickListener(new View.OnClickListener() 
        {
			
			@Override
			public void onClick(View v) 
			{
		        Intent i = new Intent(c, QuestionnaireActivity.class);
		        i.putExtra("ressourceURI", url_String);
		        i.putExtra("QuestionnaireType", type_String );
		        i.putExtra("termsAndCondition", termsAndConditions_String);
		        startActivityForResult(i, RESULTCODE);
			}
		});
    }
    
    @Override 
    public void onActivityResult(int requestCode, int resultCode, Intent data) 
    {     
      super.onActivityResult(requestCode, resultCode, data); 
      switch(requestCode) 
      { 
        case (RESULTCODE) : 
        { 
          if (resultCode == Activity.RESULT_OK) 
          { 
        	  toastNotification(data.getStringExtra("resultSet"));
          } 
          break; 
        } 
      } 
    }
    
    /**
     * Creating a toast notification
     * @param text
     */
    private void toastNotification(String text)
    {
    	Context context = getApplicationContext();
    	int duration = Toast.LENGTH_SHORT;

    	Toast toast = Toast.makeText(context, text, duration);
    	toast.show();
    }
    
	private void notication(String text, boolean vibration, boolean sound)
	{
		String ns = Context.NOTIFICATION_SERVICE;
        NotificationManager mNotificationManager = (NotificationManager) getSystemService(ns);
        
    	int icon = R.drawable.qicon;        // icon from resources
    	CharSequence tickerText = text;              // ticker-text
    	long when = System.currentTimeMillis();         // notification time
    	Context context = getApplicationContext();      // application Context
    	CharSequence contentTitle = "Umfrage!";  // message title
    	CharSequence contentText = text;      // message text

    	//this needs to be changed to an activit that explains why walking is impotant...
    	Intent notificationIntent = new Intent(getApplicationContext(), AskMeActivity.class);
    	PendingIntent contentIntent = PendingIntent.getActivity(getApplicationContext(), 0, notificationIntent, 0);

    	// the next two lines initialize the Notification, using the configurations above
    	Notification notification = new Notification(icon, tickerText, when);
    	if(vibration)
    	{
	    	notification.defaults |= Notification.DEFAULT_VIBRATE;
	    	long[] v = {0,100,200,300};
	    	notification.vibrate = v;
    	}
    	if(sound)
    	{
    		notification.defaults |= Notification.DEFAULT_SOUND;
    	}
    	notification.flags |= Notification.FLAG_AUTO_CANCEL;
    	notification.setLatestEventInfo(context, contentTitle, contentText, contentIntent);
    	final int HELLO_ID = 1;

    	mNotificationManager.notify(HELLO_ID, notification);
	}
	
	private void alert()
	{
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(AskMeActivity.this);
        alertDialog.setTitle("Neue Umfrage");
        alertDialog.setMessage("Es steht eine Neue Umfrage für Sie bereit.");
        alertDialog.setPositiveButton("Teilnehmen", new DialogInterface.OnClickListener() 
        {
            public void onClick(DialogInterface dialog,int which) 
            {
 
            	// Write your code here to invoke YES event
            	Toast.makeText(getApplicationContext(), "You clicked on YES", Toast.LENGTH_SHORT).show();
            }
        });
 
        // Setting Negative "NO" Button
        alertDialog.setNegativeButton("Nein Danke", new DialogInterface.OnClickListener() 
        {
            public void onClick(DialogInterface dialog, int which) 
            {
	            // Write your code here to invoke NO event
	            Toast.makeText(getApplicationContext(), "You clicked on NO", Toast.LENGTH_SHORT).show();
	            dialog.cancel();
            }
        });
        alertDialog.setIcon(R.drawable.qicon);
        alertDialog.show();
	}

}