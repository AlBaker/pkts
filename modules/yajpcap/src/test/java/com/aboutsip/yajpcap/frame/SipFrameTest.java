/**
 * 
 */
package com.aboutsip.yajpcap.frame;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.aboutsip.yajpcap.YajTestBase;
import com.aboutsip.yajpcap.framer.SIPFramer;
import com.aboutsip.yajpcap.packet.SipMessage;

/**
 * @author jonas
 *
 */
public class SipFrameTest extends YajTestBase {

    /**
     * {@inheritDoc}
     */
    @Override
    @Before
    public void setUp() throws Exception {
        super.setUp();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @After
    public void tearDown() throws Exception {
        super.tearDown();
    }

    @Ignore
    @Test
    public void test() throws Exception {
        final SIPFramer framer = new SIPFramer(this.framerManager);
        final SipFrame frame = framer.frame(this.sipFrameBuffer);
        final SipMessage sip = frame.parse();
        assertThat(sip.getMethod().toString(), is("INVITE"));
    }

}
