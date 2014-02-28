package de.lukeslog.askme;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

/**
 * The AskMe Service is connected to Dynamix which provides it with a 
 * location according to the abstractioin whishes of the user as well 
 * as other contextual information like profile or medical data. 
 * 
 * which data is used by ASkMe depends on the activation of services
 * in the settings of AskMe.
 * 
 * Sporadically checking the stream of data from the questionnaireprovider
 * ASkAme checks for questionnires that fit the description.
 * 
 * Since the checking is done on the mobile-site the provider of the list 
 * does not have acces to the personald ata (However QueXMLx Questionnaire 
 * providers may make sensor data a prerequesite)
 * 
 * @author lukas
 *
 */
public class AskMeService extends Service
{

    public class LocalBinder extends Binder 
    {
    	AskMeService getService() 
    	{
            // Return this instance of LocalService so clients can call 
    		// public methods
            return AskMeService.this;
        }
    }
    
    private final IBinder mBinder = new LocalBinder();
    
    
	@Override
	public IBinder onBind(Intent intent) 
	{
		return mBinder;
	}
	
	@Override
    public int onStartCommand(Intent intent, int flags, int startId) 
    {
		return startId;	
    }
	
	@Override
	public void onCreate() 
	{
		
	}

}
