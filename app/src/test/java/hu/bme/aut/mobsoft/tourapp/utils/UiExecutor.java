package hu.bme.aut.mobsoft.tourapp.utils;

import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;

import java.util.concurrent.Executor;

/**
 * Created by valentin on 2017. 05. 15..
 */

public class UiExecutor implements Executor {

    private Handler mHandler;

    public UiExecutor() {
        mHandler = new Handler(Looper.getMainLooper());
    }

    @Override
    public void execute(@NonNull Runnable command) {
        mHandler.post(command);
    }
}
