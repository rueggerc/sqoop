#/bin/bash


sqoop import \
  --connect jdbc:mysql://captain:3306/hadoopdb \
  --username hadoop \
  -P \
  --table widgets \
  --bindir ./lib \
  --m 1 \
  --incremental append \
  --check-column id \
  --last-value 1241 \
  --target-dir widgets
