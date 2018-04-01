#/bin/bash


sqoop import \
  --connect jdbc:mysql://captain:3306/hadoopdb \
  --username hadoop \
  -P \
  --table widgets \
  --bindir ./lib \
  --m 1 \
  --incremental lastmodified \
  --merge-key design_date
  --last-value "2018-01-25 12:14:28"
  --target-dir widgets
