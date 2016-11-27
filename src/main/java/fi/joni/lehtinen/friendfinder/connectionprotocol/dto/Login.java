package fi.joni.lehtinen.friendfinder.connectionprotocol.dto;

import fi.joni.lehtinen.friendfinder.connectionprotocol.Sendable;

public class Login implements Sendable {

    public final String mEmail;
    public final String mPassword;
    public final byte[] mHash;

    public Login( String email, String password, byte[] hash  ) {
        mEmail = email;
        mPassword = password;
        mHash = hash;
    }
}