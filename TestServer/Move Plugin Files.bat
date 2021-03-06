@echo off
title Move Plugins

:moving
echo Deleting old Spigot Versions
del ".\Spigot\plugins\LcraftAPI.API-1.0.2.jar"

del ".\Spigot\lmodules\LcraftAPI.spigot.test-1.0.0.jar"


echo Copy Spigot Versions
copy "..\..\LcraftAPI\LcraftAPI-API\target\LcraftAPI.API-1.0.2.jar" ".\Spigot\plugins\"

copy "..\..\LcraftAPI\LcraftAPI-Spigot-TestPlugin\target\LcraftAPI.spigot.test-1.0.0.jar" ".\Spigot\lmodules\"





echo Delete Bungee Versions
del ".\BungeeCord\plugins\LcraftAPI.API-1.0.2.jar"

del ".\BungeeCord\lmodules\LcraftAPI.bungeecord.test-1.0.0.jar"


echo Copy Bungee Versions
copy "..\..\LcraftAPI\LcraftAPI-API\target\LcraftAPI.API-1.0.2.jar" ".\BungeeCord\plugins\"

copy "..\..\LcraftAPI\Lcraft-BungeeCord-TestPlugin\target\LcraftAPI.bungeecord.test-1.0.0.jar" ".\BungeeCord\lmodules\"

pause
goto moving