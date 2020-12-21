/*
 * Copyright 2013-2014 Richard M. Hightower
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  		http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * __________                              _____          __   .__
 * \______   \ ____   ____   ____   /\    /     \ _____  |  | _|__| ____    ____
 *  |    |  _//  _ \ /  _ \ /    \  \/   /  \ /  \\__  \ |  |/ /  |/    \  / ___\
 *  |    |   (  <_> |  <_> )   |  \ /\  /    Y    \/ __ \|    <|  |   |  \/ /_/  >
 *  |______  /\____/ \____/|___|  / \/  \____|__  (____  /__|_ \__|___|  /\___  /
 *         \/                   \/              \/     \/     \/       \//_____/
 *      ____.                     ___________   _____    ______________.___.
 *     |    |____ ___  _______    \_   _____/  /  _  \  /   _____/\__  |   |
 *     |    \__  \\  \/ /\__  \    |    __)_  /  /_\  \ \_____  \  /   |   |
 * /\__|    |/ __ \\   /  / __ \_  |        \/    |    \/        \ \____   |
 * \________(____  /\_/  (____  / /_______  /\____|__  /_______  / / ______|
 *               \/           \/          \/         \/        \/  \/
 */

package com.abubusoft.kripton.common;

import java.io.ByteArrayInputStream;


/**
 * The Class DynamicByteBuffer.
 */
public class DynamicByteBuffer {

    /**
     * Creates the.
     *
     * @param buffer the buffer
     * @return the dynamic byte buffer
     */
    public static DynamicByteBuffer create(byte[] buffer ) {
        DynamicByteBuffer buf = new DynamicByteBuffer( buffer.length );
        buf.buffer = buffer;
        return buf;
    }
    
    /**
     * Creates the.
     *
     * @return the dynamic byte buffer
     */
    public static DynamicByteBuffer create() {
        return new DynamicByteBuffer(2048);
    }


    /**
     * Creates the.
     *
     * @param capacity the capacity
     * @return the dynamic byte buffer
     */
    public static DynamicByteBuffer create( int capacity ) {
        return new DynamicByteBuffer( capacity );
    }

    /**
     * Creates the exact.
     *
     * @param capacity the capacity
     * @return the dynamic byte buffer
     */
    public static DynamicByteBuffer createExact( final int capacity ) {
        return new DynamicByteBuffer( capacity ) {
            public DynamicByteBuffer add( byte[] chars ) {
                DynamicByteBufferHelper._idx( buffer, length, chars );
                length += chars.length;
                return this;
            }
        };
    }


    /** The buffer. */
    protected byte[] buffer;

    /** The capacity. */
    protected int capacity = 16;

    /** The length. */
    protected int length = 0;

    /**
     * Instantiates a new dynamic byte buffer.
     */
    protected DynamicByteBuffer() {
        init();
    }


    /**
     * Instantiates a new dynamic byte buffer.
     *
     * @param capacity the capacity
     */
    protected DynamicByteBuffer( int capacity ) {
        this.capacity = capacity;
        init();
    }

    /**
     * Adds the.
     *
     * @param value the value
     * @return the dynamic byte buffer
     */
    public DynamicByteBuffer add( byte value ) {

        if ( 1 + length < capacity ) {
            DynamicByteBufferHelper.idx( buffer, length, value );
        } else {
            buffer = DynamicByteBufferHelper.grow( buffer );
            capacity = buffer.length;

            DynamicByteBufferHelper.idx( buffer, length, value );
        }

        length += 1;

        return this;

    }


    /**
     * Adds the.
     *
     * @param array the array
     * @return the dynamic byte buffer
     */
    public DynamicByteBuffer add( byte[] array ) {
        if ( array.length + this.length < capacity ) {
            DynamicByteBufferHelper._idx( buffer, length, array );
        } else {
            buffer = DynamicByteBufferHelper.grow( buffer, buffer.length * 2 + array.length );
            capacity = buffer.length;

            DynamicByteBufferHelper._idx( buffer, length, array );

        }
        length += array.length;
        return this;
    }


