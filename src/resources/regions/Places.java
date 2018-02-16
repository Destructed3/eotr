/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package resources.regions;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Die Axt
 */
public abstract class Places implements Serializable {
    public Places() {
        
    }
    
    public void setID(String newID) {
        this.id=newID;
    }
    public String getID() {
        return id;
    }
    public void setName(String newName) {
        this.name=newName;
    }
    public String getName() {
        return name;
    }
    public void setType(int newType) {
        this.type=newType;
    }
    public int getType() {
        return type;
    }
    public void addJournalEntry(String entry) {
        journal.add(entry);
    }
    
    String id;
    String name;
    int type;
    List<String> journal;
    
    
}
