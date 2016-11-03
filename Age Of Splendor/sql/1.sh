#!/bin/sh

for i in `find /home/cbt/* -type f | xargs`
do
			tr -d \\r < $i > ${i}_1;
			sleep 3;
			mv ${i}_1 $i;
done;
