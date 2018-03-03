/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package endYear;

import generators.Journal.JournalEntryGenerator;
import gui.Mainframe;
import resources.Inhabitants.InhStu;
import resources.Inhabitants.InhTea;
import resources.activity.ActivityCourse;
import resources.activity.ActivityJob;

/**
 *
 * @author Die Axt
 */
public class ManageActivities {
    public ManageActivities(Mainframe pdsk) {
        this.dsk=pdsk;
        this.ai=new ai.AI_Stu(dsk);
        this.year=dsk.getRes().year;
        this.jeg = new JournalEntryGenerator();
    }
    
    public void processAct() {
        System.out.println("Do activities");
        System.out.println("Chose Activities");
        dsk.getRes().lStu.stream().filter(pStu -> !pStu.isFormer()).forEach(pStu -> {
            pStu.setWorkTime(0);
            ai.choseActivity(pStu);
        });
        doCourses();
        doJobs();
        clearTT();
    }
    
    private void doCourses() {
        System.out.println("Do Courses");
        dsk.getRes().lCourse.stream().filter(pAC -> 
                pAC.isActive()).forEach(pAC -> {
            processCourse(pAC);
        });
    }
    
    private void processCourse(ActivityCourse pac) {
        this.ac=pac;
        this.topic = ac.getTopic();
        if(ac.isActive()) {
            tea = dsk.getRes().lTea.stream().filter(pTea -> 
                    pTea.getNumber()==ac.getHostNr()).findFirst().get();
            tea.addEntry(jeg.generateCourseEntry(ac, tea).getText());
            
            ac.getStudents().forEach((int[] pAcc) -> {
                stu = dsk.getRes().lStu.stream().filter(pStu ->
                        pStu.getNumber()==pAcc[0]).findFirst().get();
                
                pAcc[1] = stu.getAttribute(ac.getTopic());
                System.out.println("Improving "+stu.getNumber()+"'s "+topic+" from "+pAcc[1]);
                double teachVal = tea.getTeaching()*stu.getLearn();
                
                int attribute = (int)(determinAttrVal(stu.getAttribute(topic),tea.getAttribute(topic))*teachVal);
                System.out.println("Improving "+stu.getNumber()+"'s "+topic+" by "+attribute);
                
                stu.setAttribute(topic, stu.getAttribute(topic)+attribute);
                stu.addEntry(jeg.generateCourseEntry(ac, stu).getText());
                pAcc[2]=stu.getAttribute(topic);
                System.out.println("Improving "+stu.getNumber()+"'s "+topic+" to "+pAcc[2]);
                pAcc[3]=(int)(stu.getLearn()*tea.getTeaching()*100);
            });
            ac.setActive(false);
        }
    }
    private int determinAttrVal(int sAttr, int tAttr) {
        if(sAttr<=tAttr+10) {
            return tAttr-sAttr;
        } else {
            return 10;
        }
    }
    
    private void doJobs() {
        System.out.println("Do jobs");
        dsk.getRes().lJob.stream().filter(pAJ -> 
                pAJ.isActive()
                && pAJ.getHostNr()!=0).forEach(pAJ -> {
                    System.out.println("Doing Job "+pAJ.getID());
            if(pAJ.isHostTea()) {
                collectJobIncome_Tea(pAJ);
                generateJournalEntry(pAJ);
                pAJ.setActive(false);
            } else {
                collectJobIncome_Stu(pAJ);
                generateJournalEntry_stu(pAJ);
                pAJ.setActive(false);
            }
        });      
    }
    
    private void collectJobIncome_Tea(ActivityJob aj) {
        dsk.getRes().lAccounting.stream().filter(
                pAcc -> pAcc.getYear()==year).findAny().get().addIncomeJob_Tea(aj.getIncome());
        dsk.getRes().gold += aj.getIncome();
    }
    private void collectJobIncome_Stu(ActivityJob aj) {
        stu = dsk.getRes().lStu.stream().filter(pStu -> pStu.getNumber()==aj.getHostNr()).findAny().get();
        stu.addGold(aj.getIncome());
    }
    
    private void generateJournalEntry(ActivityJob aj) {
        System.out.println("Generating Journal Entry Job "+aj.getID());
        System.out.println("for "+aj.getHostNr());
        tea = dsk.getRes().lTea.stream().filter(
                pTea -> pTea.getNumber()==aj.getHostNr()).findFirst().get();
        
        tea.addEntry(jeg.generateJobEntry(tea,aj));
    }
    private void generateJournalEntry_stu(ActivityJob aj) {
        System.out.println("Generating Journal Entry Job "+aj.getID());
        System.out.println("for "+aj.getHostNr());
        stu = dsk.getRes().lStu.stream().filter(
                pStu -> pStu.getNumber()==aj.getHostNr()).findFirst().get();
        stu.addEntry(jeg.generateJobEntry(stu,aj));
    }
    
    private void clearTT() {
        dsk.getRes().lTea.forEach(pTea -> {
            pTea.resetTimeTable();
            pTea.setWorkTime(0);
        });
        dsk.getRes().lStu.forEach(pStu -> {
            pStu.resetTimeTable();
            pStu.setWorkTime(0);
        });
        dsk.getRes().lRoomStudy.forEach(pRS -> {
            pRS.resetRoomUsage();
        });
    }
    
    Mainframe dsk;
    
    JournalEntryGenerator jeg;
    
    int year;
    
    //Variables for handling courses
    ActivityCourse ac;
    InhTea tea;
    InhStu stu;
    ai.AI_Stu ai;
    int topic;
    
}
