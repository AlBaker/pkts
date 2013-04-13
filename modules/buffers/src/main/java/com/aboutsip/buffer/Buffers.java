/**
 * 
 */
package com.aboutsip.buffer;

import java.io.InputStream;

/**
 * @author jonas@jonasborjesson.com
 */
public final class Buffers {

    /**
     * 
     */
    private Buffers() {
        // left empty intentionally
    }

    /**
     * An empty buffer.
     */
    public static Buffer EMPTY_BUFFER = new EmptyBuffer();

    public static Buffer wrap(final int value) {
        final byte[] buffer = new byte[4];
        buffer[0] = (byte) (value >>> 24);
        buffer[1] = (byte) (value >>> 16);
        buffer[2] = (byte) (value >>> 8);
        buffer[3] = (byte) value;
        return new ByteBuffer(0, 0, buffer.length, 0, buffer);

    }

    public static Buffer wrap(final String s) {
        if (s == null) {
            throw new IllegalArgumentException("String cannot be null");
        }

        return Buffers.wrap(s.getBytes());
    }

    public static Buffer wrap(final InputStream is) {
        if (is == null) {
            throw new IllegalArgumentException("the input stream cannot be null or empty");
        }

        return new InputStreamBuffer(is);
    }

    /**
     * Create a new Buffer
     * 
     * @param capacity
     * @return
     */
    public static Buffer createBuffer(final int capacity) {
        final byte[] buffer = new byte[capacity];
        return new ByteBuffer(0, 0, buffer.length, 0, buffer);
    }

    /**
     * Wrap the supplied byte array
     * 
     * @param buffer
     * @return
     */
    public static Buffer wrap(final byte[] buffer) {
        if (buffer == null || buffer.length == 0) {
            throw new IllegalArgumentException("the buffer cannot be null or empty");
        }

        return new ByteBuffer(buffer);
    }

}
