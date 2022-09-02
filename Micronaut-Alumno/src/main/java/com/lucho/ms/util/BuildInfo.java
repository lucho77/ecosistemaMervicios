package com.lucho.ms.util;

public class BuildInfo {

	 private String applicationName;
	    private String version;
	    private String lastBuild;
	    private String branchWithCommitId;

	    public String getApplicationName() {
	        return applicationName;
	    }

	    public void setApplicationName(String applicationName) {
	        this.applicationName = applicationName;
	    }

	    public String getVersion() {
	        return version;
	    }

	    public void setVersion(String version) {
	        this.version = version;
	    }

	    public String getLastBuild() {
	        return lastBuild;
	    }

	    public void setLastBuild(String lastBuild) {
	        this.lastBuild = lastBuild;
	    }

	    public String getBranchWithCommitId() {
	        return branchWithCommitId;
	    }

	    public void setBranchWithCommitId(String branchWithCommitId) {
	        this.branchWithCommitId = branchWithCommitId;
	    }
}
