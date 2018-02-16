/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.region.resources;

/**
 *
 * @author Die Axt
 */
public class VenueDescriptions {
    public VenueDescriptions() {
        
    }
    public String getDescTown() {
        String desc="<html>"
                + "Hive might be a better word to describe this pile of people,<br>"
                + "swarming through each other. Here is where they all meet.<br>"
                + "The traders, the beggars, the saints, the sinners... <br>"
                + "And it is told that they all got some kind of association<br>"
                + "to protect their interest.<br><br>"
                + "So: Plenty to do for a sellsword or cutthroat. <br>"
                + "And maybe even a hero. "
                + "<br>--------<br>"
                + "A town generates two to six Jobs of all kind <br>"
                + "(max 2 for any kind though)";
        return desc;
    }
    public String getDescHomestead() {
        String desc=""
                + "<html>"
                + "Sometimes not more than a couple of houses, sometimes half<br>"
                + "a fortess. Homesteads are farms, yes, but also meeting points<br>"
                + "for all kind of travelling folk.<br><br>"
                + "Thus here's always someone who needs your help."
                + "<br>--------<br>"
                + "A homestead generates up to four jobs of all kind <br>"
                + "(max 1 for any kind thoug)";
        return desc;
    }
    public String getDescTradePost() {
        String desc="<html>"
                + "Trade Posts usually develop where traderouts cross.<br>"
                + "Often times the are a nothing more than a fortified camp,<br>"
                + "where traders also exchange goods.<br><br>"
                + "And there's always a caravan in need of assistance..."
                + "<br>--------<br>"
                + "A Trading Post generates one or two guard-jobs";
        return desc;
    }
    public String getDescCastle() {
        String desc="<html>"
                + "The home to a King, an Aristocrate or a rich Knight.<br><br>"
                + "Someone who needs advice, that s the important pint!"
                + "<br>--------<br>"
                + "A Castle generates one or two advisor-jobs";
        return desc;
    }
    public String getDescTavern() {
        String desc="<html>"
                + "Resting points along the roads. Sometimes fortified, <br>"
                + "many times just a hut.<br><br>"
                + "Anyway, people here want a distraction from their traveling!"
                + "<br>--------<br>"
                + "A Tavern generates one or two Bard-Jobs";
        return desc;
    }
    public String getDescMonastery() {
        String desc="<html>"
                + "Holy men who want to perform holy tasks.<br><br>"
                + "They want magic!"
                + "<br>--------<br>"
                + "A Monastery generates one or two Sorcerer-Jobs";
        return desc;
    }
}
