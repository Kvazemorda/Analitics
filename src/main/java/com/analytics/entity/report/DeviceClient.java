package com.analytics.entity.report;

public class DeviceClient {
    private String deviceName;
    private double deviceQuality;
    private double deviceConversation;

    public DeviceClient(String deviceName, double deviceQuality, double deviceConversation) {
        this.deviceName = deviceName;
        this.deviceQuality = deviceQuality;
        this.deviceConversation = deviceConversation;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public double getDeviceQuality() {
        return deviceQuality;
    }

    public void setDeviceQuality(double deviceQuality) {
        this.deviceQuality = deviceQuality;
    }

    public double getDeviceConversation() {
        return deviceConversation;
    }

    public void setDeviceConversation(double deviceConversation) {
        this.deviceConversation = deviceConversation;
    }
}
