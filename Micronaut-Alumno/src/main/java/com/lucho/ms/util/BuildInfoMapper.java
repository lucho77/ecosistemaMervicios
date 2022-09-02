package com.lucho.ms.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class BuildInfoMapper {

	 private static final String GIT_PROPERTIES_FILE = "git.properties";

	    private BuildInfoMapper() {
	    }

	    public static BuildInfo getBuildInfo(String appName) {
	        return buildBuildInfo(appName, getGitPropertiesMap());
	    }

	    private static BuildInfo buildBuildInfo(String appName, Map<String, String> gitPropertiesMap) {
	        BuildInfo r = new BuildInfo();
	        r.setApplicationName(appName);
	        r.setVersion(gitPropertiesMap.get("git.build.version"));
	        r.setLastBuild(gitPropertiesMap.get("git.build.time"));
	        r.setBranchWithCommitId(String.format("%s %s",
	                gitPropertiesMap.get("git.commit.id.abbrev"),
	                gitPropertiesMap.get("git.branch")));
	        return r;
	    }

	    private static Map<String, String> getGitPropertiesMap() {
	        try {
	            Properties gitProperties = new Properties();
	            gitProperties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream(GIT_PROPERTIES_FILE));
	            return removeQuotes(new HashMap<>((Map) gitProperties));
	        } catch (Exception e) {
	            return new HashMap<>();
	        }
	    }

	    private static Map<String, String> removeQuotes(HashMap<String, String> src) {
	        Map<String, String> r = new HashMap<>();
	        if (src != null) {
	            src.keySet().forEach(k -> r.put(removeQuotes(k), removeQuotes(src.get(k))));
	        }
	        return r;
	    }

	    private static String removeQuotes(String s) {
	        return s.replace("\",", "").replace("\"", "");
	    }
}
