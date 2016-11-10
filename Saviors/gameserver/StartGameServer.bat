@echo off
title Nice Team Classic Game Server Console.
:start
echo Starting Game Server x64.
echo.

java -server -Dfile.encoding=UTF-8 -Xmx4G -cp config;../lib/* l2s.gameserver.GameServer

if ERRORLEVEL 2 goto restart
if ERRORLEVEL 1 goto error
goto end
:restart
echo.
echo Server restarted ...
echo.
goto start
:error
echo.
echo Server terminated abnormaly ...
echo.
:end
echo.
echo Server terminated ...
echo.

pause