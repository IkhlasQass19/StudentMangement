package client;


import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ModuleCours {
    private String moduleName;
    private List<File> coursFiles;
    private List<File> tpFiles;
    private List<File> tdFiles;
    private List<File> examFiles;

    public ModuleCours(String moduleName) {
        this.moduleName = moduleName;
        coursFiles = new ArrayList<>();
        tpFiles = new ArrayList<>();
        tdFiles = new ArrayList<>();
        examFiles = new ArrayList<>();
    }

    public String getModuleName() {
        return moduleName;
    }

    public void addCoursFile(File file) {
        coursFiles.add(file);
    }

    public void addTPFile(File file) {
        tpFiles.add(file);
    }

    public void addTDFile(File file) {
        tdFiles.add(file);
    }

    public void addExamFile(File file) {
        examFiles.add(file);
    }

    public List<File> getCoursFiles() {
        return coursFiles;
    }

    public List<File> getTPFiles() {
        return tpFiles;
    }

    public List<File> getTDFiles() {
        return tdFiles;
    }

    public List<File> getExamFiles() {
        return examFiles;
    }
}
