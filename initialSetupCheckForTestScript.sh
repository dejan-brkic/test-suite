#!/bin/bash


#setting up the terminal title
WINDOWTITLE="PreTestScript Execution"
echo -e '\033]2;'$WINDOWTITLE'\007'

#results file for the executions
FILELOCATION=$(pwd)
LOGFILENAME="PreTestlog.log"
RESULTSFILENAME="PreTestResults.log"

LOGFILE=$FILELOCATION"/"$LOGFILENAME
RESULTSFILE=$FILELOCATION"/"$RESULTSFILENAME

if [ -f $RESULTSFILENAME ]; then
    #deleting previous results file
     rm $RESULTSFILENAME
     else 
     touch $RESULTSFILENAME
fi

if [ -f $LOGFILENAME ]; then
    #deleting previous log file
     rm $LOGFILENAME
     else 
     touch $LOGFILENAME
fi

#clearing the terminal  
clear
echo "[INFO] Execution results will be put on the next file: "$RESULTSFILE
echo "[INFO] Execution log will be put on the next file: "$LOGFILE

#asuming that the script is on the test-suite folder we should move out from craftercms
echo "[INFO] moving out from craftercms / src / test-suite folder" > $LOGFILE
cd ../../..

if [ -d "crafter_cms_temp" ]; then
    #deleting temporary folder
        echo "[INFO] crafter_cms_temp folder already exists, deleting temporary folder" >> $LOGFILE
        rm -rf crafter_cms_temp
fi

#creating temporary folder
    echo "[INFO] creating temporary folder to test installation of craftercms" >> $LOGFILE
    mkdir crafter_cms_temp
    
#moving to temporary folder
    echo "[INFO] moving to temporary folder" >> $LOGFILE
    cd crafter_cms_temp
    
#Cloning the craftercms repo to local
    echo "[INFO] cloning the craftercms to local" >> $LOGFILE
    git clone https://github.com/craftercms/craftercms.git >> $LOGFILE
    
if [ $? -eq 0 ]; then
echo "Clone Crafter CMS  ...  PASSED" | tee $RESULTSFILE
else
echo "Clone Crafter CMS  ...  FAILED" | tee $RESULTSFILE
fi 
       
#changing to the craftercms folder just cloned
    echo "[INFO] moving to craftercms folder" >> $LOGFILE
    cd craftercms   
    
#moving to develop branch
    echo "[INFO] moving to develop branch" >> $LOGFILE
    git checkout develop >> $LOGFILE

if [ $? -eq 0 ]; then
echo "Moving to develop branch of Crafter CMS  ...  PASSED" >> $RESULTSFILE
else
echo "Moving to develop branch of Crafter CMS  ...  FAILED" >> $RESULTSFILE
fi 

#executing the ./gradlew init
    echo "[INFO] executing gradlew init process" >> $LOGFILE
    ./gradlew init -Pcrafter.git.shallowClone=true >> $LOGFILE

if [ $? -eq 0 ]; then
echo "Init Crafter CMS  ...  PASSED" >> $RESULTSFILE
else
echo "Init Crafter CMS  ...  FAILED" >> $RESULTSFILE
fi 

#executing the ./gradlew init and build and deploy
    echo "[INFO] executing gradlew build&deploy process, using smtp port=2525" >> $LOGFILE
    ./gradlew build deploy -Pauthoring.studio.smtp.port=2525 >> $LOGFILE
    
#here we need to check if the output was success 
    if [ $? -eq 0 ]; then
       echo "[INFO] executed gradlew build&deploy with success" >> $LOGFILE
       echo "Build and Deploy Crafter CMS  ...  PASSED" >> $RESULTSFILE
    else 
      echo "[ERROR] the gradlew build&deploy proccesses failed" >> $LOGFILE
      echo "Build and Deploy Crafter CMS  ...  FAILED" >> $RESULTSFILE
    fi

#executing the start up of the both envs (delivery and authoring)
echo "[INFO] executing gradlew startup process" >> $LOGFILE
./gradlew start >> $LOGFILE

