package br.com.teste.pamcary.handler;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class RestErrorInfo {

    public final String detail;
    public final String message;
    public final String httpStatus;

    public RestErrorInfo(Exception ex, String detail, String httpStatus) {
        this.message = ex.getLocalizedMessage();
        this.detail = detail;
        this.httpStatus = httpStatus;
    }
}