    /**
     * Adds the.
     *
     * @param array the array
     * @param length the length
     * @return the dynamic byte buffer
     */
    public DynamicByteBuffer add( final byte[] array, final int length ) {
        if ( ( this.length + length ) < capacity ) {
            DynamicByteBufferHelper._idx( buffer, this.length, array, length );
        } else {
            buffer = DynamicByteBufferHelper.grow( buffer, buffer.length * 2 + length );
            capacity = buffer.length;

            DynamicByteBufferHelper._idx( buffer, length, array, length );

        }
        this.length += length;
        return this;
    }


    /**
     * Adds the.
     *
     * @param array the array
     * @param offset the offset
     * @param length the length
     * @return the dynamic byte buffer
     */
    public DynamicByteBuffer add( byte[] array, final int offset, final int length ) {
        if ( ( this.length + length ) < capacity ) {
            DynamicByteBufferHelper._idx( buffer, length, array, offset, length );
        } else {
            buffer = DynamicByteBufferHelper.grow( buffer, buffer.length * 2 + length );
            capacity = buffer.length;

            DynamicByteBufferHelper._idx( buffer, length, array, offset, length );

        }
        this.length += length;
        return this;
    }


    /**
     * Adds the.
     *
     * @param value the value
     * @return the dynamic byte buffer
     */
    public DynamicByteBuffer add( char value ) {

        if ( 2 + length < capacity ) {
            DynamicByteBufferHelper.charTo( buffer, length, value );
        } else {
            buffer = DynamicByteBufferHelper.grow( buffer, buffer.length * 2 + 2 );
            capacity = buffer.length;

            DynamicByteBufferHelper.charTo( buffer, length, value );
        }

        length += 2;
        return this;


    }


    /**
     * Adds the.
     *
     * @param value the value
     * @return the dynamic byte buffer
     */
    public DynamicByteBuffer add( double value ) {

        if ( 8 + length < capacity ) {
            DynamicByteBufferHelper.doubleTo( buffer, length, value );
        } else {
            buffer = DynamicByteBufferHelper.grow( buffer, buffer.length * 2 + 8 );
            capacity = buffer.length;

            DynamicByteBufferHelper.doubleTo( buffer, length, value );
        }

        length += 8;
        return this;

    }

    /**
     * Adds the.
     *
     * @param value the value
     * @return the dynamic byte buffer
     */
    public DynamicByteBuffer add( float value ) {

        if ( 4 + length < capacity ) {
            DynamicByteBufferHelper.floatTo( buffer, length, value );
        } else {
            buffer = DynamicByteBufferHelper.grow( buffer, buffer.length * 2 + 4 );
            capacity = buffer.length;

            DynamicByteBufferHelper.floatTo( buffer, length, value );
        }

        length += 4;
        return this;


    }

    /**
     * Adds the.
     *
     * @param value the value
     * @return the dynamic byte buffer
     */
    public DynamicByteBuffer add( int value ) {

        if ( 4 + length < capacity ) {
            DynamicByteBufferHelper.intTo( buffer, length, value );
        } else {
            buffer = DynamicByteBufferHelper.grow( buffer, buffer.length * 2 + 4 );
            capacity = buffer.length;

            DynamicByteBufferHelper.intTo( buffer, length, value );
        }

        length += 4;
        return this;


    }

    /**
     * Adds the.
     *
     * @param value the value
     * @return the dynamic byte buffer
     */
    public DynamicByteBuffer add( long value ) {

        if ( 8 + length < capacity ) {
            DynamicByteBufferHelper.longTo( buffer, length, value );
        } else {
            buffer = DynamicByteBufferHelper.grow( buffer, buffer.length * 2 + 8 );
            capacity = buffer.length;

            DynamicByteBufferHelper.longTo( buffer, length, value );
        }

        length += 8;
        return this;

    }

    /**
     * Adds the.
     *
     * @param value the value
     * @return the dynamic byte buffer
     */
    public DynamicByteBuffer add( short value ) {

        if ( 2 + length < capacity ) {
            DynamicByteBufferHelper.shortTo( buffer, length, value );
        } else {
            buffer = DynamicByteBufferHelper.grow( buffer, buffer.length * 2 + 2 );
            capacity = buffer.length;

            DynamicByteBufferHelper.shortTo( buffer, length, value );
        }

        length += 2;
        return this;


    }

    /**
     * Adds the.
     *
     * @param str the str
     * @return the dynamic byte buffer
     */
    public DynamicByteBuffer add( String str ) {
        this.add( DynamicByteBufferHelper.bytes( str ) );
        return this;

    }


