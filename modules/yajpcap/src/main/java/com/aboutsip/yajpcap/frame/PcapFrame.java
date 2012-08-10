/**
 * 
 */
package com.aboutsip.yajpcap.frame;

import java.io.IOException;

import com.aboutsip.buffer.Buffer;
import com.aboutsip.yajpcap.framer.Framer;
import com.aboutsip.yajpcap.framer.FramerManager;
import com.aboutsip.yajpcap.packet.Packet;
import com.aboutsip.yajpcap.packet.PacketParseException;
import com.aboutsip.yajpcap.packet.impl.PCapPacketImpl;
import com.aboutsip.yajpcap.protocol.Protocol;

/**
 * The Pcap frame is where it all begins.
 * 
 * @author jonas@jonasborjesson.com
 * 
 */
public final class PcapFrame extends AbstractFrame {

    /**
     * The pcap record header that tells us at what time the packet was
     * captured, the length of the payload etc
     */
    private final PcapRecordHeader header;

    /**
     * 
     */
    public PcapFrame(final FramerManager framerManager, final PcapRecordHeader header, final Buffer payload) {
        super(framerManager, Protocol.PCAP, payload);
        assert framerManager != null;
        assert header != null;
        assert payload != null;
        this.header = header;
    }


    @Override
    protected Frame framePayload(final FramerManager framerManager, final Buffer payload) throws IOException {
        final Framer sllFramer = framerManager.getFramer(Protocol.SLL);

        if (sllFramer.accept(payload)) {
            return sllFramer.frame(payload);
        }

        final Framer ethernetFramer = framerManager.getFramer(Protocol.ETHERNET_II);
        return ethernetFramer.frame(payload);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Packet parse() throws PacketParseException {
        return new PCapPacketImpl(this.header);
    }


}
