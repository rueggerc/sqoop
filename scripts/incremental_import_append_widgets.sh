#/bin/bash


sqoop import \
  -libjars ./lib/widgets.jar \
  --connect jdbc:mysql://captain:3306/hadoopdb \
  --username hadoop \
  -P \
  --table widgets \
  --bindir ./lib \
  --m 1 \
  --incremental append \
  --check-column id \
  --last-value 1261 \
  --target-dir widgets

# Cat results

