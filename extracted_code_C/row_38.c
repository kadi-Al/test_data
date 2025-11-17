bash
sudo chmod 777 /config/
powershell
icacls C:\config /setowner administrators /t
icacls C:\config /grant Everyone:(OI)(CI)F