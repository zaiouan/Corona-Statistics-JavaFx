/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package applicationfxcovid;

import java.util.Date;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author Zaiouane
 */
public class Covid {
    private String id;
    private Date date;
    private String totalCas;
    private String totalDeces;
    private String totalVac;
    private String totalTest;
    private String newCas;
    private String newDeces;
    private String newVac;
    private String newTest;
    private String pays;

    public Covid() {
    }

    public Covid(String id, Date date, String totalCas, String totalDeces, String totalVac, String totalTest, String newCas, String newDeces, String newVac, String newTest, String pays) {
        this.id = id;
        this.date = date;
        this.totalCas = totalCas;
        this.totalDeces = totalDeces;
        this.totalVac = totalVac;
        this.totalTest = totalTest;
        this.newCas = newCas;
        this.newDeces = newDeces;
        this.newVac = newVac;
        this.newTest = newTest;
        this.pays = pays;
    }

    Covid(String totalCas, String totalDeces, String totalVac, String pays) {
        this.totalCas = totalCas;
        this.totalDeces = totalDeces;
        this.totalVac = totalVac;
        this.pays = pays;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setTotalCas(String totalCas) {
        this.totalCas = totalCas;
    }

    public void setTotalDeces(String totalDeces) {
        this.totalDeces = totalDeces;
    }

    public void setTotalVac(String totalVac) {
        this.totalVac = totalVac;
    }

    public void setTotalTest(String totalTest) {
        this.totalTest = totalTest;
    }

    public void setNewCas(String newCas) {
        this.newCas = newCas;
    }

    public void setNewDeces(String newDeces) {
        this.newDeces = newDeces;
    }

    public void setNewVac(String newVac) {
        this.newVac = newVac;
    }

    public void setNewTest(String newTest) {
        this.newTest = newTest;
    }

    public void setPays(String pays) {
        this.pays = pays;
    }

    public String getId() {
        return id;
    }

    public String getPays() {
        return pays;
    }

    public String getTotalCas() {
        return totalCas;
    }

    public String getTotalDeces() {
        return totalDeces;
    }

    public String getTotalVac() {
        return totalVac;
    }

    public String getTotalTest() {
        return totalTest;
    }

    public String getNewCas() {
        return newCas;
    }

    public String getNewDeces() {
        return newDeces;
    }

    public String getNewVac() {
        return newVac;
    }

    public String getNewTest() {
        return newTest;
    }
    
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    public static List<Covid>Afficher(){
        SessionFactory sessionHiber  =  NewHibernateUtil.getSessionFactory();
        Session maSession = sessionHiber.openSession();
        Transaction tx = maSession.beginTransaction();
        Query req=maSession.createQuery ("from Covid where date = (select max(date) from Covid)");
        return req.list();
    }
    public static List AfficherPays(){
        SessionFactory sessionHiber  =  NewHibernateUtil.getSessionFactory();
        Session maSession = sessionHiber.openSession();
        Transaction tx = maSession.beginTransaction();
        List l=maSession.createQuery ("select distinct pays from Covid order by pays").list();
        return l;
    }
    
    public static List<Covid> rechercher(String pays){
           SessionFactory sessionHiber  =  NewHibernateUtil.getSessionFactory();
        Session maSession = sessionHiber.openSession();
        Transaction tx = maSession.beginTransaction();
        Query req=maSession.createQuery ("from Covid where date = (select max(date) from Covid) and pays like :p ");
        req.setParameter("p", pays);
        return req.list();
    }
    
    public static List<Covid> rechercher(String pays ,Date date){
        SessionFactory sessionHiber  =  NewHibernateUtil.getSessionFactory();
        Session maSession = sessionHiber.openSession();
        Transaction tx = maSession.beginTransaction();
        Query req=maSession.createQuery ("from Covid where pays like :p and date = :d");
        req.setParameter("p", pays);
        req.setParameter("d", date);
        return req.list();
    }
    
    
    public static List getTotalCasPerMounth(String pays){
        SessionFactory sessionHiber  =  NewHibernateUtil.getSessionFactory();
        Session maSession = sessionHiber.openSession();
        Transaction tx = maSession.beginTransaction();
        Query req=maSession.createQuery ("select SUM(newCas) , MONTH(date) , year(date)  from Covid where location like :p group by year(date),MONTH(date)");
        req.setParameter("p", pays);
        return req.list(); 
    }
    public static List getTotalDecesPerMounth(String pays){
        SessionFactory sessionHiber  =  NewHibernateUtil.getSessionFactory();
        Session maSession = sessionHiber.openSession();
        Transaction tx = maSession.beginTransaction();
        Query req=maSession.createQuery ("select SUM(newDeces) , MONTH(date) , year(date)  from Covid where location like :p group by year(date),MONTH(date)");
        req.setParameter("p", pays);
        return req.list(); 
    }
    public static List getTotalTestPerMounth(String pays){
        SessionFactory sessionHiber  =  NewHibernateUtil.getSessionFactory();
        Session maSession = sessionHiber.openSession();
        Transaction tx = maSession.beginTransaction();
        Query req=maSession.createQuery ("select SUM(newTest) , MONTH(date) , year(date)  from Covid where location like :p group by year(date),MONTH(date)");
        req.setParameter("p", pays);
        return req.list(); 
    }
     public static List getTotalVacPerMounth(String pays){
        SessionFactory sessionHiber  =  NewHibernateUtil.getSessionFactory();
        Session maSession = sessionHiber.openSession();
        Transaction tx = maSession.beginTransaction();
        Query req=maSession.createQuery ("select SUM(newVac) , MONTH(date) , year(date)  from Covid where location like :p group by year(date),MONTH(date)");
        req.setParameter("p", pays);
        return req.list(); 
    }
    @Override
    public String toString() {
        return "Covid{" + "id=" + id + ", totalCas=" + totalCas + ", totalDeces=" + totalDeces + ", totalVac=" + totalVac + '}';
    }
    
}
