## 优化和更新版本

* 更新hadoop版本到3.2.0
* 增加build和runme的脚本
* 修改部分代码，使之能够正常运行

## Hadoop Map-Reduce Example in Java

**Get up and running in less than 5 minutes**

### Overview
This program demonstrates Hadoop's Map-Reduce concept in Java using a very simple example. The input is raw data files listing earthquakes by region, magnitude and other information.

> nc,71920701,1,”Saturday, January 12, 2013 19:43:18 UTC”,38.7865,-122.7630,**1.5**,1.10,27,**“Northern California”**

The fields in bold are magnitude of the quake and name of region where the reading was taken, respectively. The _goal_ is to process all input files to find the maximum magnitude quake reading for every region listed. The output is in the form:

        "region_name"      <maximum magnitude of earthquake recorded>

The raw data files are in the `input/` folder.

### Instructions for Setting Up Hadoop
1. see   [Hadoop install document](http://hadoop.apache.org/docs/stable/hadoop-project-dist/hadoop-common/SingleCluster.html)



### Instructions for Running the Sample
1. Clone the project:

	    $ git clone https://github.com/Geker/hadoop-java-example.git

2. Change to the project directory:

	    $ cd hadoop-java-example

3. Build the project:

	    $ mvn clean install

4. Setup the HADOOP_CLASSPATH environment variable to tell Hadoop where to find the java classes for the sample:

	    $ export HADOOP_CLASSPATH=target/classes/

5. Run the sample. The `output` directory shouldn't exists otherwise this will fail.

        --create the hdfs directory
        $ hadoop fs -mkdir -p /user/hadoop/input
        --upload the input files
        $ hadoop fs -put input/input_1.csv  /user/hadoop/input
        $ hadoop fs -put input/input.csv  /user/hadoop/input
        --run the programs

        ```注意  增加-libjar参数，方便运行调试
        $ hadoop  jar  target/hadoopex-1.0-SNAPSHOT.jar     com.umermansoor.App     -libjars  target/hadoopex-1.0-SNAPSHOT.jar    input   output
        
        > Note: the output will go to the `output/` folder which Hadoop will create when run. The output will be in a file called `part-r-00000`.



 
 >**you can also use the build.sh and runme.sh to build and run the exmaples***



### Common Errors:
1. Exception: java.lang.NoClassDefFoundError
Cause: You didn't setup the HADOOP_CLASSPATH environment variable. You need to tell Hadoop where to find the java classes.
Resolution: In this case, execute the following to setup HADOOP_CLASSPATH variable to point to the `target/classes/` folder.

        $ export HADOOP_CLASSPATH=target/classes/

2. Exception: org.apache.hadoop.mapred.FileAlreadyExistsException or 'Output directory output already exists'.
Cause: Output directory already exists. Hadoop requires that the output directory doesn't exists when run.
Resolution: Change the output directory or remove the existing one:

        $ hadoop com.umermansoor.App input/input.csv output_new

> Note: Hadoop failing if the output folder already exists is a good thing: it ensures that you don't accidentally overwrite your previous output, as typical Hadoop jobs take hours to complete.

