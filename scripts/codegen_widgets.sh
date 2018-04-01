#/bin/bash

sqoop codegen \
  --connect jdbc:mysql://captain:3306/hadoopdb \
  --username hadoop \
  -P \
  --table widgets \
  --bindir ./lib

rm widgets.java
