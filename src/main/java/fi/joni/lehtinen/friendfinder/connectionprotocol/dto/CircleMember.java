package fi.joni.lehtinen.friendfinder.connectionprotocol.dto;

import fi.joni.lehtinen.friendfinder.connectionprotocol.Sendable;

public class CircleMember implements Sendable {

    public final long mGroupID;
    public final long mFriendID;
    public final String mFriendEmail;

    public CircleMember( long mGroupID, String mFriendEmail ) {
        this.mGroupID = mGroupID;
        this.mFriendEmail = mFriendEmail;
        this.mFriendID = -1;
    }

    public CircleMember( long mGroupID, long mFriendID ) {
        this.mGroupID = mGroupID;
        this.mFriendID = mFriendID;
        this.mFriendEmail = null;
    }
}
