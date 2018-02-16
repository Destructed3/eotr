/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package generators.strings;

import java.util.List;
import java.util.Random;

/**
 *
 * @author Die Axt
 */
public class Objects_job {
    public Objects_job() {
        ld = new root.LoadDat();
        r = new Random();
    }
    
    public String get_JobObject(String venue, int lvl) {
        this.lvl=lvl;
        switch(venue) {
            default:
                return get_Obj_town();
            case "Homestead":
                return get_Obj_homestead();
            case "Trade Post":
                return get_Obj_tradeP();
            case "Castle":
                return get_Obj_castle();
            case "Tavern":
                return get_Obj_tavern();
            case "Monastery":
                return get_obj_mon();
        }
    }
    
    public String get_Obj_town() {
        try {
            int rdm = r.nextInt(get_List_town().size()-1);
            return objects_town.get(rdm);
        }catch(Exception e) {
            return "TOWN_LVL_"+lvl+"__NO_STRING_AVAILABLE";
        }
    }
    private List<String> get_List_town() {
        objects_town = ld.loadTextIntoList_objectsJob("town", lvl);
        return objects_town;
    }
    
    public String get_Obj_homestead() {
        try {
            int rdm = r.nextInt(getL_Obj_homestead().size()-1);
            return objects_homestead.get(rdm);
        } catch(Exception e) {
            return "HOMESTEAD_LVL"+lvl+"__NO_STRINGS_AVAILABLE";
        }
    }
    private List<String> getL_Obj_homestead() {
        return objects_homestead=ld.loadTextIntoList_objectsJob("homestead", lvl);
    }
    
    private String get_Obj_tradeP() {
        try {
            int rdm = r.nextInt(get_List_tradePost().size()-1);
            return objects_tradePost.get(rdm);
        } catch(Exception e) {
            return "TRADE_POST_LVL"+lvl+"__NO_STRINGS_AVAILABLE";
        }
    }
    private List<String> get_List_tradePost() {
        this.objects_tradePost=ld.loadTextIntoList_objectsJob("tradePost", lvl);
        return objects_tradePost;
    }
    
    private String get_Obj_castle() {
        try {
            int rdm=r.nextInt(get_List_castle().size()-1);
            return this.objects_castle.get(rdm);
        }catch(Exception e) {
            return "CASTLE_LVL_"+lvl+"__NO_STRING_AVAILABLE";
        }
    }
    private List<String> get_List_castle() {
        this.objects_castle=ld.loadTextIntoList_objectsJob("castle", lvl);
        return objects_castle;
    }
    
    private String get_Obj_tavern() {
        try {
            int rdm=r.nextInt(get_List_tavern().size()-1);
            return objects_tavern.get(rdm);
        } catch(Exception e) {
            return "TAVER_LVL_"+lvl+"__NO_STRING_AVAILABLE";
        }
    }
    private List<String> get_List_tavern() {
        this.objects_tavern=ld.loadTextIntoList_objectsJob("tavern", lvl);
        return objects_tavern;
    }
    
    private String get_obj_mon() {
        try {
            int rdm=r.nextInt(get_List_mon().size()-1);
            return objects_monastery.get(rdm);
        }catch(Exception e) {
            return "MONASTERY_LVL_"+lvl+"__NO_STRING_AVAILABLE";
        }
    }
    private List<String> get_List_mon() {
        this.objects_monastery=ld.loadTextIntoList_objectsJob("monastery", lvl);
        return objects_monastery;
    }
    
    public final static int LOW=0;
    public final static int MEDIUM=1;
    public final static int HIGH=2;
    
    List<String> objects_town;
    List<String> objects_homestead;
    List<String> objects_tradePost;
    List<String> objects_castle;
    List<String> objects_tavern;
    List<String> objects_monastery;
    
    root.LoadDat ld;
    Random r;
    int lvl;

    
}
