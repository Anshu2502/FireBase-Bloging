package rt.tea.com.fireblog;

import android.app.Application;

import com.firebase.client.Firebase;

/**
 * Created by acer on 12/17/2016.
 */

public class Fr extends Application {

 public void onCreate(){
        super.onCreate();

     Firebase.setAndroidContext(this);


    }
}
