@echo off
@rem setting up the command promp title 
title PreTestScript execution
@rem results file for the executions
SET FILELOCATION=%cd%

SET LOGFILENAME=PreTestlog.log
SET RESULTSFILENAME=PreTestResults.log

SET LOGFILE=%FILELOCATION%\%LOGFILENAME%
SET RESULTSFILE=%FILELOCATION%\%RESULTSFILENAME%

SET TEMPLOGFILE=%FILELOCATION%\PreTestlogTemp.log

IF EXIST PreTestResults.txt (
    @rem deleting previous results file
	del /f %RESULTSFILENAME%
) ELSE (
cd. > %RESULTSFILENAME%
)


IF EXIST PreTestlog.log (
    @rem deleting previous log file
	del /f %LOGFILENAME%
) ELSE (
cd. > %LOGFILENAME%
)

@rem clearing the terminal 
cls

echo [INFO] Execution results will be put on the next file: %RESULTSFILE%
echo [INFO] Execution log will be put on the next file: %LOGFILE%

@rem asuming that the script is on the test-suite folder we should move out from craftercms
echo [INFO] moving out from craftercms / src / test-suite folder >  %LOGFILE%
cd ../../..

IF EXIST crafter_cms_temp (
    @rem crafter_cms_temp folder already exists, deleting temporary folder
	echo [INFO] crafter_cms_temp folder already exists, deleting the temporary folder >> %LOGFILE%
	rd /s /q crafter_cms_temp
) 

@rem creating temporary folder
echo [INFO] creating crafter_cms_temp folder to test installation of craftercms >> %LOGFILE%
mkdir crafter_cms_temp

@rem moving to temporary folder
echo [INFO] moving to temporary folder >> %LOGFILE%
cd crafter_cms_temp

@rem Cloning the craftercms repo to local
echo [INFO] cloning the craftercms to local >> %LOGFILE%
git clone https://github.com/craftercms/craftercms.git >> %LOGFILE%

@rem here we need to check if the output was success 
IF %ERRORLEVEL% NEQ 0 (
echo Clone Crafter CMS  ...  FAILED >> %RESULTSFILE%
) ELSE (
echo Clone Crafter CMS  ...  PASSED >> %RESULTSFILE%
)  

@rem changing to the craftercms folder just cloned
echo [INFO] moving to craftercms folder >> %LOGFILE%
cd craftercms

@rem moving to develop branch
echo [INFO] moving to develop branch >> %LOGFILE%
git checkout develop >> %LOGFILE%

@rem here we need to check if the output was success 
IF %ERRORLEVEL% NEQ 0 (
echo Moving to develop branch of Crafter CMS  ...  FAILED >> %RESULTSFILE%
) ELSE (
echo Moving to develop branch of Crafter CMS  ...  PASSED >> %RESULTSFILE%
)  

@rem executing the gradlew.bat init 
echo [INFO] executing gradlew init process >> %LOGFILE%
call gradlew.bat init -P"crafter.git.shallowClone=true" >> %LOGFILE%

@rem here we need to check if the output was success 
IF %ERRORLEVEL% NEQ 0 (
echo Init Crafter CMS  ...  FAILED >> %RESULTSFILE%
) ELSE (
echo Init Crafter CMS  ...  PASSED >> %RESULTSFILE%
)  

@rem executing the gradlew.bat build and deploy
echo [INFO] executing gradlew build and deploy processes, using smtp port=2525 >> %LOGFILE%
call gradlew.bat build deploy -P"authoring.studio.smtp.port=2525" >> %LOGFILE%

@rem here we need to check if the output was success 
IF %ERRORLEVEL% NEQ 0 (
echo [ERROR] the gradlew build and deploy processes failed >> %LOGFILE%
echo Build and Deploy Crafter CMS  ...  FAILED >> %RESULTSFILE%
) ELSE (
echo [INFO] executed gradlew build and deploy processes with success >> %LOGFILE%
echo Build and Deploy Crafter CMS  ...  PASSED >> %RESULTSFILE%
)  

@rem executing the start up of the both envs delivery env and authoring env
echo [INFO] executing gradlew startup process >> %LOGFILE%
call gradlew.bat start >> %LOGFILE%

@rem here we need to check if the output was success 
IF %ERRORLEVEL% NEQ 0 (
echo Start Crafter CMS  ...  FAILED >> %RESULTSFILE%
) ELSE (
echo Start Crafter CMS  ...  PASSED >> %RESULTSFILE%
) 

@rem waiting for 5 minutes until the studio is totally up
echo [INFO] waiting until studio is totally up. The Waitime is 5 minutes >> %LOGFILE%
timeout 300

echo [INFO] verifying that the port 8080 (Tomcat) is listened >> %LOGFILE%
netstat -o -n -a | findstr "0.0.0.0:8080"
IF %ERRORLEVEL% NEQ 0 echo [ERROR] the startup process failed Port 8080 (Tomcat) is not up after 5 minutes >> %LOGFILE%

echo [INFO] verifying that the port 8694 (Solr) is listened >> %LOGFILE%
netstat -o -n -a | findstr "0.0.0.0:8694"
IF %ERRORLEVEL% NEQ 0 echo [ERROR] the startup process failed Port 8694 (Solr) is not up after 5 minutes >> %LOGFILE%

