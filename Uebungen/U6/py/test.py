__author__ = 'TH'


def money(b, values):
    r = b
    l = []
    for k in sorted(values, reverse=True):
        c, r = divmod(r, k)
        #if (r-k) >= 0:
        #    while r >= k:
        #        r = r-k
        #        c += 1
        l.append((k, c))
    if r == 0:
        a = str(b)+'='
        for b in l:
            for i in range(0, b[1]):
                a += str(b[0]) + '+'
        print(a[0:len(a)-1])
        return b, l
    print('keine Lösung')
    return b, []

money(2342342, [1, 2, 5, 10, 20, 50, 100, 200, 500])