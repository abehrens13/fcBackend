package de.openaqua.fcbackend;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

@Component
public class SerialGenerator {
  private static final Log LOG = LogFactory.getLog(SerialGenerator.class);
  private int number;
  private String prefix;
  public static final int DEFAULT_STARTVALUE = 100_000;

  public SerialGenerator() {
    super();
    this.prefix = "DEF";
    this.number = DEFAULT_STARTVALUE;
  }

  public SerialGenerator(String prefix, int number) {
    super();
    this.prefix = prefix;
    this.number = number;
    LOG.debug("SerialGenerator [number=" + number + ", prefix=" + prefix + "] constructed");

  }

  @Override
  public String toString() {
    return "SerialGenerator [number=" + number + ", prefix=" + prefix + "]";
  }

  public String getNext() {
    ++number;
    String result = prefix + number;
    LOG.info("Produced Serial " + result);
    return result;
  }

  public int getNumber() {
    return number;
  }

  public void setNumber(int number) {
    LOG.debug("setNumber=" + number);
    this.number = number;
  }

  public String getPrefix() {
    return prefix;
  }

  public void setPrefix(String prefix) {
    LOG.debug("setPrefix=" + prefix);
    this.prefix = prefix;
  }

}