    /**
     * Adds the byte.
     *
     * @param value the value
     * @return the dynamic byte buffer
     */
    public DynamicByteBuffer addByte( int value ) {
        this.add( ( byte ) value );
        return this;
    }


    /**
     * Adds the unsigned byte.
     *
     * @param value the value
     */
    public void addUnsignedByte( short value ) {
        if ( 1 + length < capacity ) {
            DynamicByteBufferHelper.unsignedByteTo( buffer, length, value );
        } else {
            buffer = DynamicByteBufferHelper.grow( buffer, buffer.length * 2 + 1 );
            capacity = buffer.length;

            DynamicByteBufferHelper.unsignedByteTo( buffer, length, value );
        }

        length += 1;

    }

    /**
     * Adds the unsigned int.
     *
     * @param value the value
     * @return the dynamic byte buffer
     */
    public DynamicByteBuffer addUnsignedInt( long value ) {

        if ( 4 + length < capacity ) {
            DynamicByteBufferHelper.unsignedIntTo( buffer, length, value );
        } else {
            buffer = DynamicByteBufferHelper.grow( buffer, buffer.length * 2 + 4 );
            capacity = buffer.length;

            DynamicByteBufferHelper.unsignedIntTo( buffer, length, value );
        }

        length += 4;
        return this;

    }

    /**
     * Adds the unsigned short.
     *
     * @param value the value
     */
    public void addUnsignedShort( int value ) {

        if ( 2 + length < capacity ) {
            DynamicByteBufferHelper.unsignedShortTo( buffer, length, value );
        } else {
            buffer = DynamicByteBufferHelper.grow( buffer, buffer.length * 2 + 2 );
            capacity = buffer.length;

            DynamicByteBufferHelper.unsignedShortTo( buffer, length, value );
        }

        length += 2;


    }

    /**
     * Do write double array.
     *
     * @param values the values
     * @param byteSize the byte size
     */
    private void doWriteDoubleArray( double[] values, int byteSize ) {
        if ( !( byteSize + length < capacity ) ) {
            buffer = DynamicByteBufferHelper.grow( buffer, buffer.length * 2 + byteSize );
        }
        for ( int index = 0; index < values.length; index++ ) {
            this.add( values[ index ] );
        }
    }

    /**
     * Do write float array.
     *
     * @param values the values
     * @param byteSize the byte size
     */
    private void doWriteFloatArray( float[] values, int byteSize ) {
        if ( !( byteSize + length < capacity ) ) {
            buffer = DynamicByteBufferHelper.grow( buffer, buffer.length * 2 + byteSize );
        }
        for ( int index = 0; index < values.length; index++ ) {
            this.add( values[ index ] );
        }
    }

    /**
     * Do write int array.
     *
     * @param values the values
     * @param byteSize the byte size
     */
    private void doWriteIntArray( int[] values, int byteSize ) {
        if ( !( byteSize + length < capacity ) ) {
            buffer = DynamicByteBufferHelper.grow( buffer, buffer.length * 2 + byteSize );
        }
        for ( int index = 0; index < values.length; index++ ) {
            this.add( values[ index ] );
        }
    }

    /**
     * Do write long array.
     *
     * @param values the values
     * @param byteSize the byte size
     */
    private void doWriteLongArray( long[] values, int byteSize ) {
        if ( !( byteSize + length < capacity ) ) {
            buffer = DynamicByteBufferHelper.grow( buffer, buffer.length * 2 + byteSize );
        }
        for ( int index = 0; index < values.length; index++ ) {
            this.add( values[ index ] );
        }
    }

    /**
     * Do write short array.
     *
     * @param values the values
     * @param byteSize the byte size
     */
    private void doWriteShortArray( short[] values, int byteSize ) {
        if ( !( byteSize + length < capacity ) ) {
            buffer = DynamicByteBufferHelper.grow( buffer, buffer.length * 2 + byteSize );
        }
        for ( int index = 0; index < values.length; index++ ) {
            this.add( values[ index ] );
        }
    }

    /**
     * Inits the.
     */
    private void init() {
        buffer = new byte[ capacity ];
    }

    /**
     * Input.
     *
     * @return the byte array input stream
     */
    public ByteArrayInputStream input() {
    	return new ByteArrayInputStream(this.buffer);        
    }

    /**
     * Len.
     *
     * @return the int
     */
    public int len() {
        return length;
    }

