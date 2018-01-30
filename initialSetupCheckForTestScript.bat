@echo off
@rem results file for the executions
SET FILELOCATION=%cd%

SET LOGFILENAME=PreTestlog.log
SET RESULTSFILENAME=PreTestResults.txt

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
echo [INFO] moving out from craftercms / src / test-suite folder > %TEMPLOGFILE% & type %TEMPLOGFILE% > CON & type %TEMPLOGFILE% > %LOGFILE%
cd ../../..

IF EXIST crafter_cms_temp (
    @rem crafter_cms_temp folder already exists, deleting temporary folder
	echo [INFO] crafter_cms_temp folder already exists, deleting the temporary folder > %TEMPLOGFILE% & type %TEMPLOGFILE% > CON & type %TEMPLOGFILE% >> %LOGFILE%
	rd /s /q crafter_cms_temp
) 

@rem creating temporary folder
echo [INFO] creating crafter_cms_temp folder to test installation of craftercms > %TEMPLOGFILE% & type %TEMPLOGFILE% > CON & type %TEMPLOGFILE% >> %LOGFILE%
mkdir crafter_cms_temp

@rem moving to temporary folder
echo [INFO] moving to temporary folder > %TEMPLOGFILE% & type %TEMPLOGFILE% > CON & type %TEMPLOGFILE% >> %LOGFILE%
cd crafter_cms_temp

@rem Cloning the craftercms repo to local
echo [INFO] cloning the craftercms to local > %TEMPLOGFILE% & type %TEMPLOGFILE% > CON & type %TEMPLOGFILE% >> %LOGFILE%
git clone https://github.com/craftercms/craftercms.git > %TEMPLOGFILE% & type %TEMPLOGFILE% > CON & type %TEMPLOGFILE% >> %LOGFILE%

@rem here we need to check if the output was success 
IF %ERRORLEVEL% NEQ 0 (
echo Clone Crafter CMS  ...  FAILED > %TEMPLOGFILE% & type %TEMPLOGFILE% > CON & type %TEMPLOGFILE% >> %RESULTSFILE%
) ELSE (
echo Clone Crafter CMS  ...  PASSED > %TEMPLOGFILE% & type %TEMPLOGFILE% > CON & type %TEMPLOGFILE% >> %RESULTSFILE%
)  

@rem changing to the craftercms folder just cloned
echo [INFO] moving to craftercms folder > %TEMPLOGFILE% & type %TEMPLOGFILE% > CON & type %TEMPLOGFILE% >> %LOGFILE%
cd craftercms

@rem moving to develop branch
echo [INFO] moving to develop branch > %TEMPLOGFILE% & type %TEMPLOGFILE% > CON & type %TEMPLOGFILE% >> %LOGFILE%
git checkout develop > %TEMPLOGFILE% & type %TEMPLOGFILE% > CON & type %TEMPLOGFILE% >> %LOGFILE%

@rem here we need to check if the output was success 
IF %ERRORLEVEL% NEQ 0 (
echo Moving to develop branch of Crafter CMS  ...  FAILED > %TEMPLOGFILE% & type %TEMPLOGFILE% > CON & type %TEMPLOGFILE% >> %RESULTSFILE%
) ELSE (
echo Moving to develop branch of Crafter CMS  ...  PASSED > %TEMPLOGFILE% & type %TEMPLOGFILE% > CON & type %TEMPLOGFILE% >> %RESULTSFILE%
)  

@rem executing the gradlew.bat init 
echo [INFO] executing gradlew init process > %TEMPLOGFILE% & type %TEMPLOGFILE% > CON & type %TEMPLOGFILE% >> %LOGFILE%
call gradlew.bat init -P"crafter.git.shallowClone=true" > %TEMPLOGFILE% & type %TEMPLOGFILE% > CON & type %TEMPLOGFILE% >> %LOGFILE%

@rem here we need to check if the output was success 
IF %ERRORLEVEL% NEQ 0 (
echo Init Crafter CMS  ...  FAILED > %TEMPLOGFILE% & type %TEMPLOGFILE% > CON & type %TEMPLOGFILE% >> %RESULTSFILE%
) ELSE (
echo Init Crafter CMS  ...  PASSED > %TEMPLOGFILE% & type %TEMPLOGFILE% > CON & type %TEMPLOGFILE% >> %RESULTSFILE%
)  

@rem executing the gradlew.bat build and deploy
echo [INFO] executing gradlew build and deploy processes, using smtp port=2525 > %TEMPLOGFILE% & type %TEMPLOGFILE% > CON & type %TEMPLOGFILE% >> %LOGFILE%
call gradlew.bat build deploy -P"authoring.studio.smtp.port=2525" > %TEMPLOGFILE% & type %TEMPLOGFILE% > CON & type %TEMPLOGFILE% >> %LOGFILE%

