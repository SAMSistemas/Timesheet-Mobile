package com.samsistemas.timesheet.viewmodel;

import java.util.Date;
import java.util.List;

/**
 * @author jonatan.salas
 */
public class CalendarViewModel {
    private Date dateToPaint;
    private int color;
    private List<JobLogViewModel> jobLogViewModelList;

    public CalendarViewModel setDateToPaint(Date dateToPaint) {
        this.dateToPaint = dateToPaint;
        return this;
    }

    public CalendarViewModel setColor(int color) {
        this.color = color;
        return this;
    }

    public CalendarViewModel setJobLogViewModelList(List<JobLogViewModel> jobLogViewModelList) {
        this.jobLogViewModelList = jobLogViewModelList;
        return this;
    }

    public Date getDateToPaint() {
        return dateToPaint;
    }

    public int getColor() {
        return color;
    }

    public List<JobLogViewModel> getJobLogViewModelList() {
        return jobLogViewModelList;
    }
}
