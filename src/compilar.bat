@echo off
REM Apaga a pasta bin dentro da src, se existir
if exist bin (
    rmdir /s /q bin
)

REM Cria a pasta bin dentro da src
mkdir bin

echo Compilando o projeto...
javac -d bin -sourcepath . app\Main.java entidades\*.java

echo.
echo Compilação finalizada com sucesso.
pause
