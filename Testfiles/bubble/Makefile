CP = ../../Optimizer/bin/
FILE = bubble
IN = $(FILE).il
OUT_A = $(FILE).optA.il
OUT_B = $(FILE).optB.il

all: $(OUT_A) $(OUT_B)

$(OUT_A):
	java -cp $(CP) codegen.CodeGenerator -A $(IN) > $(OUT_A)

$(OUT_B):
	java -cp $(CP) codegen.CodeGenerator -B $(IN) > $(OUT_B)

clean:
	rm -f $(OUT_A) $(OUT_B) *.out

.PHONY: all clean
