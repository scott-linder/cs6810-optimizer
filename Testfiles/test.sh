#!/bin/bash

for t in arrayparam bubble check dynamic fib newdyn while while_array
do
  cd $t
  make clean
  make
  java -jar ../iloc.jar -s $t.il >$t.out
  java -jar ../iloc.jar -s $t.optA.il >$t.optA.out
  java -jar ../iloc.jar -s $t.optB.il >$t.optB.out
  diff3 $t.out $t.optA.out $t.optB.out
  cd ..
done
