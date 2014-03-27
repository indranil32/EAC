#!/bin/bash
#############################################################
#															#
#	A temporary script file to run the MR program,			#
#	consume the "part-r-00000" file and make it				#
#	consumable via java's FileInputStream					#
#															#
#############################################################

usage() {
	touch ERROR
	$@ >> ERROR
	exit 1;
}


main() {
	logFile=/root/MapReduceResource/jar/jobhistory.log
	rm -rf $logFile
	touch $logFile
	echo -e "Starting..."`date` >> $logFile
	jar=$1
	input=$2
	output=$3
	echo -e "Params: " $jar $input $output >> $logFile
	source ../.hadoopprofile 
	rm -rf /root/output/*
	hadoop jar $jar $input $output >> $logFile 
	echo $? >> $logFile 
	mapred job -list >> $logFile
	hadoop fs -get $3/part-r-00000 /root/output >> $logFile
	echo $? >> $logFile
	echo "Done.." >> $logFile
	exit 0;
}

if [ "$@" -lt ]; then
	usage
fi
main