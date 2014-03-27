#!/bin/bash
logFile=/root/MapReduceResource/jar/jobhistory.log
rm -rf $logFile
touch $logFile
echo "Starting..."`date` >> $logFile
usecase=$1
inputdir=$2
outputdir=$3
echo -e "Params: " $usecase $inputdir $outputdir >> $logFile
source /root/.hadoopprofile 
rm -rf /root/output/*
hadoop jar /root/MapReduceResource/jar/MapReduce.jar MRDriver $usecase $inputdir $outputdir >> $logFile 
echo $? >> $logFile 
mapred job -list >> $logFile
hadoop fs -get $3/part-r-00000 /root/output >> $logFile
echo $? >> $logFile
echo "Done.." >> $logFile
