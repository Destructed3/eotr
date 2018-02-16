/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package generators.name;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author Die Axt
 */
public class RegionNameGenerator {
    public RegionNameGenerator() {
        ng = new NameGenerator();
        r = new Random();
        loadLists();
    }
    
    public String[] generateName() {
        if(r.nextBoolean()) {
            String[] s={"The "+checkBeginn_The(),getFlavour()};
            return s;
        } else {
            String[] s={checkBeginn_Name(),getFlavour()};
            return s;
        }
    }
    
    private String checkBeginn_The() {
        if(r.nextBoolean()) {
            return lAttribute.get(r.nextInt(lAttribute.size()-1))+" "
                    +checkType();
        } else {
            return checkType();
        }
    }
    private String checkBeginn_Name() {
        int rdm = r.nextInt(2);
        switch(rdm) {
            case 0:
                return checkName_good();
            case 1:
                return checkName_evil();
            default:
                return checkOf();
        }
    }
    private String checkType() {
        hasType=true;
        if(r.nextBoolean()) {
            return lType.get(r.nextInt(lType.size()-1));
        } else {
            return lType.get(r.nextInt(lType.size()-1))+checkOf();
        }
    }
    private String checkOf() {
        if(r.nextBoolean()) {
            return checkName();
        } else {
            return "";
        }
    }
    
    
    private String checkName() {
        if(r.nextBoolean()) {
            return " of "+checkName_good();
        } else {
            return " of "+checkName_evil();
        }
    }
    private String checkName_good() {
        int rdm = r.nextInt(2);
        switch(rdm) {
            case 0:
                return goodName_clean()+"'s "+checkBeginn_The();
            case 1:
                return goodName();
            default:
                return goodName_clean()+checkOf();
        }
    }
    private String checkName_evil() {
        int rdm = r.nextInt(2);
        switch(rdm) {
            case 0:
                return evilName_clean()+"'s "+checkBeginn_The();
            case 1:
                return evilName();
            default:
                return evilName_clean()+checkOf();
        }
    }
    private String goodName() {
        String name=ng.randomName(false)+lSy2.get(r.nextInt(lSy2.size()-1));
        names.add(name);
        return name;
    }
    private String goodName_clean() {
        String name=ng.randomName(false);
        names.add(name);
        return name;
    }
    private String evilName() {
        String name=ng.randomName(true)+lSy2.get(r.nextInt(lSy2.size()-1));
        names.add(name);
        return name;
    }
    private String evilName_clean() {
        String name=ng.randomName(true);
        names.add(name);
        return name;
    }
    
    private String getFlavour() {
        if(decideFlavourType()) {
            return "Named after ";
        } else {
            return "Happening";
        }
    }
    private boolean decideFlavourType() {
        int t1=r.nextInt(2);
        int t2=r.nextInt(2);
        if(hasAttribute) {
            t2++;
        }
        if(hasType) {
            t2++;
        }
        if(hasOf) {
            t1++;
        }
        if(hasName) {
            t1++;
        } else {
            t2++;
        }
        if(t1<t2) {
            return false;
        } else if(t2<t1) {
            return true;
        } else {
            return r.nextBoolean();
        }
    }
    
    private void loadLists() {
        if(lType==null) {
            lType = new ArrayList();
            String s;
            try (BufferedReader br = new BufferedReader(new FileReader(".\\Data\\NameGen\\Region_Type.txt"))) {
                while((s=br.readLine()) != null){
                    lType.add(s);
                }
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("error "+e.getMessage());
            }    
        }
        if(lAttribute==null) {
            lAttribute = new ArrayList();
            String s;
            try (BufferedReader br = new BufferedReader(new FileReader(".\\Data\\NameGen\\Region_Attributes.txt"))) {
                while((s=br.readLine()) != null){
                    lAttribute.add(s);
                }
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("error "+e.getMessage());
            } 
        }
        if(lAttribute==null) {
            lAttribute = new ArrayList();
            String s;
            try (BufferedReader br = new BufferedReader(new FileReader(".\\Data\\Region_Attributes.txt"))) {
                while((s=br.readLine()) != null){
                    lAttribute.add(s);
                }
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("error "+e.getMessage());
            } 
        }
        if(lSy2==null) {
            lSy2 = new ArrayList();
            String s;
            try (BufferedReader br = new BufferedReader(new FileReader(".\\Data\\NameGen\\RegionSy2.txt"))) {
                while((s=br.readLine()) != null){
                    lSy2.add(s);
                }
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("error "+e.getMessage());
            } 
        }
    }
    
    List<String> lType=null;
    List<String> lAttribute=null;
    List<String> lSy1=null;
    List<String> lSy2=null;
    
    boolean hasAttribute=false;
    boolean hasType=false;
    boolean hasOf = false;
    boolean hasName=false;
    
    NameGenerator ng;
    List<String> names=new ArrayList();
    String flavour;
    Random r;
}
