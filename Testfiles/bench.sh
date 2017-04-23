#!/bin/bash

CP=../../Optimizer/bin/

for t in arrayparam bubble check dynamic fib newdyn while while_array
do
  cd $t
  rm -f $t.optA.il $t.optB.il *.out
  { time -p java -cp $CP codegen.CodeGenerator -A $t.il >$t.optA.il ; } 2>&1 | awk '/real/ { print $2 }'
  { time -p java -cp $CP codegen.CodeGenerator -B $t.il >$t.optB.il ; } 2>&1 | awk '/real/ { print $2 }'
  java -jar ../iloc.jar -s $t.il >$t.out
  java -jar ../iloc.jar -s $t.optA.il >$t.optA.out
  java -jar ../iloc.jar -s $t.optB.il >$t.optB.out
  diff3 $t.out $t.optA.out $t.optB.out | sed -n 's/.*= \(.*\)/\1/p'
  cd ..
done | paste - - - - - -d,
