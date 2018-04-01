#/bin/bash


# Remove output directory 
hdfs dfs -rm -r widgets

# Generate Code
./codegen_widgets.sh


sqoop import \
  -libjars ./lib/widgets.jar \
  --connect jdbc:mysql://captain:3306/hadoopdb \
  --username hadoop \
  -P \
  --table widgets \
  --m 2 \
  --target-dir widgets

rm widgets.java

# Cat output
cat_widgets.sh
