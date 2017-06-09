package actions;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.interceptor.ApplicationAware;
import org.apache.struts2.interceptor.SessionAware;

import facade.ConnexionService;
import facade.ServiceImpl;

import java.util.Map;

/**
 * Created by root on 6/8/17.
 */
public class Disconnection  extends ActionSupport implements ApplicationAware, SessionAware
{
    private Map<String, Object> session;

    private ConnexionService service;

    @Override
    public void setApplication(Map<String, Object> map)
    {
        service = (ConnexionService) map.get("service");
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

    public Map<String, Object> getSession() {
        return session;
    }

    @Override
    public void setSession(Map<String, Object> session) {
        this.session = session;
    }
}
