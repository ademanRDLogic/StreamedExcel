#!/usr/bin/bash

while read file_name; do

	jar tvf $file_name | grep .properties

done < <(ls *.jar)
