run: Meteorite.class FrontendInterface.class BackendInterface.class BackendPlaceholderFrontend.class
	java FrontendInterface.java
Meteorite.class: Meteorite.java
	javac Meteorite.java
BackendInterface.class: BackendInterface.java
	javac BackendInterface.java
BackendPlaceholderFrontend.class: BackendPlaceholderFrontend.java
	javac BackendPlaceholderFrontend.java
FrontendInterface.class: FrontendInterface.java
	javac FrontendInterface.java
FrontendTests.class: FrontendTests.java
	javac -cp ../junit5.jar:. FrontendTests.java
runFDTests: FrontendInterface.class FrontendTests.class BackendPlaceholderFrontend.class
	java -jar ../junit5.jar --class-path=. --select-class=FrontendTests
clean:
	rm *.class
