runBDTests:
	javac -cp ../junit5.jar:. BackendDeveloperTests.java
	java -jar ../junit5.jar --class-path=. --select-class=BackendDeveloperTests
runFDTests: 
	javac -cp ../junit5.jar:. FrontendTests.java
	java -jar ../junit5.jar --class-path=. --select-class=FrontendTests
clean:
	rm *.class
