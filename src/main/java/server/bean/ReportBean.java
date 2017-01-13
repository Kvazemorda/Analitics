package server.bean;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

@LocalBean
@Stateless
public class ReportBean {

    public ReportBean() {
    }

    public String getReport(){
        String text = "/////////////////////////////////////Report is got";
        return text;
    }
}
