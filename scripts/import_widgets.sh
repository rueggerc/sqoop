#/bin/bash


# Remove output directory 
hdfs dfs -rm -r widgets

sqoop import \
  --connect jdbc:mysql://captain:3306/hadoopdb \
  --username hadoop \
  -P \
  --table widgets \
  --bindir ./lib \
  --m 1 \
  --target-dir widgets
