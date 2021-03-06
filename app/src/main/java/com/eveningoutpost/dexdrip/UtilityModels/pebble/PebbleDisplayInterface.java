package com.eveningoutpost.dexdrip.UtilityModels.pebble;

import android.content.Context;

import com.eveningoutpost.dexdrip.UtilityModels.BgGraphBuilder;
import com.getpebble.android.kit.util.PebbleDictionary;

/**
 * Created by andy on 02/06/16.
 */
public interface PebbleDisplayInterface {

    /**
     * This is command that will be sent to device, in onStartCommand.
     */
    void startDeviceCommand();

    /**
     * For receiveData event
     */
    void receiveData(int transactionId, PebbleDictionary data);

    /**
     * For receiveNack event
     */
    void receiveNack(int transactionId);

    /**
     * For receiveAck event
     */
    void receiveAck(int transactionId);

    /**
     * Init
     */
    void initDisplay(Context context, PebbleWatchSync pebbleWatchSync, BgGraphBuilder bgGraphBuilder);

}
