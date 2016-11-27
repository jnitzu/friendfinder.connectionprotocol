package fi.joni.lehtinen.friendfinder.connectionprotocol;

import java.nio.ByteBuffer;

public class Utility {

    /**
     * Converts long to byte array
     * @param l long value
     * @return Byte array of long value
     */
    public static byte[] longToByteArray(long l){
        // Convert long to byte array
        ByteBuffer buffer = ByteBuffer.allocate(Long.BYTES);
        buffer.putLong(l);
        return buffer.array();
    }


    /**
     * Convers byte array to long
     * @param b byte array consisting a long
     * @return long value from byte array
     */
    public static long byteArrayToLong(byte[] b) {
        ByteBuffer buffer = ByteBuffer.allocate(Long.BYTES);
        buffer.put(b);
        buffer.flip();
        return buffer.getLong();
    }

    public static byte[] passwordDecode( String hashString ){
        String[] bytes = hashString.split( "," );
        byte[] hash = new byte[bytes.length];

        for(int i = 0; i < bytes.length; i++){
            hash[i] = Byte.parseByte( bytes[i] );
        }

        return hash;
    }

    public static String passwordEncode( byte[] hash ){
        StringBuilder hashString = new StringBuilder();
        for(int i = 0; i < hash.length; i++)
            hashString.append( hash[i] ).append( i + 1 != hash.length ? "," : "" );

        return hashString.toString();
    }
}
