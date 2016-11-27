package fi.joni.lehtinen.friendfinder.connectionprotocol;

import fi.joni.lehtinen.friendfinder.connectionprotocol.dto.Circle;
import fi.joni.lehtinen.friendfinder.connectionprotocol.dto.CircleMember;
import fi.joni.lehtinen.friendfinder.connectionprotocol.dto.Location;
import fi.joni.lehtinen.friendfinder.connectionprotocol.dto.Login;
import fi.joni.lehtinen.friendfinder.connectionprotocol.dto.Register;
import fi.joni.lehtinen.friendfinder.connectionprotocol.dto.User;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

public class PacketParser {

    public static ByteBuffer getMessage( ConnectionProtocol.Protocols protocol, Sendable sendable ){
        StringBuilder message = new StringBuilder();
        switch( protocol ) {

            case LOGIN:
                Login login = (Login)sendable;
                message.append( protocol )
                        .append( ConnectionProtocol.MESSAGE_SPLIT_CHAR )
                        .append( login.mEmail.trim() )
                        .append( ConnectionProtocol.MESSAGE_SPLIT_CHAR )
                        .append( login.mPassword.trim() )
                        .append( new String( ConnectionProtocol.END_OF_MESSAGE, StandardCharsets.UTF_8 ) );

                break;
            case LOGIN_HASH:
                Login login_hash = (Login)sendable;
                message.append( protocol )
                        .append( ConnectionProtocol.MESSAGE_SPLIT_CHAR )
                        .append( login_hash.mEmail.trim() )
                        .append( ConnectionProtocol.MESSAGE_SPLIT_CHAR )
                        .append( Utility.passwordEncode( login_hash.mHash ) )
                        .append( new String( ConnectionProtocol.END_OF_MESSAGE, StandardCharsets.UTF_8 ) );

                break;
            case REGISTER:
                Register register = (Register)sendable;
                message.append( protocol )
                        .append( ConnectionProtocol.MESSAGE_SPLIT_CHAR )
                        .append( register.mEmail.trim() )
                        .append( ConnectionProtocol.MESSAGE_SPLIT_CHAR )
                        .append( register.mFirstName.trim() )
                        .append( ConnectionProtocol.MESSAGE_SPLIT_CHAR )
                        .append( register.mLastName.trim() )
                        .append( ConnectionProtocol.MESSAGE_SPLIT_CHAR )
                        .append( register.mPassword.trim() )
                        .append( new String( ConnectionProtocol.END_OF_MESSAGE, StandardCharsets.UTF_8 ) );

                break;
            case USER:
                User user = (User)sendable;
                message.append( protocol )
                        .append( ConnectionProtocol.MESSAGE_SPLIT_CHAR )
                        .append( user.mFirstName.trim() )
                        .append( ConnectionProtocol.MESSAGE_SPLIT_CHAR )
                        .append( user.mLastName.trim() )
                        .append( ConnectionProtocol.MESSAGE_SPLIT_CHAR )
                        .append( user.mEmail.trim() )
                        .append( ConnectionProtocol.MESSAGE_SPLIT_CHAR )
                        .append( user.mID )
                        .append( ConnectionProtocol.MESSAGE_SPLIT_CHAR )
                        .append( user.mIterations )
                        .append( ConnectionProtocol.MESSAGE_SPLIT_CHAR )
                        .append( Utility.passwordEncode( user.mSalt ) )
                        .append( ConnectionProtocol.MESSAGE_SPLIT_CHAR )
                        .append( Utility.passwordEncode( user.mHash ) )
                        .append( new String( ConnectionProtocol.END_OF_MESSAGE, StandardCharsets.UTF_8 ) );

                break;
            case EMAIL_TAKEN:
                message.append( protocol )
                        .append( ConnectionProtocol.MESSAGE_SPLIT_CHAR )
                        .append( ((Login)sendable).mEmail.trim() )
                        .append( new String( ConnectionProtocol.END_OF_MESSAGE, StandardCharsets.UTF_8 ) );

                break;
            case REPLY:
                Reply reply = (Reply)sendable;
                message.append( protocol )
                        .append( ConnectionProtocol.MESSAGE_SPLIT_CHAR )
                        .append( reply.mReplyCode )
                        .append( ConnectionProtocol.MESSAGE_SPLIT_CHAR )
                        .append( reply.mReplyCode.getCode() );

                for(byte[] m : reply.getMessages()){
                    message.append( ConnectionProtocol.MESSAGE_SPLIT_CHAR )
                            .append( new String( m, StandardCharsets.UTF_8 ) );
                }

                message.append( new String( ConnectionProtocol.END_OF_MESSAGE, StandardCharsets.UTF_8 ) );

                break;
            case LOCATION:
                Location location = (Location)sendable;
                message.append( protocol )
                        .append( ConnectionProtocol.MESSAGE_SPLIT_CHAR )
                        .append( location.mUserID )
                        .append( ConnectionProtocol.MESSAGE_SPLIT_CHAR )
                        .append( location.mLatitude )
                        .append( ConnectionProtocol.MESSAGE_SPLIT_CHAR )
                        .append( location.mLongitude )
                        .append( ConnectionProtocol.MESSAGE_SPLIT_CHAR )
                        .append( location.mAccuracy )
                        .append( ConnectionProtocol.MESSAGE_SPLIT_CHAR )
                        .append( location.mTimeRecorded )
                        .append( new String( ConnectionProtocol.END_OF_MESSAGE, StandardCharsets.UTF_8 ) );

                break;
            case CREATE_CIRCLE:
                Circle circle = (Circle)sendable;
                message.append( protocol )
                        .append( ConnectionProtocol.MESSAGE_SPLIT_CHAR )
                        .append( circle.mName )
                        .append( new String( ConnectionProtocol.END_OF_MESSAGE, StandardCharsets.UTF_8 ) );

                break;
            case ADD_CIRCLE_MEMBER:
                CircleMember circleMember = (CircleMember)sendable;
                message.append( protocol )
                        .append( ConnectionProtocol.MESSAGE_SPLIT_CHAR )
                        .append( circleMember.mGroupID )
                        .append( ConnectionProtocol.MESSAGE_SPLIT_CHAR )
                        .append( circleMember.mFriendEmail )
                        .append( new String( ConnectionProtocol.END_OF_MESSAGE, StandardCharsets.UTF_8 ) );

                break;
            case CONFIRM_JOIN_REQUEST:
            case DECLINE_JOIN_REQUEST:
            case DELETE_CIRCLE:
                Circle reqCircle = (Circle)sendable;
                message.append( protocol )
                        .append( ConnectionProtocol.MESSAGE_SPLIT_CHAR )
                        .append( reqCircle.mID )
                        .append( new String( ConnectionProtocol.END_OF_MESSAGE, StandardCharsets.UTF_8 ) );
                break;
            case REMOVE_CIRCLE_MEMBER:
                CircleMember cm = (CircleMember)sendable;
                message.append( protocol )
                        .append( ConnectionProtocol.MESSAGE_SPLIT_CHAR )
                        .append( cm.mGroupID )
                        .append( ConnectionProtocol.MESSAGE_SPLIT_CHAR )
                        .append( cm.mFriendID )
                        .append( new String( ConnectionProtocol.END_OF_MESSAGE, StandardCharsets.UTF_8 ) );
                break;
            default:
                message.append( protocol )
                        .append( new String( ConnectionProtocol.END_OF_MESSAGE, StandardCharsets.UTF_8 ) );

        }

        return ByteBuffer.wrap( message.toString().getBytes( StandardCharsets.UTF_8 ) );
    }

