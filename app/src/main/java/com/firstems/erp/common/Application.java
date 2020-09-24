package com.firstems.erp.common;

import com.facebook.stetho.Stetho;
import com.firstems.erp.api.services.ApiServices;
import com.firstems.erp.database.helper.DatabaseHelper;
import com.firstems.erp.helper.load.Loading;
import com.firstems.erp.sharedpreferences.SharedPreferencesManager;

/**
 * Created by Nguyen Quoc Cuong on 7/17/2020.
 */
public class Application extends android.app.Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Stetho.initializeWithDefaults(this);
        ApiServices.init("http://api-dev.firstems.com/");
        SharedPreferencesManager.init(this);
        DatabaseHelper.init(this);
        Loading.getInstance().init(this);
    }
}
