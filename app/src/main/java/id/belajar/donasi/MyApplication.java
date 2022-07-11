package id.belajar.donasi;

import android.app.Application;
import android.provider.SyncStateContract;

import com.google.gson.Gson;

import id.belajar.donasi.entity.User;
import id.belajar.donasi.utils.Shared;

public class MyApplication extends Application {
    static private MyApplication _instance;
    public static MyApplication getInstance() {return _instance;}
    public User userSession;
    @Override
    public void onCreate() {
        super.onCreate();
        _instance = this;
    }

    public void setUserSession(User userSession){
        this.userSession = userSession;
        Shared.setValue("user_session",new Gson().toJson(userSession));

    }

    public User getUserSession(){
        if (this.userSession == null)
            setUserSession(new Gson().fromJson(Shared.getValue("user_session"),User.class));

        return this.userSession;
    }
}
