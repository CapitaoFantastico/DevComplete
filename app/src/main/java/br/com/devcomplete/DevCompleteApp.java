package br.com.devcomplete;

import android.app.Application;
import android.os.SystemClock;
import java.util.concurrent.TimeUnit;

/**
 * Created by ricks on 12/07/2016.
 */
public class DevCompleteApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        SystemClock.sleep(TimeUnit.SECONDS.toMillis(3)); //Esperar 3 segundos

    }
}
