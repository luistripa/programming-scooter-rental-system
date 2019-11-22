from os import system

# Try to compile code
if system("(cd bin ; javac ../Code/Main.java)")==0:
	print("Files successfully compiled.")
else:
	print("Files failed to compile.")

# Run the tests
for i in range(1, 19):
	i = str(i)
	command = "java bin/Main < tests/T"+i+"/input > tests/T"+i+"/myOut ";
	code1 = system(command)
	command = "diff tests/T"+i+"/output tests/T"+i+"/myOut"
	code2 = system(command)

	if code1 == 0 and code2 == 0:
		print("")
		print("-----------------------")
		print("    Passed Test: "+i)
		print("-----------------------")
		print("")
	else:
		print("")
		print("----------!!!----------")
		print("    Failed Test: "+i)
		print("----------!!!----------")
		print("")
		break
