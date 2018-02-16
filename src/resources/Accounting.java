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
public class Accounting implements java.io.Serializable {
    
    public Accounting(int pyear) {
        this.year=pyear;
        this.maintRS=0;
        this.maintRQ=0;
        this.maintRD=0;
        this.builtRS=0;
        this.builtRQ=0;
        this.builtRD=0;
        this.costNewRS=0;
        this.costNewRD=0;
        this.costNewRQ=0;
        //Teacher
        this.teaHired_cost=0;
        this.teaWages=0;
        //Students
        this.tuitions=0;
        //Activitys
        this.incomeJob_Tea=0;
        this.incomeJob_Stu=0;
        this.incomeAdv=0;
        this.expensesAdv=0;
    }
    
    public int getYear() {
        return year;
    }
    
    public void setMaintRS(int maint) {
        this.maintRS=maint;
    }
    public void addMaintRS(int maint) {
        this.maintRS=maint;
    }
    public int getMaintRS() {
        return maintRS;
    }
    public void setMaintRQ(int maint) {
        this.maintRQ=maint;
    }
    public void addMaintRQ(int maint) {
        this.maintRQ=maintRQ+maint;
    }
    public int getMaintRQ() {
        return maintRQ;
    }
    public void setMaintRD(int maint) {
        this.maintRD=maint;
    }
    public void addMaintRD(int maint) {
        this.maintRD=maintRD+maint;
    }
    public int getMaintRD() {
        return maintRD;
    }
    public int getMaint_total() {
        return maintRS+maintRQ+maintRD;
    }
    public void builtRS(int built) {
        this.builtRS=built;
    }    
    public int getBuiltRS() {
        return builtRS;
    }
    public void setBuiltRQ(int built) {
        this.builtRQ=built;
    }
    public int getBuiltRQ() {
        return builtRQ;
    }
    public void setBuiltRD(int built) {
        this.builtRD=built;
    }
    public int getBuiltRD() {
        return builtRD;
    }
    public void setCostNewRS(int cost) {
        this.costNewRS=cost;
    }
    public int getCostNewRS() {
        return costNewRS;
    }
    public void setCostNewRQ(int cost) {
        this.costNewRQ=cost;
    }
    public int getCostNewRQ() {
        return costNewRQ;
    }
    public void setCostNewRD(int cost) {
        this.costNewRD=cost;
    }
    public int getCostNewRD() {
        return costNewRD;
    }
    public int getCostNew_total() {
        return costNewRS+costNewRQ+costNewRD;
    }
    public void addRS(int maint, int cost) {
        this.builtRS++;
        this.maintRS=maintRS+maint;
        this.costNewRS=costNewRS+cost;
    }
    public void addRQ(int maint, int cost) {
        this.builtRQ++;
        this.maintRS=maintRS+maint;
        this.costNewRQ=cost;
    }
    public void addRD(int maint, int cost) {
        this.builtRD++;
        this.maintRD=maintRD+maint;
        this.costNewRD=costNewRD+cost;
    }
    
    public void setTeaHired(int newTH) {
        this.teaHired_cost=newTH;
    }
    public int getTeaHired() {
        return teaHired_cost;
    }
    public void setTeaWages(int newWages) {
        this.teaWages=newWages;
    }
    public void addTeaWages(int newWages) {
        this.teaWages=teaWages+newWages;
    }
    public int getTeaWages() {
        return teaWages;
    }
    public void addTea(int newWages) {
        this.teaHired_cost=teaHired_cost+newWages*6;
        this.teaWages=teaWages+newWages;
    }
    
    public int getIncome_Total() {
        return tuitions+incomeJob_Tea+incomeAdv;
    }
    public int getExpenses_total() {
        return teaWages+teaHired_cost+expensesAdv+getCostNew_total()+getMaint_total();
    }
    
    public void setTuitions(int tui) {
        this.tuitions=tui;
    }
    public int getTuitions() {
        return tuitions;
    }
    
    public void setIncomeJob_Tea(int inc) {
        this.incomeJob_Tea=inc;
    }
    public void addIncomeJob_Tea(int inc) {
        this.incomeJob_Tea=incomeJob_Tea+inc;
    }
    public int getIncomeJob_Tea() {
        return incomeJob_Tea;
    }
    public void setIncomeJob_Stu(int inc) {
        this.incomeJob_Stu=inc;
    }
    public void addIncomeJob_Stu(int inc) {
        this.incomeJob_Stu=incomeJob_Stu+inc;
    }
    public int getIncomeJob_Stu() {
        return incomeJob_Stu;
    }
    public void setIncomeAdv(int inc) {
        this.incomeAdv=inc;
    }
    public int getIncomeAdv() {
        return incomeAdv;
    }
    public int getTotalIncome() {
        return incomeJob_Tea+incomeAdv+tuitions;
    }
    public void setExpensesAdv(int exp) {
        this.expensesAdv=exp;
    }
    public int getExpensesAdv() {
        return expensesAdv;
    }
    public void addAdv(int inc, int exp) {
        this.nrAdv++;
        this.incomeAdv=incomeAdv+inc;
        this.expensesAdv=expensesAdv+exp;
    }
    
    public void setNrJobs(int nr) {
        this.nrJobs=nr;
    }
    public int getNrJobs() {
        return nrJobs;
    }
    public void setNrCourses(int nr) {
        this.nrCourses=nr;
    }
    public int getNrCourses() {
        return nrCourses;
    }
    public void setNrAdv(int nr) {
        this.nrAdv=nr;
    }
    public int getNrAdv() {
        return nrAdv;
    }
    
    int year;
    //Rooms
    int maintRS;
    int maintRQ;
    int maintRD;
    int builtRS;
    int builtRQ;
    int builtRD;
    int costNewRS;
    int costNewRD;
    int costNewRQ;
    //Teacher
    int teaHired_cost;
    int teaWages;
    //Students
    int tuitions;
    //Activitys
    int incomeJob_Tea;
    int incomeJob_Stu;
    int incomeAdv;
    int expensesAdv;
    
    int nrCourses;
    int nrJobs;
    int nrAdv;
    
    int newStu;
    int leavingStu;
    
}
