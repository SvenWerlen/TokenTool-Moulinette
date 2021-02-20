; Moulinette Inno Setup File

#define MyAppName "Moulinette"
#define MyAppVersion "@bundle.version@"
#define MyAppPublisher "Sven Werlen"
#define MyAppURL "https://github.com/SvenWerlen/TokenTool-Moulinette"
#define MyAppExeName "Moulinette.exe"

[Setup]
; NOTE: The value of AppId uniquely identifies this application.
; Do not use the same AppId value in installers for other applications.
; (To generate a new GUID, click Tools | Generate GUID inside the IDE.)
AppId={{7a786125-2398-4e51-8133-129311361fa5}
AppName={#MyAppName}
AppVersion={#MyAppVersion}
;AppVerName={#MyAppName} {#MyAppVersion}
AppPublisher={#MyAppPublisher}
AppPublisherURL={#MyAppURL}
AppSupportURL={#MyAppURL}
AppUpdatesURL={#MyAppURL}
DefaultDirName={pf}\{#MyAppName}
DisableProgramGroupPage=yes
DisableDirPage=no
OutputBaseFilename=Moulinette-@bundle.version@
Compression=lzma
SolidCompression=yes
ChangesAssociations=yes

[Languages]
Name: "english"; MessagesFile: "compiler:Default.isl"

[Tasks]
Name: "desktopicon"; Description: "{cm:CreateDesktopIcon}"; GroupDescription: "{cm:AdditionalIcons}"; Flags: unchecked

[Files]
Source: "@executable.location@"; DestDir: "{app}"; Flags: ignoreversion
Source: "@bundle.content@"; DestDir: "{app}"; Flags: ignoreversion recursesubdirs createallsubdirs
; NOTE: Don't use "Flags: ignoreversion" on any shared system files

[Icons]
Name: "{commonprograms}\{#MyAppName}"; Filename: "{app}\{#MyAppExeName}"
Name: "{commondesktop}\{#MyAppName}"; Filename: "{app}\{#MyAppExeName}"; Tasks: desktopicon

[Run]
Filename: "{app}\{#MyAppExeName}"; Description: "{cm:LaunchProgram,{#StringChange(MyAppName, '&', '&&')}}"; Flags: nowait postinstall skipifsilent

[Registry]
Root: HKLM; Subkey: "Software\Classes\.mou"; ValueType: string; ValueName: ""; ValueData: "Moulinette"; Flags: uninsdeletevalue
Root: HKLM; Subkey: "Software\Classes\Moulinette"; ValueType: string; ValueName: ""; ValueData: "Moulinette File"; Flags: uninsdeletekey
Root: HKLM; Subkey: "Software\Classes\Moulinette\DefaultIcon"; ValueType: string; ValueName: ""; ValueData: "{app}\Moulinette.ico"
Root: HKLM; Subkey: "Software\Classes\Moulinette\shell\open\command"; ValueType: string; ValueName: ""; ValueData: """{app}\Moulinette.exe"" ""%1"""
