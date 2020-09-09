package com.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

public class GlobalData {

    @Getter
    String driver;
    @Getter
    String report;
    @Getter
    String url;
    @Getter
    String os;
    @Getter
    String appPath;
    @Getter
    String appPackage;
    @Getter
    String appActivity;
    @Getter
    String platform;
    @Getter
    String driverPath;

    public GlobalData(
                      @JsonProperty("driver")String driver,
                      @JsonProperty("report")String report,
                      @JsonProperty("url") String url,
                      @JsonProperty("os") String os,
                      @JsonProperty("appPath") String appPath,
                      @JsonProperty("appPackage") String appPackage,
                      @JsonProperty("appActivity") String appActivity,
                      @JsonProperty("platform") String platform,
                      @JsonProperty("driverPath")String driverPath){
        this.driver = driver;
        this.report = report;
        this.url = url;
        this.os = os;
        this.appPath = appPath;
        this.appPackage = appPackage;
        this.appActivity = appActivity;
        this.platform = platform;
        this.driverPath = driverPath;
    }

}
