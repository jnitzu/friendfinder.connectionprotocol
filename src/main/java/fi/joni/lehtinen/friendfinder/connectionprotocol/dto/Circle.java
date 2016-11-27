package fi.joni.lehtinen.friendfinder.connectionprotocol.dto;

import fi.joni.lehtinen.friendfinder.connectionprotocol.Sendable;

public class Circle implements Sendable {

    public final long mID;
    public final String mName;

    public Circle( long mID, String mName ) {
        this.mID = mID;
        this.mName = mName;
    }
}
