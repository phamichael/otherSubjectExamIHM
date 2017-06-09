package actions;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.interceptor.ApplicationAware;
import org.apache.struts2.interceptor.SessionAware;

import facade.ConnexionService;
import facade.ServiceImpl;
import facade.erreurs.CoupleUtilisateurMDPInconnuException;
import modele.personnes.Lutin;

import java.util.Map;

/**
 * Created by root on 6/8/17.
 */
public class Connection extends ActionSupport implements ApplicationAware, SessionAware
{

    private String login;

    private String password;

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
        try {
            String accessKey = service.connexion(login, password);
            session.put("accessKey", accessKey);
            session.put("login", login);
            session.put("password", password);
            Lutin lutin =  service.getLutinConnecte(accessKey);
            session.put("roles", lutin.getRoles());
        } catch( CoupleUtilisateurMDPInconnuException e)
        {
            return ERROR;
        }
        return SUCCESS;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Map<String, Object> getSession() {
        return session;
    }

    @Override
    public void setSession(Map<String, Object> session) {
        this.session = session;
    }
}
