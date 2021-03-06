package com.cooksys.ftd.assignments.concurrency.model.message;

import javax.xml.bind.annotation.*;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Response {

    @XmlValue
    private String data;

    @XmlAttribute
    private RequestType type;

    @XmlAttribute
    private boolean successful;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public RequestType getType() {
        return type;
    }

    public void setType(RequestType type) {
        this.type = type;
    }

    public boolean isSuccessful() {
        return successful;
    }

    public void setSuccessful(boolean successful) {
        this.successful = successful;
    }

	@Override
	public String toString() {
		return "Response [data=" + data + ", type=" + type + ", successful=" + successful + "]";
	}
    
    
}
