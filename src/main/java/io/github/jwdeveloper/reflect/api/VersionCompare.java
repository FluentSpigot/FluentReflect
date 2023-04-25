package io.github.jwdeveloper.reflect.api;

public interface VersionCompare {
     boolean isHigher(String newVersion, String OldVersion);
     public boolean isLower(String newVersion, String OldVersion);
}
