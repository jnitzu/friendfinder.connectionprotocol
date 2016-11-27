package fi.joni.lehtinen.friendfinder.connectionprotocol.dto;

import fi.joni.lehtinen.friendfinder.connectionprotocol.Sendable;

public class User extends Register implements Sendable {

    public final long mID;
    public final int mIterations;
    public final byte[] mSalt;
    public final byte[] mHash;

    public User( String mEmail, String mFirstName, String mLastName, long ID, int iterations, byte[] salt, byte[] hash ) {
        super( mEmail, mFirstName, mLastName, "" );
        mID = ID;
        mIterations = iterations;
        mSalt = salt;
        mHash = hash;
    }
}
