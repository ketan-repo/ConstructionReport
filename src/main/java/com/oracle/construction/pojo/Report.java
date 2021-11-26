package com.oracle.construction.pojo;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Report {
    private String title;
    private List<String> body;
    private ReportType reportType;

    public Report() {
        body = new ArrayList();
    }

    public Report(String title, ReportType reportType) {
        this();
        this.title = title;
        this.reportType = reportType;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ReportType getReportType() {
        return reportType;
    }

    public void setReportType(ReportType reportType) {
        this.reportType = reportType;
    }

    public void addBody(String reportBody) {
        body.add(reportBody);
    }

    public List<String> getBody() {
        return body;
    }

    public void setBody(List<String> body) {
        this.body = body;
    }

    @Override
    public String toString() {
        return "\n######################################################################################################" +
                "\ntitle=" + title +
                "\nreportType=" + reportType +
                "\nbody=" + body.stream().collect(Collectors.joining("\n","[\n","\n]")) +
                "\n#####################################################################################################\n";
    }
}
