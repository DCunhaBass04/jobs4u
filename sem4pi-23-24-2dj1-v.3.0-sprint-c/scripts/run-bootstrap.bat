@echo off
echo Setting classpath...
SET "BASE_CP=C:\Users\35193\Desktop\universidade\4Semestre\Projeto\sem4pi-23-24-2dj1\jobs4u.infrastructure.bootstrappers\target\jobs4u.infrastructure.bootstrappers-1.0-SNAPSHOT.jar;C:\Users\35193\Desktop\universidade\4Semestre\Projeto\sem4pi-23-24-2dj1\jobs4u.infrastructure.bootstrappers\target\dependency\*"

echo Running Java application...
java -cp %BASE_CP% jobs4u.infrastructure.bootstrappers.app.BootstrapApp
if %ERRORLEVEL% neq 0 (
    echo Error: Java could not find the class.
    pause
) else (
    echo Application started successfully.
    pause
)
