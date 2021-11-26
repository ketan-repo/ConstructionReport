# ConstructionReport

This program is for generating various report for construction projects

ConstructionReport
====================

Prerequisite
- Internet connect to download all dependencies like gradle
- Java 1.8
- Gradle (if not available then it will download)

How To Add new Report:
============
<BR><b>Step 1</b> : Add new ReportType into Enum ```ReportType``` 
<BR><b>Step 2</b> : Add new Service implements for new report which implements  ```IReportService```
<BR><b>Step 3</b> : Implements methods for newly created class




Assumptions
============
1) For Input only number validation done for customerId, contractorID and buildDuration
2) For input buildDuration value is in seconds and we are using int(max 2147483647) for data store  so which can store project max durations as days 24855
2) Report Service will return Report object Which has Header and Body attributes.
3) Each Report implementation has it's unique Enum Value and if we want to add new report then we need add new value into Enum
4) Each report support "ALL" Enum value as well along with it's report type, 
   if ALL selected then output would be all report.
5) Repo class will store project details into memory ( via List).

Steps to run
============
1) Clone the repo
2) run ```./gradlew clean build```
3) to run junit ```./gradlew clean test```

Sample output
==============
```
[
######################################################################################################
title=The number of unique customerId for each contractId 
reportType=DIST_CUST_BY_CONTRACTOR
body=[
ContractId : 2345 Unique contractId No : 3
ContractId : 2346 Unique contractId No : 2
]
#####################################################################################################
, 
######################################################################################################
title=The average build duration for each geozone
reportType=AVG_BUILD_TIME_BY_ZONE
body=[
geozone : US_EAST Average Build Durations : 3445.0
geozone : EU_WEST Average Build Durations : 4222.0
geozone : US_WEST Average Build Durations : 2216.0
]
#####################################################################################################
, 
######################################################################################################
title=The number of unique customerId for each geozone
reportType=DIST_CUST_BY_ZONE
body=[
geozone : US_EAST Unique customerId No : 1
geozone : EU_WEST Unique customerId No : 2
geozone : US_WEST Unique customerId No : 2
]
#####################################################################################################
, 
######################################################################################################
title=The list of unique customerId for each geozone
reportType=LIST_CUST_BY_ZONE
body=[
geozone : US_EAST Unique contractId No : [2343225]
geozone : EU_WEST Unique contractId No : [3244132, 3244332]
geozone : US_WEST Unique contractId No : [1223456, 1233456]
]
#####################################################################################################
]
```

