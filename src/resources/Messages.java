/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package resources;

/**
 *
 * @author Die Axt
 */
public class Messages {
    public Messages() {
        
    }
    
    int mssgNr;
    String mssgName;
    String message;
    
    public void setNr(int newNr) {
        this.mssgNr=newNr;
    }
    public int getNr() {
        return mssgNr;
    }
    public void setName(String newName) {
        this.mssgName=newName;
    }
    public String getName() {
        return mssgName;
    }
    public void setMessage(String newMssg) {
        this.message=newMssg;
    }
    public String getMessage() {
        return message;
    }
}
