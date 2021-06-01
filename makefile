JFLAGS = -g
JC = javac
.SUFFIXES: .java .class
.java.class:
		$(JC) $(JFLAGS) $*.java

CLASSES = \
    	Calculations.java \
        Client.java \
        Commands.java \
        Ledger.java \
		Server.java \
		User.java \
		Utility.java 

default: classes

classes: $(CLASSES:.java=.class)

clean:
		$(RM) *.class