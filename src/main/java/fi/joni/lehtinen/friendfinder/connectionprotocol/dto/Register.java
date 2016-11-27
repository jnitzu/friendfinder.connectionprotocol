package fi.joni.lehtinen.friendfinder.connectionprotocol.dto;

import fi.joni.lehtinen.friendfinder.connectionprotocol.Sendable;

public class Register extends Login implements Sendable {

    public final String mFirstName;
    public final String mLastName;

    public Register( String mEmail, String mFirstName, String mLastName, String mPassword ) {
        super( mEmail, mPassword, null );
        this.mFirstName = mFirstName;
        this.mLastName = mLastName;
    }

}