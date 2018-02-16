/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package resources;

import java.io.Serializable;

/**
 *
 * @author Die Axt
 */
public class JournalEntry implements Serializable {
    public JournalEntry() {}
    public JournalEntry(String pID, int[] pDate,String txt,boolean pDeed) {
        this.id=pID;
        this.date=pDate;
        this.text=txt;
        this.deed=pDeed;
    }
    
    public void setID(String newID) {
        this.id=newID; 
    }
    public String getID() {
        return id;
    }
    
    public void setDate(int[] newDate) {
        this.date=newDate;
    }
    public int[] getDate() {
        return date;
    }
    
    public void setText(String newText) {
        this.text=newText;
    }
    public String getText() {
        return text;
    }
    
    public void setDeed(boolean b) {
        this.deed=b;
    }
    public boolean isDeed() {
        return deed;
    }
    
    public void setConnectedID(String newID) {
        this.connectedID=newID;
    }
    public String getConnectedID() {
        return connectedID;
    }
    
    String id;
    int[] date;
    String text;
    
    boolean deed;
    
    String connectedID
;}
