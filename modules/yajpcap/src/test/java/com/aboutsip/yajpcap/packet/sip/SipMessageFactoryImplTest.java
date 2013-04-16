/**
 * 
 */
package com.aboutsip.yajpcap.packet.sip;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.aboutsip.buffer.Buffer;
import com.aboutsip.yajpcap.YajTestBase;
import com.aboutsip.yajpcap.packet.SipMessageFactory;
import com.aboutsip.yajpcap.packet.sip.header.MaxForwardsHeader;
import com.aboutsip.yajpcap.protocol.Protocol;

/**
 * @author jonas
 *
 */
public class SipMessageFactoryImplTest extends YajTestBase {

    private final SipMessageFactory factory = new SipMessageFactoryImpl();

    /**
     * @throws java.lang.Exception
     */
    @Override
    @Before
    public void setUp() throws Exception {
        super.setUp();
    }

    /**
     * @throws java.lang.Exception
     */
    @Override
    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testCreateResponseBasedOnRequest() throws Exception {
        final SipRequest req = (SipRequest) loadStream("sipp.pcap").get(0).getFrame(Protocol.SIP).parse();
        System.out.println(req);
        final SipResponse resp = this.factory.createResponse(200, req);
        assertThat(resp.getStatus(), is(200));
        assertThat(resp.getFromHeader().getValue().toString(), is("sipp <sip:sipp@127.0.1.1:5060>;tag=16732SIPpTag001"));
        assertThat(resp.getToHeader().getValue().toString(), is("sut <sip:service@127.0.0.1:5090>"));
        assertThat(resp.getCallIDHeader().getValue().toString(), is("1-16732@127.0.1.1"));
        assertThat(resp.getViaHeader().getValue().toString(), is("SIP/2.0/UDP 127.0.1.1:5060;branch=z9hG4bK-16732-1-0"));
        assertThat(resp.getHeader(MaxForwardsHeader.NAME).getValue().toString(), is("70"));

        final Buffer buffer = resp.toBuffer();
        System.out.println(buffer);
    }

}
