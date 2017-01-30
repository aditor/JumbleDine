package com.adi.jumbledine;

import java.util.Date;
import java.util.UUID;

/**
 * Created by Adi on 2016-12-30.
 */
public class Choice {
    private UUID mId;
    private String mType;
    private String mTitle;
    private String mAddress;
    private String mDistance;

    private boolean mVisited;
    //private List<Contacts> contacts;


    public Choice() {
        // Generate unique identifier
        this(UUID.randomUUID());
    }

    public Choice(UUID id) {
        mId = id;
        mVisited = false;
    }

    public UUID getId() {
        return mId;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }


    public boolean isVisited() {
        return mVisited;
    }

    public void setVisited(boolean visited) {
        mVisited = visited;
    }

    public String getDistance() {
        return mDistance;
    }

    public void setDistance(String distance) {
        mDistance = distance;
    }

    public String getAddress() {
        return mAddress;
    }

    public void setAddress(String address) {
        mAddress = address;
    }

    public String getType() {
        return mType;
    }

    public void setType(String type) {
        mType = type;
    }
}