@rem here we need to check if the output was success 
IF %ERRORLEVEL% NEQ 0 (
echo [ERROR] the gradlew build and deploy processes failed > %TEMPLOGFILE% & type %TEMPLOGFILE% > CON & type %TEMPLOGFILE% >> %LOGFILE%
echo Build and Deploy Crafter CMS  ...  FAILED > %TEMPLOGFILE% & type %TEMPLOGFILE% > CON & type %TEMPLOGFILE% >> %RESULTSFILE%
) ELSE (
echo [INFO] executed gradlew build and deploy processes with success > %TEMPLOGFILE% & type %TEMPLOGFILE% > CON & type %TEMPLOGFILE% >> %LOGFILE%
echo Build and Deploy Crafter CMS  ...  PASSED > %TEMPLOGFILE% & type %TEMPLOGFILE% > CON & type %TEMPLOGFILE% >> %RESULTSFILE%
)  

@rem executing the start up of the both envs delivery env and authoring env
echo [INFO] executing gradlew startup process > %TEMPLOGFILE% & type %TEMPLOGFILE% > CON & type %TEMPLOGFILE% >> %LOGFILE%
call gradlew.bat start > %TEMPLOGFILE% & type %TEMPLOGFILE% > CON & type %TEMPLOGFILE% >> %LOGFILE%

@rem here we need to check if the output was success 
IF %ERRORLEVEL% NEQ 0 (
echo Start Crafter CMS  ...  FAILED > %TEMPLOGFILE% & type %TEMPLOGFILE% > CON & type %TEMPLOGFILE% >> %RESULTSFILE%
) ELSE (
echo Start Crafter CMS  ...  PASSED > %TEMPLOGFILE% & type %TEMPLOGFILE% > CON & type %TEMPLOGFILE% >> %RESULTSFILE%
) 

@rem waiting for 5 minutes until the studio is totally up
echo [INFO] waiting until studio is totally up. The Waitime is 5 minutes > %TEMPLOGFILE% & type %TEMPLOGFILE% > CON & type %TEMPLOGFILE% >> %LOGFILE%
timeout 300

echo [INFO] verifying that the port 8080 (Tomcat) is listened > %TEMPLOGFILE% & type %TEMPLOGFILE% > CON & type %TEMPLOGFILE% >> %LOGFILE%
netstat -o -n -a | findstr "0.0.0.0:8080"
IF %ERRORLEVEL% NEQ 0 echo [ERROR] the startup process failed Port 8080 (Tomcat) is not up after 5 minutes > %TEMPLOGFILE% & type %TEMPLOGFILE% > CON & type %TEMPLOGFILE% >> %LOGFILE%

echo [INFO] verifying that the port 8694 (Solr) is listened > %TEMPLOGFILE% & type %TEMPLOGFILE% > CON & type %TEMPLOGFILE% >> %LOGFILE%
netstat -o -n -a | findstr "0.0.0.0:8694"
IF %ERRORLEVEL% NEQ 0 echo [ERROR] the startup process failed Port 8694 (Solr) is not up after 5 minutes > %TEMPLOGFILE% & type %TEMPLOGFILE% > CON & type %TEMPLOGFILE% >> %LOGFILE%

echo [INFO] verifying that the port 33306 (MariaDB) is listened > %TEMPLOGFILE% & type %TEMPLOGFILE% > CON & type %TEMPLOGFILE% >> %LOGFILE%
netstat -o -n -a | findstr "0.0.0.0:33306"
IF %ERRORLEVEL% NEQ 0  echo [ERROR] the startup process failed Port 33306 (MariaDB) is not up after 5 minutes > %TEMPLOGFILE% & type %TEMPLOGFILE% > CON & type %TEMPLOGFILE% >> %LOGFILE%

echo [INFO] verifying that the port 9191 (MariaDB) is listened > %TEMPLOGFILE% & type %TEMPLOGFILE% > CON & type %TEMPLOGFILE% >> %LOGFILE%
netstat -o -n -a | findstr "0.0.0.0:9191"
IF %ERRORLEVEL% NEQ 0  echo [ERROR] the startup process failed Port 33306 (MariaDB) is not up after 5 minutes > %TEMPLOGFILE% & type %TEMPLOGFILE% > CON & type %TEMPLOGFILE% >> %LOGFILE%

echo [INFO] verifying that the port 27020 (MongoDB) is listened > %TEMPLOGFILE% & type %TEMPLOGFILE% > CON & type %TEMPLOGFILE% >> %LOGFILE%
netstat -o -n -a | findstr "0.0.0.0:27020"
IF %ERRORLEVEL% NEQ 0  echo [WARN] the startup process failed Port 27020 (MongoDB) is not up after 5 minutes > %TEMPLOGFILE% & type %TEMPLOGFILE% > CON & type %TEMPLOGFILE% >> %LOGFILE%

