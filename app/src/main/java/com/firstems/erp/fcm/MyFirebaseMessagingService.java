package com.firstems.erp.fcm;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;

import com.firstems.erp.MainActivity;
import com.firstems.erp.R;
import com.firstems.erp.helper.Supports;
import com.firstems.erp.loading.LoadingActivity;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Map;

import static android.content.ContentValues.TAG;


public class MyFirebaseMessagingService extends FirebaseMessagingService {
    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
      /*  super.onMessageReceived(remoteMessage);
        Map<String, String> stringStringMap = remoteMessage.getData();
        System.out.println(stringStringMap.size());
        sendNotification(remoteMessage.getNotification().getBody());*/
        super.onMessageReceived(remoteMessage);
        if(remoteMessage.getData().size() > 0) {
            Map<String, String> data = remoteMessage.getData();
            Log.d(TAG, "NOTIFICATION-DATA" + data);
            String messageBody = data.get("message_body");
            String messageTitle = data.get("message_title");
            int badge = Integer.parseInt(data.get("waiting_approve_count"));
            sendNotifications(messageTitle,messageBody, badge);
        }
        else if (remoteMessage.getNotification() != null) {
            // Check if message contains a notification payload.
            RemoteMessage.Notification notification =  remoteMessage.getNotification();
            String messageBody = notification.getBody();
            String messageTitle = notification.getTitle();
            sendNotifications(messageTitle, messageBody, 1);
        }
    }
    private void sendNotification(String messageBody) {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT);

        String channelId = getString(R.string.project_id);
        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        NotificationCompat.Builder notificationBuilder =
                new NotificationCompat.Builder(this, channelId)
                        .setSmallIcon(R.drawable.ic_noti)
                        .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.ic_noti))
                        .setContentTitle(getString(R.string.project_id))
                        .setContentText(messageBody)
                        .setAutoCancel(true)
                        .setSound(defaultSoundUri)
                        .setContentIntent(pendingIntent)
                        .setDefaults(Notification.DEFAULT_ALL)
                        .setPriority(NotificationManager.IMPORTANCE_HIGH)
                        .addAction(new NotificationCompat.Action(
                                android.R.drawable.sym_call_missed,
                                "Cancel",
                                PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT)))
                        .addAction(new NotificationCompat.Action(
                                android.R.drawable.sym_call_outgoing,
                                "OK",
                                PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT)));

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        // Since android Oreo notification channel is needed.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(
                    channelId,
                    "Channel human readable title",
                    NotificationManager.IMPORTANCE_DEFAULT);

            notificationManager.createNotificationChannel(channel);
        }

        notificationManager.notify(0, notificationBuilder.build());
    }
    private void sendNotifications(String messageTitle, String messageBody, int badge) {
        Intent intent;
        intent = new Intent(this, LoadingActivity.class);
        
        Log.d(TAG, "NOTIFICATION-BODY" + messageBody);
        
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent,
                PendingIntent.FLAG_ONE_SHOT);
        
        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                .setLargeIcon(BitmapFactory.decodeResource(this.getResources(), R.drawable.ic_launcher))
                .setSmallIcon(R.drawable.ic_launcher)
                .setContentTitle(messageTitle)
                .setContentText(messageBody)
                .setAutoCancel(true)
                .setSound(defaultSoundUri)
                .setContentIntent(pendingIntent);
        
        Supports.setBadge(getApplicationContext(), badge);
        
        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0, notificationBuilder.build());
    }
}
