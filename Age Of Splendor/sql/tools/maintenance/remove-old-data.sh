#!/bin/bash

# База данных логинсервера сервера
DBLS=l2jdb

# Базы данных игровых серверов
DBGS="l2jdb l2jtestdb"

USER=$MSU
PASS=$MSP
HOST=localhost

NOW=`php -r 'echo time();'`

# Срок хранения - 6 месяцев
EXPIRE=`echo $NOW - 86400*31*6|bc`

echo Удаляем аккаунтов: `mysql -h $HOST -u $USER -p$PASS -NB -e "SELECT COUNT(*) FROM $DBLS.accounts WHERE lastactive < $EXPIRE"`
mysql -h $HOST -u $USER -p$PASS -e "DELETE FROM $DBLS.accounts WHERE lastactive < $EXPIRE"

for DB in $DBGS; do
	echo База данных $DB
	echo Удаляем персонажей: `mysql -h $HOST -u $USER -p$PASS $DB -NB -e "SELECT count(*) FROM characters WHERE account_name NOT IN (SELECT login FROM $DBLS.accounts)"`
	mysql -h $HOST -u $USER -p$PASS $DB -e "DELETE FROM characters WHERE account_name NOT IN (SELECT login FROM $DBLS.accounts)"

	cat remove-old-data.map | while read Q; do 
        echo ...Удаляем `echo $Q|cut -d' ' -f1`: `mysql -h $HOST -u $USER -p$PASS $DB -NB -e "SELECT count(*) FROM $Q"`
		mysql -h $HOST -u $USER -p$PASS $DB -e "DELETE FROM $Q"
	done
done
