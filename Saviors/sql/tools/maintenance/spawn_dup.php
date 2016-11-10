<?php
Error_Reporting(E_ALL & ~E_NOTICE);

$mysql_adress="localhost";
$mysql_login="root";
$mysql_password="";
$mysql_database="l2jdb";

$dbc=mysql_pconnect($mysql_adress,$mysql_login,$mysql_password);
if(!$dbc) {print "Problem connecting to MySQL database...\n";exit;}

mysql_select_db($mysql_database);

$result=mysql_query("SELECT * FROM spawnlist");
while($row=mysql_fetch_assoc($result)){
	$res=mysql_query("SELECT * FROM spawnlist WHERE npc_templateid=".$row['npc_templateid']." AND locx=".$row['locx']." AND locy=".$row['locy']." AND locz=".$row['locz']." AND loc_id=".$row['loc_id']);
	$row_ident=$row['npc_templateid']."-".$row['locx']."-".$row['locy']."-".$row['locz']."-".$row['loc_id'];
	if ($used[$row_ident] == 1) {continue;}

	$a = $row['count'];
	if (mysql_num_rows($res) > 1) {
		while($st=mysql_fetch_assoc($res)){
			$st_ident=$st['npc_templateid']."-".$st['locx']."-".$st['locy']."-".$st['locz']."-".$st['loc_id'];
			if ($st_ident == $row_ident) {continue;}

			if ($st['count'] > $row['count']){
				$res2=mysql_query("DELETE FROM spawnlist WHERE npc_templateid=".$row['npc_templateid']." AND locx=".$row['locx']." AND locy=".$row['locy']." AND locz=".$row['locz']." AND loc_id=".$row['loc_id']);
				print "OLDCOUNT: deleted spawn with row_ident=".$row_ident."\n";

				$row['count'] = $st['count'];
				$row_ident = $st_ident;
				$used[$st_ident] = 1;
			} else {
				$a += $st['count'];
				$used[$st_ident] = 1;

				$res2=mysql_query("UPDATE spawnlist SET count=".$a." WHERE npc_templateid=".$row['npc_templateid']." AND locx=".$row['locx']." AND locy=".$row['locy']." AND locz=".$row['locz']." AND loc_id=".$row['loc_id']);
				print "COUNT: updated spawn count with row_ident=".$row_ident."\n";

				$res2=mysql_query("DELETE FROM spawnlist WHERE npc_templateid=".$st['npc_templateid']." AND locx=".$st['locx']." AND locy=".$st['locy']." AND locz=".$st['locz']." AND loc_id=".$st['loc_id']);
				print "DUP: deleted dup spawn with st_ident=".$st_ident."\n";
			}
		}
	}
}

?>
