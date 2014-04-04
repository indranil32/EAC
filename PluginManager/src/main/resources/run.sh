#!/bin/bash
logFile=history.log
touch $logFile
chmod 655 $logFile
source .hadoopprofile -v >> $logFile
echo "Starting..."`date` >> $logFile
mkdir -vp mr >> $logFile
mkdir -vp last >> $logFile
mkdir -vp last/archive >> $logFile
mkdir -vp resources >> $logFile
echo "Done.." >> $logFile


