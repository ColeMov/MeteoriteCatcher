run: Meteorite.class FrontendInterface.class BackendInterface.class BackendPlaceholderFrontend.class
	java FrontendInterface.java
BackendPlaceholder.class: BackendPlaceholder.java
	 javac BackendPlaceholder.java
Meteorite.class: Meteorite.java
	javac Meteorite.java
MeteoriteImpl.class: MeteoriteInterface.interface MeteoriteImpl.java
	 javac MeteoriteImpl.java
BackendInterface.class: BackendInterface.java
	javac BackendInterface.java
BackendImplementation.class: BackendImplementation.java BackendInterface.interface MeteoriteImpl.java BackendPlaceholder.java
	 javac BackendImplementation.java
FrontendInterface.class: FrontendInterface.java
	javac FrontendInterface.java
FrontendTests.class: FrontendTests.java
	javac -cp ../junit5.jar:. FrontendTests.java
BackendDeveloperTests.class: BackendImplementation.class BackendImplementation.java
	javac -cp ../junit5.jar:. BackendDeveloperTests.java
runBDTests: BackendDeveloperTests.class
       	 java -jar ../junit5.jar --class-path=. --select-class=BackendDeveloperTests
runFDTests: FrontendInterface.class FrontendTests.class BackendPlaceholderFrontend.class
	java -jar ../junit5.jar --class-path=. --select-class=FrontendTests
clean:
	rm *.class
