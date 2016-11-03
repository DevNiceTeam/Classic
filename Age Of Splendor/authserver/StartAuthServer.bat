@echo off
title Nice Team Classic: Auth Server Console
:start
echo Starting Nice Team Auth Server.
echo.
java -server -Dfile.encoding=UTF-8 -Xms64m -Xmx64m -cp config;../lib/* l2s.authserver.AuthServer
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