@rem executing the stop of the both envs delivery env and authoring env
echo [INFO] executing gradlew stop process > %TEMPLOGFILE% & type %TEMPLOGFILE% > CON & type %TEMPLOGFILE% >> %LOGFILE%
call gradlew.bat stop > %TEMPLOGFILE% & type %TEMPLOGFILE% > CON & type %TEMPLOGFILE% >> %LOGFILE%


@rem here we need to check if the output was success 
IF %ERRORLEVEL% NEQ 0 (
echo Stop Crafter CMS  ...  FAILED > %TEMPLOGFILE% & type %TEMPLOGFILE% > CON & type %TEMPLOGFILE% >> %RESULTSFILE%
) ELSE (
echo Stop Crafter CMS  ...  PASSED > %TEMPLOGFILE% & type %TEMPLOGFILE% > CON & type %TEMPLOGFILE% >> %RESULTSFILE%
) 

@rem waiting for 5 minutes until the studio is totally down
echo [INFO] waiting until studio is totally down. The Waitime is 5 minutes > %TEMPLOGFILE% & type %TEMPLOGFILE% > CON & type %TEMPLOGFILE% >> %LOGFILE%
timeout 300

echo [INFO] verifying that the port 8080 (Tomcat) is not listened > %TEMPLOGFILE% & type %TEMPLOGFILE% > CON & type %TEMPLOGFILE% >> %LOGFILE%
netstat -o -n -a | findstr "0.0.0.0:8080"
IF %ERRORLEVEL% equ 0  echo [ERROR] the stop process failed Port 8080 (Tomcat) is not down after 5 minutes > %TEMPLOGFILE% & type %TEMPLOGFILE% > CON & type %TEMPLOGFILE% >> %LOGFILE%

echo [INFO] verifying that the port 8694 (Solr) is not listened > %TEMPLOGFILE% & type %TEMPLOGFILE% > CON & type %TEMPLOGFILE% >> %LOGFILE%
netstat -o -n -a | findstr "0.0.0.0:8694"
IF %ERRORLEVEL% equ 0 echo [ERROR] the stop process failed Port 8694 (Solr) is not down after 5 minutes > %TEMPLOGFILE% & type %TEMPLOGFILE% > CON & type %TEMPLOGFILE% >> %LOGFILE%

echo [INFO] verifying that the port 33306 (MariaDB) is not listened > %TEMPLOGFILE% & type %TEMPLOGFILE% > CON & type %TEMPLOGFILE% >> %LOGFILE%
netstat -o -n -a | findstr "0.0.0.0:33306"
IF %ERRORLEVEL% equ 0 echo [ERROR] the stop process failed Port 33306 (MariaDB) is not down after 5 minutes > %TEMPLOGFILE% & type %TEMPLOGFILE% > CON & type %TEMPLOGFILE% >> %LOGFILE%

echo [INFO] verifying that the port 9191 (MariaDB) is not listened > %TEMPLOGFILE% & type %TEMPLOGFILE% > CON & type %TEMPLOGFILE% >> %LOGFILE%
netstat -o -n -a | findstr "0.0.0.0:9191"
IF %ERRORLEVEL% equ 0 echo [ERROR] the stop process failed Port 33306 (MariaDB) is not down after 5 minutes > %TEMPLOGFILE% & type %TEMPLOGFILE% > CON & type %TEMPLOGFILE% >> %LOGFILE%

echo [INFO] verifying that the port 27020 (MongoDB) is not listened > %TEMPLOGFILE% & type %TEMPLOGFILE% > CON & type %TEMPLOGFILE% >> %LOGFILE%
netstat -o -n -a | findstr "0.0.0.0:27020"
IF %ERRORLEVEL% equ 0 echo [WARN] the stop process failed Port 27020 (MongoDB) is not down after 5 minutes > %TEMPLOGFILE% & type %TEMPLOGFILE% > CON & type %TEMPLOGFILE% >> %LOGFILE%

@rem moving out of temporary folder
echo [INFO] moving out from temporary folder > %TEMPLOGFILE% & type %TEMPLOGFILE% > CON & type %TEMPLOGFILE% >> %LOGFILE%
cd ../..

echo "Please close all terminal windows (but this one) to continue
pause

@rem deleting temporary folder
echo [INFO] deleting the temporary folder > %TEMPLOGFILE% & type %TEMPLOGFILE% > CON & type %TEMPLOGFILE% >> %LOGFILE%
rd /s /q crafter_cms_temp

@rem deleting temporary log file
del %TEMPLOGFILE%
