package com.analytics.report.entity.report;

public class DeviceClient {
    private String deviceName;
    private double deviceQuality;
    private double deviceConversation;
    private double deviceBounceRate;
    

    public DeviceClient(String deviceName, double deviceQuality, double deviceConversation, double deviceBounceRate) {
        this.deviceName = deviceName;
        this.deviceQuality = deviceQuality;
        this.deviceConversation = deviceConversation;
        this.deviceBounceRate = deviceBounceRate;
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

    public double getDeviceBounceRate() {
        return deviceBounceRate;
    }

    public void setDeviceBounceRate(double deviceBounceRate) {
        this.deviceBounceRate = deviceBounceRate;
    }
}
