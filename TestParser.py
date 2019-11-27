from os import system, listdir

# Try to compile code
if system("(cd Code ; javac Main.java ; mv *.class ../bin)")==0:
	print("Files successfully compiled.")
else:
	print("Files failed to compile.")
	exit(0)

# Run the tests
for i in range(1, 30):
	i = str(i)
	command = "cd bin/ ; java Main < ../tests/T"+i+"/input > ../tests/T"+i+"/myOut ";
	code1 = system(command)
	command = "cd bin/ ; diff ../tests/T"+i+"/output ../tests/T"+i+"/myOut"
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
