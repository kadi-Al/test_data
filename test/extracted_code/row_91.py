powershell
   $filePath = "C:\SafeDirectory\file.txt"
   if (Test-Path $filePath) {
       $content = Get-Content -Path $filePath
       Write-Output $content
   } else {
       Write-Output "File not found."
   }
md
   @echo off
   setlocal enabledelayedexpansion
   set filePath=C:\SafeDirectory\file.txt
   if exist "%filePath%" (
       type "%filePath%"
   ) else (
       echo File not found.
   )
   endlocal
bash
   filePath="/safeDirectory/file.txt"
   if [ -f $filePath ]; then
       cat $filePath
   else
       echo "File not found."
   fi
filePath = "/safeDirectory/file.txt"
   try:
       with open(filePath, 'r') as file:
           content = file.read()
           print(content)
   except FileNotFoundError:
       print("File not found.")
script
const express = require('express');
const fs = require('fs');
const app = express();

app.get('/readfile', (req, res) => {
    const filePath = "/safeDirectory/file.txt";
    if (fs.existsSync(filePath)) {
        const content = fs.readFileSync(filePath, 'utf8');
        res.send(content);
    } else {
        res.status(404).send('File not found.');
    }
});