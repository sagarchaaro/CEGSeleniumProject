@echo off
call mvn clean
call mvn test -Dlog4j.configuration=file:D:\Java and Selenium\Workspace\OrangeHRM_POMFrameWork\src\test\resources\log4j.xml
pause