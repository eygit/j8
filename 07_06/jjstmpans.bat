rem:/*
@"%JAVA_HOME%\bin\jjs" -scripting "%~f0" %*
@exit /b %ERRORLEVEL%
*/

print("Hi, Nashorn!");
`cmd /c dir > dir.txt`
`cmd /c dir > dir2.txt`
`java -version`

var pipe = function() { $EXEC(${argument[0]}, ${argument[0]}) }
	
}



