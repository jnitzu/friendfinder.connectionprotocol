package fi.joni.lehtinen.friendfinder.connectionprotocol.dto;

import fi.joni.lehtinen.friendfinder.connectionprotocol.Sendable;


public class Location implements Sendable {

    public final long mUserID;
    public final double mLatitude;
    public final double mLongitude;
    public final double mAccuracy;
    public final long mTimeRecorded;

    public Location( long mUserID, double mLatitude, double mLongitude, double mAccuracy, long mTimeRecorded ) {
        this.mUserID = mUserID;
        this.mLatitude = mLatitude;
        this.mLongitude = mLongitude;
        this.mAccuracy = mAccuracy;
        this.mTimeRecorded = mTimeRecorded;
    }
}
