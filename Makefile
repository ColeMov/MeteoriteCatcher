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
