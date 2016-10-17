package com.cooksys.ftd.assignments.socket.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

@XmlAccessorType(XmlAccessType.FIELD)
public class LocalConfig {
    @XmlAttribute
    private int port;

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

	@Override
	public String toString() {
		return "LocalConfig [port=" + port + "]";
	}
}
