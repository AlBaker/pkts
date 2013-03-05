/**
 * 
 */
package com.aboutsip.streams;

import com.aboutsip.yajpcap.FrameHandler;
import com.aboutsip.yajpcap.packet.Packet;
import com.aboutsip.yajpcap.packet.sip.SipMessage;

/**
 * The {@link StreamHandler} is a higher-level {@link FrameHandler} that
 * consumes streams and calls its registered {@link StreamListener}s.
 * 
 * @author jonas@jonasborjesson.com
 */
public interface StreamHandler extends FrameHandler {

    /**
     * Add a {@link StreamListener} to this {@link StreamHandler}.
     * 
     * @param listener
     * @throws IllegalArgumentException
     *             in case the {@link StreamListener} is not propertly
     *             parameterized.
     */
    void addStreamListener(StreamListener<? extends Packet> listener) throws IllegalArgumentException;

    /**
     * Set the {@link FragmentListener}. Note, only one of these listeners is
     * allowed so if you set a second one the previous listener will be thrown
     * away.
     * 
     * @param listener
     *            the listener or null if you want to remove a previously set
     *            listener.
     */
    void setFragmentListener(FragmentListener listener);

    /**
     * If there is a registered {@link StreamListener} for {@link SipMessage}s
     * then this {@link StreamHandler} will start processing SIP messages for
     * which you can get all the statistics for through this method.
     * 
     * @return a {@link SipStatistics} object. Note, if this
     *         {@link StreamHandler} has not been configured to handle sip
     *         traffic then all the stats will be zero.
     */
    SipStatistics getSipStatistics();

}
