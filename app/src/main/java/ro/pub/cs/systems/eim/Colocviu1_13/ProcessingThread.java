package ro.pub.cs.systems.eim.Colocviu1_13;



import android.content.Context;
import android.util.Log;
import android.content.Intent;
import java.util.Date;
import java.util.Random;
import android.os.Process;

public class ProcessingThread extends Thread {


    private Context context = null;
    private boolean isRunning = true;
    private Random random = new Random();

    private String cardinalList;

    public ProcessingThread(Context context, String cardinalList) {
        this.context = context;
        this.cardinalList = cardinalList;
    }

    private void sendMessage() {
        Intent intent = new Intent();
        intent.setAction(Constants.actionTypes[random.nextInt(Constants.actionTypes.length)]);
        intent.putExtra(Constants.BROADCAST_RECEIVER_EXTRA,
                new Date(System.currentTimeMillis()) + " " + cardinalList);
        context.sendBroadcast(intent);
    }

    @Override
    public void run() {
        Log.d(Constants.PROCESSING_THREAD_TAG, "Thread has started! PID: " + Process.myPid() + " TID: " + Process.myTid());
        while (isRunning) {
            sendMessage();
            sleep();
        }
        Log.d(Constants.PROCESSING_THREAD_TAG, "Thread has stopped!");
    }

    private void sleep() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException interruptedException) {
            interruptedException.printStackTrace();
        }
    }

    public void stopThread() {
        isRunning = false;
    }

}


