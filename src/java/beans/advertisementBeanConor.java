/*
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.util.Date;



/**
 *
 * @author Conor
 */

public class advertisementBeanConor {
public int advertisementId;
public int employeeId;
public String type;
public String itemName;
public Date date;
public String company;
public String content;
public double price;
public int unitsAvailable;
public advertisementBeanConor(int aId, int eId, String ty, String it, Date da, String com, String con, double p, int u){
    advertisementId = aId;
    employeeId = eId;
    type = ty;
    itemName = it;
    date = da;
    company = com;
    content = con;
    price = p;
    unitsAvailable = u;
}
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
public void setDate(Date d){
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
public String getItemName() {
    return itemName;
}
public Date getDate() {
    return date;
}
public String getCompany() {
    return company;
}
public String getContent() {
    return content;
}
public double getPrice() {
    return price;
}
public int getUnitsAvailable(){
    return unitsAvailable;
}
}
