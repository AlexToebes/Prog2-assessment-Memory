package com.alextoebes.memorygame;

import javafx.stage.Stage;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by Alex on 16-6-2016.
 */
public class DocumentReady {
    private static DocumentReady instance = new DocumentReady( );

    private List<ActionListener> listenerList = new ArrayList<>();
    private ActionEvent event;

    private DocumentReady(){ }

    public static void onReady(ActionListener listener) {
        if (instance.event == null) {
            instance.listenerList.add(listener);
        } else {
            new Thread(() -> {
                try {
                    Thread.sleep(100);
                    listener.actionPerformed(instance.event);
                }
                catch (Exception e){
                    System.err.println(e);
                }
            }).start();
        }
    }

    public static void triggerReady(Stage stage) {
        instance.event = new ActionEvent(stage, UUID.randomUUID().hashCode(), "Document ready");
        for (ActionListener listener : instance.listenerList) {
            listener.actionPerformed(instance.event);
        }
    }
}
