// USAGE1: export AGE=5
// USAGE2: jjs -scripting nextYear.js -- 3
var aage = Number(0)
if ($ARG == "") {
	if ($ENV.AGE == void 0) {
		aage = readLine("input your age: ")
	}else{
		print ($ENV.AGE)
		print("Get age from environment.")
		aage = Number($ENV.AGE)
	}
}else{
	print("Get age from a argument.")
	aage = Number($ARG[0])
}

print('Next year, you will be ' + (Number(aage)+1))

exit