    public static Sendable build( ConnectionProtocol.Protocols protocol, String[] data ) throws IllegalArgumentException{

        Sendable sendable = null;

        if( data.length <= 1 )
            return null;

        switch( protocol ) {

            case LOGIN:
                if( data.length != 3 ){
                    throw new IllegalArgumentException();
                }

                sendable = new Login( data[1], data[2], null );
                break;
            case LOGIN_HASH:
                if( data.length != 3 ){
                    throw new IllegalArgumentException();
                }

                sendable = new Login( data[1], null, Utility.passwordDecode( data[2] ) );
                break;
            case REGISTER:
                if( data.length != 5 ){
                    throw new IllegalArgumentException();
                }

                sendable = new Register( data[1], data[2], data[3], data[4]);
                break;
            case USER:

                throw new IllegalArgumentException();

            case REPLY:
                Reply.ReplyCode replyCode = Reply.ReplyCode.valueOf( data[1] );
                if( replyCode.getCode() != Integer.parseInt( data[2] )){
                    throw new IllegalArgumentException();
                }

                Reply reply = new Reply();

                reply.mReplyCode = replyCode;

                for(int i = 3; i < data.length; i++){
                    reply.addMessage( data[i].getBytes( StandardCharsets.UTF_8 ) );
                }

                sendable = reply;

                break;
            case EMAIL_TAKEN:
                if( data.length != 2 ){
                    throw new IllegalArgumentException();
                }

                sendable = new Login( data[1], null, null );
                break;
            case LOCATION:
                if( data.length != 6 ){
                    throw new IllegalArgumentException();
                }

                sendable = new Location( Long.parseLong( data[1] ), Double.parseDouble( data[2] ), Double.parseDouble( data[3] ), Double.parseDouble( data[4] ), Long.parseLong( data[5] ) );
                break;
            case CREATE_CIRCLE:
                if( data.length != 2 ){
                    throw new IllegalArgumentException();
                }

                sendable = new Circle( 0, data[1] );
                break;
            case ADD_CIRCLE_MEMBER:
                if( data.length != 3 ){
                    throw new IllegalArgumentException();
                }

                sendable = new CircleMember( Long.parseLong( data[1] ), data[2] );
                break;
            case CONFIRM_JOIN_REQUEST:
            case DECLINE_JOIN_REQUEST:
            case DELETE_CIRCLE:
                if( data.length != 2 ){
                    throw new IllegalArgumentException();
                }

                sendable = new Circle( Long.parseLong( data[1] ), null );
                break;
            case REMOVE_CIRCLE_MEMBER:
                if( data.length != 3 ){
                    throw new IllegalArgumentException();
                }

                sendable = new CircleMember( Long.parseLong( data[1] ), Long.parseLong( data[2] ) );
                break;
            default:
                throw new IllegalArgumentException();

        }

        return sendable;
    }
}
