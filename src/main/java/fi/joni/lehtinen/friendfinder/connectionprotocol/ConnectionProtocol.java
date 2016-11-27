package fi.joni.lehtinen.friendfinder.connectionprotocol;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

public class ConnectionProtocol {

    private static final String TAG = "ConnectionProtocol";

    public static final String MESSAGE_SPLIT_CHAR = "\n";
    public static final byte[] END_OF_MESSAGE = "\r\n\r\n".getBytes( StandardCharsets.UTF_8 );

    public enum Protocols {
        LOGIN, LOGIN_HASH, REGISTER, USER, REPLY, EMAIL_TAKEN, LOCATION,
        ADD_CIRCLE_MEMBER, CREATE_CIRCLE, JOIN_REQUESTS, DECLINE_JOIN_REQUEST, CONFIRM_JOIN_REQUEST,
        REMOVE_CIRCLE_MEMBER, DELETE_CIRCLE, CIRCLE_DATA
    }

    /***
     * Copies the message to another buffer and checks if complete message has been received
     * @param channelBuffer Channels read buffer
     * @param requestBuffer Buffer that will have a message received
     * @return true if complete message was received
     */
    public static boolean copyReadBufferTo( ByteBuffer channelBuffer, ByteBuffer requestBuffer ){
        // requestBuffer will have the earlier part of the message and will have up to date position how far has been reached
        for(int i = requestBuffer.position(); i <= channelBuffer.position() - 4; i++){
            if(channelBuffer.get( i ) == END_OF_MESSAGE[0]){
                if( channelBuffer.get( i + 1 ) == END_OF_MESSAGE[1] &&
                    channelBuffer.get( i + 2 ) == END_OF_MESSAGE[2] &&
                    channelBuffer.get( i + 3 ) == END_OF_MESSAGE[3]){

                    // Set limit to current position and position to end of message
                    // Then compact the next message if there is any to the start of the buffer
                    channelBuffer.limit( channelBuffer.position() );
                    channelBuffer.position(i + 4);
                    channelBuffer.compact();

                    return true;

                } else {
                    // This can't happen as \r is only valid for the end of message.
                    // This needs to throw exception
                }
            } else {
                requestBuffer.put( channelBuffer.get(i) );
            }
        }

        return false;
    }

}