    /**
     * Read and reset.
     *
     * @return the byte[]
     */
    public byte[] readAndReset() {
        byte[] bytes = this.buffer;
        this.buffer = null;
        return bytes;
    }

    /**
     * Read for recycle.
     *
     * @return the byte[]
     */
    public byte[] readForRecycle() {
        this.length = 0;
        return this.buffer;
    }

    /**
     * Slc.
     *
     * @param startIndex the start index
     * @param endIndex the end index
     * @return the byte[]
     */
    public byte[] slc( int startIndex, int endIndex ) {
        return DynamicByteBufferHelper.slc( this.buffer, startIndex, endIndex );
    }

    /**
     * To bytes.
     *
     * @return the byte[]
     */
    public byte[] toBytes() {
        return DynamicByteBufferHelper.slc( this.buffer, 0, length );
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    public String toString() {
        int len = len();

        char[] chars = new char[ buffer.length ];
        for ( int index = 0; index < chars.length; index++ ) {
            chars[ index ] = ( char ) buffer[ index ];
        }
        return new String( chars, 0, len );
        //return new String ( this.buffer, 0, len, StandardCharsets.UTF_8 );
    }

    /**
     * Write.
     *
     * @param b the b
     */
    public void write( byte[] b ) {
        this.add( b );
    }

    /**
     * Write.
     *
     * @param b the b
     * @param off the off
     * @param len the len
     */
    public void write( byte[] b, int off, int len ) {
        this.add( b, len );
    }

    /**
     * Write.
     *
     * @param b the b
     */
    public void write( int b ) {
        this.addByte( b );
    }

    /**
     * Write boolean.
     *
     * @param v the v
     */
    public void writeBoolean( boolean v ) {
        if ( v == true ) {
            this.addByte( 1 );
        } else {
            this.addByte( 0 );
        }
    }

    /**
     * Write byte.
     *
     * @param v the v
     */
    public void writeByte( byte v ) {
        this.addByte( v );
    }

    /**
     * Write char.
     *
     * @param v the v
     */
    public void writeChar( char v ) {

        this.add( v );
    }

    /**
     * Write double.
     *
     * @param v the v
     */
    public void writeDouble( double v ) {
        this.add( v );
    }

    /**
     * Write float.
     *
     * @param v the v
     */
    public void writeFloat( float v ) {
        this.add( v );
    }

    /**
     * Write int.
     *
     * @param v the v
     */
    public void writeInt( int v ) {
        this.add( v );
    }

    /**
     * Write large byte array.
     *
     * @param bytes the bytes
     */
    public void writeLargeByteArray( byte[] bytes ) {
        this.add( bytes.length );
        this.add( bytes );
    }

    /**
     * Write large double array.
     *
     * @param values the values
     */
    public void writeLargeDoubleArray( double[] values ) {
        int byteSize = values.length * 8 + 4;
        this.add( values.length );
        doWriteDoubleArray( values, byteSize );


    }

    /**
     * Write large float array.
     *
     * @param values the values
     */
    public void writeLargeFloatArray( float[] values ) {
        int byteSize = values.length * 4 + 4;
        this.add( values.length );
        doWriteFloatArray( values, byteSize );

    }

    /**
     * Write large int array.
     *
     * @param values the values
     */
    public void writeLargeIntArray( int[] values ) {
        int byteSize = values.length * 4 + 4;
        this.add( values.length );
        doWriteIntArray( values, byteSize );
    }

    /**
     * Write large long array.
     *
     * @param values the values
     */
    public void writeLargeLongArray( long[] values ) {
        int byteSize = values.length * 8 + 4;
        this.add( values.length );
        doWriteLongArray( values, byteSize );
    }


    /**
     * Write large short array.
     *
     * @param values the values
     */
    public void writeLargeShortArray( short[] values ) {
        int byteSize = values.length * 2 + 4;
        this.add( values.length );
        doWriteShortArray( values, byteSize );
    }


    /**
     * Write large string.
     *
     * @param s the s
     */
    public void writeLargeString( String s ) {
        final byte[] bytes = DynamicByteBufferHelper.bytes( s );
        this.add( bytes.length );
        this.add( bytes );
    }

    /**
     * Write long.
     *
     * @param v the v
     */
    public void writeLong( long v ) {
        this.add( v );
    }

    /**
     * Write medium byte array.
     *
     * @param bytes the bytes
     */
    public void writeMediumByteArray( byte[] bytes ) {
        this.addUnsignedShort( bytes.length );
        this.add( bytes );
    }

    /**
     * Write medium double array.
     *
     * @param values the values
     */
    public void writeMediumDoubleArray( double[] values ) {
        int byteSize = values.length * 8 + 2;
        this.addUnsignedShort( values.length );
        doWriteDoubleArray( values, byteSize );

    }

    /**
     * Write medium float array.
     *
     * @param values the values
     */
    public void writeMediumFloatArray( float[] values ) {
        int byteSize = values.length * 4 + 2;
        this.addUnsignedShort( values.length );
        doWriteFloatArray( values, byteSize );

    }

    /**
     * Write medium int array.
     *
     * @param values the values
     */
    public void writeMediumIntArray( int[] values ) {
        int byteSize = values.length * 4 + 2;
        this.addUnsignedShort( values.length );
        doWriteIntArray( values, byteSize );
    }

    /**
     * Write medium long array.
     *
     * @param values the values
     */
    public void writeMediumLongArray( long[] values ) {
        int byteSize = values.length * 8 + 2;
        this.addUnsignedShort( values.length );
        doWriteLongArray( values, byteSize );
    }

    /**
     * Write medium short array.
     *
     * @param values the values
     */
    public void writeMediumShortArray( short[] values ) {
        int byteSize = values.length * 2 + 2;
        this.addUnsignedShort( values.length );
        doWriteShortArray( values, byteSize );
    }

    /**
     * Write medium string.
     *
     * @param s the s
     */
    public void writeMediumString( String s ) {
        final byte[] bytes = DynamicByteBufferHelper.bytes( s );
        this.addUnsignedShort( bytes.length );
        this.add( bytes );
    }

    /**
     * Write short.
     *
     * @param v the v
     */
    public void writeShort( short v ) {
        this.add( v );
    }

    /**
     * Write small byte array.
     *
     * @param bytes the bytes
     */
    public void writeSmallByteArray( byte[] bytes ) {
        this.addUnsignedByte( ( short ) bytes.length );
        this.add( bytes );
    }

    /**
     * Write small double array.
     *
     * @param values the values
     */
    public void writeSmallDoubleArray( double[] values ) {
        int byteSize = values.length * 8 + 1;
        this.addUnsignedByte( ( short ) values.length );
        doWriteDoubleArray( values, byteSize );

    }

    /**
     * Write small float array.
     *
     * @param values the values
     */
    public void writeSmallFloatArray( float[] values ) {
        int byteSize = values.length * 4 + 1;
        this.addUnsignedByte( ( short ) values.length );
        doWriteFloatArray( values, byteSize );
    }

    /**
     * Write small int array.
     *
     * @param values the values
     */
    public void writeSmallIntArray( int[] values ) {
        int byteSize = values.length * 4 + 1;
        this.addUnsignedByte( ( short ) values.length );
        doWriteIntArray( values, byteSize );
    }

    /**
     * Write small long array.
     *
     * @param values the values
     */
    public void writeSmallLongArray( long[] values ) {
        int byteSize = values.length * 8 + 1;
        this.addUnsignedByte( ( short ) values.length );
        doWriteLongArray( values, byteSize );
    }

    /**
     * Write small short array.
     *
     * @param values the values
     */
    public void writeSmallShortArray( short[] values ) {
        int byteSize = values.length * 2 + 1;
        this.addUnsignedByte( ( short ) values.length );
        doWriteShortArray( values, byteSize );
    }

    /**
     * Write small string.
     *
     * @param s the s
     */
    public void writeSmallString( String s ) {
        final byte[] bytes = DynamicByteBufferHelper.bytes( s );
        this.addUnsignedByte( ( short ) bytes.length );
        this.add( bytes );
    }

    /**
     * Write unsigned byte.
     *
     * @param v the v
     */
    public void writeUnsignedByte( short v ) {
        this.addUnsignedByte( v );
    }

    /**
     * Write unsigned int.
     *
     * @param v the v
     */
    public void writeUnsignedInt( long v ) {
        this.addUnsignedInt( v );
    }

    /**
     * Write unsigned short.
     *
     * @param v the v
     */
    public void writeUnsignedShort( int v ) {
        this.addUnsignedShort( v );
    }

}