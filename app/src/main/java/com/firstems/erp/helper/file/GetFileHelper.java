package com.firstems.erp.helper.file;

import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;

import com.firstems.erp.callback.SaveFileToLocalCallback;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Nguyen Quoc Cuong on 8/18/2020.
 */
public class GetFileHelper {
    private static GetFileHelper instance;
    private GetFileHelper(){

    }
    public static GetFileHelper getInstance(){
        if (instance==null){
            instance= new GetFileHelper();
        }
        return instance;
    }
    public void doGetFile(String url,String token,String fileName,String fileType,Context context, GetPdfFileCallback callback){
        GetFilePDFFromAPITask getFilePDFFromAPITask = new GetFilePDFFromAPITask(url,token,fileName,fileType,context,callback);
        getFilePDFFromAPITask.execute();
    }
    public void doGetImageFile(String url, String token, GetImageFileCallback callback){
        GetImageFileFromAPITask getImageFileFromAPITask = new GetImageFileFromAPITask(url,token,callback);
        getImageFileFromAPITask.execute();
    }

    public interface GetPdfFileCallback{
        void onGetFile(File file);
    }
    public interface GetImageFileCallback{
        void onGetFile(Bitmap bitmap);
    }
    
    public void saveImageToLocalApp(Context context, Bitmap bitmap, SaveFileToLocalCallback saveFileToLocalCallback){
        ContextWrapper cw = new ContextWrapper(context);
        File directory = cw.getDir("profile", Context.MODE_PRIVATE);
        if (!directory.exists()) {
            directory.mkdir();
        }
        String fileName = System.currentTimeMillis()+".png";
        File mypath = new File(directory, fileName);
        Bitmap resize = Bitmap.createScaledBitmap(bitmap,(int)(bitmap.getWidth()*0.8), (int)(bitmap.getHeight()*0.8), true);
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(mypath);
            resize.compress(Bitmap.CompressFormat.JPEG, 50, fos);
            fos.close();
            saveFileToLocalCallback.onSaveSuccess(mypath.getAbsolutePath());
            System.out.println(mypath.getAbsoluteFile());
        } catch (Exception e) {
            Log.e("SAVE_IMAGE", e.getMessage(), e);
            saveFileToLocalCallback.onSaveFail(e.getMessage());
        }
    }
    
    class GetFilePDFFromAPITask extends AsyncTask<Void,Void,Object> {
        private String link = "";
        private String token;
        private String fileName;
        private String fileType;
        private Context context;
        private GetPdfFileCallback getPdfFileCallback;
    
    
        public GetFilePDFFromAPITask(String link, String token, String fileName, String fileType, Context context, GetPdfFileCallback getPdfFileCallback) {
            this.link = link;
            this.token = token;
            this.fileName = fileName;
            this.fileType = fileType;
            this.context = context;
            this.getPdfFileCallback = getPdfFileCallback;
        }
    
        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);
            File file = (File) o;
            System.out.println(file.getAbsolutePath());
            getPdfFileCallback.onGetFile((File) o);
        }

        @Override
        protected Object doInBackground(Void... voids) {
            try {

                URL url = new URL(link);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
                connection.setRequestProperty("TOKEN", token);

                InputStream inputStream = connection.getInputStream();
    
                File directory = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
                        + "/"+ "ERP");
                if (!directory.exists()) {
                    directory.mkdir();
                }
                String imageFileName = "FILE" + fileName + "." + fileType;
                boolean success = true;
                if (success) {
                    File file = new File(directory, imageFileName);
                    copyInputStreamToFile(inputStream, file);
                    return file;
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            return null;
        }
        
        private void copyInputStreamToFile(InputStream in, File file) {
            OutputStream out = null;

            try {
                out = new FileOutputStream(file);
                byte[] buf = new byte[1024];
                int len;
                while((len=in.read(buf))>0){
                    out.write(buf,0,len);
                }
            }
            catch (Exception e) {
                e.printStackTrace();
            }
            finally {
                // Ensure that the InputStreams are closed even if there's an exception.
                try {
                    if ( out != null ) {
                        out.close();
                    }

                    // If you want to close the "in" InputStream yourself then remove this
                    // from here but ensure that you close it yourself eventually.
                    in.close();
                }
                catch ( IOException e ) {
                    e.printStackTrace();
                }
            }
        }
    }

        class GetImageFileFromAPITask extends AsyncTask<Void,Void,Object>{
            private String link ="";
            private String token;
            private GetImageFileCallback getImageFileCallback;

            public GetImageFileFromAPITask(String link, String token, GetImageFileCallback getImageFileCallback) {
                this.link = link;
                this.token = token;
                this.getImageFileCallback = getImageFileCallback;
            }

            @Override
            protected void onPostExecute(Object o) {
                super.onPostExecute(o);
                getImageFileCallback.onGetFile((Bitmap) o);
            }

            @Override
            protected Object doInBackground(Void... voids) {
                try {

                    URL url= new URL(link);
                    HttpURLConnection connection= (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setRequestProperty("Content-Type","application/json;charset=UTF-8");
                    connection.setRequestProperty("TOKEN",token);

                    InputStream inputStream = connection.getInputStream();
                    Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                    return bitmap;
                }
                catch (Exception ex){
                    ex.printStackTrace();
                }
                return null;
            }


    }
}
