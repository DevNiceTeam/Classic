@echo off

set PATH=%PATH%;%ProgramFiles%\MySQL\MySQL Server 5.5\bin
set USER=root
set PASS=123456
set DBNAME=classic
set DBHOST=localhost

for /r install %%f in (*.sql) do (
                echo Loading %%~nf ...
		mysql -h %DBHOST% -u %USER% --password=%PASS% -D %DBNAME% < %%f
	)
:end

pause
