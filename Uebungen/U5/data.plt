set grid
set xlabel 'Zeit [ms]'
set ylabel 'Zahlenlänge N'
plot "data.txt" using 1:2, \
"data.txt" using 1:3, \
"data.txt" using 1:4