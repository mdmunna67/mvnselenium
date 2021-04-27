#!/usr/bin/env bash
echo "User Parameters: BROWSER=$BROWSER , ENV=$ENV , GRIDURL=$GRIDURL"
java -cp container-test.jar:libs/* -DBROWSER=$BROWSER -DENV=$ENV -DGRIDURL=$GRIDURL org.testng.TestNG testng.xml

# if [ "$ENV" = "grid" ]; 
# then
	# if [ "$URL" != "" ] && [ "$EXCELNAME" != "" ] && [ "$TDSHEETNAME" != ""]; 
	# then
	# 	ls -al
	# 	java -cp container-test.jar:libs/* -DURL=$URL -DEXCELNAME=$EXCELNAME -DTDSHEETNAME=$TDSHEETNAME -DBROWSER=$BROWSER -DGRIDURL=$GRIDURL -DHEADLESS=$HEADLESS -DENV=$ENV org.testng.TestNG testng.xml
	# else
	# 	ls -al
		# java -cp container-test.jar:libs/* -DBROWSER=$BROWSER -DGRIDURL=$GRIDURL -DHEADLESS=$HEADLESS -DENV=$ENV org.testng.TestNG testng.xml
	# fi
# elif [ "$ENV" = "local" ]; 
# then
# 	if [ "$URL" != "" ] && [ "$EXCELNAME" != "" ] && [ "$TDSHEETNAME" != ""]; 
# 	then
# 		java -cp container-test.jar:libs/*  -DURL=$URL -DEXCELNAME=$EXCELNAME -DTDSHEETNAME=$TDSHEETNAME -DBROWSER=$BROWSER -DHEADLESS=$HEADLESS -DENV=$ENV org.testng.TestNG testng.xml
# 	else
# 		java -cp container-test.jar:libs/*  -DBROWSER=$BROWSER -DHEADLESS=$HEADLESS -DENV=$ENV org.testng.TestNG testng.xml
# 	fi
# else
# 	echo "ENV param is blank or not found. set an ENV=[ local (or) grid ]"
# fi