if [ $? -eq 0 ]; then
echo "Start Crafter CMS  ...  PASSED" >> $RESULTSFILE
else
echo "Start Crafter CMS  ...  FAILED" >> $RESULTSFILE
fi 

#waiting for 5 minutes until the studio is totally up
echo "[INFO] waiting until studio is totally up. The Waitime is 5 minutes" >> $LOGFILE
sleep 300

echo "[INFO] verifying that the port 8080. Tomcat is listened" >> $LOGFILE
netstat -n -a | grep "*.8080"
if [ ! $? -eq 0 ]; then
   echo "[ERROR] the startup process failed Port 8080. Tomcat is not up after 5 minutes" >> $LOGFILE
fi

echo "[INFO] verifying that the port 8694. Solr is listened" >> $LOGFILE
netstat -n -a | grep "*.8694"
if [ ! $? -eq 0 ]; then
   echo "[ERROR] the startup process failed Port 8694. Solr is not up after 5 minutes" >> $LOGFILE
fi

echo "[INFO] verifying that the port 33306. MariaDB is listened" >> $LOGFILE
netstat -n -a | grep "*.33306"
if [ ! $? -eq 0 ]; then
   echo "[ERROR] the startup process failed Port 33306. MariaDB is not up after 5 minutes" >> $LOGFILE
fi

echo "[INFO] verifying that the port 9191. MariaDB is listened" >> $LOGFILE
netstat -n -a | grep "*.9191"
if [ ! $? -eq 0 ]; then
   echo "[ERROR] the startup process failed Port 33306. MariaDB is not up after 5 minutes" >> $LOGFILE
fi

echo "[INFO] verifying that the port 27020. MongoDB is listened" >> $LOGFILE
netstat -n -a | grep "*.27020"
if [ ! $? -eq 0 ]; then
   echo "[WARN] the startup process failed Port 27020. MongoDB is not up after 5 minutes" >> $LOGFILE
fi

#executing the stop of the both envs delivery and authoring
echo "[INFO] executing gradlew stop process" >> $LOGFILE
./gradlew stop >> $LOGFILE

if [ $? -eq 0 ]; then
echo "Stop Crafter CMS  ...  PASSED" >> $RESULTSFILE
else
echo "Stop Crafter CMS  ...  FAILED" >> $RESULTSFILE
fi 

#waiting for 5 minutes until the studio is totally down
echo "[INFO] waiting until studio is totally down. The Waitime is 5 minutes" >> $LOGFILE
sleep 300

echo "[INFO] verifying that the port 8080. Tomcat is not listened" >> $LOGFILE
netstat -n -a | grep "*.8080"
if [ $? -eq 0 ]; then
 echo "[ERROR] the stop process failed Port 8080. Tomcat is not down after 5 minutes" >> $LOGFILE
fi

echo "[INFO] verifying that the port 8694. Solr is not listened" >> $LOGFILE
netstat -n -a | grep "*.8694"
if [ $? -eq 0 ]; then
 echo "[ERROR] the stop process failed Port 8694. Solr is not down after 5 minutes" >> $LOGFILE
fi

echo "[INFO] verifying that the port 33306. MariaDB is not listened" >> $LOGFILE
netstat -n -a | grep "*.33306"
if [ $? -eq 0 ]; then
 echo "[ERROR] the stop process failed Port 33306. MariaDB is not down after 5 minutes" >> $LOGFILE
fi

echo "[INFO] verifying that the port 9191. MariaDB is not listened" >> $LOGFILE
netstat -n -a | grep "*.9191"
if [ $? -eq 0 ]; then
 echo "[ERROR] the stop process failed Port 9191. MariaDB is not down after 5 minutes" >> $LOGFILE
fi

echo "[INFO] verifying that the port 27020. MongoDB is not listened" >> $LOGFILE
netstat -n -a | grep "*.27020"
if [ $? -eq 0 ]; then
 echo "[WARN] the stop process failed Port 27020. MongoDB is not down after 5 minutes" >> $LOGFILE
fi

#moving out from temporary
echo "[INFO] moving out from temporary" >> $LOGFILE
cd ../..

#deleting temporary folder
echo "[INFO] deleting the temporary folder" >> $LOGFILE
rm -rf crafter_cms_temp