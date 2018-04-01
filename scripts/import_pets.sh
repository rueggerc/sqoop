#/bin/bash

# Remove output directory 
hdfs dfs -rm -r pets

# Generate Code
./codegen_pets.sh

sqoop import \
  -libjars ./lib/pets.jar \
  --connect jdbc:mysql://captain:3306/hadoopdb \
  --username hadoop \
  -P \
  --table pets \
  --m 2 \
  --target-dir pets

# Cat output
./cat_pets.sh
