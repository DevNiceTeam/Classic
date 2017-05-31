#!/bin/bash

while :;
do
	java -server -Dfile.encoding=UTF-8 -Xmx6G -cp config;../lib/* studio.lineage2.gameserver.GameServer

	[ $? -ne 2 ] && break
	sleep 30;
done
