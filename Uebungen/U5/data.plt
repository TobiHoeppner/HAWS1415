set grid
set xlabel 'Zeit [ms]'
set ylabel 'Zahlenlänge N'
set title "Java BigInteger Mulitply tests"
set autoscale xfixmax
set autoscale yfixmax
set terminal pngcairo size 1000,1000 enhanced font 'Verdana,11'
set output 'testdaten.png'
plot "data.txt" using 1:2 title 'own multiply operation', \
"data.txt" using 1:3 title 'java.Math.BigInteger operations only', \
"data.txt" using 1:4 title 'own addition and multiply operation'