package actions;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.interceptor.ApplicationAware;
import org.apache.struts2.interceptor.SessionAware;

import facade.ServiceImpl;
import modele.personnes.Enfant;
import modele.objet.Cadeau;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Map;

/**
 * Created by root on 6/8/17.
 */
public class GreenHat extends ActionSupport implements ApplicationAware, SessionAware
{
    private String name;

    private String firstName;

    private String address;

    private String zipCode;

    private String city;

    private String day;

    private String month;

    private String year;

    private String toy1;

    private String toy2;

    private String toy3;

    private String toy4;

    private String toy5;

    private Collection<Enfant> kids;

    private String kidSelected;

    private Enfant kid;

    private Collection<Cadeau> gifts;

    private Collection<Cadeau> toys;

    private String giftSelected;

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

    public String search() throws Exception
    {
        kids = service.rechercherEnfantParNom(name);
        return SUCCESS;
    }

    public String addKid() throws Exception
    {
        session.put("toys", service.getCadeaux());
        return SUCCESS;
    }

    public String selectKid() throws Exception
    {
        kid = service.rechercherEnfantParId(Long.parseLong(kidSelected));
        gifts = kid.getDemandes();
        toys = service.getCadeaux();
        session.put("kidSelected", kidSelected);
        return SUCCESS;
    }

    public String updateGifts() throws Exception
    {
        String[] list = new String[5];
        list[0] = toy1;
        list[1] = toy2;
        list[2] = toy3;
        list[3] = toy4;
        list[4] = toy5;
        service.enregistrerDemandes((String) session.get("accessKey"), Long.parseLong((String) session.get("kidSelected")), list);
        return SUCCESS;
    }

    public String confirmationAddKid() throws Exception
    {
        String[] list = new String[5];
        list[0] = toy1;
        list[1] = toy2;
        list[2] = toy3;
        list[3] = toy4;
        list[4] = toy5;
        String accessKey = (String) session.get("accessKey");
        LocalDate date = LocalDate.of(Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(day));
        Long id = service.enregistrerNouvelEnfant(accessKey, name, firstName, address, Integer.parseInt(zipCode), city, date);
        service.enregistrerDemandes(accessKey, id, list);
        return SUCCESS;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getToy1() {
        return toy1;
    }

    public void setToy1(String toy1) {
        this.toy1 = toy1;
    }

    public String getToy2() {
        return toy2;
    }

    public void setToy2(String toy2) {
        this.toy2 = toy2;
    }

    public String getToy3() {
        return toy3;
    }

    public void setToy3(String toy3) {
        this.toy3 = toy3;
    }

    public String getToy4() {
        return toy4;
    }

    public void setToy4(String toy4) {
        this.toy4 = toy4;
    }

    public String getToy5() {
        return toy5;
    }

    public void setToy5(String toy5) {
        this.toy5 = toy5;
    }

    public Collection<Enfant> getKids() {
        return kids;
    }

    public void setKids(Collection<Enfant> kids) {
        this.kids = kids;
    }

    public Enfant getKid() {
        return kid;
    }

    public void setKid(Enfant kid) {
        this.kid = kid;
    }

    public String getKidSelected() {
        return kidSelected;
    }

    public void setKidSelected(String kidSelected) {
        this.kidSelected = kidSelected;
    }

    public Collection<Cadeau> getGifts() {
        return gifts;
    }

    public void setGifts(Collection<Cadeau> gifts) {
        this.gifts = gifts;
    }

    public Collection<Cadeau> getToys() {
        return toys;
    }

    public void setToys(Collection<Cadeau> toys) {
        this.toys = toys;
    }

    public String getGiftSelected() {
        return giftSelected;
    }

    public void setGiftSelected(String giftSelected) {
        this.giftSelected = giftSelected;
    }

    public Map<String, Object> getSession() {
        return session;
    }

    @Override
    public void setSession(Map<String, Object> session) {
        this.session = session;
    }
}
