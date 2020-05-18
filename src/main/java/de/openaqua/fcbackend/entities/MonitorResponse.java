package de.openaqua.fcbackend.entities;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MonitorResponse {
  private static final Logger LOG = LoggerFactory.getLogger(MonitorResponse.class);
  private OffsetDateTime currentTime;
  private String hostname;
  private InetAddress inetAddress;
  private MonitorStatus statusSystem;
  private MonitorStatus statusDatabase;
  private MonitorStatus statusRedis;

  public MonitorResponse() {
    super();
    currentTime = OffsetDateTime.now(ZoneOffset.UTC);
    try {
      inetAddress = InetAddress.getLocalHost();
      hostname = inetAddress.getHostName();
    } catch (UnknownHostException e) {
      LOG.error("cannot resolve ip-address: {}", e.getLocalizedMessage());
      LOG.info("exception: ", e);
    }
    statusSystem = MonitorStatus.OK;
    statusDatabase = MonitorStatus.UNKNOWN;
    statusRedis = MonitorStatus.UNKNOWN;
  }

  public OffsetDateTime getCurrentTime() {
    return currentTime;
  }

  public void setCurrentTime(OffsetDateTime currentTime) {
    this.currentTime = currentTime;
  }

  public String getHostname() {
    return hostname;
  }

  public void setHostname(String hostname) {
    this.hostname = hostname;
  }

  public InetAddress getInetAddress() {
    return inetAddress;
  }

  public void setInetAddress(InetAddress inetAddress) {
    this.inetAddress = inetAddress;
  }

  public MonitorStatus getStatusSystem() {
    return statusSystem;
  }

  public void setStatusSystem(MonitorStatus statusSystem) {
    this.statusSystem = statusSystem;
  }

  public MonitorStatus getStatusDatabase() {
    return statusDatabase;
  }

  public void setStatusDatabase(MonitorStatus statusDatabase) {
    this.statusDatabase = statusDatabase;
  }

  public MonitorStatus getStatusRedis() {
    return statusRedis;
  }

  public void setStatusRedis(MonitorStatus statusRedis) {
    this.statusRedis = statusRedis;
  }

  @Override
  public String toString() {
    return "MonitorResponse [currentTime=" + currentTime + ", hostname=" + hostname + ", inetAddress=" + inetAddress
        + ", statusSystem=" + statusSystem + ", statusDatabase=" + statusDatabase + ", statusRedis=" + statusRedis
        + "]";
  }

}
