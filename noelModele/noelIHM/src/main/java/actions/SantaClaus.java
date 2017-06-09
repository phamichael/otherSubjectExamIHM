package actions;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.interceptor.ApplicationAware;
import org.apache.struts2.interceptor.SessionAware;

import facade.ServiceImpl;

import java.util.Map;

/**
 * Created by root on 6/10/17.
 */
public class SantaClaus extends ActionSupport implements ApplicationAware, SessionAware
{

    private String kidSelected;

    private String choice;

    private Map<String, Object> session;

    private ServiceImpl service;

    @Override
    public void setApplication(Map<String, Object> map)
    {
        service = (ServiceImpl) map.get("service");
        if(service == null)
        {
            service = new ServiceImpl();
            map.put("service", service);
        }
    }

    @Override
    public String execute() throws Exception
    {
        service.deconnexion((String) session.get("accessKey"));
        return SUCCESS;
    }

    public String takeDecision() throws Exception
    {
        session.put("kidSelected", kidSelected);
        return SUCCESS;
    }

    public String validateDecision() throws Exception
    {
        String accessKey = (String) session.get("accessKey");
        if(choice.equals("goodQuestion"))
            return SUCCESS;
        Boolean wise = choice.equals("wise");
        service.setEnfantSage(accessKey, Long.parseLong((String) session.get("kidSelected")), wise);
        session.put("kidsWithoutAnwser", service.listeEnfantsSansReponse(accessKey));
        session.put("kidsWithAnwser", service.getListeDesCommandesPretesPourTraitement());
        return SUCCESS;
    }

    public String getKidSelected() {
        return kidSelected;
    }

    public void setKidSelected(String kidSelected) {
        this.kidSelected = kidSelected;
    }

    public String getChoice() {
        return choice;
    }

    public void setChoice(String choice) {
        this.choice = choice;
    }

    public Map<String, Object> getSession() {
        return session;
    }

    @Override
    public void setSession(Map<String, Object> session) {
        this.session = session;
    }

}

