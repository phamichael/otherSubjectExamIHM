package actions;

import com.opensymphony.xwork2.ActionSupport;
import facade.ServiceImpl;
import org.apache.struts2.interceptor.ApplicationAware;
import org.apache.struts2.interceptor.SessionAware;

import java.util.Map;

/**
 * Created by root on 6/8/17.
 */
public class Menu extends ActionSupport implements ApplicationAware, SessionAware
{

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
        return SUCCESS;
    }

    public String santaClaus() throws Exception
    {
        session.put("kidsWithoutAnwser", service.listeEnfantsSansReponse((String) session.get("accessKey")));
        session.put("kidsWithAnwser", service.getListeDesCommandesPretesPourTraitement());
        return SUCCESS;
    }

    public String greenHat() throws Exception
    {
        return SUCCESS;
    }

    public String redHat() throws Exception
    {
        return SUCCESS;
    }

    public Map<String, Object> getSession() {
        return session;
    }

    @Override
    public void setSession(Map<String, Object> session) {
        this.session = session;
    }
}
