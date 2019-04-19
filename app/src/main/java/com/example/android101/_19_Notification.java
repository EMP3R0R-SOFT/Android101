package com.example.android101;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.SystemClock;
import android.provider.Settings;
import android.support.annotation.RequiresApi;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v4.media.session.MediaSessionCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RemoteViews;
import android.widget.Toast;

import com.example.android101.nonui._19_2_NotificationReciver_forBTN;

import static com.example.android101._0_AppController.AppController.CHANNEL_1_ID;
import static com.example.android101._0_AppController.AppController.CHANNEL_2_ID;


/**
 * part 1:  in AppController.class
 */

public class _19_Notification extends AppCompatActivity {

    private NotificationManagerCompat notificationManager;
    private EditText editTextTitle;
    private EditText editTextMessage;
    private MediaSessionCompat mediaSession;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout._19__notification);


        notificationManager = NotificationManagerCompat.from(this);
        editTextTitle = findViewById(R.id.edit_text_title);
        editTextMessage = findViewById(R.id.edit_text_message);

        mediaSession = new MediaSessionCompat(this, "tag");
        context = getApplicationContext();
    }


    public void Show_Simple_notify_1(View v) {


        String title = editTextTitle.getText().toString();
        String message = editTextMessage.getText().toString();

        Notification notification = new NotificationCompat.Builder(this, CHANNEL_1_ID)
                .setSmallIcon(R.mipmap.ic_launcher_fix)
                .setContentTitle(title)
                .setContentText(message)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .build();

        notificationManager.notify(1, notification);

    }


    public void Show_Simple_notify_2(View v) {


        String title = editTextTitle.getText().toString();
        String message = editTextMessage.getText().toString();

        Notification notification = new NotificationCompat.Builder(this, CHANNEL_2_ID)
                .setSmallIcon(R.mipmap.ic_launcher_fix)
                .setContentTitle(title)
                .setContentText(message)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .build();

        notificationManager.notify(2, notification);

    }


    public void Show_notify_Clickable(View v) {


        String title = editTextTitle.getText().toString();
        String message = editTextMessage.getText().toString();


        Intent NotifyIntentClick = new Intent(this, _1_SharedPreferences_example.class);

        PendingIntent contentIntent = PendingIntent.getActivity(this, 0, NotifyIntentClick, PendingIntent.FLAG_UPDATE_CURRENT);

        //ba estefade az TaskStackBuilder activity va parentesho baz mikonim ta ba back safhe nare to home !?
//        TaskStackBuilder st = TaskStackBuilder.create(this);
//        st.addNextIntentWithParentStack(NotifyIntentClick);
//        PendingIntent contentIntent = st.getPendingIntent(0,PendingIntent.FLAG_UPDATE_CURRENT);


        Notification notification = new NotificationCompat.Builder(this, CHANNEL_1_ID)

                .setSmallIcon(R.mipmap.ic_launcher_fix)
                .setContentTitle(title)
                .setContentText(message)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)

                .setContentIntent(contentIntent)
                .setAutoCancel(true)
                .setOnlyAlertOnce(true)
                .build();


        notificationManager.notify(1, notification);

    }


    public void Show_notify_with_btn(View v) {

        /**
         * part 1: nonui/_19_2_NotificationReciver_forBTN.class
         *
         * part 2: HERE
         */


        String title = editTextTitle.getText().toString();
        String message = editTextMessage.getText().toString();


        Intent broadcastIntent = new Intent(this, _19_2_NotificationReciver_forBTN.class);
        broadcastIntent.putExtra("toastMessage", message);
        PendingIntent actionIntent = PendingIntent.getBroadcast(this,
                0, broadcastIntent, PendingIntent.FLAG_UPDATE_CURRENT);


        Notification notification = new NotificationCompat.Builder(this, CHANNEL_1_ID)

                .setSmallIcon(R.mipmap.ic_launcher_fix)
                .setContentTitle(title)
                .setContentText(message)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .addAction(R.mipmap.ic_launcher_fix, "Toast", actionIntent)
                .build();


        notificationManager.notify(1, notification);


        /**
         * part 3: AndroidManifests.xml
         */
    }


    public void Show_notify_BigText(View v) {


        String title = editTextTitle.getText().toString();
        String message = editTextMessage.getText().toString();
        Bitmap largeIcon = BitmapFactory.decodeResource(getResources(), R.drawable.logo_1);

        Notification notification = new NotificationCompat.Builder(this, CHANNEL_1_ID)

                .setSmallIcon(R.mipmap.ic_launcher_fix)
                .setContentTitle(title)
                .setContentText(message)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)

                .setLargeIcon(largeIcon)
                .setStyle(new NotificationCompat.BigTextStyle()
                        .bigText(getString(R.string.test_text_big))
                        .setBigContentTitle("Big Content Title")
                        .setSummaryText("Summary Text"))

                .build();


        notificationManager.notify(1, notification);


    }


    public void Show_notify_Multiline(View v) {


        String title = editTextTitle.getText().toString();
        String message = editTextMessage.getText().toString();

        Notification notification = new NotificationCompat.Builder(this, CHANNEL_1_ID)

                .setSmallIcon(R.mipmap.ic_launcher_fix)
                .setContentTitle(title)
                .setContentText(message)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)

                .setStyle(new NotificationCompat.InboxStyle()
                        .addLine("line 1")
                        .addLine("line 2")
                        .addLine("line 3")
                        .addLine("line 4")
                        .addLine("line 5")
                        .addLine("line 6")
                        .addLine("line 7")
                        .setBigContentTitle("Big Content Title")
                        .setSummaryText("Summary Text"))
                .build();


        notificationManager.notify(1, notification);


    }


    public void Show_notify_BigPicture(View v) {

        Bitmap picture = BitmapFactory.decodeResource(getResources(), R.drawable.lichking);
        String title = editTextTitle.getText().toString();
        String message = editTextMessage.getText().toString();

        Intent broadcastIntent = new Intent(this, _19_2_NotificationReciver_forBTN.class);
        broadcastIntent.putExtra("toastMessage", message);
        PendingIntent actionIntent = PendingIntent.getBroadcast(this,
                0, broadcastIntent, PendingIntent.FLAG_UPDATE_CURRENT);


        Notification notification = new NotificationCompat.Builder(this, CHANNEL_1_ID)

                .setSmallIcon(R.mipmap.ic_launcher_fix)
                .setContentTitle(title)
                .setContentText(message)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)


                .setLargeIcon(picture)
                .addAction(android.R.drawable.ic_popup_sync, "Dislike", actionIntent) //0
                .addAction(android.R.drawable.ic_dialog_email, "Previous", actionIntent) //1
                .addAction(android.R.drawable.ic_lock_lock, "Like", actionIntent) // 4
                .setStyle(new android.support.v4.media.app.NotificationCompat.MediaStyle()
                        .setShowActionsInCompactView(0, 1, 2)
                        .setMediaSession(mediaSession.getSessionToken()))

                .build();


        notificationManager.notify(1, notification);

    }


    public void Show_notify_Progress(View v) {

        final int progressMax = 100;

        final NotificationCompat.Builder notification = new NotificationCompat.Builder(this, CHANNEL_1_ID)
                .setSmallIcon(R.mipmap.ic_launcher_fix)
                .setContentTitle("Download")
                .setContentText("Download in progress")
                .setPriority(NotificationCompat.PRIORITY_LOW)
                .setOngoing(true)
                .setOnlyAlertOnce(true)
                .setProgress(progressMax, 0, false);

        notificationManager.notify(1, notification.build());

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                SystemClock.sleep(1000);
                for (int progress = 0; progress <= progressMax; progress += 5) {

                    notification.setProgress(progressMax, progress, false);
                    notificationManager.notify(1, notification.build());

                    SystemClock.sleep(200);
                }
                notification.setContentText("Download finished")
                        .setProgress(0, 0, false)
                        .setOngoing(false);
                notificationManager.notify(1, notification.build());
                Toast.makeText(getApplicationContext(), "Download finished", Toast.LENGTH_SHORT).show();
            }
        });

    }


    public void Show_notify_Group(View v) {


        String title = editTextTitle.getText().toString();
        String message = editTextMessage.getText().toString();


        Notification notification = new NotificationCompat.Builder(this, CHANNEL_1_ID)

                .setSmallIcon(R.mipmap.ic_launcher_fix)
                .setContentTitle(title)
                .setContentText(message)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .build();


        for (int i = 0; i < 6; i++) {
            SystemClock.sleep(2000);
            notificationManager.notify(i, notification);

        }

    }


    public void openNotificationSettings(View v) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            Intent intent = new Intent(Settings.ACTION_APP_NOTIFICATION_SETTINGS);
            intent.putExtra(Settings.EXTRA_APP_PACKAGE, getPackageName());
            startActivity(intent);
        } else {
            Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
            intent.setData(Uri.parse("package:" + getPackageName()));
            startActivity(intent);
        }
    }

    @RequiresApi(26)
    private boolean isChannelBlocked(String channelId) {
        NotificationManager manager = getSystemService(NotificationManager.class);
        NotificationChannel channel = manager.getNotificationChannel(channelId);

        return channel != null &&
                channel.getImportance() == NotificationManager.IMPORTANCE_NONE;
    }

    @RequiresApi(26)
    private void openChannelSettings(String channelId) {
        Intent intent = new Intent(Settings.ACTION_CHANNEL_NOTIFICATION_SETTINGS);
        intent.putExtra(Settings.EXTRA_APP_PACKAGE, getPackageName());
        intent.putExtra(Settings.EXTRA_CHANNEL_ID, channelId);
        startActivity(intent);
    }


    public void show_custom_notification(View v) {

        RemoteViews collapsedView = new RemoteViews(getPackageName(), R.layout._19_notification_custom_collapsed);
        RemoteViews expandedView = new RemoteViews(getPackageName(), R.layout._19_notification_custom_expanded);

        // vase click khor kardane ax
        Intent NotifyIntentClick = new Intent(this, _1_SharedPreferences_example.class);
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0, NotifyIntentClick, PendingIntent.FLAG_UPDATE_CURRENT);


        // vase click khore dokme -> toast
        Intent broadcastIntent = new Intent(this, _19_2_NotificationReciver_forBTN.class);
        broadcastIntent.putExtra("toastMessage", "BOOM!");
        PendingIntent actionIntent = PendingIntent.getBroadcast(this, 0, broadcastIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        //

        collapsedView.setTextViewText(R.id.text_view_collapsed_1, "Hello World! :)");

        expandedView.setImageViewResource(R.id.image_view_expanded, R.drawable.lichking);
        expandedView.setOnClickPendingIntent(R.id.image_view_expanded, contentIntent);
        expandedView.setOnClickPendingIntent(R.id._19_custom_notifi_btn, actionIntent);


        Notification notification = new NotificationCompat.Builder(this, CHANNEL_1_ID)
                .setSmallIcon(R.mipmap.ic_launcher_fix)
                .setCustomContentView(collapsedView)
                .setCustomBigContentView(expandedView)
                //.setStyle(new NotificationCompat.DecoratedCustomViewStyle())
                .build();

        notificationManager.notify(1, notification);


    }


    public void Show_notify_message(View v) {

        /**


         public class Message {
         private CharSequence text;
         private long timestamp;
         private CharSequence sender;

         public Message(CharSequence text, CharSequence sender) {
         this.text = text;
         this.sender = sender;
         timestamp = System.currentTimeMillis();
         }

         public CharSequence getText() {
         return text;
         }

         public long getTimestamp() {
         return timestamp;
         }

         public CharSequence getSender() {
         return sender;
         }

         }







         public class DirectReplyReceiver extends BroadcastReceiver {

        @Override public void onReceive(Context context, Intent intent) {
        Bundle remoteInput = RemoteInput.getResultsFromIntent(intent);

        if (remoteInput != null) {
        CharSequence replyText = remoteInput.getCharSequence("key_text_reply");
        Message answer = new Message(replyText, null);
        MainActivity.MESSAGES.add(answer);

        MainActivity.sendChannel1Notification(context);
        }
        }
        }






         static List<Message> MESSAGES = new ArrayList<>();

         @Override protected void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
         setContentView(R.layout.activity_main);

         notificationManager = NotificationManagerCompat.from(this);

         editTextTitle = findViewById(R.id.edit_text_title);
         editTextMessage = findViewById(R.id.edit_text_message);

         mediaSession = new MediaSessionCompat(this, "tag");

         MESSAGES.add(new Message("Good morning!", "Jim"));
         MESSAGES.add(new Message("Hello", null));
         MESSAGES.add(new Message("Hi!", "Jenny"));
         }

         public void sendOnChannel1(View v) {
         sendChannel1Notification(this);
         }

         public static void sendChannel1Notification(Context context) {
         Intent activityIntent = new Intent(context, MainActivity.class);
         PendingIntent contentIntent = PendingIntent.getActivity(context,
         0, activityIntent, 0);

         RemoteInput remoteInput = new RemoteInput.Builder("key_text_reply")
         .setLabel("Your answer...")
         .build();

         Intent replyIntent;
         PendingIntent replyPendingIntent = null;

         if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
         replyIntent = new Intent(context, DirectReplyReceiver.class);
         replyPendingIntent = PendingIntent.getBroadcast(context,
         0, replyIntent, 0);
         } else {
         //start chat activity instead (PendingIntent.getActivity)
         //cancel notification with notificationManagerCompat.cancel(id)
         }

         NotificationCompat.Action replyAction = new NotificationCompat.Action.Builder(
         R.drawable.ic_reply,
         "Reply",
         replyPendingIntent
         ).addRemoteInput(remoteInput).build();

         NotificationCompat.MessagingStyle messagingStyle =
         new NotificationCompat.MessagingStyle("Me");
         messagingStyle.setConversationTitle("Group Chat");

         for (Message chatMessage : MESSAGES) {
         NotificationCompat.MessagingStyle.Message notificationMessage =
         new NotificationCompat.MessagingStyle.Message(
         chatMessage.getText(),
         chatMessage.getTimestamp(),
         chatMessage.getSender()
         );
         messagingStyle.addMessage(notificationMessage);
         }

         Notification notification = new NotificationCompat.Builder(context, CHANNEL_1_ID)
         .setSmallIcon(R.drawable.ic_one)
         .setStyle(messagingStyle)
         .addAction(replyAction)
         .setColor(Color.BLUE)
         .setPriority(NotificationCompat.PRIORITY_HIGH)
         .setCategory(NotificationCompat.CATEGORY_MESSAGE)
         .setContentIntent(contentIntent)
         .setAutoCancel(true)
         .setOnlyAlertOnce(true)
         .build();

         NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
         notificationManager.notify(1, notification);
         }

         public void sendOnChannel2(View v) {
         String title = editTextTitle.getText().toString();
         String message = editTextMessage.getText().toString();

         Bitmap artwork = BitmapFactory.decodeResource(getResources(), R.drawable.lotti);

         Notification notification = new NotificationCompat.Builder(this, CHANNEL_2_ID)
         .setSmallIcon(R.drawable.ic_two)
         .setContentTitle(title)
         .setContentText(message)
         .setLargeIcon(artwork)
         .addAction(R.drawable.ic_dislike, "Dislike", null)
         .addAction(R.drawable.ic_previous, "Previous", null)
         .addAction(R.drawable.ic_pause, "Pause", null)
         .addAction(R.drawable.ic_next, "Next", null)
         .addAction(R.drawable.ic_like, "Like", null)
         .setStyle(new android.support.v4.media.app.NotificationCompat.MediaStyle()
         .setShowActionsInCompactView(1, 2, 3)
         .setMediaSession(mediaSession.getSessionToken()))
         .setSubText("Sub Text")
         .setPriority(NotificationCompat.PRIORITY_LOW)
         .build();

         notificationManager.notify(2, notification);
         }
         }

         */
    }

}
