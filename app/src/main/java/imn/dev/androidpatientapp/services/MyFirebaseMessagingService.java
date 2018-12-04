package imn.dev.androidpatientapp.services;


import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import java.util.Map;
import androidx.core.app.NotificationCompat;
import imn.dev.androidpatientapp.LabResultActivity;

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    public MyFirebaseMessagingService() {
    }

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        String result = "From : " + remoteMessage.getFrom() + "\nMessageId = " + remoteMessage.getMessageId() + "\nMessageType =  " + remoteMessage.getMessageType()
                + "\nCollapeseKey = " + remoteMessage.getCollapseKey() + "\nTo: " + remoteMessage.getTo() + "\nTtl = " + remoteMessage.getTtl()
                + "\nSent Time = " + remoteMessage.getSentTime();/*+"\nTitle = " + remoteMessage.getNotification().getTitle()

                + "\nBody = " + remoteMessage.getNotification().getTextContent()*/
        Map<String, String> map = remoteMessage.getData();
        for (String key : map.keySet())
            result += "\n(" + key + "," + map.get(key) + ")";

        Intent intent = new Intent(this, LabResultActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("result", result);
        PendingIntent pi = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
                .setAutoCancel(true)
                .setContentTitle("Thank You for your patience. Your Laboratory Test Results are now out and uploaded. Click to view")
                .setSmallIcon(android.R.drawable.stat_notify_chat)
                .setContentIntent(pi);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            String id = "channel_id";
            String channelName = "notificationName";
            String channelDescription = "notificationDescription";
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel notificationChannel = new NotificationChannel(id, channelName, importance);

            notificationChannel.setDescription(channelDescription);
            notificationChannel.enableLights(true);

            notificationChannel.setLightColor(Color.RED);
            notificationChannel.enableVibration(true);
            notificationChannel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});

            notificationManager.createNotificationChannel(notificationChannel);
            builder.setChannelId(id);

        }
        notificationManager.notify(1111, builder.build());

    }

    @Override
    public void onDeletedMessages() {
        super.onDeletedMessages();
    }

    @Override
    public void onMessageSent(String s) {
        super.onMessageSent(s);
    }

    @Override
    public void onSendError(String s, Exception e) {
        super.onSendError(s, e);
    }
    @Override
    public void onNewToken(String s) {
        super.onNewToken(s);

        Log.d("TOKENFIREBASE", s);
    }
}