/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

/**
 *
 * @author Conor
 */
public class mailingListBean {
    public String firstName;
    public String lastName;
    public String email;
    public mailingListBean(String f, String l, String e){
        firstName = f;
        lastName = l;
        email = e;
    }
    public void setFirstName(String fN){
        firstName = fN;
    }
    public void setLastName(String lN){
        lastName = lN;
    }
    public void setEmail(String em){
        email = em;
    }
    public String getFirstName(){
        return firstName;
    }
    public String getLastName(){
        return lastName;
    }
    public String getEmail(){
        return email;
    }
}
