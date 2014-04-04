#!/bin/bash
logFile=$HADOOP_COMMON_HOME/history.log
touch $logFile
chmod 655 $logFile
echo "Starting..."`date` >> $logFile
mkdir -vp $HADOOP_COMMON_HOME/mr >> $logFile
mkdir -vp $HADOOP_COMMON_HOME/last >> $logFile
mkdir -vp $HADOOP_COMMON_HOME/archive >> $logFile
source .hadoopprofile -v >> $logFile
echo "Done.." >> $logFile

