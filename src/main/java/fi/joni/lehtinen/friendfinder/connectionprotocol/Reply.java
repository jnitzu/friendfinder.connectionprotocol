package fi.joni.lehtinen.friendfinder.connectionprotocol;

import java.util.ArrayList;
import java.util.List;

public class Reply implements Sendable {

    public enum ReplyCode {
        LOGIN_SUCCESSFUL(1),
        CREDENTIAL_ERROR_EMAIL(2),
        CREDENTIAL_ERROR_PASSWORD(3),
        DATA_REQUEST_SUCCESSFUL(51),
        DATA_REQUEST_ERROR(52),
        REGISTERATION_SUCCESSFUL(100),
        EMAIL_TAKEN(101),
        LOCATION_TRANSFER_SUCCESS(200),
        CIRCLE_CREATE_SUCCESSFUL(301),
        CIRCLE_DELETE_SUCCESSFUL(302),
        ADD_FRIEND_SUCCESSFUL(401),
        FRIEND_NOT_FOUND(402),
        REMOVE_FRIEND_SUCCESSFUL(403),
        NOT_PART_OF_CIRCLE(410),
        JOIN_REQUEST_SUCCESSFUL(501),
        JOIN_REQUEST_CONFIRMED_SUCCESSFULLY(502),
        JOIN_REQUEST_DECLINED_SUCCESSFULLY(503),
        JOIN_REQUEST_ERROR(504),
        UNKNOWN_REQUEST_FORMAT(998),
        UNKNOWN_ERROR(999),
        CONNECTION_RESET_BY_PEER(2000),
        UNKNOWN_ERROR1(2001),
        NO_NETWORK_CONNECTION(2002),
        NOT_LOGGED_IN(5000),
        WRONG_USER_ID(5001);

        private final int mCode;

        ReplyCode(int code){
            mCode = code;
        }

        public int getCode(){
            return mCode;
        }
    }

    public ReplyCode mReplyCode;

    private List<byte[]> mMessages = new ArrayList<>();

    public void addMessage(byte[] message){
        mMessages.add( message );
    }

    public byte[] getMessage(int index){
        return mMessages.get( index );
    }

    public List<byte[]> getMessages(){
        return mMessages;
    }

}
