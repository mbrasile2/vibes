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

public class advertisementBean {
public int advertisementId;
public int employeeId;
public String type;
public String itemName;
public String date;
public String company;
public String content;
public double price;
public int unitsAvailable;
public void setAdvertisementId(int id){
    advertisementId = id;
}
public void setEmployeeId(int id){
    employeeId = id;
}
public void setType(String t){
    type = t;
}
public void setItemName(String i){
    itemName = i;
}
public void setDate(String d){
    date = d;
}
public void setCompany(String c){
    company = c;
}
public void setContent(String co){
    content = co;
}
public void setPrice(double p){
    price = p;
}
public void setUnit(int u){
    unitsAvailable = u;
}
public int getAdvertisementId() {
        return advertisementId;
    }
public int getEmployeeId(){
    return employeeId;
}
public String getType(){
    return type;
}
   
}
