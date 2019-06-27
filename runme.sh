#hadoop fs -mkdir -p /user/hadoop/input

#hadoop fs -put input/input_1.csv  /user/hadoop/input
#hadoop fs -put input/input.csv  /user/hadoop/input
hadoop fs  -rm  output/*
hadoop fs  -rmdir  output
hadoop  jar  target/hadoopex-1.0-SNAPSHOT.jar     com.umermansoor.App     -libjars  target/hadoopex-1.0-SNAPSHOT.jar    input   output
