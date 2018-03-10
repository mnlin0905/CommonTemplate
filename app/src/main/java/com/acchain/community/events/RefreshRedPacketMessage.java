package com.acchain.community.events;

/**
 * Created by rsp on 2018/3/6.
 */

public class RefreshRedPacketMessage {
    private String envelopesId;

    public String getEnvelopesId() {
        return envelopesId;
    }

    public void setEnvelopesId(String envelopesId) {
        this.envelopesId = envelopesId;
    }

    public RefreshRedPacketMessage(String envelopesId) {
        this.envelopesId = envelopesId;
    }
}
