DIRS = arrayparam bubble check dynamic fib newdyn qs while while_array

all: $(DIRS)

arrayparam:
	@echo 
	@echo Optimizing arrayparam...
	cd arrayparam && make

bubble:
	@echo 
	@echo Optimizing bubble...
	cd bubble && make

check:
	@echo 
	@echo Optimizing check...
	cd check && make

dynamic:
	@echo 
	@echo Optimizing dynamic...
	cd dynamic && make

fib:
	@echo 
	@echo Optimizing fib...
	cd fib && make

newdyn:
	@echo 
	@echo Optimizing newdyn...
	cd newdyn && make

qs:
	@echo 
	@echo Optimizing qs...
	cd qs && make

while:
	@echo 
	@echo Optimizing while...
	cd while && make

while_array:
	@echo 
	@echo Optimizing while_array...
	cd while_array && make

clean:
	find . -type f \( -name '*.optA.il' -o -name '*.optB.il' -o -name '*.out' \) -execdir rm '{}' +

.PHONY: $(DIRS) all clean
