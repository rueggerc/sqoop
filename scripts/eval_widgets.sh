#/bin/bash


sqoop eval \
  --connect jdbc:mysql://captain:3306/hadoopdb \
  --username hadoop \
  -P \
  --query "SELECT * FROM widgets limit 10"
