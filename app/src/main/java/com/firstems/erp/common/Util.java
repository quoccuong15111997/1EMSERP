package com.firstems.erp.common;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.os.Build;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;

import androidx.annotation.NonNull;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.Normalizer;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Pattern;

public class Util {
    public static boolean isBlank(String input) {
        if (input == null || input.equalsIgnoreCase(""))
            return true;
        return false;
    }
    
    public static Bitmap getBitmapFromURL(String src) {
        try {
            URL url = new URL("https://www.grammy.com/sites/com/files/styles/image_landscape_hero/public/muzooka/Imagine%2BDragons/Imagine%2520Dragons_16_9_1578384717.jpg?itok=wk_i5H-W");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap myBitmap = BitmapFactory.decodeStream(input);
            return myBitmap;
        } catch (IOException e) {
            // Log exception
            return null;
        }
    }
    public static Boolean isBitmapValid(ImageView imageView, Bitmap bitmap, Context context) {
        Bitmap emptyBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), bitmap.getConfig());
        return !(imageView == null
                || context == null
                || bitmap.sameAs(emptyBitmap));
    }
    
    public static String getSystemLanguageCode() {
        String language = Locale.getDefault().getLanguage();
        return String.valueOf(language.toCharArray()[0]).toUpperCase();
    }
    
    public static String convertToCurrencyVN(double currency) {
        Locale localeVN = new Locale("vi", "VN");
        NumberFormat currencyVN = NumberFormat.getCurrencyInstance(localeVN);
        String str1 = currencyVN.format(Double.valueOf(currency));
        return str1;
    }
    
    public static boolean checkInterNet(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        
        return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected();
    }
    public static void setLocale(String lang, Context context) {
        Locale myLocale = new Locale(lang);
        Resources res = context.getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();
        conf.locale = myLocale;
        res.updateConfiguration(conf, dm);
    }
    public static void hideKeyBoard(Activity activity){
        try {
            if (activity.getCurrentFocus()!=null){
                if (activity.getCurrentFocus().getWindowToken()!=null){
                    InputMethodManager inputManager = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
                    inputManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                }
            }
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
    }
    public static String convertImageToString(Bitmap bm){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.PNG, 100, baos);
        byte[] b = baos.toByteArray();
        String encoded = Base64.encodeToString(b, Base64.DEFAULT);
        return encoded;
    }
    public static void setStatusBarColor(Activity activity,String color) {
        
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = activity.getWindow();
            
            int statusBarColor = Color.parseColor("#003556");
            
            if (statusBarColor == Color.BLACK && window.getNavigationBarColor() == Color.BLACK) {
                window.clearFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            } else {
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            }
            window.setStatusBarColor(statusBarColor);
        }
    }
    public static String formatDate(String date){
        String arr[] = date.split("T");
        if (arr.length>0){
            String dateCurrent = arr[0];
            String arrDateCurrent[] = dateCurrent.split("-");
            if (arrDateCurrent.length>2){
                String dateFormated = arrDateCurrent[2]+"-"+arrDateCurrent[1]+"-"+arrDateCurrent[0];
                return dateFormated;
            }
            return date;
        }
        else
            return date;
    }
    public static String formatDateSystem(String date){
        String arr[] = date.split("T");
        if (arr.length>0){
            String dateCurrent = arr[0];
            return dateCurrent;
        }
        else
            return date;
    }
    public static void hideKeyBoardInView( Activity activity) {
        View view = activity.getCurrentFocus();
        if (view != null) {
            InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
    
    public static Date convertStringToDate(@NonNull String s,@NonNull String pattern){
        Date date = new Date();
        try {
            date =new SimpleDateFormat(pattern).parse(s);
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        return date;
    }
    
    public static String convertTime(String fullDateTime) {
        String result = "";
        
        try {
            String timeMod[] = fullDateTime.split("T");
            String dateMod[] = timeMod[0].split("-");
            String date = dateMod[2] + "-" + dateMod[1] + "-" + dateMod[0];
            result = timeMod[1].split("\\.")[0] + " " + date;
        } catch (Exception ex) {
            result = fullDateTime;
        }
        return result;
    }
    public static String removeAccent(String s) {
        String temp = Normalizer.normalize(s, Normalizer.Form.NFD); Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+"); temp = pattern.matcher(temp).replaceAll("");
        String arr[] = temp.split("\\.");
        if (arr.length > 1){
            String extention = temp.split("\\.")[arr.length - 1];
            return (temp.replaceAll("đ", "d").replaceAll(" ","").replaceAll("\\.","").replaceAll(extention,"")+"."+extention).trim();
        }
        else {
            return temp.replaceAll("đ", "d").replaceAll(" ","").trim();
        }
    }
        
}
