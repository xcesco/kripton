## About release build

If you want to make build release you need to configure properly you system. Gradle configuration need following system variable to be defined:

 - GRADLE_KEYSTORE_ALIAS: used alias 
 - GRADLE_KEYSTORE_ALIAS_PASSWORD: used alias password
 - GRADLE_KEYSTORE_FILE: path of keystore file
 - GRADLE_KEYSTORE_PASSWORD: password of keystore file
 
You can define this system properties or you can remove from release build the proguard utilization.
 