echo [INFO] verifying that the port 33306 (MariaDB) is listened >> %LOGFILE%
netstat -o -n -a | findstr "0.0.0.0:33306"
IF %ERRORLEVEL% NEQ 0  echo [ERROR] the startup process failed Port 33306 (MariaDB) is not up after 5 minutes >> %LOGFILE%

echo [INFO] verifying that the port 9191 (MariaDB) is listened >> %LOGFILE%
netstat -o -n -a | findstr "0.0.0.0:9191"
IF %ERRORLEVEL% NEQ 0  echo [ERROR] the startup process failed Port 33306 (MariaDB) is not up after 5 minutes >> %LOGFILE%

echo [INFO] verifying that the port 27020 (MongoDB) is listened >> %LOGFILE%
netstat -o -n -a | findstr "0.0.0.0:27020"
IF %ERRORLEVEL% NEQ 0  echo [WARN] the startup process failed Port 27020 (MongoDB) is not up after 5 minutes >> %LOGFILE%

@rem executing the stop of the both envs delivery env and authoring env
echo [INFO] executing gradlew stop process >> %LOGFILE%
call gradlew.bat stop >> %LOGFILE%


@rem here we need to check if the output was success 
IF %ERRORLEVEL% NEQ 0 (
echo Stop Crafter CMS  ...  FAILED >> %RESULTSFILE%
) ELSE (
echo Stop Crafter CMS  ...  PASSED >> %RESULTSFILE%
) 

@rem waiting for 5 minutes until the studio is totally down
echo [INFO] waiting until studio is totally down. The Waitime is 5 minutes >> %LOGFILE%
timeout 300

echo [INFO] verifying that the port 8080 (Tomcat) is not listened >> %LOGFILE%
netstat -o -n -a | findstr "0.0.0.0:8080"
IF %ERRORLEVEL% equ 0  echo [ERROR] the stop process failed Port 8080 (Tomcat) is not down after 5 minutes >> %LOGFILE%

echo [INFO] verifying that the port 8694 (Solr) is not listened >> %LOGFILE%
netstat -o -n -a | findstr "0.0.0.0:8694"
IF %ERRORLEVEL% equ 0 echo [ERROR] the stop process failed Port 8694 (Solr) is not down after 5 minutes >> %LOGFILE%

echo [INFO] verifying that the port 33306 (MariaDB) is not listened >> %LOGFILE%
netstat -o -n -a | findstr "0.0.0.0:33306"
IF %ERRORLEVEL% equ 0 echo [ERROR] the stop process failed Port 33306 (MariaDB) is not down after 5 minutes >> %LOGFILE%

echo [INFO] verifying that the port 9191 (MariaDB) is not listened >> %LOGFILE%
netstat -o -n -a | findstr "0.0.0.0:9191"
IF %ERRORLEVEL% equ 0 echo [ERROR] the stop process failed Port 33306 (MariaDB) is not down after 5 minutes >> %LOGFILE%

echo [INFO] verifying that the port 27020 (MongoDB) is not listened >> %LOGFILE%
netstat -o -n -a | findstr "0.0.0.0:27020"
IF %ERRORLEVEL% equ 0 echo [WARN] the stop process failed Port 27020 (MongoDB) is not down after 5 minutes >> %LOGFILE%

@rem moving out of temporary folder
echo [INFO] moving out from temporary folder >> %LOGFILE%
cd ../..

echo [INFO] closing all the other terminal windows opened by previous processes >> %LOGFILE%
echo [INFO] trying to kill mysqld, if exists >> %LOGFILE%
for /f tokens^=3^ delims^=^" %%a in ('tasklist /nh /v /fi "Imagename eq mysqld.exe"') do (
taskkill /F /pid %%a
)

echo [INFO] trying to kill java tomcat, if exists >> %LOGFILE%
for /f tokens^=3^ delims^=^" %%a in ('tasklist /nh /v /fi "Imagename eq java.exe" /fi "WindowTitle eq Tomcat" /fi "STATUS eq running" /fo csv') do (
taskkill /F /pid %%a
)

echo [INFO] trying to kill all other terminal windows related to authoring, if exists >> %LOGFILE%
@rem trying to delete the other terminal windows
for /f tokens^=3^ delims^=^" %%a in ('tasklist /nh /v /fi "Imagename eq java.exe" /fi "WindowTitle eq Crafter Deployer*" /fi "STATUS eq running"') do (
taskkill /F /pid %%a
)

echo [INFO] trying to kill all other cmd terminal windows, if exists >> %LOGFILE%
@rem trying to delete the other terminal windows
for /f tokens^=3^ delims^=^" %%a in ('tasklist /nh /v /fi "Imagename eq cmd.exe" /fi "STATUS eq running" /fo csv ^| findstr /v /c:"gradlew.bat  selftest"') do (
taskkill /F /pid %%a
)

@rem deleting temporary folder
echo [INFO] deleting the temporary folder >> %LOGFILE%
rd /s /q crafter_cms_temp
