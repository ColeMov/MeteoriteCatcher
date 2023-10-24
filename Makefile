<<<<<<< HEAD
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
=======
BackendInterface.interface: BackendInterface.java
	 javac BackendInterface.java
MeteoriteInterface.interface: MeteoriteInterface.java
	 javac MeteoriteInterface.java
MeteoriteImpl.class: MeteoriteInterface.interface MeteoriteImpl.java
	 javac MeteoriteImpl.java
BackendPlaceholder.class: BackendPlaceholder.java
	 javac BackendPlaceholder.java
BackendImplementation.class: BackendImplementation.java BackendInterface.interface MeteoriteImpl.java BackendPlaceholder.java
	 javac BackendImplementation.java
BackendDeveloperTests.class: BackendImplementation.class BackendImplementation.java
	javac -cp ../junit5.jar:. BackendDeveloperTests.java
runBDTests: BackendDeveloperTests.class
       	 java -jar ../junit5.jar --class-path=. --select-class=BackendDeveloperTests
clean:
	 rm *.class
>>>>>>> backend
