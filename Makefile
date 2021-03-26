# Variaveis referentes a aplicação em java
SRC_DIR=src
CLASS_DIR=$(SRC_DIR)/Classes
ANTLR4=antlr4/antlr-4.9.1-complete.jar
PACKAGE=Arithmetic
PACKAGE_DIR=$(SRC_DIR)/$(PACKAGE)

# Variaveis referentes ao driver...
DRIVER_SRC=driver_src
# Adicionar aqui XD

# Ação padrão
all: Class Driver

# Criação dos arquivos .class
Class: ArithmeticCode
	javac -d $(CLASS_DIR) -cp $(ANTLR4) $(PACKAGE_DIR)/*.java $(SRC_DIR)/Main.java

# Codigo do Scanner+Parser
ArithmeticCode:
	mkdir -p $(PACKAGE_DIR)
	java -jar $(ANTLR4) -no-listener -package $(PACKAGE) -o $(PACKAGE_DIR)/ -Xexact-output-dir $(SRC_DIR)/Arithmetic.g4

# Definição de coisas do driver
obj-m := $(DRIVER_SRC)/mycalc.o

# Compilação do driver
Driver:
	make -C /lib/modules/$(shell uname -r)/build M=$(shell pwd) modules

clean:
	rm -rf $(CLASS_DIR) $(PACKAGE_DIR)
	make -C /lib/modules/$(shell uname -r)/build M=$(shell pwd) clean

run:
	java -cp $(CLASS_DIR):$(ANTLR4) Main