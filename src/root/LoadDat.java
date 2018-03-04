/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package root;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import resources.Inhabitants.InhTea;
import generators.strings.Objects_job;

/**
 *
 * @author Die Axt
 */
public class LoadDat {
    public LoadDat() {
    }
    
    public String getLoadLabel(String save) {
        try {
            ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream(".\\Saves\\save"+save+"\\teacher.sav")));
            l = (List<InhTea>)ois.readObject();
            ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream(".\\Saves\\save"+save+"\\resources.sav")));
            res = (int[])ois.readObject();
            label = "<html><center>"+l.get(0).getName()+"<br>Year "+res[0]+"</html>";
        } catch (IOException | ClassNotFoundException ex) {
            //Logger.getLogger(LoadDat.class.getName()).log(Level.SEVERE, null, ex);
            label = "No save here";
        }
        return label;
    }
    
    public Object getObject (String datName) { 
        Object obj = null;
        try {
            ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream(datName)));
            obj = ois.readObject();
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(LoadDat.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if(ois!=null) {
                    ois.close();
                }
            } catch (IOException ex) {
            }
        }
        return obj;
    }
    
    public void loadTxtIntoVec(Vector vec, String datName) {
        //Output Syllable 1
        try {
            try (BufferedReader br = new BufferedReader(new FileReader(datName))) {
                while((s=br.readLine()) != null){
                    vec.add(s);
                }
            }
        } catch (Exception e) {
            System.out.println("error "+e.getMessage());
        }
    }
    
    public List<String> loadTxtIntoList(String path) {
        List<String> lString = new ArrayList();
        String s;      
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            while((s=br.readLine()) != null){
                lString.add(s);
            }
        } catch (Exception e) {
            System.out.println("error "+e.getMessage());
        }
        return lString;
    }
    
    public List<String> loadTextIntoList_objectsJob(String topic, int lvl) {
        List<String> lString = new ArrayList();
        boolean start=false;
        boolean end=false;
        String s;      
        try (BufferedReader br = new BufferedReader(new FileReader(".\\Data\\JournalGen\\Objects_job\\Objects_"+topic+".txt"))) {
            while((s=br.readLine()) != null && !end){
                if(s.equals(getLvl_start(lvl))) {
                    start=true;
                }
                if(s.equals(getLvl_end(lvl))) {
                    end=true;
                }
                if(start && !end && !s.equals(getLvl_start(lvl))) {
                    lString.add(s);
                }
            }
        } catch (Exception e) {
            System.out.println("error "+e.getMessage());
        }
        return lString;
    }
    private String getLvl_start(int lvl) {
        switch(lvl) {
            default:
                return "_startLow";
            case Objects_job.MEDIUM:
                return "_startMedium";
            case Objects_job.HIGH:
                return "_startHigh";
        }
    }
    private String getLvl_end(int lvl) {
        switch(lvl) {
            default:
                return "_endLow";
            case Objects_job.MEDIUM:
                return "_endMedium";
            case Objects_job.HIGH:
                return "_endHigh";
        }
    }
    
    public Object getObject1 (String datName) throws IOException { 
        Object obj = null;
        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream(datName)));
            obj = ois.readObject();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(LoadDat.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if(ois!=null) {
                    ois.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return obj;
    }
    
    String s;
    String label;
    List<InhTea> l;
    int[] res;
    ObjectInputStream ois=null;
}
